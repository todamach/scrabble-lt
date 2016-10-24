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

    public Game() {
        dawg = new Dawg();
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
