package view;

import controller.AttackController;
import model.Country;
import model.GameMap;
import model.Player;
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

    public JPanel dicePanel;
    public JPanel resultPanel;
    private JComboBox<Country> countries;
    private JComboBox<Country> neighbouringCountries;
    /**
     * Panel for displaying neighboring countries to selected country to which fortify can done
     */
    private JPanel neighbouringPanel;

    public JComboBox<Integer> playerDice;
    public JComboBox<Integer> opponentDice;
    public JLabel resultPlayer;
    public JLabel resultOpponent;

    /**
     * Button to attack neighbouring countries
     */
    private JButton attackButton;

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
        countries = new JComboBox<>();
        countries.setName("selectCountry");
        countries.addItemListener(attackController);
        countryPanel.add(countries);

        neighbouringPanel = new JPanel();
        neighbouringPanel.setBorder(new LineBorder(Color.black, 1));
        neighbouringPanel.setLayout(new BoxLayout(neighbouringPanel, BoxLayout.Y_AXIS));
        neighbouringCountries = new JComboBox<>();
        neighbouringCountries.setName("selectNeighbourCountry");
        neighbouringCountries.addItemListener(attackController);
        neighbouringPanel.add(neighbouringCountries);

        JComboBox<String> selectMode = new JComboBox<>(new String[]{"Choose Dice", "All out"});
        selectMode.addItemListener(attackController);
        selectMode.setName("mode");

        dicePanel = new JPanel();
        dicePanel.setLayout(new BoxLayout(dicePanel, BoxLayout.Y_AXIS));
        playerDice = new JComboBox();
        opponentDice = new JComboBox();
        dicePanel.add(new JLabel("Player:"));
        dicePanel.add(playerDice);
        dicePanel.add(new JLabel("Opponent:"));
        dicePanel.add(opponentDice);

        resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        resultPlayer = new JLabel();
        resultOpponent = new JLabel();
        resultPanel.add(new JLabel("Player:"));
        resultPanel.add(resultPlayer);
        resultPanel.add(new JLabel("Opponent:"));
        resultPanel.add(resultOpponent);


        attackButton = new JButton("Attack");
        attackButton.setName("attack");
        attackButton.addActionListener(attackController);


        add(countryPanel);
        add(neighbouringPanel);
        add(selectMode);
        add(dicePanel);
        add(attackButton);
        add(resultPanel);

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
        this.countries.removeAllItems();
        for (Country country : countries) {
            this.countries.addItem(country);
        }
        revalidate();
    }

    /**
     * Adds list of countries to the neighbouring panel
     *
     * @param countries collection of countries
     */
    public void updateNeighbouringCountries(Collection<Country> countries) {
        neighbouringCountries.removeAllItems();
        for (Country country : countries) {
            if (country.owner.id != attackController.model.currentPlayer.id) {
                neighbouringCountries.addItem(country);
            }
        }
        revalidate();
    }

    public void updatePlayerDiceDropdown(int numOfDiceAllowed) {
        this.playerDice.removeAllItems();
        for (int i = 0; i < numOfDiceAllowed; i++) {
            playerDice.addItem(i + 1);
        }
    }

    public void updateOpponentDiceDropdown(int numOfDiceAllowed) {
        this.opponentDice.removeAllItems();
        for (int i = 0; i < Math.min(2, numOfDiceAllowed); i++) {
            opponentDice.addItem(i + 1);
        }
    }

    public void updateResultPlayer(String diceValues){
        resultPlayer.setText(diceValues);
    }

    public void updateResultOpponent(String diceValues) {
        resultOpponent.setText(diceValues);
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
        if (o instanceof Country) {
            Country country = ((Country) o);
            if (country.id == attackController.selectedCountry.id) {
                updateNeighbouringCountries(country.getNeighbours());
                updatePlayerDiceDropdown(country.numOfDiceAllowed);
                if(country.numOfArmies == 1) {
                    countries.removeItem(country);
                }
                if(neighbouringCountries.getItemCount() == 0){
                    attackButton.hide();
                    dicePanel.hide();
                }else{
                    attackButton.show();
                    dicePanel.show();
                }
            } else {
                updateOpponentDiceDropdown(country.numOfDiceAllowed);
            }
        } else if (o instanceof Player) {
            Player player = ((Player) o);
            updateResultPlayer(player.diceValuesPlayer.toString());
            updateResultOpponent(player.diceValuesOpponent.toString());
        } else if(o instanceof GameMap){
            showCountries(GameMap.getInstance().currentPlayer.getCountries());
        }

    }


}
