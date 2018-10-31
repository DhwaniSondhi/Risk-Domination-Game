package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
     * Armies on trading card
     */
    public int updateArmiesForCards;

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
        updateArmiesForCards = 5;

        initializeWithDummyCards();
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
     * To get the updated total armies when a set of three cards are changed
     *
     * @param totalArmiesLocal the armies allotted from countries and continents player has
     * @return the updated armies
     */
    public int getUpdatedArmiesOnCardsExchange(int totalArmiesLocal, ArrayList<Card> selectedCards) {
        totalArmiesLocal += this.updateArmiesForCards;
        this.updateArmiesForCards += 5;
        ArrayList<Card> removeCards=new ArrayList<>();
        for(Card cardSelected:selectedCards){
            for(Card cardPlayer:this.cards){
                if(cardPlayer.type==cardSelected.type){
                    removeCards.add(cardPlayer);
                    break;
                }
            }
        }
        for(Card cardRemove:removeCards){
            this.cards.remove(cardRemove);
        }
        setChanged();
        notifyObservers(this);
        return totalArmiesLocal;
    }
}
