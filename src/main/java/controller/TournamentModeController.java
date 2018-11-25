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
    int numberOfPlayer;
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
        System.out.println(e.getSource().getClass().getName());
        if (e.getStateChange() == ItemEvent.SELECTED) {
            JComboBox jComboBox = (JComboBox) e.getSource();
            numberOfPlayer = (int) jComboBox.getSelectedItem();
            System.out.println(numberOfPlayer);
        }

    }

    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        if (e.getActionCommand().equalsIgnoreCase("Submit")){

            if(numberOfPlayer==0){
                view.updatePlayersPanel(2);
            }
            else{
                view.updatePlayersPanel(numberOfPlayer);
            }

        }
        else if(e.getActionCommand().equalsIgnoreCase("Proceed")){
            view.proceedToMaps();
        }
        else if(e.getActionCommand().equalsIgnoreCase("Submit Number")){
            System.out.println("sffsfsf");
            if(numberOfPlayer==0){
                view.updateMapsPanel(1);
            }
            else{
                view.updateMapsPanel(numberOfPlayer);
            }
        }

    }
}
