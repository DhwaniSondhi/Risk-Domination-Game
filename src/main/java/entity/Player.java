package entity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class containing attributes and functions of a player object
 */

public class Player {
    public int id;
    public String name;
    public String colour = "#000";
    public ArrayList<Card> cards;
    public HashMap<String,Integer> selectedCards;
    public HashMap<String,Integer> unselectedCards;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        selectedCards=new HashMap<>();
    }

    /**
     * Updates the list of selected and unselected cards of a player on click of add button
     */
    public void setCardsOnAdd(String cardName){
        System.out.println(unselectedCards.get(cardName));
        if(unselectedCards.get(cardName)!=null){
            unselectedCards.replace(cardName,unselectedCards.get(cardName)-1);
            if(selectedCards.get(cardName)==null){
                selectedCards.put(cardName,1);
            }else{
                selectedCards.replace(cardName,selectedCards.get(cardName)+1);
            }
        }
    }

    /**
     * Set the list of selected cards of a player in the player's attribute
     */
    public void getUnSelectedCards(){
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
}
