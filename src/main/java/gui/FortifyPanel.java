package gui;

import controller.FortifyController;
import entity.Country;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Class containing Gui and functionality of Fortify part of Game
 */
public class FortifyPanel extends JPanel {
    private JPanel jPanelCountries;
    private JPanel jPanelNeighbors;
    private JPanel jPanelInnerPanelArmies;
    private JPanel jPanelTransferArmy;
    private GridBagLayout gridBagLayoutMain;
    private GridBagConstraints bagConstraintsMain;
    FortifyController fortifyController;
    JTextField jTextFieldNoOfArmiesCountries;
    JTextField jTextFieldNoOfArmiesNeighbour;
    JScrollPane scrollPaneNeighboringCountries;
    JComboBox jComboBoxNoOfArmies;

    /**
     * Constructor
     * It set up the Panels for Number of countries a player have and countries to which the can transfer army
     * Also set up the panels for choosing number of armies to transfer
     */

    public FortifyPanel() {
        fortifyController = new FortifyController(this);
        gridBagLayoutMain = new GridBagLayout();
        bagConstraintsMain = new GridBagConstraints();
        setLayout(gridBagLayoutMain);
        setBackground(Color.LIGHT_GRAY);
        setBorder(new LineBorder(Color.BLACK, 2));

        JLabel jLabelfortify = new JLabel("fortify");
        jLabelfortify.setFont(new Font("Fortify", Font.BOLD, 20));
        bagConstraintsMain.fill = GridBagConstraints.VERTICAL;
        bagConstraintsMain.gridwidth = 2;
        bagConstraintsMain.gridx = 1;
        bagConstraintsMain.gridy = 0;
        add(jLabelfortify, bagConstraintsMain);

        bagConstraintsMain.fill = GridBagConstraints.VERTICAL;
        jPanelCountries = new JPanel();
        bagConstraintsMain.gridwidth = 1;
        bagConstraintsMain.gridx = 0;
        bagConstraintsMain.gridy = 1;
        add(jPanelCountries, bagConstraintsMain);

        bagConstraintsMain.fill = GridBagConstraints.VERTICAL;
        jPanelNeighbors = new JPanel();
        bagConstraintsMain.gridx = 1;
        bagConstraintsMain.gridy = 1;
        add(jPanelNeighbors, bagConstraintsMain);
        jPanelInnerPanelArmies = new JPanel();

        bagConstraintsMain.fill = GridBagConstraints.VERTICAL;
        bagConstraintsMain.gridx = 2;
        bagConstraintsMain.gridy = 1;
        add(jPanelInnerPanelArmies, bagConstraintsMain);
        jPanelTransferArmy = new JPanel();

        bagConstraintsMain.fill = GridBagConstraints.VERTICAL;
        bagConstraintsMain.gridx = 3;
        bagConstraintsMain.gridy = 1;
        add(jPanelTransferArmy, bagConstraintsMain);
        fortifyController.updateCountryListFortify();

    }

    /**
     * It shows countries that are owned by currently playing player
     *
     * @param countries countries own by current player
     */
    public void showCountriesFortify(Collection<Country> countries) {
        GridBagLayout gridBagLayoutCountriesPanel = new GridBagLayout();
        GridBagConstraints gridBagConstraintsCountriesPanel = new GridBagConstraints();

        jPanelCountries.setLayout(gridBagLayoutCountriesPanel);
        gridBagConstraintsCountriesPanel.fill = GridBagConstraints.VERTICAL;
        gridBagConstraintsCountriesPanel.gridx = 0;
        gridBagConstraintsCountriesPanel.gridy = 0;
        JLabel jLabelCountries = new JLabel("Countries");
        jPanelCountries.add(jLabelCountries, gridBagConstraintsCountriesPanel);
        JList countriesList = new JList(countries.toArray());
        countriesList.setName("Country");
        JScrollPane scrollPaneCountries = new JScrollPane(countriesList);
        gridBagConstraintsCountriesPanel.gridx = 0;
        gridBagConstraintsCountriesPanel.gridy = 1;
        jPanelCountries.add(scrollPaneCountries, gridBagConstraintsCountriesPanel);
        countriesList.addListSelectionListener(fortifyController);
        jPanelCountries.revalidate();
        revalidate();
    }

    /**
     * It set up the component for displaying neighboring countries
     * It takes current player's countries as a default
     *
     * @param countries Neighboring countries
     */

    public void showNeighbouringCountriesFortify(Collection<Country> countries) {
        GridBagLayout gridBagLayoutCountriesPanel = new GridBagLayout();
        GridBagConstraints gridBagConstraintsCountriesPanel = new GridBagConstraints();

        jPanelNeighbors.setLayout(gridBagLayoutCountriesPanel);
        gridBagConstraintsCountriesPanel.fill = GridBagConstraints.VERTICAL;
        gridBagConstraintsCountriesPanel.gridx = 0;
        gridBagConstraintsCountriesPanel.gridy = 0;
        JLabel jLabelCountries = new JLabel("Neighbours");
        jPanelNeighbors.add(jLabelCountries, gridBagConstraintsCountriesPanel);
        JList list = new JList(countries.toArray());
        //((Country) list.getSelectedValue()).id;
        scrollPaneNeighboringCountries = new JScrollPane(list);
        gridBagConstraintsCountriesPanel.gridx = 0;
        gridBagConstraintsCountriesPanel.gridy = 1;
        jPanelNeighbors.add(scrollPaneNeighboringCountries, gridBagConstraintsCountriesPanel);
        jPanelNeighbors.revalidate();
        revalidate();
    }


    /**
     * It setup the components to show number of armies in a county and its neighboring countries
     * It also allow to choose number of armies one can transfer from a country to other country
     */
    public void transferFortify() {
        GridBagLayout gridBagLayoutInnerPanelArmies = new GridBagLayout();
        GridBagConstraints gridBagConstraintsInnerPanelArmies = new GridBagConstraints();
        jPanelInnerPanelArmies.setLayout(gridBagLayoutInnerPanelArmies);
        gridBagConstraintsInnerPanelArmies.fill = GridBagConstraints.VERTICAL;
        gridBagConstraintsInnerPanelArmies.gridx = 0;
        gridBagConstraintsInnerPanelArmies.gridy = 0;
        jPanelInnerPanelArmies.add(new JLabel("Army at Country"), gridBagConstraintsInnerPanelArmies);
        gridBagConstraintsInnerPanelArmies.gridx = 0;
        gridBagConstraintsInnerPanelArmies.gridy = 1;
        jTextFieldNoOfArmiesCountries = new JTextField(10);
        jPanelInnerPanelArmies.add(jTextFieldNoOfArmiesCountries, gridBagConstraintsInnerPanelArmies);
        gridBagConstraintsInnerPanelArmies.gridx = 0;
        gridBagConstraintsInnerPanelArmies.gridy = 2;
        jPanelInnerPanelArmies.add(new JLabel("Army at neighbor"), gridBagConstraintsInnerPanelArmies);
        jTextFieldNoOfArmiesNeighbour = new JTextField(10);
        gridBagConstraintsInnerPanelArmies.gridx = 0;
        gridBagConstraintsInnerPanelArmies.gridy = 3;
        jPanelInnerPanelArmies.add(jTextFieldNoOfArmiesNeighbour, gridBagConstraintsInnerPanelArmies);
        jPanelInnerPanelArmies.revalidate();

        GridBagLayout gridBagLayoutTransferArmy = new GridBagLayout();
        GridBagConstraints transferPanelConstraints = new GridBagConstraints();
        jPanelTransferArmy.setLayout(gridBagLayoutTransferArmy);
        transferPanelConstraints.fill = GridBagConstraints.VERTICAL;
        jComboBoxNoOfArmies = new JComboBox();
        jComboBoxNoOfArmies.addItem(1);
        jComboBoxNoOfArmies.addItem(2);
        jComboBoxNoOfArmies.addItem(3);
        jComboBoxNoOfArmies.addItem(4);
        jComboBoxNoOfArmies.addItem(5);
        transferPanelConstraints.gridx = 0;
        transferPanelConstraints.gridy = 0;
        jPanelTransferArmy.add(new JLabel("Select No. of armies to transfer"), transferPanelConstraints);
        transferPanelConstraints.gridx = 0;
        transferPanelConstraints.gridy = 1;
        jPanelTransferArmy.add(jComboBoxNoOfArmies, transferPanelConstraints);
        transferPanelConstraints.gridx = 0;
        transferPanelConstraints.gridy = 2;
        jComboBoxNoOfArmies.setName("ComboBox");
        JButton jButtonTransfer = new JButton("Transfer");
        jButtonTransfer.addActionListener(fortifyController);
        jPanelTransferArmy.add(jButtonTransfer, transferPanelConstraints);
        jPanelTransferArmy.revalidate();
        revalidate();
    }

    /**
     * Update the value of number of armies on currently selected country
     *
     * @param numberOfArmies new value for TextField
     */
    public void updateCountriesArmyTextField(int numberOfArmies) {
        jTextFieldNoOfArmiesCountries.setText(Integer.toString(numberOfArmies));

    }

    /**
     * Update the value of Armies for selected Neighboring country
     *
     * @param numberOfArmies new value for TextField
     */
    public void updateNeighboringCountriesArmyTextField(int numberOfArmies) {
        jTextFieldNoOfArmiesNeighbour.setText(Integer.toString(numberOfArmies));
    }

    /**
     * Function to get the value of ComboBox of armies to transfer
     */
    public void getTheValueOfComboBox()
    {
        fortifyController.updateComboBoxValue((Integer) jComboBoxNoOfArmies.getSelectedItem());
    }
    /**
     * Update the values of ComboBox to select number of armies to transfer
     *
     * @param numberOfArmies maximum number of army one can transfer
     */
    public void updateJComboboxArmies(int numberOfArmies) {
        jComboBoxNoOfArmies.removeAllItems();
        for (int i = 1; i < numberOfArmies; i++) {
            jComboBoxNoOfArmies.addItem(i);
        }
        jComboBoxNoOfArmies.revalidate();


    }

    /**
     * Updates the list of neighboring countries on the basis of selection
     *
     * @param neighbor contains the id's and names of neighboring countries
     */
    public void updateNeighboringCountries(LinkedHashMap<Integer, Country> neighbor) {
        Country[] neighborCountriesList = new Country[neighbor.keySet().size()];
        int index = 0;
        for (Map.Entry getCountries : neighbor.entrySet()) {
            neighborCountriesList[index] = (Country) getCountries.getValue();
            index++;
        }
        JList neighborUpdateList = new JList(neighborCountriesList);
        neighborUpdateList.setName("Neigbhor");
        scrollPaneNeighboringCountries.setViewportView(neighborUpdateList);
        neighborUpdateList.addListSelectionListener(fortifyController);

    }
}
