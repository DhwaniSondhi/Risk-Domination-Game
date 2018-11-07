package model;

import utility.FileHelper;

import java.util.*;


/**
 * Singleton class containing access to all the game components
 */
public class GameMap extends Observable {
    /**
     * Instance of GameMap class
     */
    private static GameMap INSTANCE = null;
    /**
     * Counter for continent
     */
    private static int continentCounter;
    /**
     * Counter for country
     */
    private static int countryCounter;
    /**
     * Variable for currently playing player
     */
    public Player currentPlayer = null;
    /**
     * HashMap for countries
     */
    public HashMap<Integer, Country> countries;
    /**
     * HashMap for continents
     */
    public HashMap<Integer, Continent> continents;
    /**
     * HashMap for players
     */
    public HashMap<Integer, Player> players;
    /**
     * HashMap for relationship between country and adjacent countries
     */
    public HashMap<Integer, HashSet<Country>> countryGraph;

    /**
     * flag to check if game ended
     */
    public boolean gameEnded;

    public enum Phase {
        /**
         * Enum values
         */
        STARTUP("StartUp Phase"),
        REINFORCE("Reinforcement Phase"),
        ATTACK("Attack Phase"),
        FORTIFY("Fortify Phase");
        /**
         * enum name
         */
        String name;

        /**
         * @param name name value for the enum
         */
        Phase(final String name) {
            this.name = name;
        }


        /**
         * Returns the name of this enum constant, as contained in the
         * declaration.
         *
         * @return the name of this enum constant
         */
        @Override
        public String toString() {
            return name;
        }
    }

    /**
     * current phase
     */
    public Phase currentPhase = Phase.STARTUP;
    /**
     * stores the recent move made in the game
     */
    public String recentMove;


    /**
     * Initialize countries, continents, players, countryGraph
     * Initialize counter continentCounter, countryCounter
     */
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
    public void saveContinent(String continent, int continentValue) {
        if (checkContinentExists(continent.trim()) == -1 && !continent.trim().isEmpty()) {
            continentCounter++;
            continents.put(continentCounter, new Continent(continentCounter, continent.trim(), continentValue));
        }
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
        countrySelected.deductArmies(numberOfArmiesTransfer);
        int idOfNeighbor = neighborSelected.id;
        neighborSelected.addArmies(numberOfArmiesTransfer);
        countries.put(idOfCountry, countrySelected);
        countries.put(idOfNeighbor, neighborSelected);
    }


    /**
     * Saves the country and their neighbouring countries
     * While saving checks the validity of the map (Case : Continent for a country doesnot exist)
     *
     * @param territories List of all the countries along with its neighbouring countries
     * @return true if country is added to model else false
     */
    public boolean saveCountry(List<String> territories) {
        String country = territories.get(0);

        HashSet<Country> neighbours = new HashSet<>();
        String continent = territories.get(1).trim();
        int continentId = checkContinentExists(continent.trim());
        if (continentId == -1) {
            return false;
        } else {
            int countryId = this.checkCountryExists(country);

            countryId = this.insertCountry(countryId, country.trim());
            continents.get(continentId).countries.add(countries.get(countryId));

            for (String territory : territories.subList(2, territories.size())) {
                int neighbourCountryId = this.checkCountryExists(territory.trim());

                neighbourCountryId = this.insertCountry(neighbourCountryId, territory.trim());
                neighbours.add(countries.get(neighbourCountryId));
                this.saveToGraph(countryId, neighbours);
                countries.get(countryId).neighbours = neighbours;
            }

        }

        return true;
    }

    /**
     * inserts to country hashmap if the country doesnot exist
     *
     * @param countryId id of the country
     * @param country   name of the country
     */
    private int insertCountry(int countryId, String country) {
        if (countryId == -1) {
            countryId = ++countryCounter;
            countries.put(countryCounter, new Country(countryId, country));
        }
        return countryId;
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
     * Get number of countriesOwned by every Players
     *
     * @return List with number of countries owned
     **/
    public List<Integer> getNumberOfCountriesOwnedByPlayers() {
        List<Integer> information = new ArrayList<>();
        for (Player player : players.values()) {
            information.add(player.countries.size());
        }
        return information;
    }

    /**
     * Get number of armies owned by every Players
     *
     * @return List with number of armies owned
     **/
    public List<Integer> getNumberOfArmiesOwnedByPlayers() {
        List<Integer> information = new ArrayList<>();
        for (Player player : players.values()) {
            information.add(player.getTotalArmies());
        }
        return information;
    }

    /**
     * Get number of continentsOwned by every Players
     *
     * @return List with number of continents owned
     **/
    public List<Integer> getNumberOfContinentsOwnedByPlayers() {
        List<Integer> information = new ArrayList<>();
        for (Player player : players.values()) {
            int count = 0;
            for (Continent continent : continents.values()) {
                if (continent.isOwnedBy(player)) count++;
            }
            information.add(count);
        }
        return information;
    }

    /**
     * Assign countries randomly to players
     */
    public void assignCountriesToPlayers() {
        resetCurrentPlayer();
        for (Map.Entry<Integer, Country> entry : countries.entrySet()) {
            entry.getValue().owner = currentPlayer;

            entry.getValue().numOfArmies = 0;
            currentPlayer.initializeCountryToPlayer(entry.getValue());
            changeToNextPlayer(false);
        }
        setChanged();
        notifyObservers();
    }

    /**
     * Change the current player to next player in round robin fashion
     *
     * @param checkIfLost skips player with 0 countries if set to true
     */
    public void changeToNextPlayer(boolean checkIfLost) {
        if (currentPlayer == null)
            currentPlayer = players.get(1);
        else {
            int next = currentPlayer.id + 1;
            if (next > players.size())
                next = 1;

            currentPlayer = players.get(next);
            if (checkIfLost && currentPlayer.countries.size() == 0) changeToNextPlayer(checkIfLost);
        }
        setChanged();
        notifyObservers();
    }

    /**
     * resets the current player to first player
     */
    public void resetCurrentPlayer() {
        if (!players.isEmpty()) {
            currentPlayer = players.get(1);
        }
        setChanged();
        notifyObservers();
    }




    /**
     * provides initial number of army based on number of players
     *
     * @return number of armies
     */
    public int getInitialArmy() {
        int[] armyCount = new int[]{40, 35, 30, 25, 20};
        if (!players.isEmpty())
            return armyCount[players.size() - 2];

        return 0;
    }

    /**
     * changes the current phase and notifies observers
     *
     * @param phase new phase
     */
    public void changePhase(Phase phase) {
        this.currentPhase = phase;
        setChanged();
        notifyObservers();
    }

    /**
     * changes the recent move and notifies the observer
     *
     * @param move new move
     */
    public void setRecentMove(String move) {
        recentMove = move;
        setChanged();
        notifyObservers();

        FileHelper.writeLog(recentMove);
        recentMove = null;
    }

    /**
     * notifies all the observers for the new changes
     */
    public void notifyChanges() {
        setChanged();
        notifyObservers();
    }

    /**
     * A check to see if the current player has conquered all the countries
     */
    public void checkGameEnd() {
        if (currentPlayer.countries.size() == countries.size()) {
            setRecentMove("Game Over: " + currentPlayer + " wins the game.");
            FileHelper.writeLog("========================= Game Over ========================== \n\n\n\n\n");
            gameEnded = true;
        } else {
            gameEnded = false;
        }
        setChanged();
        notifyChanges();
    }

    /**
     * adds dummy models for test cases
     */
    public void setDummyData() {
        Player player1 = new Player(1, "Player 1");
        players.put(1, player1);
        Player player2 = new Player(2, "Player 2");
        players.put(2, player2);
        Player player3 = new Player(3, "Player 3");
        players.put(3, player3);

        continents.put(1, new Continent(1, "Continent1", 5));
        continents.put(2, new Continent(2, "Continent2", 7));

        Country country1 = new Country(1, "Country 1");
        country1.owner = players.get(1);
        player1.initializeCountryToPlayer(country1);
        country1.numOfArmies = 10;
        Country country2 = new Country(2, "Country 2");
        country2.owner = players.get(2);
        player2.initializeCountryToPlayer(country2);
        country2.numOfArmies = 20;
        Country country3 = new Country(3, "Country 3");
        country3.owner = players.get(3);
        player3.initializeCountryToPlayer(country3);
        country3.numOfArmies = 40;
        Country country4 = new Country(4, "Country 4");
        country4.owner = players.get(3);
        player3.initializeCountryToPlayer(country4);
        country4.numOfArmies = 30;
        Country country5 = new Country(5, "Country 5");
        country5.owner = players.get(1);
        player1.initializeCountryToPlayer(country5);
        country5.numOfArmies = 3;
        Country country6 = new Country(6, "Country 6");
        country6.owner = players.get(2);
        player2.initializeCountryToPlayer(country6);
        country6.numOfArmies = 2;
        Country country7 = new Country(7, "Country 7");
        country7.owner = players.get(3);
        player3.initializeCountryToPlayer(country7);
        country7.numOfArmies = 1;
        Country country8 = new Country(8, "Country 8");
        country8.owner = players.get(1);
        player1.initializeCountryToPlayer(country8);
        country8.numOfArmies = 14;

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
        country1.neighbours = n1;
        countryGraph.put(2, n2);
        country2.neighbours = n2;
        countryGraph.put(3, n3);
        country3.neighbours = n3;
        countryGraph.put(4, n4);
        country4.neighbours = n4;
        countryGraph.put(5, n5);
        country5.neighbours = n5;
        countryGraph.put(6, n6);
        country6.neighbours = n6;
        countryGraph.put(7, n7);
        country7.neighbours = n7;
        countryGraph.put(8, n8);
        country8.neighbours = n8;


        currentPlayer = players.get(1);
    }
}
