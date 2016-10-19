package model;

import dawg.ModifiableDAWGNode;

import java.util.NavigableMap;

/**
 * Created by harol on 10/19/2016.
 */
public class Game {

    Board board;
    Dawg dawg;

    public Game() {
        dawg = new Dawg();
    }

    public void findAnchors() {
        for (int row = 0; row <= Board.ROWS - 1; row++) {
            for (int col = 0; col <= Board.COLS - 1; col++) {
                if (!board.getBoard()[row][col].getLetter().getLetter().equals("")) {
                    if (row > 0) {
                        if (board.getBoard()[row - 1][col].getLetter().getLetter().equals("")) {
                            board.getBoard()[row - 1][col].setAnchor(true);
                        }
                    }

                    if (col > 0) {
                        if (board.getBoard()[row][col - 1].getLetter().getLetter().equals("")) {
                            board.getBoard()[row][col - 1].setAnchor(true);
                        }
                    }

                    if (col != 14) {
                        if (board.getBoard()[row][col + 1].getLetter().getLetter().equals("")) {
                            board.getBoard()[row][col + 1].setAnchor(true);
                        }
                    }

                    if (row != 14) {
                        if (board.getBoard()[row + 1][col].getLetter().getLetter().equals("")) {
                            board.getBoard()[row + 1][col].setAnchor(true);
                        }
                    }
                }
            }
        }
    }

    public void findCrosschecks() {
        // paimam top part, paimam bottom part, ir tikrinam kas tinka tarp ju pagal grafa
        for (int row = 0; row <= Board.ROWS - 1; row++) {
            for (int col = 0; col <= Board.COLS - 1; col++) {
                if (board.getBoard()[row][col].isAnchor()) {
                    String topPart = "";
                    if (row > 0) {
                        if (!board.getBoard()[row - 1][col].getLetter().getLetter().isEmpty()) {
                            int currentRow = row - 1;
                            while (currentRow >= 0) {
                                if (board.getBoard()[currentRow][col].getLetter().getLetter().equals("")) {
                                    break;
                                }
                                topPart += board.getBoard()[currentRow][col].getLetter().getLetter();
                                currentRow--;
                            }
                            topPart = new StringBuilder(topPart).reverse().toString();

                            ModifiableDAWGNode nextNode = dawg.getDawg().sourceNode;
                            for (int i = 0; i < topPart.length(); i++) {
                                char c = topPart.charAt(i);
                                nextNode = nextNode.getOutgoingTransitions().get(c);
                            }

                            NavigableMap<Character, ModifiableDAWGNode> possibleChrosscheckLetters = nextNode.getOutgoingTransitions();
                            for (Character c : possibleChrosscheckLetters.keySet()) {
                                ModifiableDAWGNode bottomNode = possibleChrosscheckLetters.get(c);
                                currentRow = row + 1;
                                while(!board.getBoard()[currentRow][col].getLetter().getLetter().isEmpty()){
                                    Character bottomTileChar = board.getBoard()[currentRow][col].getLetter().getLetter().charAt(0);
                                    // eit iki apacios, gal visai neit, ir tikrint ar apacia yra accepted node.
                                }
                            }
                        }


                    }

                }
            }
        }
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
}
