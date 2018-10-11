package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
     *  Sets up the id, name and selected cards of the player
     * </p>
     */
    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        updateArmiesForCards=5;
    }

}
