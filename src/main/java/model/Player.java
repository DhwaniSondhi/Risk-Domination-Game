package model;

import java.util.*;

/**
 * Class containing attributes and functions of a player object
 * extends {@link Observable}
 */
public class Player extends Observable {

    /**
     * store the value of attacking Country
     */
    public Country attackingCountry;
    /**
     * store the value of country being attacked
     */
    public Country attackedCountry;
    /**
     * id for player
     */
    public int id;
    /**
     * Name for player
     */
    public String name;
    /**
     * Cards hold by player
     */
    public ArrayList<Card> cards;

    /**
     * HashMap for countries
     */
    public ArrayList<Country> countries;
    /**
     * Armies on trading card
     */
    public int updateArmiesForCards;

    /**
     * Reinforcement Panel will be updated or not on change
     */
    public boolean updateReinforcementPanel;

    /**
     * Total armies player gets during reinforcement
     */
    public int totalArmies;

    /**
     * Cards not selected by the player yet
     */
    public HashMap<String, Integer> unselectedCards;

    /**
     * Cards selected by the player
     */
    public ArrayList<Card> selectedCards;

    /**
     * dice values of player
     */
    public ArrayList<Integer> diceValuesPlayer;
    /**
     * dice values of opponent
     */
    public ArrayList<Integer> diceValuesOpponent;
    /**
     * the last dice rolled by the player
     */
    public int latestDiceRolled;
    /**
     * number of armies allowed to move if player wins
     */
    public int numArmiesAllowedToMove;
    /**
     * flag to check if player has conquered any country during the attack phase
     */
    public boolean hasConquered;


    /**
     * Constructor
     * <p>
     * Sets up the id, name and selected cards of the player
     * </p>
     *
     * @param id   id of player
     * @param name name of player
     */
    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        cards = new ArrayList<>();
        countries = new ArrayList<>();
        diceValuesPlayer = new ArrayList<>();
        diceValuesOpponent = new ArrayList<>();
        updateArmiesForCards = 5;
        selectedCards = new ArrayList<>();

        initializeWithDummyCards();
        updateReinforcementPanel = true;
    }


    /**
     * Updates the armies of countries in which armies are transferred
     *
     * @param numberOfArmiesTransfer armies user select to transfer
     * @param countrySelected        country which user select transfer from
     * @param neighborSelected       country which user select transfer to
     */
    public void fortify(int numberOfArmiesTransfer, Country countrySelected, Country neighborSelected) {
        countrySelected.numOfArmies = countrySelected.numOfArmies - numberOfArmiesTransfer;
        neighborSelected.numOfArmies = neighborSelected.numOfArmies + numberOfArmiesTransfer;
        System.out.println(countrySelected.numOfArmies);
        System.out.println(neighborSelected.numOfArmies);
        setChanged();
        notifyObservers();
    }

    /**
     * Initializes countries to current player
     *
     * @param country instance of Country
     */
    public void initializeCountryToPlayer(Country country) {
        this.countries.add(country);
        updateView();
    }

    /**
     * Adds 3 random cards to the player.
     */
    private void initializeWithDummyCards() {
        for (int i = 0; i < 3; i++) {
            cards.add(new Card());
        }
    }

    /**
     * Adds random cards
     */
    public void addRandomCard() {
        cards.add(new Card());
    }

    /**
     * To calculate the total armies from the countries and continents own by the player
     *
     * @param countries  list of countries in the instance
     * @param continents list of continents in the instance
     * @return the calculated total armies
     */
    public int getTotalArmies(HashMap<Integer, Country> countries, HashMap<Integer, Continent> continents) {
        int playerCountries = 0;
        for (Map.Entry<Integer, Country> entry : countries.entrySet()) {
            Country country = entry.getValue();
            if (country.owner.id == this.id) {
                playerCountries++;
            }
        }

        int playerContinentsControlVal = 0;
        boolean hasContinent;
        for (Map.Entry<Integer, Continent> entry : continents.entrySet()) {
            hasContinent = true;
            Continent continent = entry.getValue();
            ArrayList<Country> continentCountries = continent.countries;
            for (Country country : continentCountries) {
                if (country.owner.id != this.id) {
                    hasContinent = false;
                    break;
                }
            }
            if (hasContinent) {
                playerContinentsControlVal += continent.controlValue;
            }
        }

        int totalArmLocal = (playerCountries / 3) + playerContinentsControlVal;
        if (totalArmLocal < 3) {
            totalArmLocal = 3;
        }
        return totalArmLocal;
    }

    /**
     * To get the number of INFANTRY, ARTILLERY and CAVALRY cards the player has
     *
     * @return hashmap with values as the number of each INFANTRY, ARTILLERY and CAVALRY cards
     */
    public HashMap<String, Integer> getCardSetsOfPlayer() {
        ArrayList<Card> cards = this.cards;
        HashMap<String, Integer> cardSets = new HashMap<>();
        int infantry = 0;
        int artillery = 0;
        int cavalry = 0;
        for (Card card : cards) {
            if (card.type == Card.TYPE.INFANTRY) {
                infantry++;
            } else if (card.type == Card.TYPE.ARTILLERY) {
                artillery++;
            } else if (card.type == Card.TYPE.CAVALRY) {
                cavalry++;
            }
            cardSets.put("INFANTRY", infantry);
            cardSets.put("ARTILLERY", artillery);
            cardSets.put("CAVALRY", cavalry);
        }
        return cardSets;
    }

    /**
     * To set the list of cards available of a player in the unselectedCards attribute
     */
    public void setUnSelectedCards() {
        unselectedCards = getCardSetsOfPlayer();
        updateView();
    }

    /**
     * To set the total armies getting from getTotalArmies() method in totalArmies attribute
     */
    public void setArmiesForReinforcement() {
        if (totalArmies == 0)
            totalArmies = getTotalArmies(GameMap.getInstance().countries, GameMap.getInstance().continents);
        updateView();
    }

    /**
     * To add the armies to the respective countries on click of Add button
     */
    public void changeArmiesOfCountries(int countryIndex, String armySelected) {
        Country country = countries.get(countryIndex);
        int addedArmy = Integer.parseInt(armySelected);
        country.addArmies(addedArmy);
        totalArmies -= addedArmy;
        updateView();
    }


    /**
     * To set the total armies getting from getTotalArmies() method in totalArmies attribute
     */
    public void addInSelectedCards(String cardName) {
        if (selectedCards.size() < 3) {
            if (unselectedCards.get(cardName) != null) {
                unselectedCards.replace(cardName, unselectedCards.get(cardName) - 1);
            }
            Card card = null;
            if (cardName.equalsIgnoreCase("ARTILLERY")) {
                card = new Card(Card.TYPE.ARTILLERY);
            } else if (cardName.equalsIgnoreCase("INFANTRY")) {
                card = new Card(Card.TYPE.INFANTRY);
            } else if (cardName.equalsIgnoreCase("CAVALRY")) {
                card = new Card(Card.TYPE.CAVALRY);
            }
            if (card != null) {
                selectedCards.add(card);
            }
        }
        updateView();
    }

    /**
     * To reset the selected cards
     */
    public void resetSelectedCards() {
        for (Card card : selectedCards) {
            if (unselectedCards.get(card.type.toString()) != null) {
                unselectedCards.replace(card.type.toString(), unselectedCards.get(card.type.toString()) + 1);
            }
        }
        selectedCards.clear();
        updateView();
    }

    /**
     * To empty the selected cards attribute
     */
    public void emptySelectedCards() {
        selectedCards.clear();
        updateView();
    }

    /**
     * To get the updated total armies when a set of three cards are changed
     *
     * @return the updated armies
     */
    public int getUpdatedArmiesOnCardsExchange() {
        totalArmies += this.updateArmiesForCards;
        this.updateArmiesForCards += 5;
        ArrayList<Card> removeCards = new ArrayList<>();
        for (Card cardSelected : selectedCards) {
            for (Card cardPlayer : this.cards) {
                if (cardPlayer.type == cardSelected.type && !removeCards.contains(cardPlayer)) {
                    removeCards.add(cardPlayer);
                    break;
                }
            }
        }
        for (Card cardRemove : removeCards) {
            this.cards.remove(cardRemove);
        }
        emptySelectedCards();
        return totalArmies;
    }

    public void updateView() {
        setChanged();
        notifyObservers(this);
    }

    /**
     * Get the list of all countries owned by the player
     *
     * @return list of countries
     */
    public List<Country> getCountries() {
        return countries;
    }

    /**
     * Get the list of all countries owned by player which are eligible to attack
     *
     * @return list of countries
     */
    public List<Country> getCountriesAllowedToAttack() {
        ArrayList<Country> countriesAllowedToAttack = new ArrayList<>();
        for (Country country : countries) {
            if (country.getNumberofArmies() > 1) {
                countriesAllowedToAttack.add(country);
            }

        }
        return countriesAllowedToAttack;
    }

    /**
     * Rolls the dice and sorts their values
     *
     * @param playerNumDiceAllowed   number of valid dice rolls allowed for player
     * @param opponentNumDiceAllowed number of valid dice rolls allowed for opponent
     * @param isAllOut               a flag to check if the option is AllOut game mode
     */
    public void rollDice(int playerNumDiceAllowed, int opponentNumDiceAllowed, boolean isAllOut) {
        diceValuesPlayer.clear();
        diceValuesOpponent.clear();
        Random r = new Random();
        for (int i = 0; i < playerNumDiceAllowed; i++) {
            diceValuesPlayer.add(r.nextInt(6) + 1);
        }

        for (int i = 0; i < opponentNumDiceAllowed; i++) {
            diceValuesOpponent.add(r.nextInt(6) + 1);
        }

        Collections.sort(diceValuesPlayer, diceComparator);
        Collections.sort(diceValuesOpponent, diceComparator);
        if (!isAllOut) {
            setChanged();
            notifyObservers();
        }
    }

    /**
     * Check which country won and do the necessary deduction of armies and addition of cards
     *
     * @param selectedCountry             country of the player
     * @param selectedNeighbouringCountry country of the opponent
     */
    private void checkVictory(Country selectedCountry, Country selectedNeighbouringCountry) {
        int numConsideredDice = Math.min(diceValuesPlayer.size(), diceValuesOpponent.size());
        for (int i = 0; i < numConsideredDice; i++) {
            if (diceValuesPlayer.get(i) > diceValuesOpponent.get(i)) {
                selectedNeighbouringCountry.deductArmies(1);
                int noArmies = selectedNeighbouringCountry.getNumberofArmies();
                if (noArmies == 0) {
                    hasConquered = true;
                    latestDiceRolled = diceValuesPlayer.size();
                    numArmiesAllowedToMove = selectedCountry.numOfArmies - 1;
                    attackingCountry = selectedCountry;
                    attackedCountry = selectedNeighbouringCountry;
                    selectedNeighbouringCountry.changeOwner(this);
                    winCards(selectedNeighbouringCountry.owner);
                    setChanged();
                    notifyObservers();
                }
            } else {
                selectedCountry.deductArmies(1);
            }
        }
    }

    /**
     * Attack phase of the game
     *
     * @param selectedCountry             country of the player
     * @param selectedNeighbouringCountry country of the opponent
     * @param isAllOut                    flag to check the mode of the game
     */
    public void attack(Country selectedCountry, Country selectedNeighbouringCountry, boolean isAllOut) {
        if (isAllOut) {
            //selectedCountry.numOfDiceAllowed != 0 || selectedNeighbouringCountry.numOfDiceAllowed != 0
            while (!this.countries.contains(selectedNeighbouringCountry) && selectedCountry.numOfArmies > 1) {
                selectedCountry.updateNumOfDiceAllowed(false);
                selectedNeighbouringCountry.updateNumOfDiceAllowed(true);
                rollDice(selectedCountry.numOfDiceAllowed, selectedNeighbouringCountry.numOfDiceAllowed, isAllOut);
                checkVictory(selectedCountry, selectedNeighbouringCountry);
                attack(selectedCountry, selectedNeighbouringCountry, true);
            }
        } else {
            checkVictory(selectedCountry, selectedNeighbouringCountry);
        }
    }

    /**
     * move cards from previous owner to current player if previous owner has zero countries
     * @param prevOwner previous owner of a country
     * */
    private void winCards(Player prevOwner) {
        if (prevOwner.countries.size() == 0) {

            for (Card card : prevOwner.cards) {
                cards.add(card);
            }
            prevOwner.cards.clear();
        }

    }

    /**
     * if a player has won any country during the attack add random card to the player
     * */
    public void gainCard() {
        if (hasConquered) {
            addRandomCard();
            hasConquered = false;
        }
    }

    /**
     * comparator function for sorting
     * */
    private Comparator<Integer> diceComparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    };
}
