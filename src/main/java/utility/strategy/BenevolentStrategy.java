package utility.strategy;

import model.Country;
import model.GameMap;
import model.Player;

import java.util.*;

public class BenevolentStrategy implements PlayerStrategy {

    /**
     * To add the armies to the respective countries on click of Add button
     *
     * @param context      reference to player using this strategy
     * @param country      country to reinforce
     * @param armySelected number of armies
     */
    @Override
    public void reinforce(Player context, Country country, int armySelected) {
        context.setUnSelectedCards();
        context.setArmiesForReinforcement();
        if (context.unselectedCards.size() > 4) {
            context.exchangeCardsAutomatically();
        }
        armySelected = context.totalArmies;
        country = context.getWeakestCountry();
        country.addArmies(armySelected);
        GameMap.getInstance().setRecentMove(country.owner.name + " reinforced " +
                country.name + " with " + armySelected + " armies.");
        GameMap.getInstance().changePhase(GameMap.Phase.ATTACK);
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

    }

    /**
     * Updates the armies of countries in which armies are transferred
     *
     * @param context                reference to player using this strategy
     * @param numberOfArmiesTransfer armies user select to transfer
     * @param strongestCountry       country which user select transfer from
     * @param weakestCountry         country which user select transfer to
     */
    @Override
    public void fortify(Player context, int numberOfArmiesTransfer, Country strongestCountry, Country weakestCountry) {
        int numberOfCountriesPlayerHave = context.countries.size();
        int count = numberOfCountriesPlayerHave;
        while (count >= 0) {


            List<Country> weakestcountries = context.getStrongestCountries(count);
            weakestCountry= weakestcountries.get(count-1);
            HashMap<Integer, Country> listOfCountriesConnected = weakestCountry.connectedCountries;
            if(listOfCountriesConnected.size()!=0){
                int largestArmy = 0;
                Iterator it = listOfCountriesConnected.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<Integer, Country> entry = (Map.Entry) it.next();
                    Country country = entry.getValue();
                    if (largestArmy < country.getNumberofArmies()) {
                        largestArmy = country.getNumberofArmies();
                        strongestCountry = country;
                    }


                }
                if(strongestCountry.numOfArmies!=1){

                    numberOfArmiesTransfer = strongestCountry.numOfArmies-1;
                    GameMap.getInstance().setRecentMove(context.name + " fortified " + weakestCountry.name + " with " + numberOfArmiesTransfer
                            + " armies from " + strongestCountry.name);
                    strongestCountry.deductArmies(numberOfArmiesTransfer);
                    weakestCountry.addArmies(numberOfArmiesTransfer);
                    break;
                }

            }
            if(count==1){
                //part to skip
            }
            count--;

        }

    }
}
