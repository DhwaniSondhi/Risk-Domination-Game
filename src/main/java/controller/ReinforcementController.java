package controller;


import entity.*;
import gui.ReinforcementPanel;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

/**
 * Controller class for reinforcement phase
 * extends {@Link BaseController}
 * implements {@Link ActionListener}
 */
public class ReinforcementController extends BaseController<ReinforcementPanel> implements ActionListener {

    GameMap instance;
    public HashMap<String,Integer> unselectedCards;
    public ArrayList<Card> selectedCards;
    public HashMap<Integer,Integer> addedCountryArmy;
    Integer[] countryIdsOfCurrentPlayer;
    int totalArmies;

    /**
     * Constructor for Reinforcement Panel
     * <p>
     *     To get initial values of the attributes
     *     To get list of Unselected Cards for Reinforcement Panel
     * </p>
     */
    public ReinforcementController(ReinforcementPanel reinforcementPanel){

        super(reinforcementPanel);
        instance=GameMap.getInstance();
        selectedCards=new ArrayList<>();
        getUnSelectedCards();
        addedCountryArmy=new HashMap<>();

    }

    /**
     * To set the list of cards available of a player in the unselectedCards attribute
     */
    public void getUnSelectedCards(){
        ArrayList<Card> cards=model.currentPlayer.cards;
        if(unselectedCards==null){
            unselectedCards=new HashMap<>();
        }
        int infantry=0;
        int artillery=0;
        int cavalry=0;
        for(Card card:cards){
            if(card.type==Card.TYPE.INFANTRY){
                infantry++;
            }else if(card.type==Card.TYPE.ARTILLERY){
                artillery++;
            }else if(card.type==Card.TYPE.CAVALRY){
                cavalry++;
            }
            unselectedCards.put("INFANTRY",infantry);
            unselectedCards.put("ARTILLERY",artillery);
            unselectedCards.put("CAVALRY",cavalry);
        }
    }

    /**
     * Invoked on any action performed(Add, Reset and Update button for Card Section in Reinforcement Panel
     * Invoked on any action performed(Change Armies button for Army Section in Reinforcement Panel
     * @param e {@link ActionEvent}
     */
    @Override
    public void actionPerformed(ActionEvent e){
        String buttonName=((JButton)e.getSource()).getName();

        if(buttonName.substring(0,3).equalsIgnoreCase("ADD")){
            String cardName=buttonName.substring(3);
            if(selectedCards.size()<3){
                if(unselectedCards.get(cardName)!=null) {
                    unselectedCards.replace(cardName, unselectedCards.get(cardName) - 1);
                }
                Card card=null;
                if(cardName.equalsIgnoreCase("ARTILLERY")){
                    card=new Card(Card.TYPE.ARTILLERY);
                }else if(cardName.equalsIgnoreCase("INFANTRY")){
                    card=new Card(Card.TYPE.INFANTRY);
                }else if(cardName.equalsIgnoreCase("CAVALRY")){
                    card=new Card(Card.TYPE.CAVALRY);
                }
                if(card!=null){
                    selectedCards.add(card);
                }
            }

        }else if(buttonName.equalsIgnoreCase("Update")){
            setCardsOnUpdate();
        }else if(buttonName.equalsIgnoreCase("changeArmies")){
            changeArmiesOfCountries();
        }else if(buttonName.equalsIgnoreCase("Reset")){
            for(Card card:selectedCards){
                if(unselectedCards.get(card.type.toString())!=null){
                    unselectedCards.replace(card.type.toString(),unselectedCards.get(card.type.toString())+1);
                }
            }
            selectedCards.clear();
        }
        view.getCardSection();
    }



    /**
     * To get the card section of Reinforcement Panel
     */
    public void getCards(){
        view.getUnselectedCardGrid(unselectedCards);
        view.getSelectedCardGrid(selectedCards);

    }

    /**
     * To calculate the total armies from the countries and continents owns by the player in totalArmies attribute
     */
    public void setArmiesForReinforcement(){
        HashMap<Integer, Country> countries=instance.countries;
        HashMap<Integer, Continent> continents=instance.continents;

        int playerCountries=0;
        int armiesOnTextFields=0;
        Iterator itForCountries=countries.entrySet().iterator();
        while(itForCountries.hasNext()){
            Map.Entry countryPair=(Map.Entry)itForCountries.next();
            Country country=(Country)countryPair.getValue();
            if(country.owner!=null){
                if(country.owner.id==instance.currentPlayer.id){
                    playerCountries++;
                }
            }
        }

        int playerContinenstControlVal=0;
        boolean hasContinent;
        Iterator itForContinents=continents.entrySet().iterator();
        while(itForContinents.hasNext()){
            hasContinent=true;
            Map.Entry continentPair=(Map.Entry)itForContinents.next();
            Continent continent=(Continent)continentPair.getValue();
            ArrayList<Country> continentCountries=continent.countries;
            for(Country country:continentCountries){
                if(country.owner!=null){
                    if(country.owner.id!=instance.currentPlayer.id){
                        hasContinent=false;
                        break;
                    }
                }else{
                    hasContinent=false;
                    break;
                }
            }
            if(hasContinent==true){
                playerContinenstControlVal+=continent.controlValue;
            }
        }

        Iterator itForArmies=addedCountryArmy.entrySet().iterator();
        while(itForArmies.hasNext()){
            Map.Entry pair=(Map.Entry)itForArmies.next();
            armiesOnTextFields+=Integer.parseInt(pair.getValue().toString());
        }
        totalArmies=(playerCountries/3)+playerContinenstControlVal;
    }

    /**
     * To get the total armies available from totalArmies attribute
     */
    public int getArmiesForReinforcement(){
        return totalArmies;
    }

    /**
     * To get the countries of currentPlayer
     */
    public List<Country> getCountriesOfPlayer(){

        List<Country> countries=model.getCountriesOfCurrentPlayer();
        countryIdsOfCurrentPlayer=new Integer[countries.size()];
        int loopForIds=0;
        for(Country country:countries){
            countryIdsOfCurrentPlayer[loopForIds]=country.id;
            loopForIds++;
        }
        return model.getCountriesOfCurrentPlayer();

    }

    /**
     * To add the armies to the respective countries on click of Add button
     */
    public void changeArmiesOfCountries(){
        int countryId=countryIdsOfCurrentPlayer[view.getValueOfCountryIndexComboBox()];
        int addedArmy=Integer.parseInt(view.getValueOfArmyComboBox());
        Country countryChanged=model.countries.get(countryId);
        countryChanged.numOfArmies+=addedArmy;
        model.countries.replace(countryId,countryChanged);
        totalArmies-=addedArmy;
        view.getArmySection();
    }

    /**
     * Updates the list of selected, unselected cards and armies added for a player in Reinforcement phase on click of update button
     */
    public void setCardsOnUpdate(){
        totalArmies+=instance.currentPlayer.updateArmiesForCards;
        instance.currentPlayer.updateArmiesForCards+=5;
        selectedCards.clear();
        view.getArmySection();

    }
}
