package view;

import controller.AttackController;
import model.Country;
import utility.GameStateChangeListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Collection;

/**
 * View for attackPanel extends {@link JPanel}
 */
public class AttackPanel extends JPanel {
    AttackController attackController;
    private JPanel countryPanel;
    private JPanel neighbouringPanel;
    private JButton proceedButton;

    /**
     * Constructor
     * <p>
     * Sets up country panel and neighbouring country panel using {@link JPanel}
     * Updates the country list in the view using {@link AttackController} updateCountryList function
     */
    public AttackPanel(GameStateChangeListener stateChangeListener) {
        attackController = new AttackController(this);
        attackController.setStateChangeListener(stateChangeListener);

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

        proceedButton = new JButton("Proceed");
        proceedButton.setName("proceed");
        proceedButton.addActionListener(attackController);
        add(proceedButton);

        attackController.updateCountryList();
    }

    /**
     * Adds list of countries to the country panel
     *
     * @param countries collection of countries
     */
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

    /**
     * Adds list of countries to the neighbouring panel
     *
     * @param countries collection of countries
     */
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
