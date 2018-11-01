package model;

import controller.FortifyController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import view.FortifyPanel;

import java.util.HashMap;

import static org.junit.Assert.*;

public class CountryTest {
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


    @Test
    public void updateConnectedCountries() {
        gameMap.countries.get(1).updateConnectedCountries();
        HashMap<Integer, Country> neighbor = gameMap.countries.get(1).connectedCountries;
        Assert.assertFalse(neighbor.isEmpty());
        Assert.assertEquals(1, neighbor.size());
        Assert.assertNotNull(neighbor.get(5));
        Assert.assertEquals(5, neighbor.get(5).id);
        Assert.assertEquals("Country 5", neighbor.get(5).name);
        Assert.assertEquals(1, neighbor.get(5).owner.id);
    }
}