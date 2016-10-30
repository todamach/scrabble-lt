

import dawg.ModifiableDAWGNode;
import dawg.ModifiableDAWGSet;
import model.*;

import java.io.*;
import java.util.*;

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

        lettersInRack.add(new Letter("m", 0));
        lettersInRack.add(new Letter("i", 0));
        lettersInRack.add(new Letter("n", 0));
        lettersInRack.add(new Letter("i", 0));
        lettersInRack.add(new Letter("s", 0));
        lettersInRack.add(new Letter("r", 0));
        lettersInRack.add(new Letter("a", 0));
        lettersInRack.add(new Letter("s", 0));

        Rack rack = new Rack();
        rack.setLetters(lettersInRack);
        player = new Player();
        player.setName("Player1");
        player.setRack(rack);

        board = new Board();
        board.setTestBoard(2);



        Game game = new Game();
        game.addPlayer(player);

        board.findAnchors();
        board.findCrosschecks(game.getDawg());
        game.setBoard(board);

        System.out.println("Horizontal");
        System.out.println(Board.printBoardLetters(board.getHorizontalBoard()));
        System.out.println("Vertical");
        System.out.println(Board.printBoardLetters(board.getVerticalBoard()));

        game.generateMoves();
        player.sortLegalWordsByValue();

        player.placeBestScoringWordOnTheBoard(board);

        System.out.println("Pabaiga");
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
