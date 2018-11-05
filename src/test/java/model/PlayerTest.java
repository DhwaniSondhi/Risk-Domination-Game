package model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;

public class PlayerTest {

    /**
     * Instance of GameMap Class
     */
    GameMap gameMap;

    /**
     * This sets up dummy data by calling setDummyData function in GameMap class
     *
     * @throws Exception throws exception
     */
    @Before
    public void setUp() throws Exception {
        gameMap = GameMap.getInstance();
        gameMap.setDummyData();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void fortify() {
    }

    @Test
    public void initializeCountryToPlayer() {
    }

    @Test
    public void addRandomCard() {
    }

    @Test
    public void getTotalArmies() {
    }

    @Test
    public void getCardSetsOfPlayer() {
    }

    @Test
    public void setUnSelectedCards() {
    }

    @Test
    public void setArmiesForReinforcement() {
    }

    @Test
    public void changeArmiesOfCountries() {
    }

    @Test
    public void addInSelectedCards() {
    }

    @Test
    public void resetSelectedCards() {
    }

    @Test
    public void emptySelectedCards() {
    }

    @Test
    public void getUpdatedArmiesOnCardsExchange() {
    }

    /**
     * check the size of the countries owned by the current player which are eligible to attack
     */
    @Test
    public void getCountriesAllowedToAttack() {
        Assert.assertEquals(3, gameMap.currentPlayer.getCountriesAllowedToAttack().size());
    }

    /**
     * For allout mode
     * <p>
     * gets the number of dice allowed for player and opponent
     * calls the function rollDice
     * Check the number of diceValues rolled during the process
     */
    @Test
    public void rollDiceAllOut() {
        int playerNumOfDiceAllowed;
        int opponentNumOfDiceAllowed;
        boolean isAllOut = true;

        gameMap.countries.get(1).updateNumOfDiceAllowed(false);
        gameMap.countries.get(2).updateNumOfDiceAllowed(true);
        playerNumOfDiceAllowed = gameMap.countries.get(1).numOfDiceAllowed;
        opponentNumOfDiceAllowed = gameMap.countries.get(2).numOfDiceAllowed;
        gameMap.currentPlayer.rollDice(playerNumOfDiceAllowed, opponentNumOfDiceAllowed, isAllOut);
        Assert.assertEquals(3, gameMap.currentPlayer.diceValuesPlayer.size());
        Assert.assertEquals(2, gameMap.currentPlayer.diceValuesOpponent.size());

        gameMap.countries.get(1).updateNumOfDiceAllowed(true);
        gameMap.countries.get(2).updateNumOfDiceAllowed(false);
        playerNumOfDiceAllowed = gameMap.countries.get(2).numOfDiceAllowed;
        opponentNumOfDiceAllowed = gameMap.countries.get(1).numOfDiceAllowed;
        gameMap.currentPlayer.rollDice(playerNumOfDiceAllowed, opponentNumOfDiceAllowed, isAllOut);
        Assert.assertEquals(3, gameMap.currentPlayer.diceValuesPlayer.size());
        Assert.assertEquals(2, gameMap.currentPlayer.diceValuesOpponent.size());

        gameMap.countries.get(1).updateNumOfDiceAllowed(true);
        gameMap.countries.get(7).updateNumOfDiceAllowed(false);
        playerNumOfDiceAllowed = gameMap.countries.get(7).numOfDiceAllowed;
        opponentNumOfDiceAllowed = gameMap.countries.get(1).numOfDiceAllowed;
        gameMap.currentPlayer.rollDice(playerNumOfDiceAllowed, opponentNumOfDiceAllowed, isAllOut);
        Assert.assertEquals(0, gameMap.currentPlayer.diceValuesPlayer.size());
        Assert.assertEquals(2, gameMap.currentPlayer.diceValuesOpponent.size());

        gameMap.countries.get(7).updateNumOfDiceAllowed(true);
        gameMap.countries.get(1).updateNumOfDiceAllowed(false);
        playerNumOfDiceAllowed = gameMap.countries.get(1).numOfDiceAllowed;
        opponentNumOfDiceAllowed = gameMap.countries.get(7).numOfDiceAllowed;
        gameMap.currentPlayer.rollDice(playerNumOfDiceAllowed, opponentNumOfDiceAllowed, isAllOut);
        Assert.assertEquals(3, gameMap.currentPlayer.diceValuesPlayer.size());
        Assert.assertEquals(1, gameMap.currentPlayer.diceValuesOpponent.size());
    }

    /**
     * For normal mode
     * <p>
     * gets the number of dice allowed for player and opponent
     * calls the function rollDice
     * Check the number of diceValues rolled during the process
     */
    @Test
    public void rollDice() {
        boolean isAllOut = false;

        gameMap.currentPlayer.rollDice(3, 2, isAllOut);
        Assert.assertEquals(3, gameMap.currentPlayer.diceValuesPlayer.size());
        Assert.assertEquals(2, gameMap.currentPlayer.diceValuesOpponent.size());


        gameMap.currentPlayer.rollDice(3, 1, isAllOut);
        Assert.assertEquals(3, gameMap.currentPlayer.diceValuesPlayer.size());
        Assert.assertEquals(1, gameMap.currentPlayer.diceValuesOpponent.size());

        gameMap.currentPlayer.rollDice(2, 2, isAllOut);
        Assert.assertEquals(2, gameMap.currentPlayer.diceValuesPlayer.size());
        Assert.assertEquals(2, gameMap.currentPlayer.diceValuesOpponent.size());

        gameMap.currentPlayer.rollDice(2, 1, isAllOut);
        Assert.assertEquals(2, gameMap.currentPlayer.diceValuesPlayer.size());
        Assert.assertEquals(1, gameMap.currentPlayer.diceValuesOpponent.size());

        gameMap.currentPlayer.rollDice(1, 2, isAllOut);
        Assert.assertEquals(1, gameMap.currentPlayer.diceValuesPlayer.size());
        Assert.assertEquals(2, gameMap.currentPlayer.diceValuesOpponent.size());

        gameMap.currentPlayer.rollDice(1, 1, isAllOut);
        Assert.assertEquals(1, gameMap.currentPlayer.diceValuesPlayer.size());
        Assert.assertEquals(1, gameMap.currentPlayer.diceValuesOpponent.size());
    }

    /**
     * Given a selected country and selected neighbour country
     * Check the diceValues of player and opponent
     * check the num of dice to be considered
     * Check if the correct number of armies has been deducted for player and opponent
     */
    @Test
    public void checkVictory() {
        List<Country> countriesAllowedToAttack = gameMap.currentPlayer.getCountriesAllowedToAttack();
        Country selectedCountry = countriesAllowedToAttack.get(1);
        Country selectedNeighbour = gameMap.countries.get(6);

        int[] diceValuesP = {6, 5, 1};
        for (int diceValue : diceValuesP) {
            gameMap.currentPlayer.diceValuesPlayer.add(diceValue);
        }
        int[] diceValuesO = {6, 1};
        for (int diceValue : diceValuesO) {
            gameMap.currentPlayer.diceValuesOpponent.add(diceValue);
        }

        int numConsideredDice = Math.min(gameMap.currentPlayer.diceValuesPlayer.size(), gameMap.currentPlayer.diceValuesOpponent.size());

        Assert.assertEquals(2, numConsideredDice);

        gameMap.currentPlayer.checkVictory(selectedCountry, selectedNeighbour);
        Assert.assertEquals(2, selectedCountry.numOfArmies);
        Assert.assertEquals(1, selectedNeighbour.numOfArmies);

    }

    /**
     * Given a selected country and selected neighbour country
     * Check the diceValues of player and opponent
     * check the num of dice to be considered
     * Check if the correct number of armies has been deducted for player and opponent
     * Check if owner changes when country is captured by another player
     */
    @Test
    public void checkVictoryOwnerChanged() {
        Country selectedCountry = gameMap.countries.get(8);
        HashSet<Country> neighboursOfSelectedCountry = selectedCountry.getNeighbours();
        Country selectedNeighbour = null;
        for (Country neighbour : neighboursOfSelectedCountry) {
            selectedNeighbour = neighbour;

        }

        int[] diceValuesP = {6, 5, 1};
        for (int diceValue : diceValuesP) {
            gameMap.currentPlayer.diceValuesPlayer.add(diceValue);
        }
        int[] diceValuesO = {1};
        for (int diceValue : diceValuesO) {
            gameMap.currentPlayer.diceValuesOpponent.add(diceValue);
        }

        int numDiceAllowed = Math.min(gameMap.currentPlayer.diceValuesPlayer.size(), gameMap.currentPlayer.diceValuesOpponent.size());

        Assert.assertEquals(1, numDiceAllowed);

        gameMap.currentPlayer.checkVictory(selectedCountry, selectedNeighbour);
        Assert.assertEquals(14, selectedCountry.numOfArmies);
        Assert.assertEquals(0, selectedNeighbour.numOfArmies);
        Assert.assertEquals(selectedCountry.owner, selectedNeighbour.owner);

    }

    /**
     * Check the number of cards when a player wins countries in a round
     */
    @Test
    public void gainCard() {
        Assert.assertEquals(0, gameMap.currentPlayer.cards.size());
        gameMap.currentPlayer.hasConquered = true;
        gameMap.currentPlayer.gainCard();
        Assert.assertEquals(1, gameMap.currentPlayer.cards.size());
    }

    /**
     * check if the cards are transfered to another player if all the countries of player is captured
     * */
    @Test
    public void winCards() {
        gameMap.players.get(3).addRandomCard();
        gameMap.players.get(3).addRandomCard();
        gameMap.players.get(3).addRandomCard();
        for (Country country : gameMap.players.get(3).countries) {
            gameMap.currentPlayer.countries.add(country);
        }
        gameMap.players.get(3).countries.clear();
        gameMap.currentPlayer.winCards(gameMap.players.get(3));
        Assert.assertEquals(0, gameMap.players.get(3).cards.size());
        Assert.assertEquals(3, gameMap.currentPlayer.cards.size());
    }
}