package controller;

import entity.GameMap;
import gui.CountryPanel;

/**
 * Controller for (@link gui.CountryPanel) extends (@link BaseController)
 */

public class CountryController extends BaseController<CountryPanel> {

    /**
     * This is the constructor for the Controller
     *
     * @param view View associated wth the Controller
     */
    public CountryController(CountryPanel view){
        super(view);
    }

    /**
     * Update the list of countries in the view with current player's countries
     */
    public void updateCountryList(){
        view.updateCountries(GameMap.getInstance().countries);
    }

}
