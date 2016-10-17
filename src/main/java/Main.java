

import com.sun.deploy.util.ArrayUtil;
import dawg.ModifiableDAWGMap;
import dawg.ModifiableDAWGNode;
import dawg.ModifiableDAWGSet;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream ;

/**
 * Created by harol on 10/11/2016.
 */
public class Main {

    static String[] row = {"", "", "", "", "", "d", "", "", "", "", "", "", ""};
    static List<String> rack = new ArrayList<>();
    public static int anchorSquare = 4;

    public static void main(String[] args){

        rack.add("p");
        rack.add("s");
        rack.add("a");
        rack.add("r");
        rack.add("t");
        rack.add("m");
        rack.add("o");
        rack.add("k");
        rack.add("e");
        rack.add("ë");
        rack.add("o");

        ModifiableDAWGSet dawg  = readDAWGFromFile();
        writeDAWGToGraphWiz(dawg);

        ModifiableDAWGNode startNode = dawg.sourceNode;
        leftPart("", startNode, 1);


        System.out.println("Pabaiga");
    }

    private static void leftPart(String partialWord, ModifiableDAWGNode node, int limit) {

        int currentTile = anchorSquare - 1;
        String leftPart = "";
        // Jeigu iskarto pries anchorSquare yra raide, tai imam visas raides iki pradzios, ir nebeieskom daugiau jokiu left parts
        if(!row[currentTile].equals("")){
            // gaunam stringa raidziu einanciu pries anchorSquare ir ji apverciam
            while(currentTile >= 0){
                if(row[currentTile].equals("")){
                    break;
                }
                leftPart += row[currentTile];
                currentTile--;
            }
            partialWord = new StringBuilder(leftPart).reverse().toString();
            // gaunam to stringo paskutini node grafe
            ModifiableDAWGNode nextNode = node;
            for(int i = 0; i < partialWord.length(); i++){
                char c = partialWord.charAt(i);
                nextNode = nextNode.getOutgoingTransitions().get(c);
            }
            System.out.println("Extending right from: " + partialWord);
            extendRight(partialWord, nextNode, anchorSquare);
        }else{
            System.out.println("Extending right from: " + partialWord);
            extendRight(partialWord, node, anchorSquare);
            if(limit > 0){
                NavigableMap<Character, ModifiableDAWGNode> outgoingNodes = node.getOutgoingTransitions();
                for(Character c : outgoingNodes.keySet()){
                    if(rack.contains(c.toString())){
                        int index = rack.indexOf(c.toString());
                        rack.remove(index);
                        ModifiableDAWGNode nextNode = outgoingNodes.get(c);
                        leftPart(partialWord + c, nextNode, limit - 1);
                        rack.add(c.toString());
                    }
                }
            }
        }

    }

    private static void extendRight(String partialWord, ModifiableDAWGNode node, int square) {
        if(square <= row.length && Objects.equals(row[square], "")){
            if(node.isAcceptNode()){
                System.out.println("Legal word: " + partialWord);
            }
            NavigableMap<Character, ModifiableDAWGNode> outgoingNodes = node.getOutgoingTransitions();
            for(Character c : outgoingNodes.keySet()){
                if(rack.contains(c.toString())){
                    int index = rack.indexOf(c.toString());
                    rack.remove(index);
                    ModifiableDAWGNode nextNode = outgoingNodes.get(c);
                    int nextSquare = square + 1;
                    System.out.println("Extending right with rack: " + partialWord + c);
                    extendRight(partialWord + c, nextNode, nextSquare);
                    rack.add(c.toString());
                }
            }
        }else{
            String letter = row[square];
            NavigableMap<Character, ModifiableDAWGNode> outgoingNodes = node.getOutgoingTransitions();
            for(Character c : outgoingNodes.keySet()){
                if(letter.equals(c.toString())){
                    ModifiableDAWGNode nextNode = outgoingNodes.get(c);
                    int nextSquare = square + 1;
                    System.out.println("Extending right with board: " + partialWord + letter);
                    extendRight(partialWord + letter, nextNode, nextSquare);
                }
            }
        }
    }

    private static void writeDAWGToGraphWiz(ModifiableDAWGSet dawg) {
        String graphWiz = dawg.toGraphViz(true, false);

        PrintWriter writer = null;
        try {
            writer = new PrintWriter("D:\\Projektai\\Intellij\\DAWG programa\\src\\main\\resources\\graphWiz.txt", "Unicode");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        writer.println(graphWiz);
        writer.close();
    }

    private static ModifiableDAWGSet readDAWGFromFile() {
        ModifiableDAWGSet dawg = new ModifiableDAWGSet();

        String path = "D:\\Projektai\\Intellij\\DAWG programa\\src\\main\\resources\\15klt.txt";
        File file = new File(path);

        try (BufferedReader in = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), "Unicode"))) {
            String line;
            while ((line = in.readLine()) != null) {
                dawg.add(line.toLowerCase());
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dawg;
    }



}
