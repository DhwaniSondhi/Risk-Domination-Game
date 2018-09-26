package entity;

import java.util.ArrayList;

public class Player {
    public int id;
    public String name;
    public String colour;
    public ArrayList<Card> cards;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
