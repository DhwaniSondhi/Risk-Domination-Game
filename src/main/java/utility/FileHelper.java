package utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * This is the helper class which contains all the functionalities for File Handling
 * */
public class FileHelper {
    /**
     * Loads the txt file that user chooses by loading map to config
     * @param selectedFile
    * */
    public static void loadToConfig(File selectedFile) {
        FileReader fileReader = new FileReader(selectedFile);
        BufferedReader bufferReader = new BufferedReader(fileReader);

    }
}
