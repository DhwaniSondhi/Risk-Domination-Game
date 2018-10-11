package gui;


import controller.ReinforcementController;
import entity.Card;
import entity.Country;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Gui part of Reinforcement Panel
 * extends {@Link JPanel}
 */
public class ReinforcementPanel extends JPanel {
    JPanel cardSection;
    JPanel armySection;
    ReinforcementController reinforcementController;
    JComboBox countryList;
    JComboBox armyList;
    Card[] selectedCardsPanel;
    /**
     * Constructor
     * <p>
     * To create the Panel for Reinforcement components
     * </p>
     */
    public ReinforcementPanel(){
        setLayout(new GridBagLayout());
        reinforcementController=new ReinforcementController(this);
        cardSection=new JPanel();
        cardSection.setLayout(new GridBagLayout());
        getCardSection();
        reinforcementController.setArmiesForReinforcement();
        armySection=new JPanel();
        armySection.setLayout(new GridBagLayout());
        getArmySection();

    }

    /**
     * To add CardSection Panel for cards' selection, updation and resetting
     */
    public void getCardSection(){
        cardSection.removeAll();
        reinforcementController.getCards();
        addButtons();
        cardSection.revalidate();
        add(cardSection,getGridContraints(0,0));
        revalidate();
    }

    /**
     * To add Selected Cards Grid in CardSection Panel
     */
    public void getSelectedCardGrid(ArrayList<Card> selectedCards){
        selectedCardsPanel=new Card[selectedCards.size()];
        selectedCards.toArray(selectedCardsPanel);
        JPanel cardsSelected=new JPanel();
        cardsSelected.setLayout(new GridLayout(3,1));
        for(Card card:selectedCards){
            JPanel cardButtonPanel=new JPanel();
            cardButtonPanel.setLayout(new GridLayout(1,1));
            JLabel cardLabel=new JLabel(card.type.toString());
            cardButtonPanel.add(cardLabel);
            cardsSelected.add(cardButtonPanel);
        }
        cardSection.add(cardsSelected,getGridContraints(1,0));

    }

    /**
     * To add Unselected Cards Grid with add buttons in CardSection Panel
     */
    public void getUnselectedCardGrid(HashMap<String,Integer> cardsOfCurrentPlayer){
        JPanel cardsUnselected=new JPanel();
        cardsUnselected.setLayout(new GridLayout(3,1));
        Iterator itForCards=cardsOfCurrentPlayer.entrySet().iterator();
        while(itForCards.hasNext()){
            Map.Entry cardPair=(Map.Entry)itForCards.next();
            JPanel cardButtonPanel=new JPanel();
            cardButtonPanel.setLayout(new GridLayout(1,2));
            String label=cardPair.getValue()+" "+cardPair.getKey();
            JLabel cardLabel=new JLabel(label);
            cardButtonPanel.add(cardLabel);
            JButton add=new JButton("Add");
            add.addActionListener(reinforcementController);
            add.setName("Add"+cardPair.getKey());
            if(Integer.valueOf(label.substring(0,1))<1){
                add.setEnabled(false);
            }/*else if(selectedCards.length>=3){
                add.setEnabled(false);
            }*/else{
                add.setEnabled(true);
            }
            cardButtonPanel.add(add);
            cardsUnselected.add(cardButtonPanel);
        }
        cardSection.add(cardsUnselected,getGridContraints(0,0));
    }



    /**
     * To Add panel for Update and Reset buttons in CardSection Panel
     */
    public void addButtons(){
        JPanel buttons=new JPanel();
        buttons.setLayout(new GridLayout(2,1));
        JButton update=new JButton("Update");
        update.setName("Update");
        update.addActionListener(reinforcementController);
        if(selectedCardsPanel!=null && selectedCardsPanel.length>2
                && ( (selectedCardsPanel[0].type==selectedCardsPanel[1].type && selectedCardsPanel[0].type==selectedCardsPanel[2].type && selectedCardsPanel[1].type==selectedCardsPanel[2].type)
                || (!(selectedCardsPanel[0].type==selectedCardsPanel[1].type) && !(selectedCardsPanel[0].type==selectedCardsPanel[2].type) && !(selectedCardsPanel[1].type==selectedCardsPanel[2].type)))){
            update.setEnabled(true);
        }else{
            update.setEnabled(false);
        }
        buttons.add(update);
        JButton reset=new JButton("Reset");
        reset.setName("Reset");
        reset.addActionListener(reinforcementController);
        buttons.add(reset);
        cardSection.add(buttons,getGridContraints(2,0));

    }

    /**
     * To add Army Section Panel for armies display and distribution among countries
     */
    public void getArmySection(){
        armySection.removeAll();
        int armiesLeft=reinforcementController.getArmiesForReinforcement();
        JPanel armyDisplay=new JPanel();
        JLabel armies=new JLabel("Armies: "+armiesLeft);
        armyDisplay.add(armies);
        armySection.add(armyDisplay,getGridContraints(0,0));


        JPanel armiesChange=new JPanel(new GridBagLayout());
        List<Country> countriesOfPlayer=reinforcementController.getCountriesOfPlayer();
        JPanel countriesForArmies=new JPanel();
        countriesForArmies.setLayout(new GridLayout(countriesOfPlayer.size(),1));
        int loopForCountryNames=0;
        String[] countriesNames=new String[countriesOfPlayer.size()];
        for(Country country:countriesOfPlayer){
            countriesNames[loopForCountryNames]=country.name+"("+country.numOfArmies+"armies)";
            loopForCountryNames++;
        }
        countryList = new JComboBox(countriesNames);
        countryList.setName("countryList");
        countryList.setSelectedIndex(0);
        armiesChange.add(countryList,getGridContraints(0,0));


        String[] addArmy=new String[armiesLeft];
        for(int loopForArmies=1;loopForArmies<=armiesLeft;loopForArmies++){
            addArmy[loopForArmies-1]=String.valueOf(loopForArmies);
        }
        armyList = new JComboBox(addArmy);
        armyList.setName("armyList");
        armiesChange.add(armyList,getGridContraints(1,0));


        JButton changeArmies=new JButton("Add Armies");
        changeArmies.setName("changeArmies");
        changeArmies.addActionListener(reinforcementController);
        if(armiesLeft>0){
            changeArmies.setEnabled(true);
        }else{
            changeArmies.setEnabled(false);
        }
        armiesChange.add(changeArmies,getGridContraints(2,0));
        armySection.add(armiesChange,getGridContraints(0,2));
        armySection.revalidate();
        add(armySection,getGridContraints(0,1));


        JButton proceed=new JButton("Proceed");
        if(armiesLeft>0){
            proceed.setVisible(false);
        }else{
            proceed.setVisible(true);
        }
        add(proceed,getGridContraints(0,2));
        revalidate();
    }

    /**
     * To get the Selected Index for the Country List Combo Box in ArmySection Panel
     */
    public int getValueOfCountryIndexComboBox(){
        return countryList.getSelectedIndex();
    }

    /**
     * To get the Selected Index for the Army List Combo Box in ArmySection Panel
     */
    public String getValueOfArmyComboBox(){
        return armyList.getSelectedItem().toString();

    }

    /**
     * Set the constraints for GridBagLayout used
     */
    public GridBagConstraints getGridContraints(int x,int y){
        GridBagConstraints gridBagConstraints=new GridBagConstraints();
        gridBagConstraints.gridx=x;
        gridBagConstraints.gridy=y;
        gridBagConstraints.fill=GridBagConstraints.BOTH;
        /*gridBagConstraints.weightx=1;
        gridBagConstraints.weighty=1;*/
        return gridBagConstraints;
    }
}
