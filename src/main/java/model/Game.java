package model;

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
    List<Player> players = new ArrayList<>();
    Tile[][] currentOrientation;
    public static int anchorSquare = 0;
    public static int anchorRow = 0;
    public static int orientation;

    public Game() {
        dawg = new Dawg();
    }

    public void generateMoves(){
        currentOrientation = board.getHorizontalBoard();
        orientation = Board.HORIZONTAL;
        generateMoves(currentOrientation);
        currentOrientation = board.getVerticalBoard();
        orientation = Board.VERTICAL;
        generateMoves(currentOrientation);
    }

    private void generateMoves(Tile[][] currentOrientation){
        for (int row = 0; row <= Board.ROWS - 1; row++) {
            anchorRow = row;
            for (int col = 0; col <= Board.COLS - 1; col++) {
                anchorSquare = col;
                if (currentOrientation[row][col].isAnchor()) {
                    System.out.println("row: " + row + " col: " + col + "##################################################################################################");
                    leftPart("", getDawg().getDawg().sourceNode, board.findLimit(currentOrientation, anchorRow, anchorSquare, getCurrentPlayer().getRack()));
                }
            }
        }
    }

    private void leftPart(String partialWord, ModifiableDAWGNode node, int limit) {

        int currentCol = anchorSquare - 1;
        String leftPart = "";
        // Jeigu iskarto pries anchorSquare yra raide, tai imam visas raides iki pradzios, ir nebeieskom daugiau jokiu left parts
        Tile currentTile = currentOrientation[anchorRow][currentCol];
        if (!currentTile.getLetter().getLetter().equals("")) {
            // gaunam stringa raidziu einanciu pries anchorSquare ir ji apverciam
            while (currentCol >= 0) {
                if (currentOrientation[anchorRow][currentCol].getLetter().getLetter().equals("")) {
                    break;
                }
                leftPart += currentOrientation[anchorRow][currentCol].getLetter().getLetter();
                currentCol--;
            }
            partialWord = new StringBuilder(leftPart).reverse().toString();
            // gaunam to stringo paskutini node grafe
            ModifiableDAWGNode nextNode = node;
            for (int i = 0; i < partialWord.length(); i++) {
                char c = partialWord.charAt(i);
                nextNode = nextNode.getOutgoingTransitions().get(c);
            }
            //System.out.println("Left part: " + partialWord);
            extendRight(partialWord, nextNode, anchorSquare, partialWord.length());
        } else {
            //System.out.println("Left part: " + partialWord);
            extendRight(partialWord, node, anchorSquare, partialWord.length());
            if (limit > 0) {
                NavigableMap<Character, ModifiableDAWGNode> outgoingNodes = node.getOutgoingTransitions();
                for (Character c : outgoingNodes.keySet()) {
                    if (currentTile.crosscheckContains(c, orientation)) {
                        int index;
                        if ((index = getCurrentPlayer().getRack().contains(c.toString())) > -1) {
                            Letter letter = getCurrentPlayer().getRack().getLetters().get(index);
                            getCurrentPlayer().getRack().getLetters().remove(index);
                            ModifiableDAWGNode nextNode = outgoingNodes.get(c);
                            leftPart(partialWord + c, nextNode, limit - 1);
                            getCurrentPlayer().getRack().getLetters().add(letter);
                        }
                    }
                }
            }
        }
    }

    private void extendRight(String partialWord, ModifiableDAWGNode node, int square, int leftPartLength) {
        if (square > anchorSquare && node.isAcceptNode()) {
            if(square < currentOrientation[anchorRow].length - 1){
                Tile nextTile = currentOrientation[anchorRow][square];
                if(nextTile.getLetter().getLetter().isEmpty()){
                    LegalWord legalWord = new LegalWord(anchorSquare, anchorRow, partialWord, leftPartLength, currentOrientation, orientation);
                    getCurrentPlayer().addLegalWord(legalWord, orientation);
                    System.out.println("Legal word: " + legalWord);
                }
            }else{
                LegalWord legalWord = new LegalWord(anchorSquare, anchorRow, partialWord, leftPartLength, currentOrientation, orientation);
                getCurrentPlayer().addLegalWord(legalWord, orientation);
                System.out.println("Legal word: " + legalWord);
            }
        }
        if (square < currentOrientation[anchorRow].length) {
            Tile currentTile = currentOrientation[anchorRow][square];
            if (currentTile.getLetter().getLetter().isEmpty()) {
                NavigableMap<Character, ModifiableDAWGNode> outgoingNodes = node.getOutgoingTransitions();
                for (Character c : outgoingNodes.keySet()) {
                    if (currentTile.crosscheckContains(c, orientation)) {
                        int index;
                        if ((index = getCurrentPlayer().getRack().contains(c.toString())) > -1) {
                            Letter letter = getCurrentPlayer().getRack().getLetters().get(index);
                            getCurrentPlayer().getRack().getLetters().remove(index);
                            ModifiableDAWGNode nextNode = outgoingNodes.get(c);
                            int nextSquare = square + 1;
                            //System.out.println("Extending right with rack: " + partialWord + c);
                            extendRight(partialWord + c, nextNode, nextSquare, leftPartLength);
                            getCurrentPlayer().getRack().getLetters().add(letter);
                        }
                    }
                }
            } else {
                Letter letter = currentOrientation[anchorRow][square].getLetter();
                NavigableMap<Character, ModifiableDAWGNode> outgoingNodes = node.getOutgoingTransitions();
                for (Character c : outgoingNodes.keySet()) {
                    if (currentTile.crosscheckContains(letter.getLetter().charAt(0), orientation)) {
                        if (letter.getLetter().equals(c.toString())) {
                            ModifiableDAWGNode nextNode = outgoingNodes.get(c);
                            int nextSquare = square + 1;
                            //System.out.println("Extending right with board: " + partialWord + letter.getLetter());
                            extendRight(partialWord + letter.getLetter(), nextNode, nextSquare, leftPartLength);
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

    public Player getCurrentPlayer(){
        // TODO: pagal eile ar kazkas tokio
        return this.players.get(0);
    }
}
