package model;


import java.util.Random;

/**
 * Card Model
 */
public class Card {
    /**
     * type of card
     */
    public TYPE type;

    /**
     * Initializes with given card type
     *
     * @param type type of card
     */
    public Card(TYPE type) {
        this.type = type;
    }


    /**
     * Initializes with random card type
     */
    public Card() {
        int rand = new Random().nextInt(3);
        type = TYPE.values()[rand];
    }

    /**
     * enum for card type
     */
    public enum TYPE {
        INFANTRY,
        CAVALRY,
        ARTILLERY
    }
}
