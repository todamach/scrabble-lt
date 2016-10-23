package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by harol on 10/23/2016.
 */
public class Player {
    String name;
    Rack rack;
    List<LegalWord> legalWords = new ArrayList<>();

    public Player(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rack getRack() {
        return rack;
    }

    public void setRack(Rack rack) {
        this.rack = rack;
    }

    public List<LegalWord> getLegalWords() {
        return legalWords;
    }

    public void setLegalWords(List<LegalWord> legalWords) {
        this.legalWords = legalWords;
    }

    public void addLegalWord(LegalWord legalWord){
        this.legalWords.add(legalWord);
    }
}
