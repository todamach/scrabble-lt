

import com.sun.deploy.util.ArrayUtil;
import dawg.ModifiableDAWGMap;
import dawg.ModifiableDAWGNode;
import dawg.ModifiableDAWGSet;
import model.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by harol on 10/11/2016.
 */
public class Main {

    //static String[] row = {"", "", "", "", "", "d", "", "", "", "", "", "", ""};
    static List<Letter> lettersInRack = new ArrayList<>();
    public static int anchorSquare = 11;
    public static int anchorRow = 6;
    static Game game;
    static Player player;
    static Board board;

    public static void main(String[] args) {

        lettersInRack.add(new Letter("p", 0));
        lettersInRack.add(new Letter("a", 0));
        lettersInRack.add(new Letter("a", 0));
        lettersInRack.add(new Letter("a", 0));
        lettersInRack.add(new Letter("s", 0));
        lettersInRack.add(new Letter("r", 0));
        lettersInRack.add(new Letter("t", 0));
        lettersInRack.add(new Letter("m", 0));
        lettersInRack.add(new Letter("o", 0));
        lettersInRack.add(new Letter("k", 0));
        lettersInRack.add(new Letter("e", 0));
        lettersInRack.add(new Letter("ë", 0));
        lettersInRack.add(new Letter("o", 0));
        lettersInRack.add(new Letter("n", 0));
        lettersInRack.add(new Letter("è", 0));
        lettersInRack.add(new Letter("i", 0));

        Rack rack = new Rack();
        rack.setLetters(lettersInRack);
        player = new Player();
        player.setName("Player1");
        player.setRack(rack);

        board = new Board();
        board.setBoard(board.generateEmptyBoard());
        board.setTestBoard(2);


        Game game = new Game();
        game.setBoard(board);
        game.addPlayer(player);
        game.findAnchors();
        System.out.println(game.getBoard());
        game.findCrosschecks();


        ModifiableDAWGNode startNode = game.getDawg().getDawg().sourceNode;

        for (int row = 0; row <= Board.ROWS - 1; row++) {
            anchorRow = row;
            for (int col = 0; col <= Board.COLS - 1; col++) {
                anchorSquare = col;
                if (board.getBoard()[row][col].isAnchor()) {
                    System.out.println("row: " + row + " col: " + col + "##################################################################################################");
                    leftPart("", startNode, col);
                }
            }
        }


        System.out.println("Pabaiga");
    }

    private static void leftPart(String partialWord, ModifiableDAWGNode node, int limit) {

        int currentCol = anchorSquare - 1;
        String leftPart = "";
        // Jeigu iskarto pries anchorSquare yra raide, tai imam visas raides iki pradzios, ir nebeieskom daugiau jokiu left parts
        Tile currentTile = board.getBoard()[anchorRow][currentCol];
        if (!currentTile.getLetter().getLetter().equals("")) {
            // gaunam stringa raidziu einanciu pries anchorSquare ir ji apverciam
            while (currentCol >= 0) {
                if (board.getBoard()[anchorRow][currentCol].getLetter().getLetter().equals("")) {
                    break;
                }
                leftPart += board.getBoard()[anchorRow][currentCol].getLetter().getLetter();
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
            extendRight(partialWord, nextNode, anchorSquare);
        } else {
            //System.out.println("Left part: " + partialWord);
            extendRight(partialWord, node, anchorSquare);
            if (limit > 0) {
                NavigableMap<Character, ModifiableDAWGNode> outgoingNodes = node.getOutgoingTransitions();
                for (Character c : outgoingNodes.keySet()) {
                    if (currentTile.horizontalCrosschecksContains(String.valueOf(c))) {
                        int index;
                        if ((index = player.getRack().contains(c.toString())) > -1) {
                            Letter letter = player.getRack().getLetters().get(index);
                            player.getRack().getLetters().remove(index);
                            ModifiableDAWGNode nextNode = outgoingNodes.get(c);
                            leftPart(partialWord + c, nextNode, limit - 1);
                            player.getRack().getLetters().add(letter);
                        }
                    }
                }
            }
        }
    }

    private static void extendRight(String partialWord, ModifiableDAWGNode node, int square) {
        if (square > anchorSquare && node.isAcceptNode()) {
            LegalWord legalWord = new LegalWord();
            legalWord.setAnchorRow(anchorRow);
            legalWord.setAnchorSquare(anchorSquare);
            legalWord.setWord(partialWord);
            player.addLegalWord(legalWord);
            System.out.println("Legal word: " + partialWord);
        }
        if (square < board.getBoard()[anchorRow].length) {
            Tile currentTile = board.getBoard()[anchorRow][square];
            if (currentTile.getLetter().getLetter().isEmpty()) {
                NavigableMap<Character, ModifiableDAWGNode> outgoingNodes = node.getOutgoingTransitions();
                for (Character c : outgoingNodes.keySet()) {
                    if (currentTile.horizontalCrosschecksContains(String.valueOf(c))) {
                        int index;
                        if ((index = player.getRack().contains(c.toString())) > -1) {
                            Letter letter = player.getRack().getLetters().get(index);
                            player.getRack().getLetters().remove(index);
                            ModifiableDAWGNode nextNode = outgoingNodes.get(c);
                            int nextSquare = square + 1;
                            //System.out.println("Extending right with rack: " + partialWord + c);
                            extendRight(partialWord + c, nextNode, nextSquare);
                            player.getRack().getLetters().add(letter);
                        }
                    }
                }
            } else {
                Letter letter = board.getBoard()[anchorRow][square].getLetter();
                NavigableMap<Character, ModifiableDAWGNode> outgoingNodes = node.getOutgoingTransitions();
                for (Character c : outgoingNodes.keySet()) {
                    if (currentTile.horizontalCrosschecksContains(letter.getLetter())) {
                        if (letter.getLetter().equals(c.toString())) {
                            ModifiableDAWGNode nextNode = outgoingNodes.get(c);
                            int nextSquare = square + 1;
                            //System.out.println("Extending right with board: " + partialWord + letter.getLetter());
                            extendRight(partialWord + letter.getLetter(), nextNode, nextSquare);
                        }
                    }
                }
            }
        }
    }

    private static void writeDAWGToGraphWiz(ModifiableDAWGSet dawg) {
        String graphWiz = dawg.toGraphViz(true, false);

        PrintWriter writer = null;
        try {
            writer = new PrintWriter("D:\\Projektai\\Intellij\\DAWG programa\\src\\main\\resources\\graphWiz.txt", "Unicode");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        writer.println(graphWiz);
        writer.close();
    }


}
