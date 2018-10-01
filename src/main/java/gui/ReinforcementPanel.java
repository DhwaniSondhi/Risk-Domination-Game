package gui;


import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;


/**
 * Gui Part for Reinforcement Panel in the game
 * extends {@Link JPanel} */

public class ReinforcementPanel extends JPanel{


    public ReinforcementPanel(){
        setLayout(new GridBagLayout());
        setBackground(Color.LIGHT_GRAY);
        setBorder(new LineBorder(Color.black, 1));
        GridBagConstraints gridContraints=new GridBagConstraints();
        gridContraints.fill=GridBagConstraints.BOTH;
        gridContraints.gridx=0;
        gridContraints.gridy=0;
        JPanel cardsList=new JPanel();
        cardsList.setBorder(new LineBorder(Color.black, 1));
        cardsList.setLayout(new GridLayout(3,2));

        JLabel infantry = new JLabel("3 INFANTRY");
        JButton selectInfantryBtn = new JButton("Select");
        JLabel artillery = new JLabel("3 ARTILLERY");
        JButton selectArtilleryBtn = new JButton("Select");
        JLabel cavalry = new JLabel("3 CAVALRY");
        JButton selectCavalryBtn = new JButton("Select");

        cardsList.add(infantry);
        cardsList.add(selectInfantryBtn);
        cardsList.add(artillery);
        cardsList.add(selectArtilleryBtn);
        cardsList.add(cavalry);
        cardsList.add(selectCavalryBtn);

        add(cardsList,gridContraints);


        gridContraints.gridx=1;
        gridContraints.gridy=0;

        JPanel cardsList1=new JPanel();
        cardsList1.setBorder(new LineBorder(Color.black, 1));
        cardsList1.setLayout(new GridLayout(3,1));
        JLabel infantry1 = new JLabel("2 INFANTRY");
        JLabel artillery2 = new JLabel("1 ARTILLERY");
        cardsList1.add(infantry1);
        cardsList1.add(artillery2);
        add(cardsList1,gridContraints);

        JPanel buttons=new JPanel();
        buttons.setLayout(new GridLayout(2,1));
        JButton update=new JButton("Update");
        buttons.add(update);
        JButton reset=new JButton("Reset");
        buttons.add(reset);
        gridContraints.gridx=2;
        gridContraints.gridy=0;
        add(buttons,gridContraints);


        JPanel armiesDivide=new JPanel();
        armiesDivide.setLayout(new GridLayout(1,2));
        JLabel armies=new JLabel("Available armies: 10");
        armiesDivide.add(armies);
        gridContraints.gridx=0;
        gridContraints.gridy=1;
        add(armiesDivide,gridContraints);

        gridContraints.gridx=0;
        gridContraints.gridy=2;
        JPanel countryArmyDivide=new JPanel();
        countryArmyDivide.setBorder(new LineBorder(Color.black, 1));
        countryArmyDivide.setLayout(new GridLayout(2,3));

        JLabel country1 = new JLabel("Country1");
        JTextField fieldCountry1=new JTextField();
        JButton addCountry1 = new JButton("Add");
        JLabel country2 = new JLabel("Country2");
        JTextField fieldCountry2=new JTextField();
        JButton addCountry2 = new JButton("Add");


        countryArmyDivide.add(country1);
        countryArmyDivide.add(fieldCountry1);
        countryArmyDivide.add(addCountry1);
        countryArmyDivide.add(country2);
        countryArmyDivide.add(fieldCountry2);
        countryArmyDivide.add(addCountry2);

        add(countryArmyDivide,gridContraints);


    }
}
