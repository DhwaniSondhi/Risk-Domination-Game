package utility.strategy;

import model.Country;
import model.GameMap;
import model.Player;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
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
        Random random = new Random();
        context.setUnSelectedCards();
        context.setArmiesForReinforcement();
        if (context.unselectedCards.size() > 4) {
            context.exchangeCardsAutomatically();
        }
        armySelected = context.totalArmies;
        while (armySelected > 0) {
            country = context.countries.get(random.nextInt(context.countries.size()));
            int armyAssigned = random.nextInt(armySelected);
            country.addArmies(armyAssigned);
            armySelected -= armyAssigned;
            GameMap.getInstance().setRecentMove(country.owner.name + " reinforced " +
                    country.name + " with " + armyAssigned + " armies.");
        }
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
        while (true) {
            Random rand = new Random();
            selectedCountry = context.countries.get(rand.nextInt(context.countries.size()));
            if (selectedCountry.getNeighboursDiffOwner().size() == 0) {
                continue;
            }
            int index = rand.nextInt(selectedCountry.getNeighboursDiffOwner().size());

            selectedNeighbouringCountry = (Country) selectedCountry.getNeighboursDiffOwner().toArray()[index];
            int limit = rand.nextInt(10);
            for (int i = 0; i < limit; i++) {
                GameMap.getInstance().setRecentMove(context.name + " started Normal attack with " + selectedCountry.name
                        + " on " + selectedNeighbouringCountry.name);

                context.performAttackSteps(selectedCountry, selectedNeighbouringCountry, false);
                if (selectedCountry.getNumberofArmies() == 1 || selectedNeighbouringCountry.owner.equals(selectedCountry.owner)) {
                    break;
                }
            }
            break;
        }
        GameMap.getInstance().changePhase(GameMap.Phase.FORTIFY);
    }

    /**
     * Updates the armies of countries in which armies are transferred
     *
     * @param context                reference to player using this strategy
     * @param numberOfArmiesTransfer armies user select to transfer
     * @param firstRandomCountry     country which user select transfer from
     * @param secondRandomCountry    country which user select transfer to
     */
    @Override
    public void fortify(Player context, int numberOfArmiesTransfer, Country firstRandomCountry, Country
            secondRandomCountry) {
        Random r = new Random(Calendar.getInstance().getTimeInMillis());
        ArrayList<Country> listOfcountries = context.countries;
        ArrayList<Country> listForFirstOption = new ArrayList<Country>();
        for (int i = 0; i < listOfcountries.size(); i++) {
            if (listOfcountries.get(i).numOfArmies > 1) {
                listForFirstOption.add(listOfcountries.get(i));
            }
        }
        //to check there is any country from where we can fortify or not
        if (listForFirstOption.size() != 0) {
            //if there is only one country from where we can fortify
            if (listForFirstOption.size() == 1) {
                firstRandomCountry = listForFirstOption.get(0);
            } else {
                firstRandomCountry = listForFirstOption.get(r.nextInt(listForFirstOption.size()));
            }
            firstRandomCountry.updateConnectedCountries();
            HashMap<Integer, Country> mapOfCountriesConnected = firstRandomCountry.connectedCountries;
            //check there is any connected country to which we can fortify randomly
            fortifyCheck(context, mapOfCountriesConnected, firstRandomCountry);
        }
        GameMap.getInstance().changePhase(GameMap.Phase.REINFORCE);
    }

    public void transferRandomArmies(Player context,Country firstRandomCountry, Country secondRandomCountry){
        Random r = new Random(Calendar.getInstance().getTimeInMillis());
        int numberOfArmiesTransfer = firstRandomCountry.numOfArmies - 1;
        if (firstRandomCountry.numOfArmies == 2) {

            GameMap.getInstance().setRecentMove(context.name + " fortified " + firstRandomCountry.name + " with " + numberOfArmiesTransfer
                    + " armies from " + secondRandomCountry.name);
            firstRandomCountry.deductArmies(numberOfArmiesTransfer);
            secondRandomCountry.addArmies(numberOfArmiesTransfer);

        } else {
            numberOfArmiesTransfer = r.nextInt(firstRandomCountry.numOfArmies - 1);
            GameMap.getInstance().setRecentMove(context.name + " fortified " + firstRandomCountry.name + " with " + numberOfArmiesTransfer
                    + " armies from " + secondRandomCountry.name);
            firstRandomCountry.deductArmies(numberOfArmiesTransfer);
            secondRandomCountry.addArmies(numberOfArmiesTransfer);
        }
    }

    public void fortifyCheck(Player context,HashMap<Integer, Country> mapOfCountriesConnected, Country firstRandomCountry){
        Random r = new Random(Calendar.getInstance().getTimeInMillis());
        Country secondRandomCountry = null;
        if (mapOfCountriesConnected.size() != 0) {
            ArrayList<Country> listOfConnectedCountries = new ArrayList<Country>(mapOfCountriesConnected.values());
            //if there is only one country no need for randomness
            if (mapOfCountriesConnected.size() == 1) {
                secondRandomCountry = listOfConnectedCountries.get(0);
            } else {
                secondRandomCountry = listOfConnectedCountries.get(r.nextInt(listOfConnectedCountries.size() - 1));
            }
            //suppose selected country only have two armies so no need for randomness
            transferRandomArmies(context,firstRandomCountry,secondRandomCountry);
        }
        GameMap.getInstance().changePhase(GameMap.Phase.REINFORCE);
    }

}
