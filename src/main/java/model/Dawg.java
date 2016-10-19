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
