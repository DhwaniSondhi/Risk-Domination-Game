package utility.strategy;

import model.Country;
import model.GameMap;
import model.Player;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class AggressiveStrategy implements PlayerStrategy {

    /**
     * To add the armies to the respective countries on click of Add button
     *
     * @param context      reference to player using this strategy
     * @param country      country to reinforce
     * @param armySelected number of armies
     */
    @Override
    public void reinforce(Player context, Country country, int armySelected) {

    }

    /**
     * Attacks the country by rolling dice.
     * <ol>
     * <li>rolls the dice</li>
     * <li>deducts army for a loss</li>
     * <li>check if game over</li>
     * <li>check if card gained</li>
     * </ol>
     *
     * @param context                     reference to player using this strategy
     * @param selectedCountry             country of the player
     * @param selectedNeighbouringCountry country of the opponent
     * @param isAllOut                    flag to check the mode of the game
     */
    @Override
    public void attack(Player context, Country selectedCountry, Country selectedNeighbouringCountry, boolean isAllOut) {
        selectedCountry = context.getStrongestCountry();
        Random rand = new Random();
        int index = rand.nextInt(selectedCountry.getNeighbours().size());
        selectedNeighbouringCountry = (Country) selectedCountry.getNeighbours().toArray()[index];
        GameMap.getInstance().setRecentMove(context.name + " started AllOut attack with " + selectedCountry.name
                + " on " + selectedNeighbouringCountry.name);
        while (selectedCountry.numOfArmies > 1) {
            context.performAttackSteps(selectedCountry, selectedNeighbouringCountry, true);
            if(selectedNeighbouringCountry.owner.equals(selectedCountry.owner)){
                index = rand.nextInt(selectedCountry.getNeighbours().size());
                selectedNeighbouringCountry = (Country) selectedCountry.getNeighbours().toArray()[index];
            }
        }
    }

    /**
     * Updates the armies of countries in which armies are transferred
     *
     * @param context                reference to player using this strategy
     * @param numberOfArmiesTransfer armies user select to transfer
     * @param strongestCountry        country which user select transfer from
     * @param secondStrongestCountry       country which user select transfer to
     */
    @Override
    public void fortify(Player context, int numberOfArmiesTransfer, Country strongestCountry, Country secondStrongestCountry) {
    strongestCountry=context.getStrongestCountry();
    HashMap<Integer,Country> listOfCountriesConnected=strongestCountry.connectedCountries;


    int largestArmy = 0;
    Iterator it=listOfCountriesConnected.entrySet().iterator();
    while(it.hasNext()){
        Map.Entry<Integer,Country> entry= (Map.Entry) it.next();
        Country country= entry.getValue();
        if(largestArmy < country.getNumberofArmies()){
            largestArmy = country.getNumberofArmies();
            secondStrongestCountry = country;
        }

        it.remove();
    }
    numberOfArmiesTransfer=secondStrongestCountry.numOfArmies-1;
    GameMap.getInstance().setRecentMove(context.name + " fortified " + secondStrongestCountry.name + " with " + numberOfArmiesTransfer
                + " armies from " + strongestCountry.name);
    strongestCountry.deductArmies(numberOfArmiesTransfer);
    secondStrongestCountry.addArmies(numberOfArmiesTransfer);




    }
}