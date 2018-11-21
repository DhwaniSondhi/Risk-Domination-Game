package utility.strategy;

import model.Country;
import model.GameMap;
import model.Player;

import java.util.ArrayList;
import java.util.HashSet;

public class CheaterStrategy implements PlayerStrategy {

    /**
     * To add the armies to the respective countries on click of Add button
     *
     * @param context      reference to player using this strategy
     * @param country      country to reinforce
     * @param armySelected number of armies
     */

    @Override
    public void reinforce(Player context, Country country, int armySelected) {
        ArrayList<Country> listOfcountries=context.countries;
        for(int i=0;i<listOfcountries.size();i++){
            listOfcountries.get(i).addArmies(2*listOfcountries.get(i).numOfArmies);

        }

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
        ArrayList<Country> listOfcountries=context.countries;
        for(int i=0;i<listOfcountries.size();i++){
            HashSet<Country> setOfCountries=GameMap.getInstance().countryGraph.get(listOfcountries.get(i));
            for(Country country : setOfCountries){
                if(country.id!=context.id){
                    country.changeOwner(context);
                    break;
                }
            }

        }

    }

    /**
     * Updates the armies of countries in which armies are transferred
     *
     * @param context                reference to player using this strategy
     * @param numberOfArmiesTransfer armies user select to transfer
     * @param countrySelected        country which user select transfer from
     * @param neighborSelected       country which user select transfer to
     */
    @Override
    public void fortify(Player context, int numberOfArmiesTransfer, Country countrySelected, Country neighborSelected) {
        ArrayList<Country> listOfcountries = context.countries;
        for (int i = 0; i < listOfcountries.size(); i++) {
            HashSet<Country> setOfCountries = GameMap.getInstance().countryGraph.get(listOfcountries.get(i));
            for (Country country : setOfCountries) {
                if (country.id != context.id) {
                    listOfcountries.get(i).addArmies(2 * listOfcountries.get(i).numOfArmies);
                    break;
                }
            }
        }
    }
}
