package gui;

import controller.MapCreatorController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * GUI class for the Map Create Form
 */
public class MapCreatorFrame extends JFrame {

    /**
     * From state if it is in edit mode
     */
    private boolean isEdit;
    /**
     * Controller for the form
     */
    private MapCreatorController controller;

    public JTextField numOfContinents, numOfCountries, mapName;

    private JPanel formPanel;
    private JPanel continentsPanel;
    private JPanel countriesPanel;
    private JButton submitButton;

    /**
     * Constructor
     *
     * @param title  title for the frame
     * @param isEdit set the form mode
     */
    public MapCreatorFrame(String title, boolean isEdit) {
        super(title);
        this.isEdit = isEdit;
        controller = new MapCreatorController(this);

        formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        add(formPanel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(600, 600);
        setVisible(true);

        initializeForm();
    }


    /**
     * Initialize the form panel with default text-fields and labels
     */
    private void initializeForm() {
        formPanel.add(new JLabel("Number of continents:"), getConstraints(0, 0));
        formPanel.add(new JLabel("Number of countries:"), getConstraints(0, 1));
        formPanel.add(new JLabel("Continent Details:"), getConstraints(0, 3));
        formPanel.add(new JLabel("(ContinentName = CV)"), getConstraints(0, 2));
        formPanel.add(new JLabel("Countries Details:"), getConstraints(0, 5));
        formPanel.add(new JLabel("(CountryName,ContinentName,NeighbouringCountries)"), getConstraints(0, 4));
        formPanel.add(new JLabel("Map name:"), getConstraints(0, 6));

        continentsPanel = new JPanel();
        continentsPanel.setLayout(new BoxLayout(continentsPanel, BoxLayout.Y_AXIS));
        continentsPanel.setBorder(new LineBorder(Color.black, 2));
        countriesPanel = new JPanel();
        countriesPanel.setLayout(new BoxLayout(countriesPanel, BoxLayout.Y_AXIS));
        countriesPanel.setBorder(new LineBorder(Color.black, 2));

        formPanel.add(continentsPanel, getConstraints(1, 3, 1, 0));
        formPanel.add(countriesPanel, getConstraints(1, 5, 1, 0));

        numOfContinents = createTextField("numOfContinents");
        formPanel.add(numOfContinents, getConstraints(1, 0, 1, 0));
        numOfCountries = createTextField("numOfCountries");
        formPanel.add(numOfCountries, getConstraints(1, 1, 1, 0));

        mapName = new JTextField();
        formPanel.add(mapName, getConstraints(1, 6, 1, 0));

        submitButton = new JButton("Save");
        formPanel.add(submitButton, getConstraints(1, 7, 1, 0));
        submitButton.addActionListener(controller);
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
     * Creates {@link JTextField} with given name.
     * Also adds the controller as {@link javax.swing.event.DocumentListener}
     *
     * @param name name for the text field
     */
    private JTextField createTextField(String name) {
        JTextField field = new JTextField();
        field.setName(name);
        field.getDocument().putProperty("owner", field);
        field.getDocument().addDocumentListener(controller);
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
