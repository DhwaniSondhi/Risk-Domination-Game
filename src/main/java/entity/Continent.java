package entity;

import java.util.ArrayList;

public class Continent {
    public int id;
    public String name;
    public ArrayList<Country> countries;
    public int controlValue;

    public Continent(int id, String name, int controlValue) {
        this.id = id;
        this.name = name;
        this.controlValue = controlValue;
        countries = new ArrayList<>();
    }

    @Override
    public String toString() {
        return name + "|" + countries.size();
    }
}
