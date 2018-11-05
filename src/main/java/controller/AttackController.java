package controller;

import model.Country;
import view.AttackPanel;

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
     * Store the value of player country
     */
    public Country selectedCountry;
    /**
     * store the value of opponent country
     */
    public Country selectedNeighbouringCountry;

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
     * observe the current player
     */
    public void initialize() {
        model.currentPlayer.addObserver(view);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e event
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equalsIgnoreCase("attack")) {
            selectedCountry.addObserver(view);
            selectedNeighbouringCountry.addObserver(view);
            boolean isAllOut = view.selectMode.getSelectedIndex() == 1;

            model.currentPlayer.rollDice((Integer) view.playerDice.getSelectedItem(), (Integer) view.opponentDice.getSelectedItem(), isAllOut);
            model.currentPlayer.attack(selectedCountry, selectedNeighbouringCountry, isAllOut);
            selectedCountry.deleteObserver(view);
            selectedNeighbouringCountry.deleteObserver(view);

        } else if (e.getActionCommand().equalsIgnoreCase("proceed")) {
            model.currentPlayer.gainCard();
            model.currentPlayer.deleteObserver(view);
            stateChangeListener.onAttackCompleted();

        } else if (e.getActionCommand().equalsIgnoreCase("move")) {
            selectedCountry.addObserver(view);
            selectedNeighbouringCountry.addObserver(view);
            int armies = (Integer) view.armyToMove.getSelectedItem();
            model.setRecentMove(model.currentPlayer.name + " moved " + armies + " armies from " + selectedCountry.name
                    + " to " + selectedNeighbouringCountry.name);
            model.updateArmiesOfCountries(armies, selectedCountry, selectedNeighbouringCountry);
            selectedCountry.deleteObserver(view);
            selectedNeighbouringCountry.deleteObserver(view);
            view.moveArmyPanel.hide();
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
        if (e.getStateChange() == ItemEvent.SELECTED) {
            String sourceName = ((Component) e.getSource()).getName();
            if (sourceName.equals("selectCountry")) {
                if (selectedCountry != null) {
                    selectedCountry.deleteObserver(view);
                }
                selectedCountry = ((Country) e.getItem());
                selectedCountry.addObserver(view);
                selectedCountry.updateNumOfDiceAllowed(false);
                view.updateNeighbouringCountries(selectedCountry.neighbours);
            } else if (sourceName.equals("selectNeighbourCountry")) {
                if (selectedNeighbouringCountry != null) {
                    selectedNeighbouringCountry.deleteObserver(view);
                }
                selectedNeighbouringCountry = ((Country) e.getItem());
                selectedNeighbouringCountry.addObserver(view);
                selectedNeighbouringCountry.updateNumOfDiceAllowed(true);
            } else if (sourceName.equals("mode")) {
                if (e.getItem().equals("Choose Dice")) {
                    view.dicePanel.show();
                    view.resultPanel.show();
                    view.resultPlayer.setText("");
                    view.resultOpponent.setText("");
                } else {
                    view.dicePanel.hide();
                    view.resultPanel.hide();

                }
                view.dicePanel.revalidate();
                view.dicePanel.repaint();
                view.resultPanel.revalidate();
                view.resultPanel.repaint();
            }
        }
    }
}
