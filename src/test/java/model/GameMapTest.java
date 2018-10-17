package model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class GameMapTest {

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


    @Test
    public void getCountriesOfCurrentPlayer() {
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

}