package entity;

import java.util.*;


/**
 * Singleton class containing access to all the game components
 */
public class GameMap {

    private static GameMap INSTANCE = null;

    public Player currentPlayer = null;

    public HashMap<Integer, Country> countries;
    public HashMap<Integer, Continent> continents;
    public HashMap<Integer, Player> players;
    public HashMap<Integer, HashSet<Country>> countryGraph;
    private static int continentCounter;
    private static int countryCounter;

    private GameMap() {
        countries = new HashMap<>();
        continents = new HashMap<>();
        players = new HashMap<>();
        countryGraph = new HashMap<>();
        continentCounter = 0;
        countryCounter = 0;
    }

    /**
     * @return returns the singleton instance of the class
     */
    public static GameMap getInstance() {
        if (INSTANCE == null)
            INSTANCE = new GameMap();

        return INSTANCE;
    }

    /**
     * saves the continent and continent value from the file
     *
     * @param continent      gets continent from file
     * @param continentValue gets confinent value from file
     */
    public boolean saveContinent(String continent, int continentValue) {
        if (checkContinentExists(continent.trim()) == -1) {
            continentCounter++;
            continents.put(continentCounter, new Continent(continentCounter, continent.trim(), continentValue));
            return true;
        }
        return false;
    }

    /**
     * To check if the continent is already added
     *
     * @param continentName Gets the continent name
     * @return false if continent doesnot exist
     */
    private int checkContinentExists(String continentName) {
        for (Map.Entry<Integer, Continent> continent : continents.entrySet()) {
            if (continent.getValue().name.equalsIgnoreCase(continentName)) {
                return continent.getKey();
            }
        }
        return -1;
    }

    /**
     * Updates the armies of countries in which armies are transferred
     *
     * @param numberOfArmiesTransfer armies user select to transfer
     * @param countrySelected        country which user select transfer from
     * @param neighborSelected       country which user select transfer to
     */
    public void updateArmiesOfCountries(int numberOfArmiesTransfer, Country countrySelected, Country neighborSelected) {
        int idOfCountry = countrySelected.id;
        countrySelected.numOfArmies = countrySelected.numOfArmies - numberOfArmiesTransfer;
        int idOfNeighbor = neighborSelected.id;
        neighborSelected.numOfArmies = neighborSelected.numOfArmies + numberOfArmiesTransfer;
        countries.put(idOfCountry, countrySelected);
        countries.put(idOfNeighbor, neighborSelected);
    }


    /**
     * Saves the country and their neighbouring countries
     * While saving checks the validity of the map (Case : Continent for a country doesnot exist)
     *
     * @param territories List of all the countries along with its neighbouring countries
     */
    public boolean saveCountry(List<String> territories) {
        String country = territories.get(0);

        HashSet<Country> neighbours = new HashSet<>();
        String continent = territories.get(1).trim();
        int continentId = checkContinentExists(continent.trim());
        if (continentId == -1) {
            System.out.println("Invalid map");
            return false;
        } else {
            int countryId = this.checkCountryExists(country);
            if (countryId == -1) {
                countryId = ++countryCounter;
                countries.put(countryCounter, new Country(countryId, country.trim()));
            }
            continents.get(continentId).countries.add(countries.get(countryId));

            for (String territory : territories.subList(2, territories.size())) {
                int neighbourCountryId = this.checkCountryExists(territory.trim());
                if (neighbourCountryId == -1) {
                    neighbourCountryId = ++countryCounter;
                    countries.put(countryCounter, new Country(neighbourCountryId, territory.trim()));

                }
                neighbours.add(countries.get(neighbourCountryId));

                this.saveToGraph(countryId, neighbours);
            }

        }

        return true;
    }

    /**
     * Saves the neighbour related to country in a countryGraph
     *
     * @param countryId  id of a country
     * @param neighbours set of neighbouring countries
     */
    private void saveToGraph(int countryId, HashSet<Country> neighbours) {
        countryGraph.put(countryId, neighbours);
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
     * update the instance with the countryGraph
     *
     * @param map {@link HashMap} of game countryGraph
     */
    public void setMapData(HashMap<Integer, HashSet<Country>> map) {
        this.countryGraph = map;
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

    /**
     * It clears the data of HashMap
     */
    public void clearInformation() {
        continents.clear();
        countries.clear();
        countryGraph.clear();
        players.clear();
        countryCounter = 0;
        continentCounter = 0;
        currentPlayer = null;
    }

    /**
     * Gives the number of armies at a country
     *
     * @param idOfCountry id of country whose army value is needed to know
     * @return number of army at that country
     */
    public int getNumberofArmiesAtCountry(int idOfCountry) {
        int numberOfArmies = countries.get(idOfCountry).numOfArmies;
        return numberOfArmies;

    }


    /**
     * Assign countries randomly to players
     */
    public void assignCountriesToPlayers() {
        int playerId = 1;
        for (Map.Entry<Integer, Country> entry : countries.entrySet()) {
            entry.getValue().owner = players.get(playerId);
            if (playerId != players.size()) {
                playerId++;
            } else {
                playerId = 1;
            }
        }
    }

    /**
     * Assign army to country in startup phase
     * @param countryId id of the country
     * @param numArmies no. of armies
     * */
    public void assignArmyToCountry(int countryId, int numArmies) {
        countries.get(countryId).numOfArmies = numArmies;
    }

    public void setDummyData() {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(Card.TYPE.CAVALRY));
        cards.add(new Card(Card.TYPE.INFANTRY));
        cards.add(new Card(Card.TYPE.ARTILLERY));
        cards.add(new Card(Card.TYPE.CAVALRY));
        cards.add(new Card(Card.TYPE.INFANTRY));
        cards.add(new Card(Card.TYPE.ARTILLERY));
        cards.add(new Card(Card.TYPE.CAVALRY));
        cards.add(new Card(Card.TYPE.INFANTRY));
        Player player1 = new Player(1, "Player 1");
        player1.cards = cards;
        players.put(1, player1);
        Player player2 = new Player(2, "Player 2");
        player2.cards = cards;
        players.put(2, player2);
        Player player3 = new Player(3, "Player 3");
        player3.cards = cards;
        players.put(3, player3);

        continents.put(1, new Continent(1, "Continent1", 5));
        continents.put(2, new Continent(2, "Continent2", 7));

        Country country1 = new Country(1, "Country 1");
        country1.owner = players.get(1);
        //country1.numOfArmies = 50;
        Country country2 = new Country(2, "Country 2");
        country2.owner = players.get(2);
        //country2.numOfArmies = 5;
        Country country3 = new Country(3, "Country 3");
        country3.owner = players.get(3);
       // country3.numOfArmies = 10;
        Country country4 = new Country(4, "Country 4");
        country4.owner = players.get(3);
        //country4.numOfArmies = 40;
        Country country5 = new Country(5, "Country 5");
        country5.owner = players.get(1);
        //country5.numOfArmies = 12;
        Country country6 = new Country(6, "Country 6");
        country6.owner = players.get(2);
       // country6.numOfArmies = 24;
        Country country7 = new Country(7, "Country 7");
        country7.owner = players.get(3);
        //country7.numOfArmies = 10;
        Country country8 = new Country(8, "Country 8");
        country8.owner = players.get(1);
        //country8.numOfArmies = 20;

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


        countryGraph.put(1, n1);
        countryGraph.put(2, n2);
        countryGraph.put(3, n3);
        countryGraph.put(4, n4);
        countryGraph.put(5, n5);
        countryGraph.put(6, n6);
        countryGraph.put(7, n7);
        countryGraph.put(8, n8);


        currentPlayer = players.get(1);

    }


}
