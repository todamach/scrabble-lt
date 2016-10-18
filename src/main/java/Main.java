

import com.sun.deploy.util.ArrayUtil;
import dawg.ModifiableDAWGMap;
import dawg.ModifiableDAWGNode;
import dawg.ModifiableDAWGSet;
import model.Board;
import model.Letter;
import model.Rack;
import model.Tile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream ;

/**
 * Created by harol on 10/11/2016.
 */
public class Main {

    //static String[] row = {"", "", "", "", "", "d", "", "", "", "", "", "", ""};
    static List<Letter> lettersInRack = new ArrayList<>();
    public static int anchorSquare = 7;
    public static int anchorRow = 6;
    static Board board;
    static Rack rack;

    public static void main(String[] args){

        lettersInRack.add(new Letter("p", 0));
        lettersInRack.add(new Letter("s", 0));
        lettersInRack.add(new Letter("r", 0));
        lettersInRack.add(new Letter("t", 0));
        lettersInRack.add(new Letter("m", 0));
        lettersInRack.add(new Letter("o", 0));
        lettersInRack.add(new Letter("k", 0));
        lettersInRack.add(new Letter("e", 0));
        lettersInRack.add(new Letter("ë", 0));
        lettersInRack.add(new Letter("o", 0));

//        ModifiableDAWGSet dawg  = readDAWGFromFile();
//        writeDAWGToGraphWiz(dawg);
//
        ModifiableDAWGNode startNode = dawg.sourceNode;
        leftPart("", startNode, 1);

        board = new Board();
        board.setBoard(board.generateEmptyBoard());

        board.getBoard()[7][7].setLetter(Letter.getLetter("a"));
        board.findAnchors();



        System.out.println("Pabaiga");
    }

    private static void leftPart(String partialWord, ModifiableDAWGNode node, int limit) {

        int currentTile = anchorSquare - 1;
        String leftPart = "";
        // Jeigu iskarto pries anchorSquare yra raide, tai imam visas raides iki pradzios, ir nebeieskom daugiau jokiu left parts
        if(!board.getBoard()[anchorRow][currentTile].getLetter().getLetter().equals("")){
            // gaunam stringa raidziu einanciu pries anchorSquare ir ji apverciam
            while(currentTile >= 0){
                if(board.getBoard()[anchorRow][currentTile].getLetter().getLetter().equals("")){
                    break;
                }
                leftPart += board.getBoard()[anchorRow][currentTile].getLetter().getLetter();
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
                    int index;
                    if((index = rack.contains(c.toString())) > - 1){
                        Letter letter = rack.getLetters().get(index);
                        rack.getLetters().remove(index);
                        ModifiableDAWGNode nextNode = outgoingNodes.get(c);
                        leftPart(partialWord + c, nextNode, limit - 1);
                        rack.getLetters().add(letter);
                    }
                }
            }
        }

    }

    private static void extendRight(String partialWord, ModifiableDAWGNode node, int square) {
        if(square <= board.getBoard()[anchorRow].length && Objects.equals(board.getBoard()[anchorRow][square].getLetter().getLetter(), "")){
            if(node.isAcceptNode()){
                System.out.println("Legal word: " + partialWord);
            }
            NavigableMap<Character, ModifiableDAWGNode> outgoingNodes = node.getOutgoingTransitions();
            for(Character c : outgoingNodes.keySet()){
                int index;
                if((index = rack.contains(c.toString())) > -1){
                    Letter letter = rack.getLetters().get(index);
                    rack.getLetters().remove(index);
                    ModifiableDAWGNode nextNode = outgoingNodes.get(c);
                    int nextSquare = square + 1;
                    System.out.println("Extending right with rack: " + partialWord + c);
                    extendRight(partialWord + c, nextNode, nextSquare);
                    rack.getLetters().add(letter);
                }
            }
        }else{
            Letter letter = board.getBoard()[anchorRow][square].getLetter();
            NavigableMap<Character, ModifiableDAWGNode> outgoingNodes = node.getOutgoingTransitions();
            for(Character c : outgoingNodes.keySet()){
                if(letter.getLetter().equals(c.toString())){
                    ModifiableDAWGNode nextNode = outgoingNodes.get(c);
                    int nextSquare = square + 1;
                    System.out.println("Extending right with board: " + partialWord + letter.getLetter());
                    extendRight(partialWord + letter.getLetter(), nextNode, nextSquare);
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
