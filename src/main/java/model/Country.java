package model;

import java.util.HashSet;
import java.util.Observable;

/**
 * Class for Country containing access to all the components of country
 */
public class Country extends Observable {
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

    public int numOfDiceAllowed = 0;

    public HashSet<Country> neighbours;


    /**
     * Constructor to set current name and id of country
     *
     * @param id   id of country
     * @param name name of country
     */
    public Country(int id, String name) {
        this.id = id;
        this.name = name;
        this.neighbours = new HashSet<>();
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
     * @return list of countries
     */
    public HashSet<Country> getNeighbours() {
        return neighbours;
    }

    /**
     * Gives the number of armies at a country
     *
     * @return number of army at that country
     */
    public int getNumberofArmies() {
        return numOfArmies;
    }

    public void updateNumOfDiceAllowed(boolean isOpponent) {
        numOfDiceAllowed = 0;
        int numArmies = this.getNumberofArmies();
        if (numArmies > 3) {
            numOfDiceAllowed = 3;
        } else if (numArmies == 3) {
            numOfDiceAllowed = 2;
        } else if (numArmies == 2) {
            numOfDiceAllowed = 1;
            if(isOpponent){
                numOfDiceAllowed = 2;
            }
        }
        setChanged();
        notifyObservers();
    }


    public void deductArmy() {
        this.numOfArmies -= 1;
        setChanged();
        notifyObservers();
    }

    public void changeOwner(Player newOwner) {
        this.owner = newOwner;
        setChanged();
        notifyObservers();
    }
}
