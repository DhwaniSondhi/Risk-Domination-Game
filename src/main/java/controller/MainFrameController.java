package controller;

import gui.MainFrame;
import gui.MapCreatorFrame;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Paths;

import utility.FileHelper;
import utility.MapHelper;

/**
 * This is the Controller for the MainFrame. see {@link BaseController}
 * implements {@link ActionListener} for MenuItems
 * */
public class MainFrameController extends BaseController<MainFrame> implements ActionListener {

    public MainFrameController(MainFrame view) {
        super(view);
    }

    /**
     * This function is checking if the action is
     * Load countryGraph : loads the countryGraph using {@link JFileChooser}
     * Create countryGraph : creates the countryGraph and does validation
     * Exit : exits the game
     * */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("Load GameMap")) {
            File dir = new File("maps");
            dir.mkdir();
            JFileChooser file = new JFileChooser(dir);
            int confirmValue = file.showOpenDialog(null);

            if(confirmValue == JFileChooser.APPROVE_OPTION){
                File selectedFile = file.getSelectedFile();
                FileHelper.loadToConfig(selectedFile);
                if(MapHelper.validateMap()){

                } else {
                    FileHelper.emptyConfig();
                    System.out.println("File validation failed");
                }
//                view.setUpGamePanels();

            }

        } else if (e.getActionCommand().equalsIgnoreCase("Create GameMap")) {
            new MapCreatorFrame("Create Map", false);
        } else if (e.getActionCommand().equalsIgnoreCase("exit")) {
            System.exit(0);
        }
    }
}
