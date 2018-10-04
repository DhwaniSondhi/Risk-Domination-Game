package controller;

import gui.MainFrame;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import utility.FileHelper;

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
     * Load map : loads the map using {@link JFileChooser}
     * Create map : creates the map and does validation
     * Exit : exits the game
     * */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("Load Map")) {
            JFileChooser file = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            int confirmValue = file.showOpenDialog(null);

            if(confirmValue == JFileChooser.APPROVE_OPTION){
                File selectedFile = file.getSelectedFile();
                FileHelper.loadToConfig(selectedFile);
            }

        } else if (e.getActionCommand().equalsIgnoreCase("Create Map")) {

        } else if (e.getActionCommand().equalsIgnoreCase("exit")) {
            System.exit(0);
        }
    }
}
