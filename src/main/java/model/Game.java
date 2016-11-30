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
    LetterPool pool;
    List<Player> players = new ArrayList<>();
    MovesGenerator movesGenerator;

    int currentPlayer = 0;

    public Game() {
        pool = new LetterPool();
        board = new Board();
        movesGenerator = new MovesGenerator();
        createPlayers();
    }

    public void start(){
        while(true){
            System.out.println("Player rack at the begining of the turn:");
            System.out.println(getCurrentPlayer().getRack());

            getBoard().findAnchors();
            getBoard().findCrosschecks(getCurrentPlayer().getDawg());

            generateMoves();
            getCurrentPlayer().sortLegalWordsByValue();
            getCurrentPlayer().placeBestScoringWordOnTheBoard(getBoard());

            System.out.println("Game state");
            printGameState();

            getCurrentPlayer().clearLegalWords();
            getCurrentPlayer().drawLetters(getPool());
            nextPlayer();
        }
    }

    private void generateMoves(){
        movesGenerator.generateMoves(getCurrentPlayer(), board);
    }

    private void createPlayers(){
        Player player1;
        Player player2;

        Dawg dawg = new Dawg();

        player1 = new Player(dawg);
        player1.setName("Player1");
        player1.drawLetters(getPool());
        //player1.setTestRack();

        player2 = new Player(dawg);
        player2.setName("Player2");
        player2.drawLetters(getPool());
        //player2.setTestRack();

        addPlayer(player1);
        addPlayer(player2);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
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


    public void printGameState() {
        System.out.println(Board.printBoardLetters(this.getBoard().getHorizontalBoard()));
        for(Player player : getPlayers()){
            System.out.println(player);
        }

        System.out.println(this.getPool());
        System.out.println();
    }
}
