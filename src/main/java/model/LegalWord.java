package model;

/**
 * Created by harol on 10/23/2016.
 */
public class LegalWord {
    int anchorRow;
    int anchorSquare;
    String word;

    public LegalWord(){}

    public int getAnchorRow() {
        return anchorRow;
    }

    public void setAnchorRow(int anchorRow) {
        this.anchorRow = anchorRow;
    }

    public int getAnchorSquare() {
        return anchorSquare;
    }

    public void setAnchorSquare(int anchorSquare) {
        this.anchorSquare = anchorSquare;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString(){
        return this.word;
    }
}
