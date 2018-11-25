package controller;


import view.TournamentModeFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Controller class for tournament mode
 */
public class TournamentModeController extends BaseController<TournamentModeFrame>implements ItemListener, ActionListener {
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
    }

    /**
     * Invoked when an item has been selected or deselected by the user.
     * The code written for this method performs the operations
     * that need to occur when an item is selected (or deselected).
     *
     * @param e
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

        if (e.getActionCommand().equalsIgnoreCase("Submit")){

            if(variableForComboBox ==0){
                view.updatePlayersPanel(2);
            }
            else{
                view.updatePlayersPanel(variableForComboBox);
            }

        }
        else if(e.getActionCommand().equalsIgnoreCase("Proceed")){
            view.proceedToMaps();
        }
        else if(e.getActionCommand().equalsIgnoreCase("Submit Number")){

            if(variableForComboBox ==0){
                view.updateMapsPanel(1);
            }
            else{
                view.updateMapsPanel(variableForComboBox);
            }
        }

    }
}
