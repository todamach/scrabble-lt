package Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by harol on 10/19/2016.
 */
public class Util {

    public static List<String> getAbc(){
        List<String> abc = new ArrayList<>();
        abc.add("A");
        abc.add("�");
        abc.add("B");
        abc.add("C");
        abc.add("�");
        abc.add("D");
        abc.add("E");
        abc.add("�");
        abc.add("�");
        abc.add("F");
        abc.add("G");
        abc.add("H");
        abc.add("I");
        abc.add("�");
        abc.add("Y");
        abc.add("J");
        abc.add("K");
        abc.add("L");
        abc.add("M");
        abc.add("N");
        abc.add("O");
        abc.add("P");
        abc.add("R");
        abc.add("S");
        abc.add("�");
        abc.add("T");
        abc.add("U");
        abc.add("�");
        abc.add("�");
        abc.add("V");
        abc.add("Z");
        abc.add("�");

        return abc;
    }

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
}
