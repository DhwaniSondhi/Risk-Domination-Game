package utility.strategy;

import model.Country;
import model.GameMap;
import model.Player;

import java.util.Random;

public class BenevolentStrategy implements PlayerStrategy {

    /**
     * To add the armies to the respective countries on click of Add button
     *
     * @param context reference to player using this strategy
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
        
    }

    /**
     * Updates the armies of countries in which armies are transferred
     *
     * @param context reference to player using this strategy
     * @param numberOfArmiesTransfer armies user select to transfer
     * @param strongestCountry        country which user select transfer from
     * @param weakestCountry       country which user select transfer to
     */
    @Override
    public void fortify(Player context, int numberOfArmiesTransfer, Country strongestCountry, Country weakestCountry) {
    strongestCountry=context.getStrongestCountry();
    weakestCountry=context.getWeakestCountry();
    Random r = new Random();
    int armiesAtCountry=strongestCountry.numOfArmies-1;
    numberOfArmiesTransfer=r.nextInt(armiesAtCountry) + 1;
    GameMap.getInstance().setRecentMove(context.name + " fortified " + weakestCountry.name + " with " + numberOfArmiesTransfer
                + " armies from " + strongestCountry.name);
    strongestCountry.deductArmies(numberOfArmiesTransfer);
    weakestCountry.addArmies(numberOfArmiesTransfer);
    }
}
