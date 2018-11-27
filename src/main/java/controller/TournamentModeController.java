package controller;


import model.Player;
import utility.FileHelper;
import utility.MapHelper;
import utility.strategy.PlayerStrategy;
import view.TournamentModeFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.HashMap;

/**
 * Controller class for tournament mode
 */
public class TournamentModeController extends BaseController<TournamentModeFrame> implements ItemListener, ActionListener {
    /**
     * Variable to store the integer value of combobox
     */
    int variableForComboBox;

    /**
     * This is the constructor for the Controller
     *
     * @param view View associated with the Controller
     */
    public TournamentModeController(TournamentModeFrame view) {
        super(view);
        model.tournamentMode = true;
    }

    /**
     * Invoked when an item has been selected or deselected by the user.
     * The code written for this method performs the operations
     * that need to occur when an item is selected (or deselected).
     *
     * @param e event invoked on item changed in combobox
     */
    @Override
    public void itemStateChanged(ItemEvent e) {

        if (e.getStateChange() == ItemEvent.SELECTED) {
            JComboBox jComboBox = (JComboBox) e.getSource();
            variableForComboBox = (int) jComboBox.getSelectedItem();

        }

    }

    /**
     * Invoked when an action occurs.
     *
     * @param e invoked event
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equalsIgnoreCase("Submit")) {

            if (variableForComboBox == 0) {
                view.updatePlayersPanel(2);
            } else {
                view.updatePlayersPanel(variableForComboBox);
            }

        } else if (e.getActionCommand().equalsIgnoreCase("Proceed")) {
            int playerId = 1;
            model.players = new HashMap<>();
            for (Component componentPanelForPlayers : view.jPanelForStrategyOfPlayer.getComponents()) {
                if (componentPanelForPlayers instanceof JPanel) {
                    Component[] components = ((JPanel) componentPanelForPlayers).getComponents();
                    String name = ((JTextField) components[0]).getText();
                    PlayerStrategy.Strategy strategy = (PlayerStrategy.Strategy) ((JComboBox) components[1]).getSelectedItem();
                    Player player = new Player(playerId, name, strategy);
                    model.players.put(playerId, player);
                    playerId++;
                }
            }
            view.proceedToMaps();
        } else if (e.getActionCommand().equalsIgnoreCase("Submit Number")) {

            if (variableForComboBox == 0) {
                view.updateMapsPanel(1);
            } else {
                view.updateMapsPanel(variableForComboBox);
            }
        } else if (e.getActionCommand().equalsIgnoreCase("Start Tournament")) {
            Component[] components = view.jPanelForNumberOfGamesOnMap.getComponents();
            int index = 1;
            for (Component component : components) {
                JComboBox<Integer> field = (JComboBox<Integer>) ((JPanel) component).getComponents()[1];
                int count = (int) field.getSelectedItem();
                model.gameNumbers.put(index, count);
                index++;
            }

            System.out.println(model.gameNumbers);
            model.tournamentMode = true;
            model.startTournamentMode(true);
            view.dispose();
        } else if (e.getActionCommand().equalsIgnoreCase("Maps1") || e.getActionCommand().equalsIgnoreCase("Maps2") || e.getActionCommand().equalsIgnoreCase("Maps3") || e.getActionCommand().equalsIgnoreCase("Maps4") || e.getActionCommand().equalsIgnoreCase("Maps5")) {
            int index = Integer.valueOf(e.getActionCommand().split("")[4]);
            File file = loadMaps();
            if (file != null) {
                System.out.println(index);
                model.maps.put(index, file);
            } else {
                System.out.println("File null");
            }
        }else if (e.getActionCommand().equalsIgnoreCase("Close")) {

            model.tournamentMode=false;
            view.dispose();
        }

    }

    /**
     * load map from directory
     */
    public File loadMaps() {
        File dir = new File("maps");
        dir.mkdir();
        JFileChooser file = new JFileChooser(dir);
        int confirmValue = file.showOpenDialog(null);

        if (confirmValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();

            try {
                FileHelper.loadToConfig(selectedFile);
                if (MapHelper.validateContinentGraph() && MapHelper.validateMap()) {
                    return selectedFile;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
