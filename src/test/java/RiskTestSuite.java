import controller.ContinentControllerTest;
import controller.CountryControllerTest;
import controller.FortifyControllerTest;
import controller.MapCreatorControllerTest;
import model.GameMapTest;
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
        FortifyControllerTest.class,
        MapCreatorControllerTest.class,/*
        ReinforcementControllerTest.class,*/
        GameMapTest.class,
        FileHelperTest.class,
        MapHelperTest.class
})
public class RiskTestSuite {
}
