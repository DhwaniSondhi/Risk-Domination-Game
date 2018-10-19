package view;

import controller.StartUpController;
import model.Country;
import model.GameMap;
import utility.GameStateChangeListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

/**
 * GUI part of StartUp Panel
 * extends {@link JPanel}
 */
public class StartUpFrame extends JFrame {

    /**
     * Drop down for the number of players
     */
    JComboBox numOfPlayers;

    /**
     * Reference for StartUp Controller
     */
    StartUpController startUpController;

    /**
     * Label to display current player playing
     */
    JLabel playersLabel;

    /**
     * Drop down to select the countries
     */
    JComboBox countriesDropDown;

    /**
     * Drop down to select the armies
     */
    JComboBox numberOfArmies;

    /**
     * Button to assign armies
     */
    JButton assignArmyButton;

    /**
     * Panel for main start
     */
    private JPanel mainStartPanel;

    /**
     * Panel for countries
     */
    private JPanel countriesPanel;

    /**
     * Label to display "Welcome to the RISK Game"
     */
    private JLabel labelWelcome = new JLabel("Welcome to the RISK Game\n");

    /**
     * Label to display "Choose number of players"
     */
    private JLabel labelChoosePlayers = new JLabel("Choose number of players:");

    /**
     * Button for submit the number of players
     */
    private JButton buttonSubmit = new JButton("Submit");

    /**
     * Text Field for adding armies
     */
    private JTextField addArmies;


    /**
     * Constructor
     * <p>
     *     To create the Panel for StartUp Phase of the Game
     * </p>
     * @param stateChangeListener observer for game state
     */
    public StartUpFrame(GameStateChangeListener stateChangeListener) {
        super("Welcome to the Game");

        startUpController = new StartUpController(this);
        startUpController.setStateChangeListener(stateChangeListener);

        mainStartPanel = new JPanel();
        playersLabel = new JLabel();
        mainStartPanel.setLayout(new BoxLayout(mainStartPanel, BoxLayout.Y_AXIS));
        add(mainStartPanel);

        mainStartPanel.setBorder(new LineBorder(Color.BLACK));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setVisible(true);

        buttonSubmit.setName("submit");
        buttonSubmit.addActionListener(startUpController);
        numOfPlayers = new JComboBox();
        for (int i = 1; i < Math.min(6, GameMap.getInstance().countries.size()); i++) {
            numOfPlayers.addItem(i + 1);
        }


        countriesPanel = new JPanel();
        countriesPanel.setLayout(new GridBagLayout());
        mainStartPanel.add(labelWelcome);
        mainStartPanel.add(labelChoosePlayers);
        mainStartPanel.add(numOfPlayers);
        mainStartPanel.add(buttonSubmit);
        mainStartPanel.add(countriesPanel);

        mainStartPanel.revalidate();
        assignArmyButton = new JButton("Assign");
        assignArmyButton.setName("Assign");
        assignArmyButton.addActionListener(startUpController);
    }

    /**
     * To get the number of players needed in the game
     *
     * @return the selected number of players in the drop down
     */
    public int getNumOfPlayers() {
        return (Integer) numOfPlayers.getSelectedItem();
    }

    /**
     * To get the unique player IDs
     *
     * @return the player Id
     */
    public String getLabelPlayerValue() {
        return playersLabel.getText();
    }

    /**
     * To update the current player Id displayed
     *
     * @param newPlayerId  new Player id to be displayed
     */
    public void setLabelPlayerValue(String newPlayerId) {
        playersLabel.setText(newPlayerId);
    }

    /**
     * To get index selected in the countries drop down
     *
     * @return index of the countries selected
     */
    public int getCountryIndex() {

        return countriesDropDown.getSelectedIndex();
    }

    /**
     * To get the number of armies selected
     *
     * @return armies selected in the drop down
     */
    public int getNumberOfArmies() {
        return (Integer) numberOfArmies.getSelectedItem();
    }

    /**
     * observer for the assign button
     */
    public void clickAssignButton() {
        assignArmyButton.doClick();
    }

    /**
     * Creates {@link GridBagConstraints} with provided gridX and gridY values
     *
     * @param x value for constraints gridX (row in the grid)
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
     * To update the list of countries which the player still has
     *
     * @param countries list of the countries of current player
     */
    public void updateCountries(List<Country> countries) {
        countriesPanel.removeAll();
        addArmies = new JTextField(10);
        countriesDropDown = new JComboBox();

        if (countries != null) {
            for (Country countryName : countries) {
                countriesDropDown.addItem(countryName);
            }
        }

        countriesPanel.add(new JLabel("Player"), getConstraints(0, 0));
        playersLabel.setText(String.valueOf(GameMap.getInstance().currentPlayer.id));
        countriesPanel.add(playersLabel, getConstraints(0, 1));
        countriesPanel.add(countriesDropDown, getConstraints(0, 2));
        countriesPanel.add(new JLabel("Select Number of armies"), getConstraints(0, 3));
        numberOfArmies = new JComboBox(startUpController.getArmies());
        countriesPanel.add(numberOfArmies, getConstraints(0, 4));


        countriesPanel.add(assignArmyButton, getConstraints(0, 5));
        countriesPanel.revalidate();
        countriesPanel.repaint();
    }
}
