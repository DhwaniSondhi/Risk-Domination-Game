package model;

import java.util.*;

/**
 * Class for Country containing access to all the components of country
 */
public class Country extends Observable {

    public enum Update{
        ARMY,
        OWNER,
        DICE

    }
    public Update state = null;
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

    /**
     * flag for distinction
     */
    public int flagForObserver;
    /**
     * HashSet for Countries
     */
    public HashSet<Country> neighbours;
    /**
     * HashSet to save connected Country
     */
    public HashMap<Integer , Country> connectedCountries;
    /**
     * Variable for number of armies at selected connected country
     */
    int numberOfArmiesAtConnnectedCountry;

    /**
     * Constructor to set current name and id of country
     *
     * @param id   id of country
     * @param name name of country
     */
    public Country(int id, String name) {
        connectedCountries=new HashMap<>();
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
            if(isOpponent){
                numOfDiceAllowed = 2;
            }
        } else if (numArmies == 3) {
            numOfDiceAllowed = 2;
        } else if (numArmies == 2) {
            numOfDiceAllowed = 1;
            if (isOpponent) {
                numOfDiceAllowed = 2;
            }
        } else if(numArmies == 1 && isOpponent){
            numOfDiceAllowed = 1;
        }
        state = Update.DICE;
        setChanged();
        notifyObservers();
    }

    /**
     * This give updated connected countries based on selected country
     */
    public void updateConnectedCountries() {
        connectedCountries.clear();
        //int countryId = selectedCountry.id;
        //int ownerId = selectedCountry.owner.id;

        Queue<Country> queueNeighbor = new LinkedList<>();
        queueNeighbor.add(this);
        connectedCountries.put(id, this);
        while (!queueNeighbor.isEmpty()) {
            Country last = queueNeighbor.remove();
            HashSet<Country> listNeighbouring = last.getNeighbours();
            for (Country country : listNeighbouring) {
                if (country.owner.id == owner.id) {
                    if (connectedCountries.get(country.id) == null) {
                        queueNeighbor.add(country);
                        connectedCountries.put(country.id, country);
                        //neighbor.put(country.id, country);
                    }

                }
            }
            connectedCountries.remove(id);

        }

        flagForObserver=1;
        setChanged();
        notifyObservers();
    }

    /**
     * This function update the TextField for number of armies at selected connected country
     */
    public void updateTextFieldForNeighbour()
    {
        numberOfArmiesAtConnnectedCountry =this.numOfArmies;
        flagForObserver=2;
        setChanged();
        notifyObservers();
    }

    /*
     * Updates the armies of countries in which armies are transferred
     *
     * @param numberOfArmiesTransfer armies user select to transfer
     * @param country       country which user select transfer to
     */
    /*public void updateArmiesOfCountries(int numberOfArmiesTransfer, Country country) {
        this.deductArmies(numberOfArmiesTransfer);
        country.addArmies(numberOfArmiesTransfer);
        GameMap.getInstance().countries.put(this.id, this);
        GameMap.getInstance().countries.put(country.id, country);
    }*/

    public void changeOwner(Player newOwner) {
        owner.countries.remove(this);
        this.owner = newOwner;

        owner.countries.add(this);
        GameMap.getInstance().notifyChanges();
        state = Update.OWNER;
        setChanged();
        notifyObservers();
    }

    public void addArmies(int numOfArmies){
        this.numOfArmies += numOfArmies;
        GameMap.getInstance().notifyChanges();
        state = Update.ARMY;
        setChanged();
        notifyObservers();
    }

    public void deductArmies(int numOfArmies){
        this.numOfArmies -= numOfArmies;
        GameMap.getInstance().notifyChanges();
        state = Update.ARMY;
        setChanged();
        notifyObservers();
    }
}
