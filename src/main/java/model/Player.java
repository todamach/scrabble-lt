package model;

import java.util.*;

/**
 * Created by harol on 10/23/2016.
 */
public class Player {

    static private Comparator<LegalWord> descWordValue;

    String name;
    Rack rack;
    List<LegalWord> legalWordsHorizontal = new ArrayList<>();
    List<LegalWord> legalWordsVertical = new ArrayList<>();

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
        Collections.sort(legalWordsHorizontal, descWordValue);
        Collections.sort(legalWordsVertical, descWordValue);
    }

    public void placeBestScoringWordOnTheBoard(Board board) {
        List<LegalWord> legalWordsMerged = new ArrayList<>();
        legalWordsMerged.addAll(legalWordsHorizontal);
        legalWordsMerged.addAll(legalWordsVertical);

        Collections.sort(legalWordsMerged, descWordValue);

        for(LegalWord legalWord : legalWordsMerged){
            if(wordAndCrosschecksRealWords(legalWord)){
                Tile[][] boardOrientation = board.getBoard(legalWord.getOrientation());

            }


        }

    }

    private boolean wordAndCrosschecksRealWords(LegalWord legalWord){
        return true;
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

    public List<LegalWord> getLegalWordsHorizontal() {
        return legalWordsHorizontal;
    }

    public void setLegalWordsHorizontal(List<LegalWord> legalWords) {
        this.legalWordsVertical = legalWords;
    }

    public void addLegalWord(int anchorSquare, int anchorRow, String partialWord, int leftPartLength, Tile[][] board, int orientation){
        LegalWord legalWord = new LegalWord(anchorSquare, anchorRow, partialWord, leftPartLength, board, orientation, rack.getLetters().size());

        if(orientation == Board.HORIZONTAL){
            this.legalWordsHorizontal.add(legalWord);
        }else if(orientation == Board.VERTICAL){
            this.legalWordsVertical.add(legalWord);
        }

    }
}
