package controller;

import model.GameMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import view.ContinentPanel;

/**
 * Test class for ContinentController
 */
public class ContinentControllerTest {

    @Mock
    ContinentPanel view;

    ContinentController controller;
    GameMap config;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new ContinentController(view);
        config = GameMap.getInstance();
        config.setDummyData();
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * check if view method is called or not from controller method
     */
    @Test
    public void updateContinentList() throws Exception {
        controller.updateContinentList();
        Mockito.verify(view).updateContinentList(config.continents.values());
    }
}
