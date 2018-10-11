package controller;

import gui.MainFrame;
import gui.MapCreatorFrame;
import utility.FileHelper;
import utility.MapHelper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * This is the Controller for the MainFrame. see {@link BaseController}
 * implements {@link ActionListener} for MenuItems
 */
public class MainFrameController extends BaseController<MainFrame> implements ActionListener {

    public MainFrameController(MainFrame view) {
        super(view);
    }

    /**
     * This function is checking if the action is
     * Load countryGraph : loads the countryGraph using {@link JFileChooser}
     * Create countryGraph : creates the countryGraph and does validation
     * Exit : exits the game
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean isLoadMap = false, isEditMap = false;
        isLoadMap = e.getActionCommand().equalsIgnoreCase("Load GameMap");
        isEditMap = e.getActionCommand().equalsIgnoreCase("Edit GameMap");
        if (isEditMap || isLoadMap) {
            File dir = new File("maps");
            dir.mkdir();
            JFileChooser file = new JFileChooser(dir);
            int confirmValue = file.showOpenDialog(null);

            if (confirmValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = file.getSelectedFile();
                if (isLoadMap) {
                    try {

                        FileHelper.loadToConfig(selectedFile);
                        if (MapHelper.validateContinentGraph() && MapHelper.validateMap()) {
//                      view.setUpGamePanels();
                        } else {
                            FileHelper.emptyConfig();
                            System.out.println("File validation failed");
                        }
//                      view.setUpGamePanels();
                    } catch (IllegalStateException exception) {

                        FileHelper.emptyConfig();
                        System.out.println("File validation failed : " + exception.getMessage());
                    }
                } else {
                    new MapCreatorFrame("Edit Map", selectedFile);
                }
            }
        } else if (e.getActionCommand().equalsIgnoreCase("Create GameMap")) {
            new MapCreatorFrame("Create Map");
        } else if (e.getActionCommand().equalsIgnoreCase("exit")) {
            System.exit(0);
        }
    }
}
