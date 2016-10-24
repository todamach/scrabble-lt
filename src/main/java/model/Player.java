package model;

import java.util.*;

/**
 * Created by harol on 10/23/2016.
 */
public class Player {

    static private Comparator<LegalWord> descWordValue;

    String name;
    Rack rack;
    List<LegalWord> legalWords = new ArrayList<>();

    public Player(){

    }

    // We initialize static variables inside a static block.
    static {
        descWordValue = new Comparator<LegalWord>(){
            @Override
            public int compare(LegalWord l1, LegalWord l2){
                // Java 7 has an Integer#compare function
                return Integer.compare(l2.getWordValue(), l1.getWordValue());
            }
        };
    }

    public void sortLegalWordsByValue(){
        Collections.sort(legalWords, descWordValue);
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
