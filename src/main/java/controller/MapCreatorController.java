package controller;

import gui.MapCreatorFrame;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the Controller for the MapCreator. see {@link BaseController}
 * implements {@link ActionListener} for MenuItems
 */
public class MapCreatorController extends BaseController<MapCreatorFrame> implements
        ActionListener,
        DocumentListener {

    public MapCreatorController(MapCreatorFrame view) {
        super(view);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        JTextField owner = (JTextField) e.getDocument().getProperty("owner");
        handleTextChange(owner);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        JTextField owner = (JTextField) e.getDocument().getProperty("owner");
        handleTextChange(owner);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    }

    private void handleTextChange(JTextField field) {
        if (field.getName().equals("numOfContinents") || field.getName().equals("numOfCountries")) {
            int number = getNumberValue(field.getText());
            if (number < 0) view.showWarning("Please enter a valid number");

            if (field.getName().equals("numOfContinents")) {
                view.updateContinentFields(number);
            } else {
                view.updateCountryFields(number);
            }
        }
    }

    private int getNumberValue(String value) {
        int num = -1;
        try {
            num = Integer.valueOf(value.trim());
        } catch (Exception e) {
            num = -1;
        } finally {
            return num;
        }
    }
}
