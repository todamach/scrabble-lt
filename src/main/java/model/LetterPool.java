package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by harol on 10/30/2016.
 */
public class LetterPool {
    private List<Letter> pool = new ArrayList<>();

    public LetterPool(){
        generateLetters();
    }

    private void generateLetters() {
        for(int i = 0; i < 2; i++){
            pool.add(new Letter("*"));
        }

        for(int i = 0; i < 12; i++){
            pool.add(new Letter("a"));
        }
        pool.add(new Letter("à"));
        pool.add(new Letter("b"));
        pool.add(new Letter("c"));
        pool.add(new Letter("è"));
        for(int i = 0; i < 3; i++){
            pool.add(new Letter("d"));
        }
        for(int i = 0; i < 5; i++){
            pool.add(new Letter("e"));
        }
        pool.add(new Letter("æ"));
        for(int i = 0; i < 2; i++){
            pool.add(new Letter("ë"));
        }
        pool.add(new Letter("f"));
        for(int i = 0; i < 2; i++){
            pool.add(new Letter("g"));
        }
        pool.add(new Letter("h"));
        for(int i = 0; i < 13; i++){
            pool.add(new Letter("i"));
        }
        pool.add(new Letter("á"));
        pool.add(new Letter("y"));
        for(int i = 0; i < 2; i++){
            pool.add(new Letter("j"));
        }
        for(int i = 0; i < 4; i++){
            pool.add(new Letter("k"));
        }
        for(int i = 0; i < 3; i++){
            pool.add(new Letter("l"));
        }
        for(int i = 0; i < 3; i++){
            pool.add(new Letter("m"));
        }
        for(int i = 0; i < 5; i++){
            pool.add(new Letter("n"));
        }
        for(int i = 0; i < 6; i++){
            pool.add(new Letter("o"));
        }
        for(int i = 0; i < 3; i++){
            pool.add(new Letter("p"));
        }
        for(int i = 0; i < 5; i++){
            pool.add(new Letter("r"));
        }
        for(int i = 0; i < 8; i++){
            pool.add(new Letter("s"));
        }
        pool.add(new Letter("ð"));
        for(int i = 0; i < 6; i++){
            pool.add(new Letter("t"));
        }
        for(int i = 0; i < 4; i++){
            pool.add(new Letter("u"));
        }
        pool.add(new Letter("ø"));
        pool.add(new Letter("û"));
        for(int i = 0; i < 2; i++){
            pool.add(new Letter("v"));
        }
        pool.add(new Letter("z"));
        pool.add(new Letter("þ"));
    }

    public List<Letter> getPool() {
        return pool;
    }

    public void setPool(List<Letter> pool) {
        this.pool = pool;
    }

    @Override
    public String toString(){
        String pool = "";

        for(Letter letter : getPool()){
            pool += letter.getLetter() + ", ";
        }
        return "" + getPool().size() + ", " + pool;
    }
}
