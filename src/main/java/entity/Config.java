package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


/**
 * Singleton class containing access to all the game components
 */
public class Config {

    private static Config INSTANCE = null;

    public Player currentPlayer = null;

    public HashMap<Integer, Country> countries;
    public HashMap<Integer, Continent> continents;
    public HashMap<Integer, Player> players;

    public HashMap<Integer, HashSet<Country>> map;

    private Config() {
        countries = new HashMap<>();
        continents = new HashMap<>();
        players = new HashMap<>();
        map = new HashMap<>();
    }

    /**
     * @return returns the singleton instance of the class
     */
    public static Config getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Config();

        return INSTANCE;
    }


    /**
     * update the instance with the game data
     *
     * @param continents continents of the game.
     * @param countries  countries of the game.
     * @param players    players of the game
     */
    public void setUpWithData(HashMap<Integer, Country> countries, HashMap<Integer, Continent> continents,
                              HashMap<Integer, Player> players) {
        this.countries = countries;
        this.continents = continents;
        this.players = players;
    }

    /**
     * update the instance with the map
     *
     * @param map {@link HashMap} of game map
     */
    public void setMapData(HashMap<Integer, HashSet<Country>> map) {
        this.map = map;
    }


    /**
     * Get the list of Neighbour countries
     *
     * @param countryId Id of the country whose neighbors are required
     * @return list of countries
     */
    public List<Country> getNeighbourCountries(int countryId) {
        List<Country> countries = new ArrayList<>();
        for (Country country : map.get(countryId)) {
            countries.add(country);
        }
        return countries;
    }

    /**
     * Get the list of all countries owned by the current player
     *
     * @return list of countries
     */
    public List<Country> getCountriesOfCurrentPlayer() {
        List<Country> countries = new ArrayList<>();

        for (Integer countryId : this.countries.keySet()) {
            if (this.countries.get(countryId).owner.id == currentPlayer.id) {
                countries.add(this.countries.get(countryId));
            }
        }
        return countries;
    }

    /**
     * @return total number of continents
     */
    public int getNumberOfContinents() {
        return continents.size();
    }

    /**
     * @return total number of players
     */
    public int getNumberOfPlayers() {
        return players.size();
    }

    /**
     * @return total number of countries
     */
    public int getNumberOfCountries() {
        return countries.size();
    }

    public void setDummyData() {
        players.put(1, new Player(1, "Player 1"));
        players.put(2, new Player(2, "Player 2"));
        players.put(3, new Player(3, "Player 3"));

        continents.put(1, new Continent(1, "Continent1", 5));
        continents.put(2, new Continent(2, "Continent2", 7));

        Country country1 = new Country(1, "Country 1");
        country1.owner = players.get(1);
        country1.numOfArmies = 50;
        Country country2 = new Country(2, "Country 2");
        country2.owner = players.get(2);
        country2.numOfArmies = 5;
        Country country3 = new Country(3, "Country 3");
        country3.owner = players.get(3);
        country3.numOfArmies = 10;
        Country country4 = new Country(4, "Country 4");
        country4.owner = players.get(3);
        country4.numOfArmies = 40;
        Country country5 = new Country(5,"Country 5");
        country5.owner=players.get(1);
        country5.numOfArmies=12;
        Country country6 = new Country(6,"Country 6");
        country6.owner=players.get(2);
        country6.numOfArmies=24;
        Country country7=new Country(7,"Country 7");
        country7.owner=players.get(3);
        country7.numOfArmies=10;
        Country country8=new Country(8,"Country 8");
        country8.owner=players.get(1);
        country8.numOfArmies=20;

        countries.put(1, country1);
        countries.put(2, country2);
        countries.put(3, country3);
        countries.put(4, country4);
        countries.put(5, country5);
        countries.put(6, country6);
        countries.put(7, country7);
        countries.put(8, country8);

        HashSet<Country> n1 = new HashSet<>();
        n1.add(country3);
        n1.add(country4);
        HashSet<Country> n2 = new HashSet<>();
        n2.add(country4);
        HashSet<Country> n3 = new HashSet<>();
        n3.add(country1);
        HashSet<Country> n4 = new HashSet<>();
        n4.add(country1);
        n4.add(country2);
        HashSet<Country> n5 = new HashSet<>();
        n5.add(country1);
        n5.add(country5);
        HashSet<Country> n6 = new HashSet<>();
        n6.add(country5);
        n6.add(country6);
        HashSet<Country> n7 = new HashSet<>();
        n7.add(country6);
        n7.add(country7);
        HashSet<Country> n8 = new HashSet<>();
        n8.add(country7);
        n8.add(country8);


        map.put(1, n1);
        map.put(2, n2);
        map.put(3, n3);
        map.put(4, n4);
        map.put(5, n5);
        map.put(6, n6);
        map.put(7, n7);
        map.put(8, n8);


        currentPlayer = players.get(3);

    }
}
