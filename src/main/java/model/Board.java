package model;

/**
 * Created by harol on 10/17/2016.
 */
public class Board {

    private final static int ROWS = 15;
    private final static int COLS = 15;

    private Tile[][] board;

    public Board(){}

    public Tile[][] getBoard() {
        return board;
    }

    public void setBoard(Tile[][] board) {
        this.board = board;
    }
}
