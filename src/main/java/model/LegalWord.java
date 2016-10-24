package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by harol on 10/23/2016.
 */
public class LegalWord {
    int anchorRow;
    int anchorSquare;
    String word;
    List<String> wordsToCrosscheck = new ArrayList<>();
    int leftPartLength;
    int wordValue;

    public LegalWord(){}

    public LegalWord(int anchorSquare, int anchorRow, String partialWord, int leftPartLength, Board board) {
        this.anchorRow = anchorRow;
        this.anchorSquare = anchorSquare;
        this.word = partialWord;
        this.leftPartLength = leftPartLength;
        this.wordValue = calculateWordValue(board);

        findCrosschecks(board);
    }

    private int calculateWordValue(Board board){
        int currentCol = anchorSquare - leftPartLength;
        int totalValue = 0;
        int tripleWord = 0;
        int doubleWord = 0;
        for(char c : word.toCharArray()){
            Tile tile = board.getBoard()[anchorRow][currentCol];
            int value = Util.Util.getLetterValue(String.valueOf(c));
            switch(tile.getMultiplier()){
                case Tile.DOUBLE_LETTER:
                    totalValue += value*2;
                    break;
                case Tile.TRIPLE_LETTER:
                    totalValue += value*3;
                    break;
                case Tile.DOUBLE_WORD:
                    totalValue += value;
                    doubleWord++;
                    break;
                case Tile.TRIPLE_WORD:
                    totalValue += value;
                    tripleWord++;
                    break;
                default:
                    totalValue += value;
                    break;
            }
            currentCol++;
        }

        for(int i = 0; i<doubleWord; i++){
            totalValue *= 2;
        }
        for(int i = 0; i<tripleWord; i++){
            totalValue *= 3;
        }
        return totalValue;
    }

    private void findCrosschecks(Board board) {
        int currentCol = anchorSquare - leftPartLength;

        for(char c : word.toCharArray()){
            if(currentCol != anchorSquare){
                Tile tile = board.getBoard()[anchorRow][currentCol];
                String crosscheckWord = findCrosscheckWord(tile.getHorizontalCrosschecks(), c);

                if(!crosscheckWord.isEmpty())
                    this.wordsToCrosscheck.add(crosscheckWord);
            }
            currentCol++;
        }
    }

    private String findCrosscheckWord(Map<String, Crosscheck> crosschecks, char c){
        Crosscheck crosscheck = crosschecks.get(String.valueOf(c));
        if(crosscheck != null){
            return crosscheck.getCrosscheckWord();
        }
        return "";
    }

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

    public List<String> getWordsToCrosscheck() {
        return wordsToCrosscheck;
    }

    public void setWordsToCrosscheck(List<String> wordsToCrosscheck) {
        this.wordsToCrosscheck = wordsToCrosscheck;
    }

    public int getWordValue() {
        return wordValue;
    }

    public void setWordValue(int wordValue) {
        this.wordValue = wordValue;
    }

    @Override
    public String toString(){
        String returnString = word + " " + anchorRow + " " + anchorSquare + " " + leftPartLength + " " + wordValue;
        for(String string : wordsToCrosscheck){
            returnString += " " + string + ",";
        }
        return returnString;
    }
}
