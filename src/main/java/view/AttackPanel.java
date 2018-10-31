package view;

import controller.AttackController;
import model.Country;
import utility.GameStateChangeListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

/**
 * View for attackPanel extends {@link JPanel}
 */
public class AttackPanel extends JPanel implements Observer {
    /**
     * Controller for AttackPanel
     */
    AttackController attackController;
    /**
     * Panel for displaying Countries owned by current player
     */
    private JPanel countryPanel;
    /**
     * Panel for displaying neighboring countries to selected country to which fortify can done
     */
    private JPanel neighbouringPanel;
    /**
     * Button to proceed to next part of game
     */
    private JButton proceedButton;

    /**
     * Constructor
     * <p>
     * Sets up country panel and neighbouring country panel using {@link JPanel}
     * Updates the country list in the view using {@link AttackController} updateCountryList function
     *
     * @param stateChangeListener observer for game state
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

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {
        
    }
}
