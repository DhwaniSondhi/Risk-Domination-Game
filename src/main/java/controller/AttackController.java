package controller;

import gui.AttackPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedCountryId = Integer.valueOf(((JButton) e.getSource()).getName());
        if(e.getActionCommand().equalsIgnoreCase("select")) {
            view.showNeighbouringCountries(model.getNeighbourCountries(selectedCountryId));
        }
        else{

        }

    }
}
