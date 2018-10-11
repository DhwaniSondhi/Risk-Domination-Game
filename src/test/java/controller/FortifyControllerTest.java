package controller;

import entity.Country;
import entity.GameMap;
import gui.FortifyPanel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;

/**
 * This is test class for functions of FortifyController
 */
public class FortifyControllerTest {
    GameMap gameMap;
    FortifyController fortifyController;

    /**
     * This sets up dummy data by calling setDummyData function in GameMap class
     *
     * @throws Exception throws exception
     */
    @Before
    public void setUp() throws Exception {
        gameMap = GameMap.getInstance();
        fortifyController = new FortifyController(new FortifyPanel());
        gameMap.setDummyData();


    }


    @Test
    public void getArmiesOfSelectedNeighbor() {


    }

    /**
     * It test the function for getting neighboring countries from selected country.
     */
    @Test
    public void getNeighborsOfCountry() {
        LinkedHashMap<Integer, Country> neighbor = fortifyController.getNeighborsOfCountry(gameMap.countries.get(1));
        Assert.assertFalse(neighbor.isEmpty());
        Assert.assertEquals(1, neighbor.size());
        Assert.assertNotNull(neighbor.get(5));
        Assert.assertEquals(5, neighbor.get(5).id);
        Assert.assertEquals("Country 5", neighbor.get(5).name);
        Assert.assertEquals(1, neighbor.get(5).owner.id);
    }
}