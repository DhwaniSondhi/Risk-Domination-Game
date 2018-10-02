package controller;

import entity.GameMap;
import gui.ContinentPanel;

/**
 * Controller for {@link gui.ContinentPanel} extends {@link BaseController}
 */
public class ContinentController extends BaseController<ContinentPanel> {

    /**
     * This is the constructor for the Controller
     *
     * @param view View associated with the Controller
     */
    public ContinentController(ContinentPanel view) {
        super(view);

    }

    /**
     * Update the list of countries in the view with current player's countries
     */
    public void updateContinentList() {
        view.updateContinentList(GameMap.getInstance().continents.values());
    }
}
