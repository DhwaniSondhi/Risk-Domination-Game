package gui;

import javax.swing.*;
import java.awt.*;
/**
 * Class containing Gui and functionality of Fortify part of Game
 * */
public class FortifyPanel extends JPanel {
    public FortifyPanel()
    {   GridBagLayout gridBagLayout=new GridBagLayout();
        GridBagConstraints bagConstraints=new GridBagConstraints();
        JLabel jLabelCountries=new JLabel("Countries");
        JLabel jLabelNeigbour=new JLabel("Neighbours");
        String labels[] = { "India", "Pakistan", "China", "Afganistan","Nepal", "Bangladesh", "Sri Lanka", "Malasyia","Mangol", "Russia" };
        String labels1[] = { "India", "Pakistan", "China", "Afganistan","Nepal" };
        JList list = new JList(labels);
        JScrollPane scrollPane = new JScrollPane(list);
        JList list1 = new JList(labels1);
        JScrollPane scrollPane1 = new JScrollPane(list1);
        setLayout(gridBagLayout);

        bagConstraints.fill=GridBagConstraints.VERTICAL;
        bagConstraints.gridx=0;
        bagConstraints.gridy=0;
        add(jLabelCountries,bagConstraints);
        bagConstraints.gridx=0;
        bagConstraints.gridy=1;
        add(scrollPane,bagConstraints);

        bagConstraints.fill=GridBagConstraints.VERTICAL;
        bagConstraints.gridx=1;
        bagConstraints.gridy=0;
        add(jLabelNeigbour,bagConstraints);
        bagConstraints.gridx=1;
        bagConstraints.gridy=1;
        add(scrollPane1,bagConstraints);

        bagConstraints.fill=GridBagConstraints.VERTICAL;
        JPanel jPanelInnerPanelArmies=new JPanel();
        GridBagLayout gridBagLayoutInnerPanelArmies=new GridBagLayout();
        GridBagConstraints gridBagConstraintsInnerPanelArmies=new GridBagConstraints();
        jPanelInnerPanelArmies.setLayout(gridBagLayoutInnerPanelArmies);
        gridBagConstraintsInnerPanelArmies.gridx=0;
        gridBagConstraintsInnerPanelArmies.gridy=0;
        jPanelInnerPanelArmies.add(new JLabel("Army at Country"),gridBagConstraintsInnerPanelArmies);
        gridBagConstraintsInnerPanelArmies.gridx=0;
        gridBagConstraintsInnerPanelArmies.gridy=1;
        JTextField jTextFieldNoOfArmiesCountries=new JTextField(10);
        jPanelInnerPanelArmies.add(jTextFieldNoOfArmiesCountries,gridBagConstraintsInnerPanelArmies);
        gridBagConstraintsInnerPanelArmies.gridx=0;
        gridBagConstraintsInnerPanelArmies.gridy=2;
        jPanelInnerPanelArmies.add(new JLabel("Army at niebhor"),gridBagConstraintsInnerPanelArmies);
        JTextField jTextFieldNoOfArmiesNeighbour=new JTextField(10);
        gridBagConstraintsInnerPanelArmies.gridx=0;
        gridBagConstraintsInnerPanelArmies.gridy=3;
        jPanelInnerPanelArmies.add(jTextFieldNoOfArmiesNeighbour,gridBagConstraintsInnerPanelArmies);
        bagConstraints.gridx=2;
        bagConstraints.gridy=1;
        add(jPanelInnerPanelArmies,bagConstraints);

        bagConstraints.fill=GridBagConstraints.VERTICAL;
        JPanel jPanelTransferArmy=new JPanel();
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
        bagConstraints.gridx=3;
        bagConstraints.gridy=1;
        add(jPanelTransferArmy,bagConstraints);
    }
}
