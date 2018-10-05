package controller;

import gui.MapCreatorFrame;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;

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
    public void actionPerformed(ActionEvent event) {
        try {
            String mapName = view.mapName.getText();
            HashSet<String> continentCheckList = new HashSet<>();
            HashSet<String> countryCheckList = new HashSet<>();
            HashSet<String> neighboursCheckList = new HashSet<>();

            ArrayList<String> continents = new ArrayList<>();
            for (JTextField field : view.getContinentFields()) {
                String text = field.getText().trim();
                if (!text.isEmpty()) {
                    String[] data = text.replace(" ", "").split("=");

                    if (data.length != 2) throw new IllegalStateException("CV not provided");
                    int cv = Integer.valueOf(data[1]);

                    if (!continentCheckList.add(data[0]))
                        throw new IllegalArgumentException("Same continent name added twice");

                    continents.add(text);
                }
            }

            ArrayList<String> countries = new ArrayList<>();
            for (JTextField field : view.getCountryFields()) {
                String text = field.getText().trim();
                if (!text.isEmpty()) {
                    String[] data = text.replace(" ", "").split(",");


                    if (data.length <= 1)
                        throw new IllegalStateException("No continent provided for a country");
                    if (data.length <= 2)
                        throw new IllegalStateException("No neighbours provided for a country");
                    if (!countryCheckList.add(data[0]))
                        throw new IllegalArgumentException("Same country name added twice");
                    if (!continentCheckList.contains(data[1]))
                        throw new IllegalStateException("Invalid continent associated with a country.");

                    for (int i = 2; i < data.length; i++) {
                        neighboursCheckList.add(data[i]);
                    }
                    countries.add(text);
                }
            }

            if (continents.isEmpty() || countries.isEmpty())
                throw new IllegalStateException("No data provided");

            if (countryCheckList.size() != neighboursCheckList.size())
                throw new IllegalStateException("Not all the country has a neighbour");

            //saveMapToFile(mapName, continents, countries);

        } catch (NumberFormatException nfe) {
            view.showWarning("Not a valid number");
        } catch (IllegalStateException ise) {
            view.showWarning(ise.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            view.showWarning(e.getMessage());
        }
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
            if (value.trim().isEmpty()) num = 0;
            else num = Integer.valueOf(value.trim());
        } catch (Exception e) {
            num = -1;
        } finally {
            return num;
        }
    }
}

/*
Sample inputs for file creation

Asia=3
Europe=6

Nepal, Asia, India,Germany,Portugal
India, Asia, Nepal, Spain
Spain,Europe,India,Portugal
Portugal,Europe,Nepal,Spain,Italy
Italy,Europe,Portugal,Spain
Germany,Europe,Spain,Nepal

*/