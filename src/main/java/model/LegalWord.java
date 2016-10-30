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
    List<CrosscheckWord> wordsToCrosscheck = new ArrayList<>();
    int leftPartLength;
    int wordValue;
    int orientation;
    int rackSize;

    public LegalWord(){}

    public LegalWord(int anchorSquare, int anchorRow, String partialWord, int leftPartLength, Tile[][] board, int orientation, int rackSize) {
        this.anchorRow = anchorRow;
        this.anchorSquare = anchorSquare;
        this.word = partialWord;
        this.leftPartLength = leftPartLength;
        this.orientation = orientation;
        this.rackSize = rackSize;
        this.wordValue = calculateWordValue(board);

        findCrosschecks(board);
    }

    private int calculateWordValue(Tile[][] board){
        int currentCol = anchorSquare - leftPartLength;
        int totalValue = 0;
        int tripleWord = 0;
        int doubleWord = 0;
        for(char c : word.toCharArray()){
            Tile tile = board[anchorRow][currentCol];
            if(tile.getLetter().getLetter().isEmpty()){
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
            }
            currentCol++;
        }

        totalValue += calculateCrosscheckScores();

        for(int i = 0; i<doubleWord; i++){
            totalValue *= 2;
        }
        for(int i = 0; i<tripleWord; i++){
            totalValue *= 3;
        }

        return totalValue + checkForBonus();
    }

    private int calculateCrosscheckScores(){
        int score = 0;
        for(CrosscheckWord cw : wordsToCrosscheck){
            score += cw.getValue();
        }
        return score;
    }

    private int checkForBonus(){
        int bonus = 0;
        if(rackSize == 0){
            bonus = 50;
        }
        return bonus;
    }

    private void findCrosschecks(Tile[][] board) {
        int currentCol = anchorSquare - leftPartLength;

        for(char c : word.toCharArray()){
            //if(currentCol != anchorSquare){
                Tile tile = board[anchorRow][currentCol];
                CrosscheckWord crosscheckWord = findCrosscheckWord(tile.getCrosschecks(orientation), c);

                if(crosscheckWord != null)
                    this.wordsToCrosscheck.add(crosscheckWord);
            //}
            currentCol++;
        }
    }

    private CrosscheckWord findCrosscheckWord(Map<String, Crosscheck> crosschecks, char c){
        Crosscheck crosscheck = crosschecks.get(String.valueOf(c));
        if(crosscheck != null){
            return new CrosscheckWord(crosscheck.getCrosscheckWord(), crosscheck.getValue());
        }
        return null;
    }

    public int getOrientation() {
        return orientation;
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

    public List<CrosscheckWord> getWordsToCrosscheck() {
        return wordsToCrosscheck;
    }

    public void setWordsToCrosscheck(List<CrosscheckWord> wordsToCrosscheck) {
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
        for(CrosscheckWord crosscheckWord : wordsToCrosscheck){
            returnString += " " + crosscheckWord.getWord() + ",";
        }
        return returnString;
    }

    public class CrosscheckWord{
        String word;
        int value;

        CrosscheckWord(String word, int value){
            this.word = word;
            this.value = value;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
