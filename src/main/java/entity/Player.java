package entity;

import java.util.ArrayList;

/**
 * Class containing attributes and functions of a player object
 */
public class Player {
    public int id;
    public String name;
    public String colour = "#000";
    public ArrayList<Card> cards;
    public int updateArmiesForCards;

    /**
     * Constructor
     * <p>
     * Sets up the id, name and selected cards of the player
     * </p>
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

    public void addRandomCard() {
        cards.add(new Card());
    }
}
