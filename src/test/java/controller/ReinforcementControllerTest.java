package controller;

import entity.Continent;
import entity.Country;
import entity.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

public class ReinforcementControllerTest {

    HashMap<Integer, Continent> continents;
    HashMap<Integer, Country> countries;
    ArrayList<Country> countriesInContinent;
    HashMap<Integer, Player> players;

    ReinforcementController reinforcementController;

    @Before
    public void before(){
        continents=new HashMap<>();
        countries=new HashMap<>();
        countriesInContinent=new ArrayList<>();
        players=new HashMap<>();
        reinforcementController=new ReinforcementController(null);


        Player player;
        for(int loopForPlayers=1;loopForPlayers<4;loopForPlayers++){
            player=player=new Player(loopForPlayers,"player"+loopForPlayers);
            players.put(loopForPlayers,player);
        }


        int loopForContinent=1;
        Integer[] controlValues=new Integer[]{7,10};
        for(int i=1;i<30;i++){
            Country country=new Country(i,"Country"+i);
            if(i<13){
                country.owner=players.get(1);
            }else if(i<17){
                country.owner=players.get(2);
            }else{
                country.owner=players.get(3);
            }
            countriesInContinent.add(country);
            countries.put(i,country);
            if(i==12 || i==29){
                Continent continent=new Continent(loopForContinent,"Continent"+loopForContinent,controlValues[loopForContinent-1]);
                continent.countries=countriesInContinent;
                continents.put(loopForContinent,continent);
                countriesInContinent=new ArrayList<>();
                loopForContinent++;
            }
        }
    }

    @After
    public void after(){

    }

    @Test
    public void toTestTotalArmiesReinforcement(){
        int totalArmies;
        // to check armies for player having countries less than 9
        //---will be given 3 armies
        totalArmies=reinforcementController.getTotalArmies(countries,continents,players.get(2).id);
        assertEquals(3,totalArmies);

        
    }
}
