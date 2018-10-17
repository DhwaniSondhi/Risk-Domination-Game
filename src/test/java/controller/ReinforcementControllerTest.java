package controller;

import model.Continent;
import model.Country;
import model.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ReinforcementControllerTest {

    HashMap<Integer, Continent> continents;
    HashMap<Integer, Country> countries;
    ArrayList<Country> countriesInContinent;
    HashMap<Integer, Player> players;

    ReinforcementController reinforcementController;

    @Before
    public void before() {
        continents = new HashMap<>();
        countries = new HashMap<>();
        countriesInContinent = new ArrayList<>();
        players = new HashMap<>();
        reinforcementController = new ReinforcementController(null);


        Player player;
        for (int loopForPlayers = 1; loopForPlayers < 4; loopForPlayers++) {
            player = player = new Player(loopForPlayers, "player" + loopForPlayers);
            players.put(loopForPlayers, player);
        }


        int loopForContinent = 1;
        Integer[] controlValues = new Integer[]{7, 10};
        for (int i = 1; i < 30; i++) {
            Country country = new Country(i, "Country" + i);
            if (i < 13) {
                country.owner = players.get(1);
            } else if (i < 17) {
                country.owner = players.get(2);
            } else {
                country.owner = players.get(3);
            }
            countriesInContinent.add(country);
            countries.put(i, country);
            if (i == 12 || i == 29) {
                Continent continent = new Continent(loopForContinent, "Continent" + loopForContinent, controlValues[loopForContinent - 1]);
                continent.countries = countriesInContinent;
                continents.put(loopForContinent, continent);
                countriesInContinent = new ArrayList<>();
                loopForContinent++;
            }
        }
    }

    @After
    public void after() {

    }

    @Test
    public void toTestTotalArmiesReinforcement() {
        int totalArmies;
        // to check armies for player having countries less than 9
        //---will be given 3 armies
        totalArmies=reinforcementController.getTotalArmies(countries,continents,players.get(2).id);
        assertEquals(3,totalArmies);

        //to check armies for player having countries equal to 13 but not complete continent
        //---will be given 13/3=4 armies
        totalArmies=reinforcementController.getTotalArmies(countries,continents,players.get(3).id);
        assertEquals(4,totalArmies);

        //to check armies for player having countries equal to 12 but have a complete continent with control value 7
        //---will be given 12/3+7=11 armies
        totalArmies=reinforcementController.getTotalArmies(countries,continents,players.get(1).id);
        assertEquals(11,totalArmies);


    }
}
