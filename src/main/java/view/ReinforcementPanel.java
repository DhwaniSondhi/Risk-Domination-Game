package view;


import controller.ReinforcementController;
import model.Card;
import model.Country;
import utility.GameStateChangeListener;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Gui part of Reinforcement Panel
 * extends {@link JPanel}
 */
public class ReinforcementPanel extends JPanel {

    /**
     * Panel for card section
     */
    JPanel cardSection;

    /**
     * Panel for army section
     */
    JPanel armySection;

    /**
     * Reference for Reinforcement controller
     */
    ReinforcementController reinforcementController;

    /**
     * Drop down to select countries for placing armies
     */
    JComboBox countryList;

    /**
     * Drop down to select armies
     */
    JComboBox armyList;

    /**
     * Number of the unselected cards
     */
    int unselectedCardsNum;

    /**
     * Array for selected cards
     */
    Card[] selectedCardsArray;

    /**
     * Constructor
     * <p>
     * To create the Panel for Reinforcement components
     * </p>
     *
     * @param stateChangeListener observer for game state
     */
    public ReinforcementPanel(GameStateChangeListener stateChangeListener) {
        reinforcementController = new ReinforcementController(this);
        reinforcementController.setStateChangeListener(stateChangeListener);
        reinforcementController.setUnSelectedCards();
        reinforcementController.setArmiesForReinforcement();

        setLayout(new GridBagLayout());

        cardSection = new JPanel();
        cardSection.setLayout(new GridBagLayout());
        addCardSection();
        add(cardSection, getGridContraints(0, 0));

        armySection = new JPanel();
        armySection.setLayout(new GridBagLayout());
        addArmySection();
        add(armySection, getGridContraints(0, 1));
    }


    /**
     * To update the Card Section and Army Section of the Reinforcement Panel
     */

    public void update() {
        addCardSection();
        addArmySection();
        revalidate();
    }

    /**
     * To add CardSection Panel for cards' selection, updation and resetting
     */
    public void addCardSection() {
        cardSection.removeAll();
        reinforcementController.getCardsInGui();
        addButtons();
        cardSection.revalidate();
        cardSection.repaint();
    }

    /**
     * To add Selected Cards Grid in CardSection Panel
     *
     * @param selectedCards list of the selected cards
     */
    public void addSelectedCardGrid(ArrayList<Card> selectedCards) {
        selectedCardsArray = new Card[selectedCards.size()];
        selectedCards.toArray(selectedCardsArray);
        JPanel cardsSelected = new JPanel();
        cardsSelected.setLayout(new GridLayout(3, 1));
        for (Card card : selectedCards) {
            JPanel cardButtonPanel = new JPanel();
            cardButtonPanel.setLayout(new GridLayout(1, 1));
            JLabel cardLabel = new JLabel(card.type.toString());
            cardButtonPanel.add(cardLabel);
            cardsSelected.add(cardButtonPanel);
        }
        cardSection.add(cardsSelected, getGridContraints(1, 0));

    }

    /**
     * To add Unselected Cards Grid with add buttons in CardSection Panel
     *
     * @param cardsOfCurrentPlayer the list of cards of current player
     */
    public void addUnselectedCardGrid(HashMap<String, Integer> cardsOfCurrentPlayer) {
        unselectedCardsNum = 0;
        JPanel cardsUnselected = new JPanel();
        cardsUnselected.setLayout(new GridLayout(3, 1));
        Iterator itForCards = cardsOfCurrentPlayer.entrySet().iterator();
        while (itForCards.hasNext()) {
            Map.Entry cardPair = (Map.Entry) itForCards.next();
            JPanel cardButtonPanel = new JPanel();
            cardButtonPanel.setLayout(new GridLayout(1, 2));
            String label = cardPair.getValue() + " " + cardPair.getKey();
            unselectedCardsNum += (Integer.parseInt(cardPair.getValue().toString()));
            JLabel cardLabel = new JLabel(label);
            cardButtonPanel.add(cardLabel);
            JButton add = new JButton("Add");
            add.addActionListener(reinforcementController);
            add.setName("Add" + cardPair.getKey());
            if (Integer.valueOf(label.substring(0, 1)) < 1) {
                add.setEnabled(false);
            } else {
                add.setEnabled(true);
            }
            cardButtonPanel.add(add);
            cardsUnselected.add(cardButtonPanel);
        }
        cardSection.add(cardsUnselected, getGridContraints(0, 0));
    }

    /**
     * To add panel for Update and Reset buttons in CardSection Panel
     */
    public void addButtons() {
        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(2, 1));
        JButton update = new JButton("Update");
        update.setName("Update");
        update.addActionListener(reinforcementController);
        if (selectedCardsArray != null && selectedCardsArray.length > 2
                && ((selectedCardsArray[0].type == selectedCardsArray[1].type && selectedCardsArray[0].type == selectedCardsArray[2].type && selectedCardsArray[1].type == selectedCardsArray[2].type)
                || (!(selectedCardsArray[0].type == selectedCardsArray[1].type) && !(selectedCardsArray[0].type == selectedCardsArray[2].type) && !(selectedCardsArray[1].type == selectedCardsArray[2].type)))) {
            update.setEnabled(true);
        } else {
            update.setEnabled(false);
        }
        buttons.add(update);
        JButton reset = new JButton("Reset");
        reset.setName("Reset");
        reset.addActionListener(reinforcementController);
        buttons.add(reset);
        cardSection.add(buttons, getGridContraints(2, 0));

    }

    /**
     * To add Army Section Panel for armies display and distribution among countries
     */
    public void addArmySection() {
        armySection.removeAll();
        int armiesLeft = reinforcementController.getArmiesForReinforcement();
        JPanel armyDisplay = new JPanel();
        JLabel armies = new JLabel("Armies: " + armiesLeft);
        armyDisplay.add(armies);
        armySection.add(armyDisplay, getGridContraints(0, 0));


        JPanel armiesChange = new JPanel(new GridBagLayout());
        List<Country> countriesOfPlayer = reinforcementController.getCountriesAndIdsOfCurrentPlayer();
        int loopForCountryNames = 0;
        String[] countriesNames = new String[countriesOfPlayer.size()];
        for (Country country : countriesOfPlayer) {
            countriesNames[loopForCountryNames] = country.name + "(" + country.numOfArmies + "armies)";
            loopForCountryNames++;
        }
        countryList = new JComboBox(countriesNames);
        countryList.setName("countryList");
        countryList.setSelectedIndex(0);
        armiesChange.add(countryList, getGridContraints(0, 0));


        String[] addArmy = new String[armiesLeft];
        for (int loopForArmies = 1; loopForArmies <= armiesLeft; loopForArmies++) {
            addArmy[loopForArmies - 1] = String.valueOf(loopForArmies);
        }
        armyList = new JComboBox(addArmy);
        armyList.setName("armyList");
        armiesChange.add(armyList, getGridContraints(1, 0));


        JButton changeArmies = new JButton("Add Armies");
        changeArmies.setName("changeArmies");
        changeArmies.addActionListener(reinforcementController);
        if (armiesLeft > 0 && unselectedCardsNum < 5) {
            changeArmies.setEnabled(true);
        } else {
            changeArmies.setEnabled(false);
        }
        armiesChange.add(changeArmies, getGridContraints(2, 0));
        armySection.add(armiesChange, getGridContraints(0, 2));
        if (unselectedCardsNum >= 5) {
            JLabel msgForCards = new JLabel("Cards cannot be greater than 5. Please exchange them.");
            armySection.add(msgForCards, getGridContraints(0, 3));
        }

        JButton proceed = new JButton("Proceed");
        proceed.setName("proceed");
        proceed.addActionListener(reinforcementController);
        if (armiesLeft > 0) {
            proceed.setVisible(false);
        } else {
            proceed.setVisible(true);
        }
        armySection.add(proceed, getGridContraints(0, 4));

        armySection.revalidate();
        armySection.repaint();

    }

    /**
     * To get the Selected Index for the Country List Combo Box in ArmySection Panel
     *
     * @return the index of selected country in the Country ComboBox
     */
    public int getValueOfCountryIndexComboBox() {
        return countryList.getSelectedIndex();
    }

    /**
     * To get the Selected Index for the Army List Combo Box in ArmySection Panel
     *
     * @return the item selected in the Army ComboBox
     */
    public String getValueOfArmyComboBox() {
        return armyList.getSelectedItem().toString();
    }

    /**
     * Set the constraints for GridBagLayout used
     *
     * @param x value for constraints gridx (row in the grid)
     * @param y value for constraints gridY (col in the grid)
     * @return default constraints (see {@link GridBagConstraints}) with provided x,y values
     */
    public GridBagConstraints getGridContraints(int x, int y) {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = x;
        gridBagConstraints.gridy = y;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        return gridBagConstraints;
    }
}
