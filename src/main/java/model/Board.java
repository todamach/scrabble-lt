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

    public void findAnchors(){
        for(int row = 0; row <= ROWS - 1; row++){
            for(int col = 0; col <= COLS - 1; col++){
                if(board[row][col].getLetter() != null){
                    if(row > 0){
                        if(board[row - 1][col].getLetter() == null){
                            board[row - 1][col].setAnchor(true);
                        }
                    }

                    if(col > 0){
                        if(board[row][col - 1].getLetter() == null){
                            board[row][col - 1].setAnchor(true);
                        }
                    }

                    if(col != 14){
                        if(board[row][col + 1].getLetter() == null){
                            board[row][col + 1].setAnchor(true);
                        }
                    }

                    if(row != 14){
                        if(board[row + 1][col].getLetter() == null){
                            board[row + 1][col].setAnchor(true);
                        }
                    }
                }
            }
        }
    }

    public Tile[][] generateEmptyBoard(){
        Tile[][] board = new Tile[ROWS][COLS];

        for(int row = 0; row <= ROWS - 1; row++){
            for(int col = 0; col <= COLS - 1; col++){
                board[row][col] = new Tile(Tile.NONE);
            }
        }

        board[0][0].setMultiplier(Tile.TRIPLE_WORD);
        board[0][7].setMultiplier(Tile.TRIPLE_WORD);
        board[0][14].setMultiplier(Tile.TRIPLE_WORD);
        board[7][0].setMultiplier(Tile.TRIPLE_WORD);
        board[7][14].setMultiplier(Tile.TRIPLE_WORD);
        board[14][0].setMultiplier(Tile.TRIPLE_WORD);
        board[14][7].setMultiplier(Tile.TRIPLE_WORD);
        board[14][14].setMultiplier(Tile.TRIPLE_WORD);

        board[0][3].setMultiplier(Tile.DOUBLE_LETTER);
        board[0][11].setMultiplier(Tile.DOUBLE_LETTER);
        board[2][6].setMultiplier(Tile.DOUBLE_LETTER);
        board[2][8].setMultiplier(Tile.DOUBLE_LETTER);
        board[3][0].setMultiplier(Tile.DOUBLE_LETTER);
        board[3][7].setMultiplier(Tile.DOUBLE_LETTER);
        board[3][14].setMultiplier(Tile.DOUBLE_LETTER);
        board[6][2].setMultiplier(Tile.DOUBLE_LETTER);
        board[6][6].setMultiplier(Tile.DOUBLE_LETTER);
        board[6][8].setMultiplier(Tile.DOUBLE_LETTER);
        board[6][12].setMultiplier(Tile.DOUBLE_LETTER);
        board[7][3].setMultiplier(Tile.DOUBLE_LETTER);
        board[7][11].setMultiplier(Tile.DOUBLE_LETTER);
        board[8][2].setMultiplier(Tile.DOUBLE_LETTER);
        board[8][6].setMultiplier(Tile.DOUBLE_LETTER);
        board[8][8].setMultiplier(Tile.DOUBLE_LETTER);
        board[8][12].setMultiplier(Tile.DOUBLE_LETTER);
        board[11][0].setMultiplier(Tile.DOUBLE_LETTER);
        board[11][7].setMultiplier(Tile.DOUBLE_LETTER);
        board[11][14].setMultiplier(Tile.DOUBLE_LETTER);
        board[12][6].setMultiplier(Tile.DOUBLE_LETTER);
        board[12][8].setMultiplier(Tile.DOUBLE_LETTER);
        board[14][3].setMultiplier(Tile.DOUBLE_LETTER);
        board[14][11].setMultiplier(Tile.DOUBLE_LETTER);

        board[1][1].setMultiplier(Tile.DOUBLE_WORD);
        board[1][13].setMultiplier(Tile.DOUBLE_WORD);
        board[2][2].setMultiplier(Tile.DOUBLE_WORD);
        board[2][12].setMultiplier(Tile.DOUBLE_WORD);
        board[3][3].setMultiplier(Tile.DOUBLE_WORD);
        board[2][11].setMultiplier(Tile.DOUBLE_WORD);
        board[4][4].setMultiplier(Tile.DOUBLE_WORD);
        board[4][10].setMultiplier(Tile.DOUBLE_WORD);
        board[7][7].setMultiplier(Tile.DOUBLE_WORD);
        board[10][4].setMultiplier(Tile.DOUBLE_WORD);
        board[10][10].setMultiplier(Tile.DOUBLE_WORD);
        board[11][3].setMultiplier(Tile.DOUBLE_WORD);
        board[11][11].setMultiplier(Tile.DOUBLE_WORD);
        board[12][2].setMultiplier(Tile.DOUBLE_WORD);
        board[12][12].setMultiplier(Tile.DOUBLE_WORD);
        board[13][1].setMultiplier(Tile.DOUBLE_WORD);
        board[13][13].setMultiplier(Tile.DOUBLE_WORD);

        board[1][5].setMultiplier(Tile.TRIPLE_LETTER);
        board[1][9].setMultiplier(Tile.TRIPLE_LETTER);
        board[5][1].setMultiplier(Tile.TRIPLE_LETTER);
        board[5][5].setMultiplier(Tile.TRIPLE_LETTER);
        board[5][9].setMultiplier(Tile.TRIPLE_LETTER);
        board[5][13].setMultiplier(Tile.TRIPLE_LETTER);
        board[9][1].setMultiplier(Tile.TRIPLE_LETTER);
        board[9][5].setMultiplier(Tile.TRIPLE_LETTER);
        board[9][9].setMultiplier(Tile.TRIPLE_LETTER);
        board[9][13].setMultiplier(Tile.TRIPLE_LETTER);
        board[13][5].setMultiplier(Tile.TRIPLE_LETTER);
        board[13][9].setMultiplier(Tile.TRIPLE_LETTER);

        return board;
    }
}
