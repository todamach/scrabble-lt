package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by harol on 10/17/2016.
 */
public class Rack {
    private List<Letter> letters = new ArrayList<>();

    public Rack(){}

    public List<Letter> getLetters() {
        return letters;
    }

    public void setLetters(List<Letter> letters) {
        this.letters = letters;
    }
}
