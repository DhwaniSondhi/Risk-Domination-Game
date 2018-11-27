package model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class GameMapTest {
    /**
     * Reference for GameMap Class
     */
    GameMap gameMap;

    @Before
    public void setUp() {
        gameMap = GameMap.getInstance();
        gameMap.clearInformation();
    }

    @After
    public void tearDown() {

    }

    /**
     * checks if the size of the continent is correct
     * when inserting empty value
     */
    @Test
    public void saveContinent() {
        gameMap.saveContinent("", 0);
        gameMap.saveContinent("Asia", 12);
        gameMap.saveContinent("North America", 100);
        Assert.assertEquals(2, gameMap.continents.size());


    }

    /**
     * checks if the continent contains key and name
     */
    @Test
    public void saveContinentCheckName() {
        gameMap.saveContinent("", 0);
        gameMap.saveContinent("Asia", 12);
        gameMap.saveContinent("North America", 100);
        Assert.assertTrue(gameMap.continents.containsKey(1));
        Assert.assertTrue(gameMap.continents.containsKey(2));
        Assert.assertEquals("Asia", gameMap.continents.get(1).name);
        Assert.assertEquals("North America", gameMap.continents.get(2).name);
    }

    /**
     * checks if the continent contains the correct continent value
     */
    @Test
    public void saveContinentCheckCV() {
        gameMap.saveContinent("", 0);
        gameMap.saveContinent("Asia", 12);
        gameMap.saveContinent("North America", 100);
        Assert.assertTrue(gameMap.continents.containsKey(1));
        Assert.assertTrue(gameMap.continents.containsKey(2));
        Assert.assertEquals(100, gameMap.continents.get(2).controlValue);
    }

    /**
     * checks if the continent contains duplicate name
     * if duplicate then do not increase size of continents
     */
    @Test
    public void saveContinentDuplicateContinent() {
        gameMap.saveContinent("", 0);
        gameMap.saveContinent("Asia", 12);
        gameMap.saveContinent("North America", 100);
        gameMap.saveContinent("Asia", 100);
        Assert.assertEquals(2, gameMap.continents.size());
        Assert.assertEquals(12, gameMap.continents.get(1).controlValue);
        Assert.assertNull(gameMap.continents.get(3));
    }

    /**
     * Check if the country is being added to gameMap by checking the size
     */
    @Test
    public void saveCountry() {
        gameMap.saveContinent("", 0);
        gameMap.saveContinent("Asia", 12);
        gameMap.saveContinent("North America", 100);
        String[] territories = new String[]{"Nepal", "Asia", "India", "China"};
        gameMap.saveCountry(Arrays.asList(territories));
        Assert.assertEquals(3, gameMap.countries.size());

    }

    /**
     * Check if the country is being added to gameMap by checking the size
     * Validate before inserting inside map
     */
    @Test
    public void saveCountryWithoutContinent() {
        gameMap.saveContinent("", 0);
        gameMap.saveContinent("Asia", 12);
        gameMap.saveContinent("North America", 100);
        String[] territories = new String[]{"Nepal", "Europe", "India", "China"};
        gameMap.saveCountry(Arrays.asList(territories));
        Assert.assertFalse(gameMap.continents.entrySet().contains("Europe"));
        Assert.assertEquals(0, gameMap.countries.size());

    }

    /**
     * test for the number of armies of player calculation
     */
    @Test
    public void getNumberOfArmiesOwnedByPlayers() {
        gameMap.setDummyData();
        List<Integer> armies = gameMap.getNumberOfArmiesOwnedByPlayers();
        Assert.assertEquals(3, armies.size());
        Assert.assertEquals(Integer.valueOf(27), armies.get(0));
        Assert.assertEquals(Integer.valueOf(22), armies.get(1));
        Assert.assertEquals(Integer.valueOf(71), armies.get(2));
    }

    /**
     * test for the number of countries of player calculation
     */
    @Test
    public void getNumberOfCountriesOwnedByPlayers() {
        gameMap.setDummyData();
        List<Integer> countries = gameMap.getNumberOfCountriesOwnedByPlayers();
        Assert.assertEquals(3, countries.size());
        Assert.assertEquals(Integer.valueOf(3), countries.get(0));
        Assert.assertEquals(Integer.valueOf(2), countries.get(1));
        Assert.assertEquals(Integer.valueOf(3), countries.get(2));
    }

    /**
     * test for the number of armies of player calculation
     */

    @Test
    public void getNumberOfContinentsOwnedByPlayers() {
        gameMap.setDummyData();
        List<Integer> data = gameMap.getNumberOfContinentsOwnedByPlayers();
        Assert.assertEquals(3, data.size());
        Assert.assertEquals(Integer.valueOf(2), data.get(0));
        Assert.assertEquals(Integer.valueOf(2), data.get(1));
        Assert.assertEquals(Integer.valueOf(2), data.get(2));
    }


    @Test
    public void checkGameEnd() {
        gameMap.setDummyData();
        for (Country country : gameMap.countries.values()) {
            country.changeOwner(gameMap.currentPlayer);
        }
        gameMap.checkGameEnd();
        Assert.assertTrue(gameMap.gameEnded);
    }

    @Test
    public void changePhase() {
        gameMap.changePhase(GameMap.Phase.FORTIFY);
        Assert.assertEquals(GameMap.Phase.FORTIFY, gameMap.currentPhase);
    }

    @Test
    public void updateArmiesOfCountries() {
        gameMap.setDummyData();
        int numberArmiesToTransfer = 2;
        gameMap.updateArmiesOfCountries(numberArmiesToTransfer, gameMap.countries.get(1), gameMap.countries.get(2));
        Assert.assertEquals(gameMap.countries.get(1).numOfArmies, 8);
        Assert.assertEquals(gameMap.countries.get(2).numOfArmies, 22);
    }
}