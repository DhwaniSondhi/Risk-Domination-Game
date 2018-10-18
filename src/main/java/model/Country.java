package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Class for Country containing access to all the components of country
 */
public class Country {
    /**
     * Country id
     */
    public int id;
    /**
     * Country name
     */
    public String name;
    /**
     * Player that owns of country
     */
    public Player owner;
    /**
     * Number of armies at country
     */
    public int numOfArmies;

    /**
     * Constructor to set current name and id of country
     *
     * @param id   id of country
     * @param name name of country
     */
    public Country(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
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
