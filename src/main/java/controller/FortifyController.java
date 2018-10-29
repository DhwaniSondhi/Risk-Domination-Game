package controller;

import model.Country;
import view.FortifyPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Controller for FortifyPanel
 */
public class FortifyController extends BaseController<FortifyPanel> implements ActionListener, ListSelectionListener {
    /**
     * Country selected by player
     */
    Country selectedCountry;
    /**
     * Neighboring country selected by player
     */
    Country selectedNeighbour;
    /**
     * Number of armies player want to fortify
     */
    int armiesToTransfer;

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
     * Hide unnecessary components at first run
     */
    public void updateCountryListFortify() {
        view.showCountriesFortify(model.getCountriesOfCurrentPlayer());
        view.transferFortify();
        view.disableButton();
        view.setVisibleFalseNeighbourPanel();
        view.setComboBoxAndNeighborTextFieldFalse();
        view.disableCountriesArmyLabelAndTextField();
    }

    /**
     * Sets the selected value from ComboBox
     *
     * @param number number selected by user
     */
    public void updateComboBoxValue(int number) {
        armiesToTransfer = number;
    }

    /**
     * Perform action when invoked
     * Get the value from ComboBox of number of armies user want to transfer
     * Update the hashmap of countries(Transfer the armies)
     * Update the value of number of armies in TextField after transfer
     *
     * @param e triggered after pressing button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("proceed")) {
            if (stateChangeListener != null)
                stateChangeListener.onFortificationCompleted();
        } else {
            view.getTheValueOfComboBox();
            model.updateArmiesOfCountries(armiesToTransfer, selectedCountry, selectedNeighbour);
            view.updateTextFieldsArmiesAfterTransfer(model.getNumberofArmiesAtCountry(selectedCountry.id), model.getNumberofArmiesAtCountry(selectedNeighbour.id));
        }
    }

    /**
     * To get the number of armies at a country and its neighbor based on user selection
     * It calls function to get the list of countries to which a player can fortify from a selected country
     * Also calls function to calculate value for comboBox
     *
     * @param e event triggered after list item selected
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        JList<Country> source = (JList<Country>) e.getSource();
        if (e.getValueIsAdjusting()) {
            if (((JList<Country>) e.getSource()).getName().equalsIgnoreCase("Country")) {
                selectedCountry = source.getSelectedValue();
                view.enableCountriesArmyLabelAndTextField();
                view.updateCountriesArmyTextField(source.getSelectedValue().numOfArmies);
                HashMap<Integer, Country> neighbor = getNeighborsOfCountry(selectedCountry);
                view.showNeighbouringCountriesFortify(model.getCountriesOfCurrentPlayer());
                view.updateNeighboringCountries(neighbor);

            } else {
                selectedNeighbour = source.getSelectedValue();
                getArmiesOfSelectedNeighbor(selectedNeighbour);
                view.enableButton();
                view.setComboBoxAndNeighborTextFieldTrue();
            }


        }
    }

    /**
     * Update TextField for armies on neighboring countries before transfer
     *
     * @param selectedNeighbour Country to which player want to transfer army
     */
    public void getArmiesOfSelectedNeighbor(Country selectedNeighbour) {
        String selectedNeighborCountry = selectedNeighbour.name;
        view.updateNeighboringCountriesArmyTextField(selectedNeighbour.numOfArmies);
        int armyOnCountry = selectedCountry.numOfArmies;
        if (armyOnCountry == 1) {
            view.disableButton();
        }
        view.updateJComboboxArmies(armyOnCountry);

    }

    /**
     * Function that get the LinkedHashMap of neighbouring countries of selected country
     *
     * @param selectedCountry country whose neighbours need to find
     * @return HashMap of countries where fortify can be done
     */
    public HashMap getNeighborsOfCountry(Country selectedCountry) {
        HashMap<Integer, Country> neighbor = new HashMap<>();
        int countryId = selectedCountry.id;
        int ownerId = selectedCountry.owner.id;
        Queue<Integer> queueNeighbor = new LinkedList<>();
        queueNeighbor.add(countryId);
        neighbor.put(countryId, selectedCountry);
        while (!queueNeighbor.isEmpty()) {
            int last = queueNeighbor.remove();
            ArrayList<Country> listNeighbouring = new ArrayList<>();
            listNeighbouring = (ArrayList<Country>) model.countries.get(last).getNeighbours(model.countryGraph);
            for (int i = 0; i < listNeighbouring.size(); i++) {
                if (listNeighbouring.get(i).owner.id == ownerId) {
                    if (neighbor.get(listNeighbouring.get(i).id) == null) {
                        queueNeighbor.add(listNeighbouring.get(i).id);
                        neighbor.put(listNeighbouring.get(i).id, listNeighbouring.get(i));
                        neighbor.put(listNeighbouring.get(i).id, listNeighbouring.get(i));
                    }

                }

            }
            neighbor.remove(countryId);

        }

        if (neighbor.isEmpty()) {
            view.disableButton();
            view.setVisibleFalseNeighbourPanel();
            view.setComboBoxAndNeighborTextFieldFalse();
        } else {
            view.setVisibleTrueNeighbourPanel();
            view.disableButton();
            view.setComboBoxAndNeighborTextFieldFalse();
        }
        return neighbor;
    }

}
