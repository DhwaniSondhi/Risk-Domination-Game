package model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

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
     * */
    @Test
    public void getCountriesAllowedToAttack() {
        Assert.assertEquals(3, gameMap.currentPlayer.getCountriesAllowedToAttack().size());
    }

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

    @Test
    public void gainCard() {
    }

    @Test
    public void checkVictory(){

    }

    @Test
    public void winCards(){

    }
}