package Util;

import model.Letter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by harol on 10/19/2016.
 */
public class Util {

    public static List<String> ABC = Arrays.asList("a","�","b","c","�","d","e","�","�","f","g","h","i","�","y","j","k","l","m","n","o","p","r","s","�","t","u","�","�","v","z","�");

    public static int getLetterValue(String letter){

        int value = 0;
        switch(letter){
            case "a":
                value = 1;
                break;
            case "�":
                value = 8;
                break;
            case "b":
                value = 2;
                break;
            case "c":
                value = 10;
                break;
            case "�":
                value = 8;
                break;
            case "d":
                value = 2;
                break;
            case "e":
                value = 1;
                break;
            case "�":
                value = 10;
                break;
            case "�":
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
            case "�":
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
            case "�":
                value = 5;
                break;
            case "t":
                value = 1;
                break;
            case "u":
                value = 1;
                break;
            case "�":
                value = 6;
                break;
            case "�":
                value = 8;
                break;
            case "v":
                value = 4;
                break;
            case "z":
                value = 10;
                break;
            case "�":
                value = 6;
                break;
        }

        return value;
    }

    public static List<Letter> deepCopy(List<Letter> letters){
        List<Letter> copyList = new ArrayList<>();
        for(Letter letter : letters){
            copyList.add(new Letter(letter));
        }

        return copyList;
    }
}
