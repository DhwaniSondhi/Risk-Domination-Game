package gui;

import controller.AttackController;
import entity.Config;
import entity.Country;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Collection;

public class AttackPanel extends JPanel {
    private JPanel countryPanel;
    private JPanel neighbouringPanel;

    AttackController attackController;

    public AttackPanel() {
        attackController = new AttackController(this);
        setBackground(Color.LIGHT_GRAY);
        setBorder(new LineBorder(Color.BLACK, 2));
        setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));

        countryPanel = new JPanel();
        countryPanel.setBorder(new LineBorder(Color.black, 1));
        countryPanel.setLayout(new BoxLayout(countryPanel, BoxLayout.Y_AXIS));
        neighbouringPanel = new JPanel();
        neighbouringPanel.setBorder(new LineBorder(Color.black, 1));
        neighbouringPanel.setLayout(new BoxLayout(neighbouringPanel, BoxLayout.Y_AXIS));
        add(countryPanel);
        add(neighbouringPanel);
        showCountries(Config.getInstance().countries.values());
    }

    public void showCountries(Collection<Country> countries) {
        countryPanel.removeAll();
        for (Country country : countries) {
            JPanel row = new JPanel();
            row.setLayout(new GridLayout(1, 2, 50, 20));
            JLabel countryName = new JLabel(country.name + " | " + country.numOfArmies);
            JButton selectCountryBtn = new JButton("Select");
            selectCountryBtn.setName(String.valueOf(country.id));
            selectCountryBtn.addActionListener(attackController);
            row.add(countryName);
            row.add(selectCountryBtn);

            countryPanel.add(row);
        }
        revalidate();
    }

    public void showNeighbouringCountries(Collection<Country> countries) {
        neighbouringPanel.removeAll();
        for (Country country : countries) {
            JPanel row = new JPanel();
            row.setLayout(new GridLayout(1, 2, 50, 20));
            JLabel countryName = new JLabel(country.name + " | " + country.numOfArmies);
            JButton attackCountryBtn = new JButton("Attack");
            attackCountryBtn.setName(String.valueOf(country.id));
            attackCountryBtn.addActionListener(attackController);
            row.add(countryName);
            row.add(attackCountryBtn);

            neighbouringPanel.add(row);
        }

        revalidate();

    }

}
