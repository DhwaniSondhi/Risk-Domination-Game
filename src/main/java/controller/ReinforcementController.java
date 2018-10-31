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
     * HashMap to keep the name of the card as key and number of cards as value
     */
    private HashMap<String, Integer> unselectedCards;

    /**
     * List for the cards selected
     */
    private ArrayList<Card> selectedCards;

    /**
     * Variable for total armies for Reinforcement Phase
     */
    int totalArmies;

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
        selectedCards = new ArrayList<>();
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
            String cardName = buttonName.substring(3);
            if (selectedCards.size() < 3) {
                if (unselectedCards.get(cardName) != null) {
                    unselectedCards.replace(cardName, unselectedCards.get(cardName) - 1);
                }
                Card card = null;
                if (cardName.equalsIgnoreCase("ARTILLERY")) {
                    card = new Card(Card.TYPE.ARTILLERY);
                } else if (cardName.equalsIgnoreCase("INFANTRY")) {
                    card = new Card(Card.TYPE.INFANTRY);
                } else if (cardName.equalsIgnoreCase("CAVALRY")) {
                    card = new Card(Card.TYPE.CAVALRY);
                }
                if (card != null) {
                    selectedCards.add(card);
                }
            }
            view.addCardSection();
        } else if (buttonName.equalsIgnoreCase("Update")) {
            ArrayList<Card> selectedCardsLocal=selectedCards;
            selectedCards.clear();
            totalArmies = model.currentPlayer.getUpdatedArmiesOnCardsExchange(totalArmies,selectedCardsLocal);

            /*view.addArmySection();
            view.addCardSection();*/
        } else if (buttonName.equalsIgnoreCase("changeArmies")) {
            changeArmiesOfCountries();
            view.addCardSection();
        } else if (buttonName.equalsIgnoreCase("Reset")) {
            for (Card card : selectedCards) {
                if (unselectedCards.get(card.type.toString()) != null) {
                    unselectedCards.replace(card.type.toString(), unselectedCards.get(card.type.toString()) + 1);
                }
            }
            selectedCards.clear();
            view.addCardSection();
        } else if (buttonName.equalsIgnoreCase("proceed")) {
            if (stateChangeListener != null)
                stateChangeListener.onReinforcementCompleted();
            return;
        }

    }

    /**
     * To get the card section of Reinforcement Panel
     */
    public void getCardsInGui() {
        view.addUnselectedCardGrid(unselectedCards);
        view.addSelectedCardGrid(selectedCards);
    }

    /**
     * To set the list of cards available of a player in the unselectedCards attribute
     */
    public void setUnSelectedCards() {
        unselectedCards = model.currentPlayer.getCardSetsOfPlayer();
    }


    /**
     * To set the total armies getting from getTotalArmies() method in totalArmies attribute
     */
    public void setArmiesForReinforcement() {
        totalArmies = model.currentPlayer.getTotalArmies(model.countries, model.continents);
    }

    /**
     * To get the total armies available from totalArmies attribute
     *
     * @return the value of totalArmies attribute
     */
    public int getArmiesForReinforcement() {
        return totalArmies;
    }

    /**
     * To get the countries of currentPlayer
     *
     * @return List of countries of CurrentPlayer
     */
    public List<Country> getCountriesAndIdsOfCurrentPlayer() {
        return model.currentPlayer.getCountries();
    }

    /**
     * To add the armies to the respective countries on click of Add button
     */
    public void changeArmiesOfCountries() {
        int countryId = model.currentPlayer.getCountries().get(view.getValueOfCountryIndexComboBox()).id;
        int addedArmy = Integer.parseInt(view.getValueOfArmyComboBox());
        Country countryChanged = model.countries.get(countryId);
        countryChanged.numOfArmies += addedArmy;
        model.countries.replace(countryId, countryChanged);
        totalArmies -= addedArmy;
        view.addArmySection();
    }


}
