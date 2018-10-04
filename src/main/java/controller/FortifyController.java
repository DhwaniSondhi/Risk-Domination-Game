package controller;

import gui.FortifyPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for FortifyPanel
 */
public class FortifyController extends BaseController<FortifyPanel> implements ActionListener {
    /**
     * This is the constructor for the Controller
     *
     * @param view View associated with the Controller
     */
    public FortifyController(FortifyPanel view) {
        super(view);
    }

    /**
     * Updates the countries and its Neighbouring countries and also show number of armies in them
     */
    public void updateCountryListFortify() {
        view.showCountriesFortify(model.getCountriesOfCurrentPlayer());
        view.showNeighbouringCountriesFortify(model.getCountriesOfCurrentPlayer());
        view.transferFortify();
    }

    /**
     * Perform action when invoked
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
