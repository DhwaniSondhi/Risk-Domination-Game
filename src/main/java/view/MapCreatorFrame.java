package view;

import controller.MapCreatorController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * GUI class for the Map Create Form
 */
public class MapCreatorFrame extends JFrame {

    public JTextField mapName;
    public JComboBox<Integer> numOfContinents, numOfCountries;
    /**
     * From state if it is in edit mode
     */
    private boolean isEdit;
    /**
     * Controller for the form
     */
    private MapCreatorController controller;
    private JScrollPane container;
    private JPanel formPanel;
    private JPanel continentsPanel;
    private JPanel countriesPanel;
    private JButton submitButton;

    /**
     * Constructor
     *
     * @param title title for the frame
     */
    public MapCreatorFrame(String title) {
        super(title);
        this.isEdit = false;
        controller = new MapCreatorController(this);

        initializeForm();
    }

    /**
     * Constructor
     *
     * @param title        title for the frame
     * @param selectedFile selected File From which the content was loaded
     */
    public MapCreatorFrame(String title, File selectedFile) {
        super(title);
        this.isEdit = true;
        controller = new MapCreatorController(this);

        initializeForm();

        controller.loadDataFromFile(selectedFile);
    }

    /**
     * Initialize the form panel with default text-fields and labels
     */
    private void initializeForm() {
        formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(600, 600);
        setVisible(true);

        formPanel.add(new JLabel("Map name:"), getConstraints(0, 0));
        mapName = new JTextField();
        formPanel.add(mapName, getConstraints(1, 0, 1, 0));

        formPanel.add(new JLabel("======================================="), getConstraints(0, 1));

        formPanel.add(new JLabel("======================================="), getConstraints(0, 4));

        formPanel.add(new JLabel("<html>Continent Details:<br/><i>(ContinentName = CV)</i><html>"), getConstraints(0, 3));
        continentsPanel = new JPanel();
        continentsPanel.setLayout(new BoxLayout(continentsPanel, BoxLayout.Y_AXIS));
        continentsPanel.setBorder(new LineBorder(Color.black, 2));
        formPanel.add(continentsPanel, getConstraints(1, 3, 1, 0));


        formPanel.add(new JLabel("<html>Countries Details: <br/><i>(CountryName,ContinentName,NeighbouringCountries)</i></html>"), getConstraints(0, 7));
        countriesPanel = new JPanel();
        countriesPanel.setLayout(new BoxLayout(countriesPanel, BoxLayout.Y_AXIS));
        countriesPanel.setBorder(new LineBorder(Color.black, 2));
        formPanel.add(countriesPanel, getConstraints(1, 7, 1, 0));

        formPanel.add(new JLabel("Number of continents:"), getConstraints(0, 2));
        numOfContinents = createComboBox("numOfContinents", 10);
        formPanel.add(numOfContinents, getConstraints(1, 2, 1, 0));

        formPanel.add(new JLabel("Number of countries:"), getConstraints(0, 5));
        numOfCountries = createComboBox("numOfCountries", 80);
        formPanel.add(numOfCountries, getConstraints(1, 5, 1, 0));

        submitButton = new JButton("Save");
        submitButton.setName("Save");
        formPanel.add(submitButton, getConstraints(1, 9, 1, 0));
        submitButton.addActionListener(controller);

        container = new JScrollPane(formPanel);
        add(container);
    }

    /**
     * Pre-fills the form with provided data
     *
     * @param mapName       name of the mapFile
     * @param continentData list of continents with its CV
     * @param countryData   list of countries with its neighbours
     */
    public void fillFormWithData(String mapName, List<String> continentData, List<String> countryData) {
        this.mapName.setText(mapName);

        numOfContinents.setSelectedIndex(continentData.size() - 1);
        numOfCountries.setSelectedIndex(countryData.size() - 1);

        for (int i = 0; i < continentData.size(); i++) {
            ((JTextField) continentsPanel.getComponents()[i]).setText(continentData.get(i));
        }

        for (int i = 0; i < countryData.size(); i++) {
            ((JTextField) countriesPanel.getComponents()[i]).setText(countryData.get(i));
        }
    }

    /**
     * Creates {@link GridBagConstraints} with provided gridX and gridY values
     *
     * @param x value for constraints gridx (row in the grid)
     * @param y value for constraints gridY (col in the grid)
     * @return default constraints (see {@link GridBagConstraints}) with provided x,y values
     */
    private GridBagConstraints getConstraints(int x, int y) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.ipadx = 3;
        constraints.ipady = 3;
        constraints.fill = GridBagConstraints.BOTH;
        return constraints;
    }

    /**
     * Creates {@link GridBagConstraints} with provided gridX and gridY values
     *
     * @param x  value for constraints gridx (row in the grid)
     * @param y  value for constraints gridY (col in the grid)
     * @param wx value for constraints weightx
     * @param wy value for constraints weighty
     * @return default constraints (see {@link GridBagConstraints}) with provided x,y values
     */
    private GridBagConstraints getConstraints(int x, int y, int wx, int wy) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.ipadx = 3;
        constraints.ipady = 3;
        constraints.weighty = wy;
        constraints.weightx = wx;
        constraints.fill = GridBagConstraints.BOTH;
        return constraints;
    }


    /**
     * Creates {@link JComboBox} with given name and range og numbers.
     * Also adds the controller as {@link javax.swing.event.DocumentListener}
     *
     * @param name  name for the field
     * @param range range of data to show in combo-box
     * @return combobox with range of data
     */
    private JComboBox<Integer> createComboBox(String name, int range) {
        Integer[] data = new Integer[range];
        for (int i = 0; i < range; i++) {
            data[i] = i + 1;
        }
        JComboBox<Integer> field = new JComboBox<>(data);
        field.setName(name);
        field.addActionListener(controller);
        field.setSelectedIndex(0);
        return field;
    }


    /**
     * Show error prompt
     *
     * @param message message to display on the prompt
     */
    public void showWarning(String message) {
        JOptionPane.showMessageDialog(null, message, "Error Message", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Add or remove text fields for continents based on number provided
     *
     * @param number total number of textfields that need to be displayed
     */
    public void updateContinentFields(int number) {
        addOrRemoveTextFields(continentsPanel, number);
    }

    /**
     * Add or remove text fields for countries panel based on number provided
     *
     * @param number total number of textfields that need to be displayed
     */
    public void updateCountryFields(int number) {
        addOrRemoveTextFields(countriesPanel, number);
    }


    /**
     * Add or remove text fields for panel based on munber provided
     *
     * @param panel  parent panel to add/remove text fields to/from
     * @param number total number of textfields that need to be displayed
     */
    private void addOrRemoveTextFields(JPanel panel, int number) {
        int currentSize = panel.getComponents().length;
        int diff = Math.abs(currentSize - number);
        if (currentSize <= number) {
            for (int i = 0; i < diff; i++) {
                JTextField field = new JTextField();
                panel.add(field);
            }
        } else {
            for (int i = currentSize - 1; i > currentSize - (diff + 1); i--) {
                panel.remove(i);
            }
        }
        panel.revalidate();
    }


    /**
     * Provides list of textFields in countryPanel
     *
     * @return List of {@link JTextField}
     */
    public ArrayList<JTextField> getCountryFields() {
        ArrayList<JTextField> fields = new ArrayList<>();
        for (Component component : countriesPanel.getComponents()) {
            fields.add((JTextField) component);
        }
        return fields;
    }

    /**
     * Provides list of textFields in continentPanel
     *
     * @return List of {@link JTextField}
     */
    public ArrayList<JTextField> getContinentFields() {
        ArrayList<JTextField> fields = new ArrayList<>();
        for (Component component : continentsPanel.getComponents()) {
            fields.add((JTextField) component);
        }
        return fields;
    }
}
