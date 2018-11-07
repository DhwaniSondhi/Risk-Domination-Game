import controller.ContinentControllerTest;
import controller.CountryControllerTest;
import controller.MapCreatorControllerTest;
import model.CountryTest;
import model.GameMapTest;
import model.PlayerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import utility.FileHelperTest;
import utility.MapHelperTest;


/**
 * Test suite with all the test classes
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ContinentControllerTest.class,
        CountryControllerTest.class,
        CountryTest.class,
        PlayerTest.class,
        MapCreatorControllerTest.class,
        GameMapTest.class,
        FileHelperTest.class,
        MapHelperTest.class
})
public class RiskTestSuite {
}
