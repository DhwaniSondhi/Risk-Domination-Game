package entity;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameMapTest {

    GameMap gameMap;
    @Before
    public void setUp(){
        gameMap = GameMap.getInstance();
    }

    @After
    public void tearDown(){

    }

    @Test
    public void saveContinent() {
        gameMap.saveContinent("Asia",12);
        gameMap.saveContinent("North America",100);
        Assert.assertEquals(2, gameMap.continents.size());
        Assert.assertTrue(gameMap.continents.containsKey(1));
        Assert.assertTrue(gameMap.continents.containsKey(2));
        Assert.assertFalse(gameMap.saveContinent("Asia",12));
    }

    @Test
    public void getCountriesOfCurrentPlayer() {
    }

    @Test
    public void saveCountry() {

    }
}