package model;

import Util.Annotator;

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
    int score;
    Dawg dawg;

    public Player(Dawg dawg){
        this.dawg = dawg;
        rack = new Rack();
    }

    public void drawLetters(LetterPool pool){

            int neededLetters = Rack.SIZE - rack.getLetters().size();
            for(int i = 0; i < neededLetters; i++){
                if(pool.getPool().size() > 0) {
                    int randomNumber = generateRandomNumber(0, pool.getPool().size() - 1);
                    Letter randomLetter = pool.getPool().get(randomNumber);
                    rack.getLetters().add(randomLetter);
                    pool.getPool().remove(randomLetter);
                }
            }

    }

    private int generateRandomNumber(int start, int end){
        Random r = new Random();
        int low = start;
        int high = end;
        if(low == 0 && high == 0){
            return 0;
        }
        return r.nextInt(high-low) + low;
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

    public boolean placeBestScoringWordOnTheBoard(Board board) {
        List<LegalWord> legalWordsMerged = new ArrayList<>();
        legalWordsMerged.addAll(legalWordsHorizontal);
        legalWordsMerged.addAll(legalWordsVertical);

        Collections.sort(legalWordsMerged, descWordValue);
        boolean wordPlaced = false;
        for(LegalWord legalWord : legalWordsMerged){
            if(wordAndCrosschecksRealWords(legalWord)){
                System.out.println(legalWord);
                Tile[][] boardOrientation = board.getBoard(legalWord.getOrientation());
                int currentCol =  legalWord.getWordStart();
                for(Letter letter : legalWord.getPartialWord().getLetters()){
                    Tile tile = boardOrientation[legalWord.getAnchorRow()][currentCol];
                    if(tile.getLetter().getLetter().isEmpty()){
                        letter.setPlaced(true);
                        tile.setLetter(letter);
                        //tile.getLetter().setLetter(letter.getLetter());
                        tile.setAnchor(false);
                        if(letter.isWildcard()){
                            rack.remove("*");
                        }else{
                            rack.remove(letter.getLetter());
                        }
                        wordPlaced = true;
                    }
                    currentCol++;
                }
                score += legalWord.getWordValue();
                break;
            }
        }
        return wordPlaced;
    }

    private boolean wordAndCrosschecksRealWords(LegalWord legalWord){
        if(Annotator.checkWord(legalWord.getPartialWord().getWord())){
            for(LegalWord.CrosscheckWord word : legalWord.getWordsToCrosscheck()){
                if(!Annotator.checkWord(word.getWord())){
                    return false;
                }
            }
        }else{
            return false;
        }
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Dawg getDawg() {
        return dawg;
    }

    public void setDawg(Dawg dawg) {
        this.dawg = dawg;
    }

    public void addLegalWord(int anchorSquare, int anchorRow, PartialWord partialWord, int leftPartLength, Tile[][] board, int orientation){
        LegalWord legalWord = new LegalWord(anchorSquare, anchorRow, partialWord, leftPartLength, board, orientation, rack.getLetters().size());

        if(orientation == Board.HORIZONTAL){
            this.legalWordsHorizontal.add(legalWord);
        }else if(orientation == Board.VERTICAL){
            this.legalWordsVertical.add(legalWord);
        }

    }

    public void clearLegalWords() {
        legalWordsVertical.clear();
        legalWordsHorizontal.clear();
    }

    public void setTestRack() {
        rack.getLetters().add(new Letter("g"));
        rack.getLetters().add(new Letter("h"));
        rack.getLetters().add(new Letter("*"));
        rack.getLetters().add(new Letter("r"));
        rack.getLetters().add(new Letter("j"));
        rack.getLetters().add(new Letter("a"));
        rack.getLetters().add(new Letter("h"));
        rack.getLetters().add(new Letter("l"));
    }

    @Override
    public String toString(){
        return "" + name + " " + score + " " + this.getRack();
    }
}
