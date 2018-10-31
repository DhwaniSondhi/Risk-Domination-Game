package view;

import controller.StartUpController;
import model.Country;
import model.GameMap;
import utility.GameStateChangeListener;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class StartUpFrame extends JFrame implements Observer {

    /**
     * Panel to hold countries
     */
    private JPanel countriesPanel;
    /**
     * Drop-down to select number of players
     */
    public JComboBox<Integer> numOfPlayers;
    /**
     * button to submit number of players
     */
    private JButton submitButton;
    /**
     * button to assign armies
     */
    private JButton assignButton;
    /**
     * Label showing current player name
     */
    private JLabel selectedPlayer;
    /**
     * Drop-down to select number of counrties
     */
    public JComboBox<Country> playerCountries;
    /**
     * Drop-down to select number of armies
     */
    public JComboBox<Integer> numOfArmies;

    /**
     * controller for the view
     * */
    private StartUpController controller;


    /**
     * initializes the controller and sets up the layout for the view
     * */
    public StartUpFrame(GameStateChangeListener stateChangeListener) {
        super("Welcome to the Game");

        controller = new StartUpController(this);
        controller.setStateChangeListener(stateChangeListener);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setSize(300, 300);
        setVisible(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        add(mainPanel);

        mainPanel.add(new JLabel("Welcome to the RISK Game"));
        mainPanel.add(new JLabel("Choose number of players:"));

        numOfPlayers = new JComboBox<>();
        for (int i = 2; i <= Math.min(6, GameMap.getInstance().countries.size()); i++) {
            numOfPlayers.addItem(i);
        }
        mainPanel.add(numOfPlayers);

        submitButton = new JButton("Submit");
        submitButton.setName("submit");
        submitButton.addActionListener(controller);
        mainPanel.add(submitButton);

        countriesPanel = new JPanel();
        countriesPanel.setLayout(new GridBagLayout());
        mainPanel.add(countriesPanel);

        selectedPlayer = new JLabel("--");
        countriesPanel.add(selectedPlayer, getConstraints(0, 0));

        countriesPanel.add(new JLabel("Select the country:"), getConstraints(0, 1));
        playerCountries = new JComboBox<>();
        countriesPanel.add(playerCountries, getConstraints(0, 2));

        countriesPanel.add(new JLabel("Select no. of armies:"), getConstraints(0, 3));
        numOfArmies = new JComboBox<>();
        countriesPanel.add(numOfArmies, getConstraints(0, 4));

        assignButton = new JButton("Assign");
        assignButton.setName("assign");
        assignButton.addActionListener(controller);
        countriesPanel.add(assignButton, getConstraints(0, 5));

        mainPanel.revalidate();
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
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weighty = 0;
        constraints.weightx = 0;
        return constraints;
    }

    /**
     * updates the country panel with current players remaining countries
     *
     * @param countries        list of countries to display
     * @param initialArmyCount initial army for the player
     * @param playerName       name of current player
     */
    public void updateCountriesPanel(String playerName, List<Country> countries, int initialArmyCount) {
        selectedPlayer.setText(playerName);
        playerCountries.removeAllItems();
        int placedArmyCount = 0;
        if (countries != null) {
            for (Country country : countries) {
                if (country.numOfArmies == 0)
                    playerCountries.addItem(country);
                else
                    placedArmyCount += country.numOfArmies;
            }
        }

        int maxCount = initialArmyCount - placedArmyCount - playerCountries.getItemCount() + 1;
        numOfArmies.removeAllItems();
        if (playerCountries.getItemCount() == 1) {
            numOfArmies.addItem(maxCount);
        } else {
            for (int i = 1; i <= maxCount; i++) {
                numOfArmies.addItem(i);
            }
        }
        countriesPanel.revalidate();
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
        GameMap instance = GameMap.getInstance();
        if (instance.currentPlayer != null) {
            updateCountriesPanel(instance.currentPlayer.name, instance.currentPlayer.getCountries(),
                    instance.getInitialArmy());
        }
    }
}

