package controller;

import entity.Country;
import gui.FortifyPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for FortifyPanel
 */
public class FortifyController extends BaseController<FortifyPanel> implements ActionListener, ListSelectionListener {
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
     * @param e triggered after pressing button
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /**
     * To get the number of armies at a country and it neighbor which is selected by user from lists
     *
     * @param e event triggered after list item selected
     */

    @Override
    public void valueChanged(ListSelectionEvent e) {
        JList<Country> source = (JList<Country>) e.getSource();
        if (e.getValueIsAdjusting()) {
            System.out.println(source.getSelectedValue());
            System.out.println(source.getSelectedValue().numOfArmies);
            view.updateCountriesArmyTextField(source.getSelectedValue().numOfArmies);

        }

    }
}
