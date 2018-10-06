package controller;


import entity.Card;
import gui.ReinforcementPanel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Controller class for reinforcement phase
 * extends {@Link BaseController}
 * implements {@Link ActionListener}
 */
public class ReinforcementController extends BaseController<ReinforcementPanel> implements ActionListener {

    public HashMap<String,Integer> selectedCards;
    public HashMap<String,Integer> unselectedCards;
    public int armiesOnUpdate;

    /**
     * Constructor for Reinforcement Panel
     */
    public ReinforcementController(ReinforcementPanel reinforcementPanel){

        super(reinforcementPanel);
        selectedCards=new HashMap<>();
        getUnSelectedCards();
        armiesOnUpdate=0;
    }

    /**
     * To get the card section of Reinforcement Panel
     */
    public void getCards(){
        view.getUnselectedCardGrid(unselectedCards);
        view.getSelectedCardGrid(selectedCards);

    }

    /**
     * Set the list of cards available of a player in the unselectedCards attribute
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
     * Called on any action performed(Add, Reset and Update) in Reinforcement Panel
     */
    @Override
    public void actionPerformed(ActionEvent e){
        String buttonName=((JButton)e.getSource()).getName();

        if(buttonName.substring(0,3).equalsIgnoreCase("ADD")){
            String cardName=buttonName.substring(3);
            if(unselectedCards.get(cardName)!=null){
                unselectedCards.replace(cardName,unselectedCards.get(cardName)-1);
                if(selectedCards.get(cardName)==null){
                    selectedCards.put(cardName,1);
                }else{
                    selectedCards.replace(cardName,selectedCards.get(cardName)+1);
                }
            }
        }else if(buttonName.equalsIgnoreCase("Update")){
            setCardsOnUpdate();
        }else if(buttonName.equalsIgnoreCase("Reset")){
            Iterator itForReset=selectedCards.entrySet().iterator();
            while(itForReset.hasNext()){
                Map.Entry cardPair=(Map.Entry)itForReset.next();
                int cardNumber=(int)Integer.valueOf(cardPair.getValue().toString());
                String cardName=cardPair.getKey().toString();
                if(unselectedCards.get(cardName)!=null){
                    unselectedCards.replace(cardName,unselectedCards.get(cardName)+cardNumber);
                }
            }
            selectedCards.clear();
        }
        view.getCardSection();
    }

    /**
     * Updates the list of selected, unselected cards and armies added for a player in Reinforcement phase on click of update button
     */
    public void setCardsOnUpdate(){
        //TODO: to add code for updated armies and update cards


    }
    /**
     * Setting the armies of the player available for the Reinforcement Phase in armiesForReinforcement attribute
     */
    public int getArmiesForReinforcement(){
/*        GameMap instance=GameMap.getInstance();
        HashMap<Integer, Country> countries=instance.countries;
        HashMap<Integer, Continent> continents=instance.continents;

        int playerCountries=0;
        Iterator itForCountries=countries.entrySet().iterator();
        while(itForCountries.hasNext()){
            GameMap.Entry countryPair=(GameMap.Entry)itForCountries.next();
            Country country=(Country)countryPair.getValue();
            if(country.owner!=null){
                if(country.owner.id==instance.currentPlayer.id){
                    playerCountries++;
                }
            }
        }

        int playerContinents=0;
        boolean hasContinent=false;
        Iterator itForContinents=continents.entrySet().iterator();
        while(itForContinents.hasNext()){
            GameMap.Entry continentPair=(GameMap.Entry)itForContinents.next();
            Continent continent=(Continent)continentPair.getValue();
            ArrayList<Country> continentCountries=continent.countries;
            for(Country country:continentCountries){
                if(country.owner!=null){
                    if(country.owner.id==instance.currentPlayer.id){
                        hasContinent=true;
                    }else{
                        break;
                    }
                }
            }
            if(hasContinent==true){
                playerContinents++;
            }
        }

        return (playerCountries/3)+playerContinents+armiesOnUpdate;*/
        return 0;
    }


}
