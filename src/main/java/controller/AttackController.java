package controller;

import model.Country;
import view.AttackPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Controller for {@link AttackPanel} extends {@link BaseController} and implements {@link ActionListener}
 */
public class AttackController extends BaseController<AttackPanel> implements ActionListener, ItemListener {


    /**
     * This is the constructor for the Controller
     *
     * @param view View associated with the Controller
     */
    public AttackController(AttackPanel view) {
        super(view);
        model.addObserver(view);
        model.currentPlayer.addObserver(view);
    }

    /**
     * Update the list of countries in the view with current player's countries
     */
    public void updateCountryList() {
        view.showCountries(model.currentPlayer.getCountriesAllowedToAttack());
    }


    /**
     * Invoked when an action occurs.
     *
     * @param e event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("selectCountry")) {

        } else if (e.getActionCommand().equalsIgnoreCase("proceed")) {
            model.assignCardToPlayer(model.currentPlayer.id);
            stateChangeListener.onAttackCompleted();
        }

    }

    /**
     * Invoked when an item has been selected or deselected by the user.
     * The code written for this method performs the operations
     * that need to occur when an item is selected (or deselected).
     *
     * @param e
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED){
            String sourceName = ((Component) e.getSource()).getName();
            if(sourceName.equals("selectCountry")) {
                Country selectedCountry = ((Country) e.getItem());
                view.showNeighbouringCountries(selectedCountry.getNeighbours());
            } else if(sourceName.equals("mode")){
                if(e.getItem().equals("Choose Dice")){
                    view.dicePanel.show();
                }else{
                    view.dicePanel.hide();
                }
                view.dicePanel.revalidate();
                view.dicePanel.repaint();
            }
        }
    }
}
