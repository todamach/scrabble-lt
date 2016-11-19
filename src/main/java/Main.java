

import dawg.ModifiableDAWGNode;
import dawg.ModifiableDAWGSet;
import model.*;

import java.io.*;
import java.util.*;

/**
 * Created by harol on 10/11/2016.
 */
public class Main {



    public static void main(String[] args) {

        Game game;
        Board board;

        Player player1;
        Player player2;


        player1 = new Player();
        player1.setName("Player1");
        //player1.setTestRack();

        player2 = new Player();
        player2.setName("Player2");
        //player2.setTestRack();

        board = new Board();
        //board.setTestBoard(3);
        //board.readFromFile();

        game = new Game();
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.setBoard(board);

        System.out.println("Horizontal");
        System.out.println(Board.printBoardLetters(game.getBoard().getHorizontalBoard()));

        while(true){
            game.getCurrentPlayer().drawLetters(game.getPool());
            game.getBoard().findAnchors();
            game.getBoard().findCrosschecks(game.getDawg());

            game.generateMoves();
            game.getCurrentPlayer().sortLegalWordsByValue();
            game.getCurrentPlayer().placeBestScoringWordOnTheBoard(game.getBoard());

            System.out.println("Horizontal");
            System.out.println(Board.printBoardLetters(game.getBoard().getHorizontalBoard()));

            System.out.println(game.getCurrentPlayer());
            System.out.println("Pabaiga");

            game.getCurrentPlayer().clearLegalWords();
            game.nextPlayer();
        }

    }
}
