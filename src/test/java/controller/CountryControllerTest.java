package controller;

import entity.GameMap;
import gui.CountryPanel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class CountryControllerTest {
    @Mock
    CountryPanel view;

    CountryController controller;
    GameMap config;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new CountryController(view);
        config = GameMap.getInstance();
        config.setDummyData();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void updateCountries() throws Exception {
        controller.updateCountryList();
        Mockito.verify(view).updateCountries(config.countries);
    }
}
