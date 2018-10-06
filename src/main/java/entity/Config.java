package entity;

import java.util.*;


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
    private static int continentCounter;
    private static int countryCounter;

    private Config() {
        countries = new HashMap<>();
        continents = new HashMap<>();
        players = new HashMap<>();
        map = new HashMap<>();
        continentCounter = 0;
        countryCounter = 0;
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
     * saves the continent and continent value from the file
     *
     * @param continent      gets continent from file
     * @param continentValue gets confinent value from file
     */
    public void saveContinent(String continent, int continentValue) {
        continentCounter++;
        continents.put(continentCounter, new Continent(continentCounter, continent, continentValue));
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
        ArrayList<Card> cards=new ArrayList<Card>();
        cards.add(new Card(Card.TYPE.CAVALRY));
        cards.add(new Card(Card.TYPE.INFANTRY));
        cards.add(new Card(Card.TYPE.ARTILLERY));
        Player player1=new Player(1, "Player 1");
        player1.cards=cards;
        players.put(1,player1);
        Player player2=new Player(2, "Player 2");
        player2.cards=cards;
        players.put(2,player2);
        Player player3=new Player(3, "Player 3");
        player3.cards=cards;
        players.put(3,player3);

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
        Country country5 = new Country(5, "Country 5");
        country5.owner = players.get(1);
        country5.numOfArmies = 12;
        Country country6 = new Country(6, "Country 6");
        country6.owner = players.get(2);
        country6.numOfArmies = 24;
        Country country7 = new Country(7, "Country 7");
        country7.owner = players.get(3);
        country7.numOfArmies = 10;
        Country country8 = new Country(8, "Country 8");
        country8.owner = players.get(1);
        country8.numOfArmies = 20;

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
        n1.add(country5);
        HashSet<Country> n2 = new HashSet<>();
        n2.add(country4);
        n2.add(country6);
        HashSet<Country> n3 = new HashSet<>();
        n3.add(country1);
        n3.add(country7);
        HashSet<Country> n4 = new HashSet<>();
        n4.add(country1);
        n4.add(country2);
        n4.add(country7);
        HashSet<Country> n5 = new HashSet<>();
        n5.add(country1);
        n5.add(country6);
        HashSet<Country> n6 = new HashSet<>();
        n6.add(country5);
        n6.add(country7);
        n6.add(country2);
        HashSet<Country> n7 = new HashSet<>();
        n7.add(country6);
        n7.add(country8);
        n7.add(country4);
        n7.add(country3);
        HashSet<Country> n8 = new HashSet<>();
        n8.add(country7);



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

    /**
     * Saves the country and their neighbouring countries
     *
     * @param territories List of all the countries along with its neighbouring countries
     */
    public void saveCountry(ArrayList<String> territories) {
        String country = territories.get(0);
        int countryId = this.checkCountryExists(country);
        if (countryId == -1) {
            //add country to config
            countryId = countryCounter++;
            countries.put(countryCounter, new Country(countryId, country));
        }
        HashSet<Country> neighbours = new HashSet<>();
        for (String territory : territories.subList(1, territories.size())) {
            int neighbourCountryId = this.checkCountryExists(territory);
            if (neighbourCountryId == -1) {
                //add country to config
                neighbourCountryId = countryCounter++;
                countries.put(countryCounter, new Country(neighbourCountryId, territory));

            }
            neighbours.add(countries.get(neighbourCountryId));
        }
    }

    /**
     * To check if the country is already added
     *
     * @param country Gets the country name
     * @return false if country doesnot exist
     */
    private int checkCountryExists(String country) {
        for (Map.Entry<Integer, Country> entry : countries.entrySet()) {
            if (entry.getValue().name.equalsIgnoreCase(country)) {
                return entry.getKey();
            }
        }
        return -1;
    }
}
