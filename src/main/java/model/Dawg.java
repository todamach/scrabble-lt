package model;

import dawg.ModifiableDAWGSet;

import java.io.*;

/**
 * Created by harol on 10/19/2016.
 */
public class Dawg {
    ModifiableDAWGSet dawg;

    public Dawg(){
        dawg = readDAWGFromFile();
       // writeDAWGToGraphWiz(dawg);
    }

    public ModifiableDAWGSet getDawg() {
        return dawg;
    }

    public void setDawg(ModifiableDAWGSet dawg) {
        this.dawg = dawg;
    }

    private ModifiableDAWGSet readDAWGFromFile() {
        ModifiableDAWGSet dawg = new ModifiableDAWGSet();

        String path = "D:\\Projektai\\Intellij\\DAWG programa\\src\\main\\resources\\15klt.txt";
        //String path = "D:\\Projektai\\Intellij\\DAWG programa\\src\\main\\resources\\test.txt";
        File file = new File(path);

        try (BufferedReader in = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), "Unicode"))) {
            String line;
            while ((line = in.readLine()) != null) {
                if(line.length() > 1)
                    dawg.add(line.toLowerCase());
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dawg;
    }

    private static void writeDAWGToGraphWiz(ModifiableDAWGSet dawg) {
        String graphWiz = dawg.toGraphViz(false, false);

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
}
