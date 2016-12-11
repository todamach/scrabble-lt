package model;

import Util.Util;
import dawg.ModifiableDAWGNode;

import java.util.*;

/**
 * Created by harol on 10/19/2016.
 */
public class Game {

    Board board;
    LetterPool pool;
    List<Player> players = new ArrayList<>();
    MovesGenerator movesGenerator;
    Map<String, Dawg> dawgsMap = new HashMap<>();
    boolean gameEnd = false;


    int currentPlayer = 0;

    public Game() {
        System.out.println("Creating LetterPool..");
        pool = new LetterPool();
        System.out.println("Creating Board..");
        board = new Board();
        System.out.println("Creating MovesGenerator..");
        movesGenerator = new MovesGenerator();
        System.out.println("Creating dawgs..");
        createDawgs();
        System.out.println("Creating players..");
        createPlayers();
    }

    public void start(){
        while(!gameEnd){
            System.out.println(getCurrentPlayer().getName());
            System.out.println("Player rack at the begining of the turn:");
            System.out.println(getCurrentPlayer().getRack());

            getBoard().findAnchors();
            getBoard().findCrosschecks(getCurrentPlayer().getDawg());

            generateMoves();
            getCurrentPlayer().sortLegalWordsByValue();
            LegalWord wordPlaced = getCurrentPlayer().placeBestScoringWordOnTheBoard(getBoard());
            updateDawgsWithNewWord(wordPlaced);
            System.out.println("Game state");
            printGameState();

            getCurrentPlayer().clearLegalWords();
            getCurrentPlayer().drawLetters(getPool());
            nextPlayer();
        }
    }

    private void updateDawgsWithNewWord(LegalWord word) {
        if(word != null){
            for(Player player : players){
                if(getCurrentPlayer() != player){
                    if(!player.getDawg().getDawg().contains(word.getPartialWord().getWord())){
                        player.getDawg().getDawg().add(word.getPartialWord().getWord());
                    }
                    for(LegalWord.CrosscheckWord crosscheck : word.getWordsToCrosscheck()){
                        if(!player.getDawg().getDawg().contains(crosscheck.getWord())){
                            player.getDawg().getDawg().add(crosscheck.getWord());
                        }
                    }

                }
            }
        }
    }

    private void generateMoves(){
        movesGenerator.generateMoves(getCurrentPlayer(), board);
    }

    private void createDawgs(){
        Dawg dawg15 = new Dawg(Dawg.EASY);
        Dawg dawg800 = new Dawg(Dawg.HARD);

        dawgsMap.put(Dawg.EASY, dawg15);
        dawgsMap.put(Dawg.HARD, dawg800);

    }

    private void createPlayers(){
        Player player1;
        Player player2;

        player1 = new Player(dawgsMap.get(Dawg.HARD));
        player1.setName("Amateur");
        player1.drawLetters(getPool());
        //player1.setTestRack();

        player2 = new Player(dawgsMap.get(Dawg.HARD));
        player2.setName("Pro");
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
