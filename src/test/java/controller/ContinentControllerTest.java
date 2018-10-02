package controller;

import entity.GameMap;
import gui.ContinentPanel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

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

    @Test
    public void updateContinentList() throws Exception {
        controller.updateContinentList();
        Mockito.verify(view).updateContinentList(config.continents.values());
    }
}
