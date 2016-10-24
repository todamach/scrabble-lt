package model;

/**
 * Created by harol on 10/17/2016.
 */
public class Crosscheck {
    private Letter letter;
    private int value;
    private String crosscheckWord;

    public Crosscheck(){

    }

    public Crosscheck(String crosscheckWord, Letter letter) {
        this.crosscheckWord = crosscheckWord;
        this.letter = letter;
    }

    public Letter getLetter() {
        return letter;
    }

    public void setLetter(Letter letter) {
        this.letter = letter;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getCrosscheckWord() {
        return crosscheckWord;
    }

    public void setCrosscheckWord(String crosscheckWord) {
        this.crosscheckWord = crosscheckWord;
    }

    @Override
    public String toString(){
        return letter.getLetter() + " " + crosscheckWord;
    }
}
