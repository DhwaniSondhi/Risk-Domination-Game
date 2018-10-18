package controller;

import utility.FileHelper;
import utility.GameStateChangeListener;
import utility.MapHelper;
import view.MainFrame;
import view.MapCreatorFrame;
import view.StartUpFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * This is the Controller for the MainFrame. see {@link BaseController}
 * implements {@link ActionListener} for MenuItems
 * implements {@link GameStateChangeListener} to observe state change
 */
public class MainFrameController extends BaseController<MainFrame> implements
        ActionListener,
        GameStateChangeListener {
    /**
     * Boolean for startup
     */
    private boolean startUpCompleted = false;

    /**
     * Controller for MainFrame
     *
     * @param view MainFrame view
     */
    public MainFrameController(MainFrame view) {
        super(view);
    }

    /**
     * Invoked when an game map is loaded
     */
    @Override
    public void onMapLoaded() {
        new StartUpFrame(this);
    }

    /**
     * Invoked when an startup is completed is loaded
     */
    @Override
    public void onStartUpCompleted() {
        if (!startUpCompleted) {
            startUpCompleted = true;
            model.resetCurrentPlayer();
            view.setUpGamePanels();
            view.currentPlayer.setText(model.currentPlayer.name);
        }
    }

    /**
     * Invoked when reinforcement is done
     */
    @Override
    public void onReinforcementCompleted() {
        view.reinforcementPanel.setVisible(false);
        view.attackPanel.setVisible(true);
        view.attackPanel.revalidate();
    }

    /**
     * Invoked when attack is done
     */
    @Override
    public void onAttackCompleted() {
        view.attackPanel.setVisible(false);
        view.fortifyPanel.setVisible(true);
        view.fortifyPanel.update();
    }

    /**
     * Invoked when fortification is done
     */
    @Override
    public void onFortificationCompleted() {
        model.changeToNextPlayer();
        view.fortifyPanel.setVisible(false);
        view.reinforcementPanel.setVisible(true);
        view.reinforcementPanel.update();
        view.currentPlayer.setText(model.currentPlayer.name);
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
                            onMapLoaded();
                        } else {
                            FileHelper.emptyConfig();
                            System.out.println("File validation failed");
                        }
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
