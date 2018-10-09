package utility;

import entity.GameMap;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * This is the helper class which contains all the functionalities for File Handling
 */
public class FileHelper {
    /**
     * Loads the txt file that user chooses by loading countryGraph to config
     *
     * @param selectedFile input file from {@link javafx.stage.FileChooser}
     */
    public static void loadToConfig(File selectedFile) {
        GameMap.getInstance().clearInformation();
        try {
            FileReader fileReader = new FileReader(selectedFile);
            BufferedReader bufferReader = new BufferedReader(fileReader);

            String line;
            boolean statusContinent = false;
            boolean statusTerritories = false;
            while ((line = bufferReader.readLine()) != null) {
                if (line.equalsIgnoreCase("[Continents]")) {
                    statusContinent = true;
                    statusTerritories = false;
                    continue;
                }
                if (line.equalsIgnoreCase("[Territories]")) {
                    statusContinent = false;
                    statusTerritories = true;
                    continue;
                }
                if (statusContinent && !line.isEmpty() && !statusTerritories) {
                    String[] continent = line.split("=");
                    GameMap.getInstance().saveContinent(continent[0], Integer.valueOf(continent[1].trim()));

                }
                if (statusTerritories && !line.isEmpty() && !statusContinent) {
                    String[] data = line.replaceAll("\\d+ ,|\\d+,", "").split(",");
                    List<String> territories = Arrays.asList(data);
                    GameMap.getInstance().saveCountry(territories);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the txt file that user chooses to edit
     *
     * @param selectedFile input file from {@link javafx.stage.FileChooser}
     */
    public static HashMap<String, List<String>> loadToForm(File selectedFile) {
        HashMap<String, List<String>> data = new HashMap<>();
        data.put("continent", new ArrayList<>());
        data.put("country", new ArrayList<>());
        try {
            FileReader fileReader = new FileReader(selectedFile);
            BufferedReader bufferReader = new BufferedReader(fileReader);

            String line;
            boolean statusContinent = false;
            boolean statusTerritories = false;
            while ((line = bufferReader.readLine()) != null) {
                if (line.equalsIgnoreCase("[Continents]")) {
                    statusContinent = true;
                    statusTerritories = false;
                    continue;
                }
                if (line.equalsIgnoreCase("[Territories]")) {
                    statusContinent = false;
                    statusTerritories = true;
                    continue;
                }
                if (statusContinent && !line.trim().isEmpty()) {
                    data.get("continent").add(line.trim());

                }
                if (statusTerritories && !line.trim().isEmpty() && !statusContinent) {
                    data.get("country").add(line.replaceAll("\\d+ ,|\\d+,", "").trim());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    /**
     * Saves the form inputs to .map file
     *
     * @param mapName         String name of the file
     * @param continentValues list of texts from continent fields
     * @param countryValues   list oftexts from country fields
     */
    public static void saveMapToFile(String mapName, ArrayList<String> continentValues, ArrayList<String> countryValues) {
        BufferedWriter bufferedWriter;
        FileWriter fileWriter;

        String EOL = System.getProperty("line.separator");

        try {
            File dir = new File("maps");
            dir.mkdirs();
            File file = new File("maps/" + mapName + ".map");
            fileWriter = new FileWriter(file);

            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("[CONTINENTS]" + EOL);
            for (String value : continentValues) {
                bufferedWriter.write(value + EOL);
            }
            bufferedWriter.write(EOL);
            bufferedWriter.write("[TERRITORIES]" + EOL);
            for (String value : countryValues) {
                bufferedWriter.write(value + EOL);
            }

            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * empty continents and countries from config
     */
    public static void emptyConfig() {
        GameMap.getInstance().clearInformation();
//        GameMap.getInstance().setDummyData();

    }
}
