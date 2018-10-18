package controller;

import view.AttackPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for {@link AttackPanel} extends {@link BaseController} and implements {@link ActionListener}
 */
public class AttackController extends BaseController<AttackPanel> implements ActionListener {


    /**
     * This is the constructor for the Controller
     *
     * @param view View associated with the Controller
     */
    public AttackController(AttackPanel view) {
        super(view);
    }

    /**
     * Update the list of countries in the view with current player's countries
     */
    public void updateCountryList() {
        view.showCountries(model.getCountriesOfCurrentPlayer());
    }


    /**
     * Invoked when an action occurs.
     *
     * @param e event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("select")) {
            int selectedCountryId = Integer.valueOf(((JButton) e.getSource()).getName());
            view.showNeighbouringCountries(model.countries.get(selectedCountryId).getNeighbours(model.countryGraph));
        } else if (e.getActionCommand().equalsIgnoreCase("proceed")) {
            model.assignCardToPlayer(model.currentPlayer.id);
            stateChangeListener.onAttackCompleted();
        }

    }
}
