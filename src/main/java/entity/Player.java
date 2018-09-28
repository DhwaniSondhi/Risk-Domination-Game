package entity;

import java.util.ArrayList;

public class Player {
    public int id;
    public String name;
    public String colour = "#000";
    public ArrayList<Card> cards;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
