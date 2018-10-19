package controller;

import model.Card;
import model.Continent;
import model.Country;
import model.Player;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Test Class for different test cases for reinforcement phase
 */
public class ReinforcementControllerTest {

    /**
     * HashMap for continents(the continent's id as the key and continent object as the value)
     */
    HashMap<Integer, Continent> continents;

    /**
     * HashMap for countries(the country's id as the key and country object as the value)
     */
    HashMap<Integer, Country> countries;

    /**
     * List for countries in the continent
     */
    ArrayList<Country> countriesInContinent;

    /**
     * HashMap for players(the player's id as the key and player object as the value)
     */
    HashMap<Integer, Player> players;

    /**
     * List for cards
     */
    ArrayList<Card> cards;

    /**
     * Reference for reinforcement controller
     */
    ReinforcementController reinforcementController;

    /**
     * To set up context for the test methods
     * It sets up the continents, countries, players and cards in the game to test various cases in reinforcement.
     */
    @Before
    public void before() {
        continents = new HashMap<>();
        countries = new HashMap<>();
        countriesInContinent = new ArrayList<>();
        players = new HashMap<>();
        reinforcementController = new ReinforcementController(null);


        Player player;
        for (int loopForPlayers = 1; loopForPlayers < 4; loopForPlayers++) {
            player = new Player(loopForPlayers, "player" + loopForPlayers);
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

        cards=new ArrayList<Card>();
        cards.add(new Card(Card.TYPE.CAVALRY));
        cards.add(new Card(Card.TYPE.CAVALRY));
        cards.add(new Card(Card.TYPE.CAVALRY));
        cards.add(new Card(Card.TYPE.INFANTRY));
        cards.add(new Card(Card.TYPE.ARTILLERY));
        cards.add(new Card(Card.TYPE.ARTILLERY));
        cards.add(new Card(Card.TYPE.ARTILLERY));
        cards.add(new Card(Card.TYPE.ARTILLERY));
        players.get(1).cards=cards;

        cards=new ArrayList<Card>();
        cards.add(new Card(Card.TYPE.CAVALRY));
        cards.add(new Card(Card.TYPE.CAVALRY));
        cards.add(new Card(Card.TYPE.ARTILLERY));
        players.get(2).cards=cards;


    }

    /**
     * To test total armies the player will get for reinforcement phase
     */
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


    /**
     * To test armies the player will get for exchanging cards
     */
    @Test
    public void toTestArmiesFromCards(){
        int totalArmies;

        //to check armies for player having countries equal to 13 but not complete continent
        //---will be given 13/3=4 armies
        totalArmies=reinforcementController.getTotalArmies(countries,continents,players.get(3).id);
        assertEquals(4,totalArmies);

        //initially the armies were 4
        //on every set of 3 cards are exchanged---5 armies will be allotted on first exchange
        //---will be given 4+5=9 armies
        totalArmies=reinforcementController.getUpdatedArmiesOnCardsExchange(totalArmies,players.get(3));
        assertEquals(9,totalArmies);

        //now the armies were 9
        //on every set of 3 cards are exchanged---10 armies will be allotted on second exchange
        //---will be given 9+10=19 armies
        totalArmies=reinforcementController.getUpdatedArmiesOnCardsExchange(totalArmies,players.get(3));
        assertEquals(19,totalArmies);

        //now the armies were 19
        //on every set of 3 cards are exchanged---15 armies will be allotted on third exchange
        //---will be given 19+15=34 armies
        totalArmies=reinforcementController.getUpdatedArmiesOnCardsExchange(totalArmies,players.get(3));
        assertEquals(34,totalArmies);
    }

    /**
     * To test number of different types of cards the player has
     */
    @Test
    public void toTestSetUnselectedCards(){
        HashMap<String,Integer> cardsToBeTested;

        cardsToBeTested=reinforcementController.getCardSetsOfPlayer(players.get(1));
        assertEquals(3,cardsToBeTested.size());//size will always be 3
        assertEquals(3,cardsToBeTested.get("CAVALRY").intValue());
        assertEquals(1,cardsToBeTested.get("INFANTRY").intValue());
        assertEquals(4,cardsToBeTested.get("ARTILLERY").intValue());

        cardsToBeTested=reinforcementController.getCardSetsOfPlayer(players.get(2));
        assertEquals(3,cardsToBeTested.size());//size will always be 3
        assertEquals(2,cardsToBeTested.get("CAVALRY").intValue());
        assertEquals(0,cardsToBeTested.get("INFANTRY").intValue());
        assertEquals(1,cardsToBeTested.get("ARTILLERY").intValue());

    }

}
