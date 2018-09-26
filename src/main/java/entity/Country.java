package entity;

public class Country {
    public int id;
    public String name;
    public Player owner;
    public int numOfArmies;

    public Country(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
