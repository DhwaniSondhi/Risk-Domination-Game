package controller;


import model.Card;
import view.CardExchangeFrame;
import view.ReinforcementPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is the Controller class for reinforcement phase
 * extends the abstract class {@link BaseController}
 * implements {@link ActionListener} for actions performed on Gui part(Reinforcement panel)
 */
public class ReinforcementController extends BaseController<ReinforcementPanel> implements ActionListener {

    /**
     * Variable for total armies for Reinforcement Phase
     */
    int totalArmies;
    /**
     * HashMap to keep the name of the card as key and number of cards as value
     */
    private HashMap<String, Integer> unselectedCards;
    /**
     * List for the cards selected
     */
    private ArrayList<Card> selectedCards;

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

    /**
     * To initialize the values for Reinforcement Panel
     */
    public void initialize() {
        model.currentPlayer.addObserver(view);
        model.currentPlayer.updateReinforcementPanel = true;
        model.currentPlayer.setUnSelectedCards();
        model.currentPlayer.setArmiesForReinforcement();
    }

    /**
     * Invoked on any action performed on Exchange Cards button for Card Section in Reinforcement Panel
     * Invoked on any action performed on Change Armies button for Army Section in Reinforcement Panel
     * Invoked on any action performed on Proceed button to proceed to next phase for the player
     *
     * @param e {@link ActionEvent}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonName = ((JButton) e.getSource()).getName();
        if (buttonName.equalsIgnoreCase("exchangeCards")) {

            model.currentPlayer.updateReinforcementPanel = false;
            CardExchangeFrame cardExchangeFrame = new CardExchangeFrame();

        } else if (buttonName.equalsIgnoreCase("changeArmies")) {
            model.currentPlayer.changeArmiesOfCountries(view.getValueOfCountryIndexComboBox(), view.getValueOfArmyComboBox());

        } else if (buttonName.equalsIgnoreCase("proceed")) {

            if (stateChangeListener != null) {
                stateChangeListener.onReinforcementCompleted();
                model.currentPlayer.totalArmies = 0;
            }

            model.currentPlayer.deleteObserver(view);
            return;
        }

    }
}
