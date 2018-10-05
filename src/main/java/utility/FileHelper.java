package utility;

import entity.Config;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * This is the helper class which contains all the functionalities for File Handling
 */
public class FileHelper {
    /**
     * Loads the txt file that user chooses by loading map to config
     * @param selectedFile input file from {@link javafx.stage.FileChooser}
     */
    public static void loadToConfig(File selectedFile) {
        try {
            FileReader fileReader = new FileReader(selectedFile);
            BufferedReader bufferReader = new BufferedReader(fileReader);

            String line;
            boolean statusContinent = false;
            boolean statusTerritories = false;
            while ((line = bufferReader.readLine()) != null){
                if (line.equalsIgnoreCase("[Continents]")){
                    statusContinent = true;
                    statusTerritories = false;
                    continue;
                }
                if(line.equalsIgnoreCase("[Territories]")){
                    statusContinent = false;
                    statusTerritories = true;
                    continue;
                }
                if(statusContinent && !line.isEmpty() && !statusTerritories){
                    String[] continent = line.split("=");
                    Config.getInstance().saveContinent(continent[0],Integer.valueOf(continent[1]));

                }
                if(statusTerritories && !line.isEmpty() && !statusContinent){
                    ArrayList<String> territories = new ArrayList<>();
                    territories.add(Arrays.deepToString(line.replaceAll("\\d+ ,|\\d+,","").split(",")));
                    Config.getInstance().saveCountry(territories);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
