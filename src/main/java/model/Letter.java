package model;

/**
 * Created by harol on 10/17/2016.
 */
public class Letter {
    private String letter = "";
    private int value = 0;

    public Letter(String letter, int value){
        this.letter = letter;
        this.value = value;
    }

    public Letter(){}

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static Letter getLetter(String letter){
        return new Letter(letter, 0);
    }
}
