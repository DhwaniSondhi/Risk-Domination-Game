package utility;

import entity.GameMap;

import java.io.*;
import java.util.*;

/**
 * This is the helper class which contains all the functionalities for File Handling
 */
public class FileHelper {
    /**
     * Loads the txt file that user chooses by loading countryGraph to config
     *
     * @param selectedFile input file from {@link javafx.stage.FileChooser}
     * @throws IllegalStateException if continent doesnot exist in territory
     */
    public static void loadToConfig(File selectedFile) throws IllegalStateException {
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
                    if (!GameMap.getInstance().saveCountry(territories)) {
                        throw new IllegalStateException("Continent does not exists");
                    }
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
     * @param file            File to save map to
     * @param continentValues list of texts from continent fields
     * @param countryValues   list oftexts from country fields
     */
    public static void saveMapToFile(File file, List<String> continentValues, List<String> countryValues) {
        BufferedWriter bufferedWriter;
        FileWriter fileWriter;

        String EOL = System.getProperty("line.separator");

        try {
            fileWriter = new FileWriter(file, false);

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
