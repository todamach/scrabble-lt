package model;

import dawg.ModifiableDAWGNode;

import java.util.NavigableMap;

/**
 * Created by harol on 10/17/2016.
 */
public class Board {

    public final static int VERTICAL = 1;
    public final static int HORIZONTAL = 0;

    public final static int ROWS = 15;
    public final static int COLS = 15;

    private Tile[][] horizontalBoard;
    private Tile[][] verticalBoard = new Tile[ROWS][COLS];

    public Board() {
        setHorizontalBoard(generateEmptyBoard());
        generateVerticalBoard();
    }

    public void generateVerticalBoard(){
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                verticalBoard[j][i] = new Tile();
                verticalBoard[j][i] = horizontalBoard[i][j];
            }
        }
    }

    public Tile[][] getHorizontalBoard() {
        return horizontalBoard;
    }

    public void setHorizontalBoard(Tile[][] horizontalBoard) {
        this.horizontalBoard = horizontalBoard;
    }

    public Tile[][] getVerticalBoard() {
        return verticalBoard;
    }

    public void setVerticalBoard(Tile[][] verticalBoard) {
        this.verticalBoard = verticalBoard;
    }

    public Tile[][] generateEmptyBoard() {
        Tile[][] board = new Tile[ROWS][COLS];

        for (int row = 0; row <= ROWS - 1; row++) {
            for (int col = 0; col <= COLS - 1; col++) {
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
        board[3][11].setMultiplier(Tile.DOUBLE_WORD);
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

    public void setTestBoard(int i) {
        if (i == 1) {
            //  m
            //trenkti
            //  d
            //  i
            //  s

            horizontalBoard[7][5].setLetter(Letter.getLetter("t"));
            horizontalBoard[7][6].setLetter(Letter.getLetter("r"));
            horizontalBoard[7][7].setLetter(Letter.getLetter("e"));
            horizontalBoard[7][8].setLetter(Letter.getLetter("n"));
            horizontalBoard[7][9].setLetter(Letter.getLetter("k"));
            horizontalBoard[7][10].setLetter(Letter.getLetter("t"));
            horizontalBoard[7][11].setLetter(Letter.getLetter("i"));

            horizontalBoard[6][7].setLetter(Letter.getLetter("m"));
            horizontalBoard[8][7].setLetter(Letter.getLetter("d"));
            horizontalBoard[9][7].setLetter(Letter.getLetter("i"));
            horizontalBoard[10][7].setLetter(Letter.getLetter("s"));
        } else if (i == 2) {
            //      r
            //  m
            //trenkti
            //  d   k
            //  i   i
            //  s   a

            horizontalBoard[7][5].setLetter(Letter.getLetter("t"));
            horizontalBoard[7][6].setLetter(Letter.getLetter("r"));
            horizontalBoard[7][7].setLetter(Letter.getLetter("e"));
            horizontalBoard[7][8].setLetter(Letter.getLetter("n"));
            horizontalBoard[7][9].setLetter(Letter.getLetter("k"));
            horizontalBoard[7][10].setLetter(Letter.getLetter("t"));
            horizontalBoard[7][11].setLetter(Letter.getLetter("i"));

            horizontalBoard[6][7].setLetter(Letter.getLetter("m"));
            horizontalBoard[8][7].setLetter(Letter.getLetter("d"));
            horizontalBoard[9][7].setLetter(Letter.getLetter("i"));
            horizontalBoard[10][7].setLetter(Letter.getLetter("s"));

            horizontalBoard[5][11].setLetter(Letter.getLetter("r"));
            horizontalBoard[8][11].setLetter(Letter.getLetter("k"));
            horizontalBoard[9][11].setLetter(Letter.getLetter("i"));
            horizontalBoard[10][11].setLetter(Letter.getLetter("a"));
        }
    }

    public void findAnchors() {
        for (int row = 0; row <= Board.ROWS - 1; row++) {
            for (int col = 0; col <= Board.COLS - 1; col++) {
                if (!horizontalBoard[row][col].getLetter().getLetter().equals("")) {
                    if (row > 0) {
                        if (horizontalBoard[row - 1][col].getLetter().getLetter().equals("")) {
                            horizontalBoard[row - 1][col].setAnchor(true);
                        }
                    }

                    if (col > 0) {
                        if (horizontalBoard[row][col - 1].getLetter().getLetter().equals("")) {
                            horizontalBoard[row][col - 1].setAnchor(true);
                        }
                    }

                    if (col != 14) {
                        if (horizontalBoard[row][col + 1].getLetter().getLetter().equals("")) {
                            horizontalBoard[row][col + 1].setAnchor(true);
                        }
                    }

                    if (row != 14) {
                        if (horizontalBoard[row + 1][col].getLetter().getLetter().equals("")) {
                            horizontalBoard[row + 1][col].setAnchor(true);
                        }
                    }
                }
            }
        }
    }

    public void findCrosschecks(Dawg dawg) {
        findCrosschecks(dawg, horizontalBoard, VERTICAL);
        findCrosschecks(dawg, verticalBoard, HORIZONTAL);

    }

    private void findCrosschecks(Dawg dawg, Tile[][] board, int orientation){
        for (int row = 0; row <= Board.ROWS - 1; row++) {
            for (int col = 0; col <= Board.COLS - 1; col++) {
                if (board[row][col].isAnchor()) {
                    Tile tile = board[row][col];
                    if(board[row - 1][col].getLetter().getLetter().isEmpty() &&
                            board[row + 1][col].getLetter().getLetter().isEmpty()){
                        // jeigu langeliai virs ir po tile yra tusti, tai visi crosschekai galimi
                        tile.setAllCrosschecks(true, orientation);
                    }else{
                        //System.out.println("Find crosscheck for " + row + " " + col);
                        String topPart = "";
                        int currentRow = row - 1;

                        //randam top part, jeigu ji yra
                        if (row > 0) {
                            if (!board[row - 1][col].getLetter().getLetter().isEmpty()) {
                                while (currentRow >= 0) {
                                    if (board[currentRow][col].getLetter().getLetter().equals("")) {
                                        break;
                                    }
                                    topPart += board[currentRow][col].getLetter().getLetter();
                                    currentRow--;
                                }
                                topPart = new StringBuilder(topPart).reverse().toString();
                            }
                        }

                        // randam top part node seka grafe
                        ModifiableDAWGNode nextNode = dawg.getDawg().sourceNode;
                        for (int i = 0; i < topPart.length(); i++) {
                            char c = topPart.charAt(i);
                            nextNode = nextNode.getOutgoingTransitions().get(c);
                        }

                        String bottomPart = "";

                        if(nextNode != null){
                            NavigableMap<Character, ModifiableDAWGNode> possibleChrosscheckLetters = nextNode.getOutgoingTransitions();
                            for (Character c : possibleChrosscheckLetters.keySet()) {
                                ModifiableDAWGNode bottomNode = possibleChrosscheckLetters.get(c);
                                currentRow = row + 1;
                                bottomPart = "";
                                boolean foundLastNode = true;
                                while (currentRow <= 14 && !board[currentRow][col].getLetter().getLetter().isEmpty()) {
                                    Character bottomTileChar = board[currentRow][col].getLetter().getLetter().charAt(0);
                                    NavigableMap<Character, ModifiableDAWGNode> bottomNodeTransitions = bottomNode.getOutgoingTransitions();
                                    if(bottomNodeTransitions.size() == 0){
                                        foundLastNode = false;
                                        break;
                                    }
                                    bottomNode = bottomNodeTransitions.get(bottomTileChar);
                                    if(bottomNode == null){
                                        foundLastNode = false;
                                        break;
                                    }
                                    // eit iki apacios, gal visai neit, ir tikrint ar apacia yra accepted node.
                                    bottomPart += bottomTileChar;
                                    currentRow++;
                                }

                                if(foundLastNode && bottomNode != null && bottomNode.isAcceptNode()){
                                    //System.out.println("tinka crosschekas: " + c);
                                    String crosscheckWord = topPart + c + bottomPart;
                                    //TODO: su crosscheku saugot ir zodi
                                    Crosscheck crosscheck = new Crosscheck(crosscheckWord, new Letter(c.toString()));
                                    String crosscheckValueString = topPart + bottomPart;
                                    crosscheck.calculateValue(crosscheckValueString);
                                    if(orientation == HORIZONTAL){
                                        tile.addVerticalCrosscheck(crosscheck, String.valueOf(c));
                                    }else if(orientation == VERTICAL){
                                        tile.addHorizontalCrosscheck(crosscheck, String.valueOf(c));
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
    }



    public static String printBoardMulti(Tile[][] board) {
        String boardString = "   ";
        for (int col = 0; col <= COLS - 1; col++) {
            boardString += " " + (col % 10) + " ";
        }
        boardString += "\n";
        for (int row = 0; row <= ROWS - 1; row++) {
            boardString += " " + (row % 10) + " ";
            for (int col = 0; col <= COLS - 1; col++) {
                Letter letter = board[row][col].getLetter();
                Tile tile = board[row][col];
                switch(tile.getMultiplier()){
                    case Tile.DOUBLE_LETTER:
                        boardString += " " + "A" + " ";
                        break;
                    case Tile.TRIPLE_LETTER:
                        boardString += " " + "B" + " ";
                        break;
                    case Tile.DOUBLE_WORD:
                        boardString += " " + "C" + " ";
                        break;
                    case Tile.TRIPLE_WORD:
                        boardString += " " + "D" + " ";
                        break;
                    default:
                        boardString += " " + " " + " ";
                        break;
                }
            }
            boardString += "\n";
        }

        return boardString;
    }

    public static String printBoardLetters(Tile[][] board) {
        String boardString = "   ";
        for (int col = 0; col <= COLS - 1; col++) {
            boardString += " " + (col % 10) + " ";
        }
        boardString += "\n";
        for (int row = 0; row <= ROWS - 1; row++) {
            boardString += " " + (row % 10) + " ";
            for (int col = 0; col <= COLS - 1; col++) {
                Letter letter = board[row][col].getLetter();
                Tile tile = board[row][col];
                if(!letter.getLetter().isEmpty()){
                    boardString += " " + letter.getLetter() + " ";
                }else if(tile.isAnchor()){
                    boardString += " # ";
                }else {
                    boardString += "   ";
                }
            }
            boardString += "\n";
        }

        return boardString;
    }

    @Override
    public String toString() {
        String board = "   ";
        for (int col = 0; col <= COLS - 1; col++) {
            board += " " + (col % 10) + " ";
        }
        board += "\n";
        for (int row = 0; row <= ROWS - 1; row++) {
            board += " " + (row % 10) + " ";
            for (int col = 0; col <= COLS - 1; col++) {
                Letter letter = getHorizontalBoard()[row][col].getLetter();
                Tile tile = getHorizontalBoard()[row][col];
                if(!letter.getLetter().isEmpty()){
                    board += " " + letter.getLetter() + " ";
                }else if(tile.isAnchor()){
                    board += " # ";
                }else {
                    board += "   ";
                }
            }
            board += "\n";
        }

        return board;
    }

    public int findLimit(Tile[][] board, int anchorRow, int anchorSquare, Rack rack) {
        int limit = -1;
        while(anchorSquare >= 0 && board[anchorRow][anchorSquare].getLetter().getLetter().isEmpty()){
            limit++;
            anchorSquare--;
        }
        if(limit > rack.getLetters().size()){
           return rack.getLetters().size();
        }
        return limit;
    }

    public Tile[][] getBoard(int orientation) {
        if(orientation == Board.HORIZONTAL){
            return getHorizontalBoard();
        }else{
            return getVerticalBoard();
        }
    }
}
