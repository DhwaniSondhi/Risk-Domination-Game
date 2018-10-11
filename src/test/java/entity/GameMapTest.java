package entity;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameMapTest {

    GameMap gameMap;

    @Before
    public void setUp() {
        gameMap = GameMap.getInstance();
        gameMap.setDummyData();
    }

    @After
    public void tearDown() {

    }

    @Test
    public void saveContinent() {
        gameMap.saveContinent("Asia", 12);
        gameMap.saveContinent("North America", 100);
        Assert.assertEquals(2, gameMap.continents.size());
        Assert.assertTrue(gameMap.continents.containsKey(1));
        Assert.assertTrue(gameMap.continents.containsKey(2));
        Assert.assertFalse(gameMap.saveContinent("Asia", 12));
    }

    @Test
    public void getCountriesOfCurrentPlayer() {
    }

    @Test
    public void saveCountry() {

    }

    /**
     * Test case that checks whether operation of transfer of army is carried out accurately or not.
     */
    @Test
    public void updateArmiesOfCountries() {
        gameMap.updateArmiesOfCountries(3, gameMap.countries.get(1), gameMap.countries.get(5));
        Assert.assertEquals(7, gameMap.countries.get(1).numOfArmies);
        Assert.assertEquals(13, gameMap.countries.get(5).numOfArmies);
    }

    @Test
    public void getCountriesOfCurrentPlayer1() {

    }
}