package utility;

import model.Continent;
import model.Country;
import model.GameMap;
import model.Node;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

public class MapHelperTest {

    GameMap model;
    HashMap<Integer, model.Node> nodeHashMap;

    /**
     * Setup the country and their respective neighbour in countryGraph
     * Setup continent and their respective countries in countryGraph
     * nodeHashMap contains all the countryNodes(Countries)
     */
    @Before
    public void setUp() throws Exception {
        nodeHashMap = new HashMap<>();
        model = GameMap.getInstance();

        for (int i = 1; i <= 5; i++) {
            Node countryNode = new Node(i);
            Country country = new Country(i, "Country" + i);
            model.countries.put(i, country);
            nodeHashMap.put(i, countryNode);
        }

        model.continents.put(1, new Continent(1, "Asia", 56));
        model.continents.put(2, new Continent(2, "Europe", 50));

        HashSet<Country> n1 = new HashSet<>();
        n1.add(model.countries.get(2));
        n1.add(model.countries.get(4));
        n1.add(model.countries.get(5));
        model.countryGraph.put(1, n1);

        HashSet<Country> n2 = new HashSet<>();
        n2.add(model.countries.get(1));
        n2.add(model.countries.get(5));
        n2.add(model.countries.get(3));
        model.countryGraph.put(2, n2);

        HashSet<Country> n3 = new HashSet<>();
        n2.add(model.countries.get(2));
        model.countryGraph.put(3, n3);

        HashSet<Country> n4 = new HashSet<>();
        n2.add(model.countries.get(1));
        model.countryGraph.put(4, n4);

        HashSet<Country> n5 = new HashSet<>();
        n2.add(model.countries.get(1));
        n2.add(model.countries.get(2));
        model.countryGraph.put(5, n5);

        model.continents.get(1).countries.add(model.countries.get(1));
        model.continents.get(1).countries.add(model.countries.get(2));
        model.continents.get(1).countries.add(model.countries.get(3));
        model.continents.get(2).countries.add(model.countries.get(4));
        model.continents.get(2).countries.add(model.countries.get(5));
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Checks if all the countries are connected
     */
    @Test
    public void bfsCheckConnection() {
        boolean result = MapHelper.bfs(nodeHashMap, nodeHashMap.get(1));
        Assert.assertTrue(result);
    }

    /**
     * Removes the connection from two countries of countryGraph
     * Checks if the map is still connected
     */
    @Test
    public void bfsNotConnected() {
        model.countryGraph.get(3).remove(model.countries.get(2));
        model.countryGraph.get(2).remove(model.countries.get(3));

        boolean result = MapHelper.bfs(nodeHashMap, nodeHashMap.get(1));
        Assert.assertFalse(result);
    }

    /**
     * Checks if the nodeGraph is properly created from given list of countries
     */
    @Test
    public void createNodeGraphFromCountries() {
        HashMap<Integer, Node> nodeGraphFromCountries = MapHelper.createNodeGraphFromCountries(model.countries.values());
        Assert.assertEquals(5, nodeGraphFromCountries.size());
        Assert.assertTrue(nodeGraphFromCountries.containsKey(1));
        Assert.assertTrue(nodeGraphFromCountries.containsKey(2));
        Assert.assertTrue(nodeGraphFromCountries.containsKey(3));
        Assert.assertTrue(nodeGraphFromCountries.containsKey(4));
        Assert.assertTrue(nodeGraphFromCountries.containsKey(5));
        Assert.assertFalse(nodeGraphFromCountries.containsKey(0));

    }

    /**
     * Checks if all the countries inside a continent is not connected
     */
    @Test
    public void bfsNotConnectedInContinent() {
        HashMap<Integer, Node> nodeGraphFromCountries = MapHelper.createNodeGraphFromCountries(model.continents.get(2).countries);
        boolean result = MapHelper.bfs(nodeGraphFromCountries, nodeGraphFromCountries.get(5));
        Assert.assertFalse(result);
    }

    /**
     * Checks if all the countries inside the continent are connected
     */
    @Test
    public void bfsConnectedInContinent() {
        HashMap<Integer, Node> nodeGraphFromCountries = MapHelper.createNodeGraphFromCountries(model.continents.get(1).countries);
        boolean result = MapHelper.bfs(nodeGraphFromCountries, nodeGraphFromCountries.get(1));
        Assert.assertTrue(result);
    }


}