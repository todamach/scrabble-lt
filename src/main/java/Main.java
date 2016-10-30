

import dawg.ModifiableDAWGNode;
import dawg.ModifiableDAWGSet;
import model.*;

import java.io.*;
import java.util.*;

/**
 * Created by harol on 10/11/2016.
 */
public class Main {

    static Game game;
    static Player player;
    static Board board;

    public static void main(String[] args) {

        player = new Player();
        player.setName("Player1");

        board = new Board();
        board.setTestBoard(3);

        game = new Game();
        game.addPlayer(player);
        game.setBoard(board);

        while(true){
            game.getCurrentPlayer().drawLetters(game.getPool());
            game.getBoard().findAnchors();
            game.getBoard().findCrosschecks(game.getDawg());

            game.generateMoves();
            game.getCurrentPlayer().sortLegalWordsByValue();
            game.getCurrentPlayer().placeBestScoringWordOnTheBoard(game.getBoard());

            System.out.println("Horizontal");
            System.out.println(Board.printBoardLetters(game.getBoard().getHorizontalBoard()));
            //System.out.println("Vertical");
            //System.out.println(Board.printBoardLetters(game.getBoard().getVerticalBoard()));

            System.out.println("Pabaiga");

            game.getCurrentPlayer().clearLegalWords();
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
