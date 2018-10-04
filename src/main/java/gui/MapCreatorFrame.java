package gui;

import controller.MapCreatorController;

import javax.swing.*;
import java.awt.*;

public class MapCreatorFrame extends JFrame {

    private boolean isEdit;
    private MapCreatorController controller;

    private JTextField numOfContinents, numOfCountries;

    private JPanel formPanel;
    private JPanel continentsPanel;
    private JPanel countriesPanel;

    public MapCreatorFrame(String title, boolean isEdit) {
        super(title);
        this.isEdit = isEdit;
        controller = new MapCreatorController(this);

        formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        add(formPanel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setVisible(true);

        initializeForm();
    }

    private void initializeForm() {
        formPanel.add(new JLabel("Number of continents:"), getConstraints(0, 0));
        formPanel.add(new JLabel("Number of countries:"), getConstraints(0, 1));
        formPanel.add(new JLabel("Continent Details:"), getConstraints(0, 2));
        formPanel.add(new JLabel("Countries Details:"), getConstraints(0, 3));

        formPanel.add(continentsPanel, getConstraints(1, 2));
        formPanel.add(countriesPanel, getConstraints(1, 3));

        numOfContinents = createTextField("numOfContinents");
        formPanel.add(numOfContinents, getConstraints(1, 0));
        numOfCountries = createTextField("numOfCountries");
        formPanel.add(numOfCountries, getConstraints(1, 1));

    }

    private GridBagConstraints getConstraints(int x, int y) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.ipadx = 3;
        constraints.ipady = 3;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weighty = 1;
        constraints.weightx = 1;
        return constraints;
    }

    private JTextField createTextField(String name) {
        JTextField field = new JTextField();
        field.setName(name);
        field.getDocument().putProperty("owner", field);
        field.getDocument().addDocumentListener(controller);
        return field;
    }

    public void showWarning(String message) {
        JOptionPane.showMessageDialog(null, message, "Error Message", JOptionPane.ERROR_MESSAGE);
    }

    public void updateContinentFields(int number) {
        
    }

    public void updateCountryFields(int number) {

    }
}
