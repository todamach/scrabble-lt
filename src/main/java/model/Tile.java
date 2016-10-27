package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by harol on 10/17/2016.
 */
public class Tile {

    public static final int NONE = 0, DOUBLE_LETTER = 1, TRIPLE_LETTER = 2, DOUBLE_WORD = 3, TRIPLE_WORD = 4;

    private Letter letter = new Letter();
    private boolean isAnchor;
    private Map<String, Crosscheck> verticalCrosschecks = new HashMap<>();
    private Map<String, Crosscheck> horizontalCrosschecks = new HashMap<>();
    private int multiplier = NONE;

    private boolean allHorizontalCrosschecks;
    private boolean allVerticalCrosschecks;

    public Tile(){

    }

    public Tile(int multiplier){
        this.multiplier = multiplier;
        this.isAnchor = false;
    }

    public boolean crosscheckContains(char c, int orientation){
        if(orientation == Board.HORIZONTAL){
            return horizontalCrosschecksContains(String.valueOf(c));
        }else if(orientation == Board.VERTICAL){
            return verticalCrosschecksContains(String.valueOf(c));
        }

        return false;
    }

    public Letter getLetter() {
        return letter;
    }

    public void setLetter(Letter letter) {
        this.letter = letter;
    }

    public boolean isAnchor() {
        return isAnchor;
    }

    public void setAnchor(boolean anchor) {
        isAnchor = anchor;
    }

    public Map<String, Crosscheck> getVerticalCrosschecks() {
        return verticalCrosschecks;
    }

    public void setVerticalCrosschecks(Map<String, Crosscheck> verticalCrosschecks) {
        this.verticalCrosschecks = verticalCrosschecks;
    }

    public Map<String, Crosscheck> getHorizontalCrosschecks() {
        return horizontalCrosschecks;
    }

    public void setHorizontalCrosschecks(Map<String, Crosscheck> horizontalCrosschecks) {
        this.horizontalCrosschecks = horizontalCrosschecks;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public void addHorizontalCrosscheck(Crosscheck crosscheck, String letter){
        horizontalCrosschecks.put(letter,crosscheck);
    }

    public void addVerticalCrosscheck(Crosscheck crosscheck, String letter){
        verticalCrosschecks.put(letter,crosscheck);
    }

    public boolean horizontalCrosschecksContains(String letter){
        if(!this.isAnchor || this.allHorizontalCrosschecks){
            return true;
        }

        return horizontalCrosschecks.get(letter) != null;
    }

    public boolean verticalCrosschecksContains(String letter){
        if(!this.isAnchor || this.allVerticalCrosschecks){
            return true;
        }

        return verticalCrosschecks.get(letter) != null;
    }


    public boolean isAllCrosschecks() {
        return allHorizontalCrosschecks;
    }

    public void setAllCrosschecks(boolean allCrosschecks, int orientation) {
        if(orientation == Board.HORIZONTAL){
            this.allHorizontalCrosschecks = allCrosschecks;
        }else if(orientation == Board.VERTICAL){
            this.allVerticalCrosschecks = allCrosschecks;
        }

    }

    public Map<String,Crosscheck> getCrosschecks(int orientation) {
        if(orientation == Board.HORIZONTAL){
            return getHorizontalCrosschecks();
        }else if(orientation == Board.VERTICAL){
            return getVerticalCrosschecks();
        }
        return null;
    }
}
