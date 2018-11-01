package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Observable;

/**
 * Class containing attributes and functions of a player object
 */
public class Player extends Observable {
    /**
     * id for player
     */
    public int id;
    /**
     * Name for player
     */
    public String name;
    /**
     * Cards hold by player
     */
    public ArrayList<Card> cards;

    /**
     * HashMap for countries
     */
    public ArrayList<Country> countries;
    /**
     * Armies on trading card
     */
    public int updateArmiesForCards;
    /**
     * Variable for total armies for Reinforcement Phase
     */
    public int totalArmies;
    /**
     * List for the cards selected
     */
    public ArrayList<Card> selectedCards;
    /**
     * HashMap to keep the name of the card as key and number of cards as value
     */
    public HashMap<String, Integer> unselectedCards;

    /**
     * Constructor
     * <p>
     * Sets up the id, name and selected cards of the player
     * </p>
     *
     * @param id   id of player
     * @param name name of player
     */
    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        cards = new ArrayList<>();
        countries = new ArrayList<>();
        updateArmiesForCards = 5;
        selectedCards = new ArrayList<>();
        initializeWithDummyCards();
    }

    /**
     * Initializes countries to current player
     * @param country instance of Country
     * */
    public void initializeCountryToPlayer(Country country) {
        this.countries.add(country);
        updateView();
    }

    /**
     * adds 3 random cards to the player.
     */
    private void initializeWithDummyCards() {
        for (int i = 0; i < 3; i++) {
            cards.add(new Card());
        }
    }

    /**
     * Adds random cards
     */
    public void addRandomCard() {
        cards.add(new Card());
    }

    /**
     * To add cards to selected card section on ADD button click
     *
     * @param cardName the card added
     */
    public void addInSelectedCards(String cardName){
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
        updateView();
    }

    /**
     * To calculate the total armies from the countries and continents own by the player
     *
     * @param countries  list of countries in the instance
     * @param continents list of continents in the instance
     * @return the calculated total armies
     */
    public int getTotalArmies(HashMap<Integer, Country> countries, HashMap<Integer, Continent> continents) {
        int playerCountries = 0;
        for (Map.Entry<Integer, Country> entry : countries.entrySet()) {
            Country country = entry.getValue();
            if (country.owner.id == this.id) {
                playerCountries++;
            }
        }

        int playerContinentsControlVal = 0;
        boolean hasContinent;
        for (Map.Entry<Integer, Continent> entry : continents.entrySet()) {
            hasContinent = true;
            Continent continent = entry.getValue();
            ArrayList<Country> continentCountries = continent.countries;
            for (Country country : continentCountries) {
                if (country.owner.id != this.id) {
                    hasContinent = false;
                    break;
                }
            }
            if (hasContinent) {
                playerContinentsControlVal += continent.controlValue;
            }
        }

        int totalArmLocal = (playerCountries / 3) + playerContinentsControlVal;
        if (totalArmLocal < 3) {
            totalArmLocal = 3;
        }
        return totalArmLocal;
    }

    /**
     * To get the number of INFANTRY, ARTILLERY and CAVALRY cards the player has
     *
     * @return hashmap with values as the number of each INFANTRY, ARTILLERY and CAVALRY cards
     */
    public HashMap<String, Integer> getCardSetsOfPlayer() {
        ArrayList<Card> cards = this.cards;
        HashMap<String, Integer> cardSets = new HashMap<>();
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
            cardSets.put("INFANTRY", infantry);
            cardSets.put("ARTILLERY", artillery);
            cardSets.put("CAVALRY", cavalry);
        }
        return cardSets;
    }

    /**
     * To set the list of cards available of a player in the unselectedCards attribute
     */
    public void setUnSelectedCards() {
        unselectedCards = getCardSetsOfPlayer();
        updateView();
    }

    /**
     * To add the armies to the respective countries on click of Add button
     */
    public void changeArmiesOfCountries(int countryIdFromView,String armySelected) {
        int countryId = countries.get(countryIdFromView).id;
        int addedArmy = Integer.parseInt(armySelected);
        Country countryChanged = GameMap.getInstance().countries.get(countryId);
        countryChanged.numOfArmies += addedArmy;
        GameMap.getInstance().countries.replace(countryId, countryChanged);
        totalArmies -= addedArmy;
        updateView();
    }

    /**
     * To set the total armies getting from getTotalArmies() method in totalArmies attribute
     */
    public void setArmiesForReinforcement() {
        totalArmies=getTotalArmies(GameMap.getInstance().countries, GameMap.getInstance().continents);
        updateView();
    }

    /**
     * To get the updated total armies when a set of three cards are changed
     *
     */
    public void getUpdatedArmiesOnCardsExchange() {
        totalArmies += this.updateArmiesForCards;
        this.updateArmiesForCards += 5;
        ArrayList<Card> removeCards = new ArrayList<>();
        for (Card cardSelected : selectedCards) {
            for (Card cardPlayer : this.cards) {
                if (cardPlayer.type == cardSelected.type) {
                    removeCards.add(cardPlayer);
                    break;
                }
            }
        }
        for (Card cardRemove : removeCards) {
            this.cards.remove(cardRemove);
        }
        selectedCards.clear();
        updateView();
    }

    /**
     * To reset the selected cards
     */
    public void resetSelectedCards(){
        for (Card card : selectedCards) {
            if (unselectedCards.get(card.type.toString()) != null) {
                unselectedCards.replace(card.type.toString(), unselectedCards.get(card.type.toString()) + 1);
            }
        }
        selectedCards.clear();
        updateView();
    }
     /** Get the list of all countries owned by the player
     *
     * @return list of countries
     */
    public List<Country> getCountries() {
        return countries;
    }

    /**
     * To update view whenever any parameter changes
     */
    public void updateView(){
        setChanged();
        notifyObservers(this);
    }
}
