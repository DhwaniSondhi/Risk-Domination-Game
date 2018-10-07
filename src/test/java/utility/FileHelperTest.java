package utility;

import entity.GameMap;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class FileHelperTest {

    File testFile;

    @Before
    public void setUp() throws Exception {
        testFile = new File("maps/test.map");
    }

    @After
    public void tearDown() throws Exception {
        testFile = null;
    }

    /**
     * Check if the size of continent from the given map file is correct
     * Check if the size of country from the given map file is correct
     * Check if the no. of neighbours for a country is correct
     * */
    @Test
    public void loadToConfig() {
        FileHelper.loadToConfig(testFile);
        Assert.assertEquals(1, GameMap.getInstance().continents.size());
        Assert.assertEquals(3, GameMap.getInstance().countries.size());
        Assert.assertEquals(2, GameMap.getInstance().countryGraph.get(1).size());

//        if(selectedFile.is)
    }
}