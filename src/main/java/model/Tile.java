package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by harol on 10/17/2016.
 */
public class Tile {
    private Letter letter;
    private boolean isAnchor;
    private List<Crosscheck> verticalCrosschecks = new ArrayList<>();
    private List<Crosscheck> horizontalCrosschecks = new ArrayList<>();

    public Tile(){}

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
}
