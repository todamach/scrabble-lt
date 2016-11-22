package model;

import Util.Util;

/**
 * Created by harol on 10/17/2016.
 */
public class Letter {
    private String letter = "";
    private String letterValue = "";
    private boolean isWildcard = false;
    private int pointValue = 0;
    private boolean placed = false;

    public Letter(String letter, int value){
        this.letter = letter;
        this.pointValue = value;
    }

    public Letter(Letter letter){
        this.letter = letter.letter;
        this.letterValue = letter.letterValue;
        this.isWildcard = letter.isWildcard;
        this.pointValue = letter.pointValue;
    }

    public Letter(Character letter){
        this(letter.toString());
    }

    public Letter (String letter){
        this.letter = letter;

        if(letter.equals("*")){
            this.isWildcard = true;
            this.pointValue = 0;
        }else{
            this.letterValue = letter;
            this.pointValue = Util.getLetterValue(letter);
        }

    }

    public Letter(){}

    public char getChar(){
        return letter.charAt(0);
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public int getValue() {
        return pointValue;
    }

    public void setValue(int value) {
        this.pointValue = value;
    }

    public static Letter getLetter(String letter){
        return new Letter(letter, 0);
    }

    public String getLetterValue() {
        return letterValue;
    }

    public void setLetterValue(String letterValue) {
        this.letterValue = letterValue;
    }

    public boolean isWildcard() {
        return isWildcard;
    }

    public void setWildcard(boolean wildcard) {
        isWildcard = wildcard;
    }

    public int getPointValue() {
        return pointValue;
    }

    public void setPointValue(int pointValue) {
        this.pointValue = pointValue;
    }

    public boolean isPlaced() {
        return placed;
    }

    public void setPlaced(boolean placed) {
        this.placed = placed;
    }
}
