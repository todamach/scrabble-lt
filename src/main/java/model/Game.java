package model;

import Util.Util;
import dawg.ModifiableDAWGNode;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;

/**
 * Created by harol on 10/19/2016.
 */
public class Game {

    Board board;
    Dawg dawg;
    LetterPool pool;
    List<Player> players = new ArrayList<>();
    Tile[][] currentOrientation;
    public static int anchorSquare = 0;
    public static int anchorRow = 0;
    public static int orientation;
    private static int currentCol = 0;
    private static int limit = 0;
    private int currentPlayer = 0;

    private int turn = 1;

    public Game() {
        dawg = new Dawg();
        pool = new LetterPool();
    }

    public void generateMoves(){
        currentOrientation = board.getHorizontalBoard();
        orientation = Board.HORIZONTAL;
        generateMoves(currentOrientation);
        currentOrientation = board.getVerticalBoard();
        orientation = Board.VERTICAL;
        generateMoves(currentOrientation);
        turn++;
    }

    private void generateMoves(Tile[][] currentOrientation){
        if(turn == 1){
            anchorSquare = 7;
            currentCol = 7;
            anchorRow = 7;
            limit = board.findLimit(currentOrientation, anchorRow, anchorSquare, getCurrentPlayer().getRack());
            leftPart(new PartialWord(), getDawg().getDawg().sourceNode, limit);
        }else {
            for (int row = 0; row <= Board.ROWS - 1; row++) {
                anchorRow = row;
                for (int col = 0; col <= Board.COLS - 1; col++) {
                    anchorSquare = col;
                    if (currentOrientation[row][col].isAnchor()) {
                        //System.out.println("row: " + row + " col: " + col + "##################################################################################################");
                        currentCol = anchorSquare;
                        limit = board.findLimit(currentOrientation, anchorRow, anchorSquare, getCurrentPlayer().getRack());
                        leftPart(new PartialWord(), getDawg().getDawg().sourceNode, limit);
                    }
                }
            }
        }
    }

    private void leftPart(PartialWord partialWord, ModifiableDAWGNode node, int currentLimit) {

        if(currentCol == 0){
            PartialWord newPartialWord = new PartialWord(partialWord);
            extendRight(newPartialWord, node, anchorSquare, partialWord.getLetters().size());
        }else{
            currentCol = anchorSquare - 1;
            List<Letter> leftPart = new ArrayList<>();
            // Jeigu iskarto pries anchorSquare yra raide, tai imam visas raides iki pradzios, ir nebeieskom daugiau jokiu left parts
            Tile currentTile = currentOrientation[anchorRow][anchorSquare - 1];
            if (!currentTile.getLetter().getLetter().equals("")) {
                // gaunam stringa raidziu einanciu pries anchorSquare ir ji apverciam
                while (currentCol >= 0) {
                    if (currentOrientation[anchorRow][currentCol].getLetter().getLetter().equals("")) {
                        break;
                    }
                    leftPart.add(currentOrientation[anchorRow][currentCol].getLetter());
                    currentCol--;
                }
                partialWord.addLetters(leftPart, true);
                // gaunam to stringo paskutini node grafe
                ModifiableDAWGNode nextNode = node;
                for (int i = 0; i < partialWord.getLetters().size(); i++) {
                    char c = partialWord.getLetters().get(i).getChar();
                    nextNode = nextNode.getOutgoingTransitions().get(c);
                }
                //System.out.println("Left part: " + partialWord);
                if(nextNode != null){
                    PartialWord newPartialWord = new PartialWord(partialWord);
                    extendRight(newPartialWord, nextNode, anchorSquare, partialWord.getLetters().size());
                }
            } else {
                //System.out.println("Left part: " + partialWord);
                PartialWord newPartialWord = new PartialWord(partialWord);
                extendRight(newPartialWord, node, anchorSquare, partialWord.getLetters().size());
                if (currentLimit > 0) {
                    NavigableMap<Character, ModifiableDAWGNode> outgoingNodes = node.getOutgoingTransitions();
                    try{
                        currentTile = currentOrientation[anchorRow][anchorSquare - (limit - (currentLimit - 1))];
                    }catch (Exception e){
                        System.out.print("");
                    }

                    for (Character c : outgoingNodes.keySet()) {
                        if (currentTile.crosscheckContains(c, orientation)) {
                            ModifiableDAWGNode nextNode = outgoingNodes.get(c);
                            int index;
                            if ((index = getCurrentPlayer().getRack().contains(c.toString())) > -1) {
                                Letter letter = getCurrentPlayer().getRack().getLetters().get(index);
                                getCurrentPlayer().getRack().getLetters().remove(index);
                                newPartialWord = new PartialWord(partialWord);
                                newPartialWord.addChar(c);
                                leftPart(newPartialWord, nextNode, currentLimit - 1);
                                getCurrentPlayer().getRack().getLetters().add(letter);
                            }else if((index = containsWildcardAndLetterABC(c)) > -1){
                                Letter letter = getCurrentPlayer().getRack().getLetters().get(index);
                                getCurrentPlayer().getRack().getLetters().remove(index);
                                newPartialWord = new PartialWord(partialWord);
                                Letter newLetter = new Letter(c);
                                newLetter.setWildcard(true);
                                newPartialWord.addLetter(newLetter);
                                leftPart(newPartialWord, nextNode, currentLimit - 1);
                                getCurrentPlayer().getRack().getLetters().add(letter);
                            }
                        }
                    }
                }
            }
        }

    }

    private int containsWildcardAndLetterABC(Character letter){
        int index;
        if((index = getCurrentPlayer().getRack().containsWildcard()) > -1){
            if(Util.ABC.contains(String.valueOf(letter))){
                return index;
            }
        }
        return index;
    }

    private void extendRight(PartialWord partialWord, ModifiableDAWGNode node, int square, int leftPartLength) {
        if (square > anchorSquare && node.isAcceptNode()) {
            if(square < currentOrientation[anchorRow].length){
                Tile nextTile = currentOrientation[anchorRow][square];
                if(nextTile.getLetter().getLetter().isEmpty()){
                    PartialWord newPartialWord = new PartialWord(partialWord);
                    getCurrentPlayer().addLegalWord(anchorSquare, anchorRow, newPartialWord, leftPartLength, currentOrientation, orientation);
                }
            }else{
                PartialWord newPartialWord = new PartialWord(partialWord);
                getCurrentPlayer().addLegalWord(anchorSquare, anchorRow, newPartialWord, leftPartLength, currentOrientation, orientation);
            }
        }
        if (square < currentOrientation[anchorRow].length) {
            Tile currentTile = currentOrientation[anchorRow][square];
            // jei laukelis tuscias
            if (currentTile.getLetter().getLetter().isEmpty()) {
                NavigableMap<Character, ModifiableDAWGNode> outgoingNodes = null;
                try{
                    outgoingNodes = node.getOutgoingTransitions();
                }catch(Exception e){
                    System.out.println();
                }

                for (Character c : outgoingNodes.keySet()) {
                    if (currentTile.crosscheckContains(c, orientation)) {
                        ModifiableDAWGNode nextNode = outgoingNodes.get(c);
                        int index;
                        if ((index = getCurrentPlayer().getRack().contains(c.toString())) > -1) {
                            Letter letter = getCurrentPlayer().getRack().getLetters().get(index);
                            getCurrentPlayer().getRack().getLetters().remove(index);
                            int nextSquare = square + 1;
                            //System.out.println("Extending right with rack: " + partialWord + c);
                            PartialWord newPartialWord = new PartialWord(partialWord);
                            newPartialWord.addChar(c);
                            extendRight(newPartialWord, nextNode, nextSquare, leftPartLength);
                            getCurrentPlayer().getRack().getLetters().add(letter);
                        }else if((index = containsWildcardAndLetterABC(c)) > -1){
                            Letter letter = getCurrentPlayer().getRack().getLetters().get(index);
                            getCurrentPlayer().getRack().getLetters().remove(index);
                            int nextSquare = square + 1;
                            //System.out.println("Extending right with rack: " + partialWord + c);
                            PartialWord newPartialWord = new PartialWord(partialWord);
                            Letter newLetter = new Letter(c);
                            newLetter.setWildcard(true);
                            newPartialWord.addLetter(newLetter);
                            extendRight(newPartialWord, nextNode, nextSquare, leftPartLength);
                            getCurrentPlayer().getRack().getLetters().add(letter);
                        }
                    }
                }
            // jei laukelis uzimtas
            } else {
                Letter letter = currentOrientation[anchorRow][square].getLetter();
                NavigableMap<Character, ModifiableDAWGNode> outgoingNodes = node.getOutgoingTransitions();
                for (Character c : outgoingNodes.keySet()) {
                    if (currentTile.crosscheckContains(letter.getLetter().charAt(0), orientation)) {
                        if (letter.getLetter().equals(c.toString())) {
                            ModifiableDAWGNode nextNode = outgoingNodes.get(c);
                            int nextSquare = square + 1;
                            //System.out.println("Extending right with board: " + partialWord + letter.getLetter());
                            PartialWord newPartialWord = new PartialWord(partialWord);
                            newPartialWord.addLetter(letter);
                            extendRight(newPartialWord, nextNode, nextSquare, leftPartLength);
                        }
                    }
                }
            }
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Dawg getDawg() {
        return dawg;
    }

    public void setDawg(Dawg dawg) {
        this.dawg = dawg;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public LetterPool getPool() {
        return pool;
    }

    public void setPool(LetterPool pool) {
        this.pool = pool;
    }

    public Player getCurrentPlayer(){
        return this.players.get(currentPlayer);
    }

    public void nextPlayer(){
        if(currentPlayer < players.size() - 1){
            currentPlayer++;
        }else{
            currentPlayer = 0;
        }
    }


}
