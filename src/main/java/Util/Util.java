package Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by harol on 10/19/2016.
 */
public class Util {

    public static List<String> getAbc(){
        List<String> abc = new ArrayList<>();
        abc.add("a");
        abc.add("à");
        abc.add("b");
        abc.add("c");
        abc.add("è");
        abc.add("d");
        abc.add("e");
        abc.add("æ");
        abc.add("ë");
        abc.add("f");
        abc.add("g");
        abc.add("h");
        abc.add("i");
        abc.add("á");
        abc.add("y");
        abc.add("j");
        abc.add("k");
        abc.add("l");
        abc.add("m");
        abc.add("n");
        abc.add("o");
        abc.add("p");
        abc.add("r");
        abc.add("d");
        abc.add("ð");
        abc.add("t");
        abc.add("u");
        abc.add("ø");
        abc.add("û");
        abc.add("v");
        abc.add("z");
        abc.add("þ");

        return abc;
    }

    public static int getLetterValue(String letter){

        int value = 0;
        switch(letter){
            case "a":
                value = 1;
                break;
            case "à":
                value = 8;
                break;
            case "b":
                value = 2;
                break;
            case "c":
                value = 10;
                break;
            case "è":
                value = 8;
                break;
            case "d":
                value = 2;
                break;
            case "e":
                value = 1;
                break;
            case "æ":
                value = 10;
                break;
            case "ë":
                value = 4;
                break;
            case "f":
                value = 10;
                break;
            case "g":
                value = 4;
                break;
            case "h":
                value = 10;
                break;
            case "i":
                value = 1;
                break;
            case "á":
                value = 8;
                break;
            case "y":
                value = 5;
                break;
            case "j":
                value = 4;
                break;
            case "k":
                value = 1;
                break;
            case "l":
                value = 2;
                break;
            case "m":
                value = 2;
                break;
            case "n":
                value = 1;
                break;
            case "o":
                value = 1;
                break;
            case "p":
                value = 3;
                break;
            case "r":
                value = 1;
                break;
            case "s":
                value = 1;
                break;
            case "ð":
                value = 5;
                break;
            case "t":
                value = 1;
                break;
            case "u":
                value = 1;
                break;
            case "ø":
                value = 6;
                break;
            case "û":
                value = 8;
                break;
            case "v":
                value = 4;
                break;
            case "z":
                value = 10;
                break;
            case "þ":
                value = 6;
                break;
        }

        return value;
    }
}
