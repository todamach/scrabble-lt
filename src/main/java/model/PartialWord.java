package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by harol on 11/20/2016.
 */
public class PartialWord {

    List<Letter> letters = new ArrayList<>();
    String word = "";

    PartialWord(){
    }

    PartialWord(List<Letter> letters, String word){
        this.letters = letters;
        this.word = word;
    }

    PartialWord(PartialWord partialWord){
        this(Util.Util.deepCopy(partialWord.getLetters()), partialWord.getWord());
    }

    public void addLetter(Letter letter){
        letters.add(letter);
        this.word += letter.getLetter();
    }

    public void addChar(Character letter){
        addLetter(new Letter(letter));
    }

    public void addLetters(List<Letter> letters, boolean reverse){
        if(reverse){
            for(int i = letters.size() - 1; i >= 0; i--){
                Letter letter = letters.get(i);
                addLetter(letter);
            }
        }else{
            for(int i = 0; i < letters.size(); i++){
                Letter letter = letters.get(i);
                addLetter(letter);
            }
        }

    }

    public List<Letter> getLetters() {
        return letters;
    }

    public void setLetters(List<Letter> letters) {
        this.letters = letters;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString(){
        return word;
    }
}
