package controller;


import entity.Card;
import entity.Continent;
import entity.Country;
import entity.GameMap;
import gui.ReinforcementPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * This is the Controller class for reinforcement phase
 * extends the abstract class for controller, {@Link BaseController}
 * implements {@Link ActionListener} for actions performed on Gui part of reinforcement panel
 */
public class ReinforcementController extends BaseController<ReinforcementPanel> implements ActionListener {

    public HashMap<String, Integer> unselectedCards;
    public ArrayList<Card> selectedCards;
    GameMap instance;
    Integer[] countryIdsOfCurrentPlayer;
    int totalArmies;

    /**
     * Constructor for Reinforcement Panel
     * <p>
     * To initialize attributes
     * To call method setUnselectedCards()
     *
     * @param reinforcementPanel the reinforcement panel attached to it
     * </p>
     */
    public ReinforcementController(ReinforcementPanel reinforcementPanel) {
        super(reinforcementPanel);
        instance = GameMap.getInstance();
        selectedCards = new ArrayList<>();
        setUnSelectedCards();
    }

    /**
     * To set the list of cards available of a player in the unselectedCards attribute
     */
    public void setUnSelectedCards() {
        ArrayList<Card> cards = model.currentPlayer.cards;
        if (unselectedCards == null) {
            unselectedCards = new HashMap<>();
        }
        int infantry = 0;
        int artillery = 0;
        int cavalry = 0;
        for (Card card : cards) {
            if (card.type == Card.TYPE.INFANTRY) {
                infantry++;
            } else if (card.type == Card.TYPE.ARTILLERY) {
                artillery++;
            } else if (card.type == Card.TYPE.CAVALRY) {
                cavalry++;
            }
            unselectedCards.put("INFANTRY", infantry);
            unselectedCards.put("ARTILLERY", artillery);
            unselectedCards.put("CAVALRY", cavalry);
        }
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
        } else if (buttonName.equalsIgnoreCase("Update")) {
            setCardsOnUpdate();
        } else if (buttonName.equalsIgnoreCase("changeArmies")) {
            changeArmiesOfCountries();
        } else if (buttonName.equalsIgnoreCase("Reset")) {
            for (Card card : selectedCards) {
                if (unselectedCards.get(card.type.toString()) != null) {
                    unselectedCards.replace(card.type.toString(), unselectedCards.get(card.type.toString()) + 1);
                }
            }
            selectedCards.clear();
        } else if (buttonName.equalsIgnoreCase("proceed")) {
            if (stateChangeListener != null)
                stateChangeListener.onReinforcementCompleted();
            return;
        }
        view.addCardSection();
    }


    /**
     * To get the card section of Reinforcement Panel
     */
    public void getCardsInGui() {
        view.addUnselectedCardGrid(unselectedCards);
        view.addSelectedCardGrid(selectedCards);
    }

    /**
     * To calculate the total armies from the countries and continents owns by the player in totalArmies attribute
     */
    public void setArmiesForReinforcement() {
        HashMap<Integer, Country> countries = instance.countries;
        HashMap<Integer, Continent> continents = instance.continents;

        int playerCountries = 0;
        Iterator itForCountries = countries.entrySet().iterator();
        while (itForCountries.hasNext()) {
            Map.Entry countryPair = (Map.Entry) itForCountries.next();
            Country country = (Country) countryPair.getValue();
            if (country.owner != null) {
                if (country.owner.id == instance.currentPlayer.id) {
                    playerCountries++;
                }
            }
        }

        int playerContinenstControlVal = 0;
        boolean hasContinent;
        Iterator itForContinents = continents.entrySet().iterator();
        while (itForContinents.hasNext()) {
            hasContinent = true;
            Map.Entry continentPair = (Map.Entry) itForContinents.next();
            Continent continent = (Continent) continentPair.getValue();
            ArrayList<Country> continentCountries = continent.countries;
            for (Country country : continentCountries) {
                if (country.owner != null) {
                    if (country.owner.id != instance.currentPlayer.id) {
                        hasContinent = false;
                        break;
                    }
                } else {
                    hasContinent = false;
                    break;
                }
            }
            if (hasContinent == true) {
                playerContinenstControlVal += continent.controlValue;
            }
        }

        totalArmies = (playerCountries / 3) + playerContinenstControlVal;
        if(totalArmies<3){
            totalArmies=3;
        }
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
     * It also the ids for the countries for the current player in countryIdsOfCurrentPlayer attribute
     *
     * @return List of countries of CurrentPlayer
     */
    public List<Country> getCountriesAndIdsOfCurrentPlayer() {
        List<Country> countries = model.getCountriesOfCurrentPlayer();
        countryIdsOfCurrentPlayer = new Integer[countries.size()];
        int loopForIds = 0;
        for (Country country : countries) {
            countryIdsOfCurrentPlayer[loopForIds] = country.id;
            loopForIds++;
        }
        return model.getCountriesOfCurrentPlayer();
    }

    /**
     * To add the armies to the respective countries on click of Add button
     */
    public void changeArmiesOfCountries() {
        int countryId = countryIdsOfCurrentPlayer[view.getValueOfCountryIndexComboBox()];
        int addedArmy = Integer.parseInt(view.getValueOfArmyComboBox());
        Country countryChanged = model.countries.get(countryId);
        countryChanged.numOfArmies += addedArmy;
        model.countries.replace(countryId, countryChanged);
        totalArmies -= addedArmy;
        view.addArmySection();
    }

    /**
     * To update the list of selected, unselected cards and armies added for a player in Reinforcement phase on click of update button
     */
    public void setCardsOnUpdate() {
        totalArmies += instance.currentPlayer.updateArmiesForCards;
        instance.currentPlayer.updateArmiesForCards += 5;
        selectedCards.clear();
        view.addArmySection();

    }
}
