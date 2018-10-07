package controller;

import entity.Country;
import gui.FortifyPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Controller for FortifyPanel
 */
public class FortifyController extends BaseController<FortifyPanel> implements ActionListener, ListSelectionListener {
    Country selectedCountry;
    Country selectedNeighbour;

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
     * To get the number of armies at a country and its neighbor based on user selection
     * It gives the list of countries to which a player can fortify from a selected country
     * Also calculate value for comboBox
     *
     * @param e event triggered after list item selected
     */

    @Override
    public void valueChanged(ListSelectionEvent e) {
        JList<Country> source = (JList<Country>) e.getSource();
        if (e.getValueIsAdjusting()) {
            if (((JList<Country>) e.getSource()).getName().equalsIgnoreCase("Country")) {
                selectedCountry = source.getSelectedValue();
                view.updateCountriesArmyTextField(source.getSelectedValue().numOfArmies);
                int countryId = source.getSelectedValue().id;
                int ownerId = source.getSelectedValue().owner.id;

                Queue<Integer> queueNeighbor = new LinkedList<>();
                queueNeighbor.add(countryId);
                LinkedHashMap<Integer, Country> neighbor = new LinkedHashMap<Integer, Country>();
                LinkedHashMap<Integer, Country> neighborCheck = new LinkedHashMap<Integer, Country>();
                neighborCheck.put(countryId, source.getSelectedValue());
                while (!queueNeighbor.isEmpty()) {
                    int last = queueNeighbor.remove();
                    ArrayList<Country> listNeighbouring = new ArrayList<>();
                    listNeighbouring = (ArrayList<Country>) model.countries.get(last).getNeighbours(model.countryGraph);
                    for (int i = 0; i < listNeighbouring.size(); i++) {
                        if (listNeighbouring.get(i).owner.id == ownerId) {
                            if (neighborCheck.get(listNeighbouring.get(i).id) == null) {
                                queueNeighbor.add(listNeighbouring.get(i).id);
                                neighbor.put(listNeighbouring.get(i).id, listNeighbouring.get(i));
                                neighborCheck.put(listNeighbouring.get(i).id, listNeighbouring.get(i));
                            }

                        }

                    }

                }

                view.updateNeighboringCountries(neighbor);
            } else {
                selectedNeighbour = source.getSelectedValue();
                String selectedNeighborCountry = source.getSelectedValue().name;
                view.updateNeighboringCountriesArmyTextField(source.getSelectedValue().numOfArmies);
                int armyOnCountry = selectedCountry.numOfArmies;
                view.updateJComboboxArmies(armyOnCountry);
            }


        }
    }
}
