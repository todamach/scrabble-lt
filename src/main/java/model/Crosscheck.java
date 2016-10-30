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

    public void calculateValue(String crosscheckValueString) {
        int value = 0;
        for (int i = 0; i < crosscheckValueString.length(); i++){
            char c = crosscheckValueString.charAt(i);
            value += Util.Util.getLetterValue(String.valueOf(c));
        }

        this.value = value;
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
