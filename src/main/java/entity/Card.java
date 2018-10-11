package entity;


import java.util.Random;

public class Card {
    public TYPE type;

    public Card(TYPE type) {
        this.type = type;
    }

    public Card() {
        int rand = new Random().nextInt(3);
        type = TYPE.values()[rand];
    }

    public enum TYPE {
        INFANTRY,
        CAVALRY,
        ARTILLERY
    }
}
