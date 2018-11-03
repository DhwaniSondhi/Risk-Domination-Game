package controller;

import model.Country;
import view.FortifyPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

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
        model.addObserver(view);
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
            model.currentPlayer.fortify(armiesToTransfer, selectedCountry, selectedNeighbour);
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
                if (selectedCountry != null)
                    selectedCountry.deleteObserver(view);
                selectedCountry = source.getSelectedValue();
                selectedCountry.addObserver(view);
                selectedCountry.updateConnectedCountries();
            } else {
                if(selectedNeighbour!=null)
                    selectedNeighbour.deleteObserver(view);
                selectedNeighbour = source.getSelectedValue();
                selectedNeighbour.addObserver(view);
                selectedNeighbour.updateTextFieldForNeighbour();

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


}
