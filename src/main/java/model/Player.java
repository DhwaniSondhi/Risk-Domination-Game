package model;

import java.util.ArrayList;
import java.util.HashMap;
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

        initializeWithDummyCards();
    }

    /**
     * Initailaizes countries to current player
     * @param country instance of Country
     * */
    public void initializeCountryToPlayer(Country country) {
        this.countries.add(country);
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
     * Get the list of all countries owned by the player
     *
     * @return list of countries
     */
    public List<Country> getCountries() {
        return countries;
    }
}
