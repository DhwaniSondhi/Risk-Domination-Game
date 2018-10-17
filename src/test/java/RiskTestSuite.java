import controller.*;
import model.GameMapTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import utility.FileHelperTest;
import utility.MapHelperTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ContinentControllerTest.class,
        CountryControllerTest.class,
        FortifyControllerTest.class,
        MapCreatorControllerTest.class,
        ReinforcementControllerTest.class,
        GameMapTest.class,
        FileHelperTest.class,
        MapHelperTest.class
})
public class RiskTestSuite {
}
