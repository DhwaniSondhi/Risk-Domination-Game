package utility.strategy;

import model.Country;
import model.GameMap;
import model.Player;

import java.util.ArrayList;
import java.util.HashSet;

public class CheaterStrategy implements PlayerStrategy {


    @Override
    public void reinforce(Player context, Country country, int armySelected) {
        ArrayList<Country> listOfcountries=context.countries;
        for(int i=0;i<listOfcountries.size();i++){
            listOfcountries.get(i).addArmies(2*listOfcountries.get(i).numOfArmies);

        }

    }

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
