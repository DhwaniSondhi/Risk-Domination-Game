package gui;

import controller.FortifyController;
import entity.Country;
import entity.Player;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Class containing Gui and functionality of Fortify part of Game
 * */
public class FortifyPanel extends JPanel {
    private JPanel jPanelCountries;
    private JPanel jPanelNeighbors;
    private JPanel jPanelInnerPanelArmies;
    private JPanel jPanelTransferArmy;
    private GridBagLayout gridBagLayoutMain;
    private GridBagConstraints bagConstraintsMain;
    FortifyController fortifyController;

    /**
     * Constructor
     *
     */

    public FortifyPanel() {
        fortifyController=new FortifyController(this);
        gridBagLayoutMain=new GridBagLayout();
        bagConstraintsMain=new GridBagConstraints();
        setLayout(gridBagLayoutMain);
        setBackground(Color.LIGHT_GRAY);
        setBorder(new LineBorder(Color.BLACK, 2));

        JLabel jLabelfortify=new JLabel("fortify");
        jLabelfortify.setFont(new Font("Fortify", Font.BOLD,20));
        bagConstraintsMain.fill=GridBagConstraints.VERTICAL;
        bagConstraintsMain.gridwidth=2;
        bagConstraintsMain.gridx=1;
        bagConstraintsMain.gridy=0;
        add(jLabelfortify,bagConstraintsMain);

        bagConstraintsMain.fill=GridBagConstraints.VERTICAL;
        jPanelCountries=new JPanel();
        bagConstraintsMain.gridwidth=1;
        bagConstraintsMain.gridx=0;
        bagConstraintsMain.gridy=1;
        add(jPanelCountries,bagConstraintsMain);

        bagConstraintsMain.fill=GridBagConstraints.VERTICAL;
        jPanelNeighbors=new JPanel();
        bagConstraintsMain.gridx=1;
        bagConstraintsMain.gridy=1;
        add(jPanelNeighbors,bagConstraintsMain);
        jPanelInnerPanelArmies=new JPanel();

        bagConstraintsMain.fill=GridBagConstraints.VERTICAL;
        bagConstraintsMain.gridx=2;
        bagConstraintsMain.gridy=1;
        add(jPanelInnerPanelArmies,bagConstraintsMain);
        jPanelTransferArmy=new JPanel();

        bagConstraintsMain.fill=GridBagConstraints.VERTICAL;
        bagConstraintsMain.gridx=3;
        bagConstraintsMain.gridy=1;
        add(jPanelTransferArmy,bagConstraintsMain);
        fortifyController.updateCountryListFortify();

    }

    public void showCountriesFortify(Collection<Country> countries) {
        ArrayList<String> countriesList=new ArrayList<String>();
        for (Country country : countries)
        {

            countriesList.add(country.name);


        }
        String[] countriesNames=new String[countriesList.size()];
        for(int i=0;i<countriesList.size();i++)
        {
            countriesNames[i]=countriesList.get(i);
        }
        GridBagLayout gridBagLayoutCountriesPanel=new GridBagLayout();
        GridBagConstraints gridBagConstraintsCountriesPanel=new GridBagConstraints();

        jPanelCountries.setLayout(gridBagLayoutCountriesPanel);
        gridBagConstraintsCountriesPanel.fill=GridBagConstraints.VERTICAL;
        gridBagConstraintsCountriesPanel.gridx=0;
        gridBagConstraintsCountriesPanel.gridy=0;
        JLabel jLabelCountries=new JLabel("Countries");
        jPanelCountries.add(jLabelCountries,gridBagConstraintsCountriesPanel);
        JList list = new JList(countriesNames);
        JScrollPane scrollPaneCountries = new JScrollPane(list);
        gridBagConstraintsCountriesPanel.gridx=0;
        gridBagConstraintsCountriesPanel.gridy=1;
        jPanelCountries.add(scrollPaneCountries,gridBagConstraintsCountriesPanel);
        revalidate();
    }

    public void showNeighbouringCountriesFortify(Collection<Country> countries) {
        ArrayList<String> countriesList=new ArrayList<String>();
        for (Country country : countries)
        {

            countriesList.add(country.name);


        }
        String[] countriesNames=new String[countriesList.size()];
        for(int i=0;i<countriesList.size();i++)
        {
            countriesNames[i]=countriesList.get(i);
        }
        GridBagLayout gridBagLayoutCountriesPanel=new GridBagLayout();
        GridBagConstraints gridBagConstraintsCountriesPanel=new GridBagConstraints();

        jPanelNeighbors.setLayout(gridBagLayoutCountriesPanel);
        gridBagConstraintsCountriesPanel.fill=GridBagConstraints.VERTICAL;
        gridBagConstraintsCountriesPanel.gridx=0;
        gridBagConstraintsCountriesPanel.gridy=0;
        JLabel jLabelCountries=new JLabel("Neighbours");
        jPanelNeighbors.add(jLabelCountries,gridBagConstraintsCountriesPanel);
        JList list = new JList(countriesNames);
        JScrollPane scrollPaneCountries = new JScrollPane(list);
        gridBagConstraintsCountriesPanel.gridx=0;
        gridBagConstraintsCountriesPanel.gridy=1;
        jPanelNeighbors.add(scrollPaneCountries,gridBagConstraintsCountriesPanel);
        jPanelNeighbors.revalidate();
        revalidate();
    }

    public void TransferFortify() {
        GridBagLayout gridBagLayoutInnerPanelArmies=new GridBagLayout();
        GridBagConstraints gridBagConstraintsInnerPanelArmies=new GridBagConstraints();
        jPanelInnerPanelArmies.setLayout(gridBagLayoutInnerPanelArmies);
        gridBagConstraintsInnerPanelArmies.fill=GridBagConstraints.VERTICAL;
        gridBagConstraintsInnerPanelArmies.gridx=0;
        gridBagConstraintsInnerPanelArmies.gridy=0;
        jPanelInnerPanelArmies.add(new JLabel("Army at Country"),gridBagConstraintsInnerPanelArmies);
        gridBagConstraintsInnerPanelArmies.gridx=0;
        gridBagConstraintsInnerPanelArmies.gridy=1;
        JTextField jTextFieldNoOfArmiesCountries=new JTextField(10);
        jPanelInnerPanelArmies.add(jTextFieldNoOfArmiesCountries,gridBagConstraintsInnerPanelArmies);
        gridBagConstraintsInnerPanelArmies.gridx=0;
        gridBagConstraintsInnerPanelArmies.gridy=2;
        jPanelInnerPanelArmies.add(new JLabel("Army at neighbor"),gridBagConstraintsInnerPanelArmies);
        JTextField jTextFieldNoOfArmiesNeighbour=new JTextField(10);
        gridBagConstraintsInnerPanelArmies.gridx=0;
        gridBagConstraintsInnerPanelArmies.gridy=3;
        jPanelInnerPanelArmies.add(jTextFieldNoOfArmiesNeighbour,gridBagConstraintsInnerPanelArmies);
        jPanelInnerPanelArmies.revalidate();

        GridBagLayout gridBagLayoutTransferArmy=new GridBagLayout();
        GridBagConstraints transferPanelConstraints=new GridBagConstraints();
        jPanelTransferArmy.setLayout(gridBagLayoutTransferArmy);
        transferPanelConstraints.fill=GridBagConstraints.VERTICAL;
        JComboBox jComboBoxNoOfArmies=new JComboBox();
        jComboBoxNoOfArmies.addItem(1);
        jComboBoxNoOfArmies.addItem(2);
        jComboBoxNoOfArmies.addItem(3);
        jComboBoxNoOfArmies.addItem(4);
        jComboBoxNoOfArmies.addItem(5);
        transferPanelConstraints.gridx=0;
        transferPanelConstraints.gridy=0;
        jPanelTransferArmy.add(new JLabel("Select No. of armies to transfer"),transferPanelConstraints);
        transferPanelConstraints.gridx=0;
        transferPanelConstraints.gridy=1;
        jPanelTransferArmy.add(jComboBoxNoOfArmies,transferPanelConstraints);
        transferPanelConstraints.gridx=0;
        transferPanelConstraints.gridy=2;
        JButton jButtonTransfer=new JButton("Transfer");
        jPanelTransferArmy.add(jButtonTransfer,transferPanelConstraints);
        jPanelTransferArmy.revalidate();
        revalidate();
    }
}
