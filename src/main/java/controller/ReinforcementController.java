package controller;


import model.*;
import view.ReinforcementPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * This is the Controller class for reinforcement phase
 * extends the abstract class {@link BaseController}
 * implements {@link ActionListener} for actions performed on Gui part(Reinforcement panel)
 */
public class ReinforcementController extends BaseController<ReinforcementPanel> implements ActionListener {

    /**
     * Constructor for Reinforcement Controller
     * <p>
     * To initialize attributes
     * </p>
     *
     * @param reinforcementPanel the reinforcement panel attached to it
     */
    public ReinforcementController(ReinforcementPanel reinforcementPanel) {
        super(reinforcementPanel);

    }

    public void initialize(){
        model.currentPlayer.addObserver(view);
        model.currentPlayer.setUnSelectedCards();
        model.currentPlayer.setArmiesForReinforcement();
    }
    /**
     * Invoked on any action performed on Add, Reset and Update buttons for Card Section in Reinforcement Panel
     * Invoked on any action performed on Change Armies button for Army Section in Reinforcement Panel
     * Invoked on any action performed on Proceed button to proceed to next phase for the player
     *
     * @param e {@link ActionEvent}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonName = ((JButton) e.getSource()).getName();

        if (buttonName.substring(0, 3).equalsIgnoreCase("ADD")) {
            model.currentPlayer.addInSelectedCards(buttonName.substring(3));
        } else if (buttonName.equalsIgnoreCase("Update")) {
            model.currentPlayer.getUpdatedArmiesOnCardsExchange();
        } else if (buttonName.equalsIgnoreCase("changeArmies")) {
            model.currentPlayer.changeArmiesOfCountries(view.getValueOfCountryIndexComboBox(),view.getValueOfArmyComboBox());
        } else if (buttonName.equalsIgnoreCase("Reset")) {
            model.currentPlayer.resetSelectedCards();
        } else if (buttonName.equalsIgnoreCase("proceed")) {
            if (stateChangeListener != null)
                stateChangeListener.onReinforcementCompleted();
            model.currentPlayer.deleteObserver(view);
            return;

        }

    }
}
