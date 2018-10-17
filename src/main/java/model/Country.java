package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Class for Country containing access to all the components of country
 */
public class Country {
    public int id;
    public String name;
    public Player owner;
    public int numOfArmies;

    public Country(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return id + ". " + name;
    }

    /**
     * Get the list of Neighbour countries
     *
     * @param map HashMap of country id and set of countries
     * @return list of countries
     */
    public List<Country> getNeighbours(HashMap<Integer, HashSet<Country>> map) {
        List<Country> countries = new ArrayList<>();
        for (Country country : map.get(this.id)) {
            countries.add(country);
        }
        return countries;
    }

}
