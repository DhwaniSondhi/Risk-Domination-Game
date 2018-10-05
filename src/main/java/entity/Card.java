package entity;


public class Card {
    public enum TYPE {
        INFANTRY,
        CAVALRY,
        ARTILLERY
    }

    public TYPE type;

    public Card(TYPE type) {
        this.type = type;
    }
}
