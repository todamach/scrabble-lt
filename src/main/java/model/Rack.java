package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by harol on 10/17/2016.
 */
public class Rack {
    public static final int SIZE = 7;
    private List<Letter> letters = new ArrayList<>();

    public Rack() {
    }

    public List<Letter> getLetters() {
        return letters;
    }

    public void setLetters(List<Letter> letters) {
        this.letters = letters;
    }

    public void remove(String letter) {
        for (Letter rackLetter : letters) {
            if (rackLetter.getLetter().equals(letter)) {
                letters.remove(rackLetter);
                break;
            }
        }
    }

    public int contains(String letter) {
        for (Letter rackLetter : letters) {
            if (rackLetter.getLetter().equals(letter)) {
                return letters.indexOf(rackLetter);
            }
        }
        return -1;
    }

    public int containsWildcard() {
        for (Letter rackLetter : letters) {
            if (rackLetter.getLetter().equals("*")) {
                return letters.indexOf(rackLetter);
            }
        }
        return -1;
    }

    @Override
    public String toString(){
        String rack = "";
        for(Letter letter : letters){
            rack += ", " + letter.getLetter() + " ";
        }
        return rack;
    }
}
