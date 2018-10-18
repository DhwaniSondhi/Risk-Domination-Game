package controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Test class for MapCreatorController
 */
public class MapCreatorControllerTest {

    MapCreatorController controller;

    List<String> continentItems, countryItems;
    List<String> invalidContinentItems, invalidCountryItems;

    @Before
    public void setUp() throws Exception {
        controller = new MapCreatorController(null);

        continentItems = Arrays.asList("Asia=5", "Europe=6");
        countryItems = Arrays.asList("Nepal, Asia, India, China, Spain",
                "India, Asia, Nepal, China",
                "China, Asia, India, Nepal",
                "France, Europe, Italy",
                "Italy, Europe, France, Spain",
                "Spain, Europe, Italy, Nepal");

        invalidContinentItems = Arrays.asList("Asia=5", "Europe=6");
        invalidCountryItems = Arrays.asList("Nepal, Asia, India, China",
                "India, Asia,Nepal,China",
                "China,Asia,India,Nepal",
                "France,Europe,Italy",
                "Italy, Europe,France,Spain",
                "Spain,Europe, Italy");
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Checks if the validateFormData return true for valid data
     */
    @Test
    public void checkValidFormData() throws Exception {
        boolean errorOccurred = false;
        try {
            controller.validateFormData(continentItems, countryItems);
        } catch (Exception e) {
            errorOccurred = true;
        } finally {
            Assert.assertFalse(errorOccurred);
        }
    }

    /**
     * Checks if the validateFormData return false for invalid data
     * Check for error message for not connected graph
     */
    @Test
    public void checkInvalidFormData() throws Exception {
        try {
            controller.validateFormData(invalidContinentItems, invalidCountryItems);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IllegalStateException);
            Assert.assertEquals("Map could not be verified as connected graph / sub-graph", e.getMessage());
        }

    }
}