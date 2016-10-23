package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by harol on 10/17/2016.
 */
public class Tile {

    public static final int NONE = 0, DOUBLE_LETTER = 1, TRIPLE_LETTER = 2, DOUBLE_WORD = 3, TRIPLE_WORD = 4;

    private Letter letter = new Letter();
    private boolean isAnchor;
    private List<Crosscheck> verticalCrosschecks = new ArrayList<>();
    private List<Crosscheck> horizontalCrosschecks = new ArrayList<>();
    private int multiplier = NONE;

    private boolean allHorizontalCrosschekcs;

    public Tile(){

    }

    public Tile(int multiplier){
        this.multiplier = multiplier;
        this.isAnchor = false;
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

    public List<Crosscheck> getVerticalCrosschecks() {
        return verticalCrosschecks;
    }

    public void setVerticalCrosschecks(List<Crosscheck> verticalCrosschecks) {
        this.verticalCrosschecks = verticalCrosschecks;
    }

    public List<Crosscheck> getHorizontalCrosschecks() {
        return horizontalCrosschecks;
    }

    public void setHorizontalCrosschecks(List<Crosscheck> horizontalCrosschecks) {
        this.horizontalCrosschecks = horizontalCrosschecks;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public void addHorizontalCrosscheck(Crosscheck crosscheck){
        horizontalCrosschecks.add(crosscheck);
    }

    public boolean horizontalCrosschecksContains(String letter){
        if(!this.isAnchor || this.allHorizontalCrosschekcs){
            return true;
        }
        for (Crosscheck crosscheck : horizontalCrosschecks) {
            if(crosscheck.getLetter().getLetter().equals(letter)){
                return true;
            }
        }
        return false;
    }

    public boolean isAllHorizontalCrosschekcs() {
        return allHorizontalCrosschekcs;
    }

    public void setAllHorizontalCrosschekcs(boolean allHorizontalCrosschekcs) {
        this.allHorizontalCrosschekcs = allHorizontalCrosschekcs;
    }
}
