package utility.strategy;

import model.Country;
import model.GameMap;
import model.Player;

import java.util.Random;

public class RandomStrategy implements PlayerStrategy {

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
        Random rand = new Random();
        selectedCountry = context.countries.get(rand.nextInt(context.countries.size()));
        int index = rand.nextInt(selectedCountry.getNeighbours().size());
        selectedNeighbouringCountry = (Country) selectedCountry.getNeighbours().toArray()[index];
        int limit = rand.nextInt(10);
        for (int i = 0; i < limit; i++) {
            GameMap.getInstance().setRecentMove(context.name + " started Normal attack with " + selectedCountry.name
                    + " on " + selectedNeighbouringCountry.name);

            context.performAttackSteps(selectedCountry, selectedNeighbouringCountry, false);
            if(selectedCountry.getNumberofArmies() == 1 || selectedNeighbouringCountry.owner.equals(selectedCountry.owner)){
                break;
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

    }
}
