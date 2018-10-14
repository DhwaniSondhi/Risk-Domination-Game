package controller;

import entity.Country;
import entity.GameMap;
import entity.Player;
import gui.StartUpFrame;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class StartUpController extends BaseController<StartUpFrame> implements ActionListener {
    Set<Integer> playerIds;
    Integer[] playerNumArmies=new Integer[]{40,35,30,25,30};
    Integer[] countryIdPlayers;

    public StartUpController(StartUpFrame view){
        super(view);
        playerIds=GameMap.getInstance().players.keySet();
        //countryIdPlayers=new HashSet<>();
    }
    public int getPlayerId(){
        String playerId=view.getjLabelPlayerValue();
        if(playerId!=null && !playerId.trim().equalsIgnoreCase("")){
            int playerIdInt=Integer.parseInt(playerId);
            if(GameMap.getInstance().players.size()==playerIdInt){
                playerIdInt=0;
            }
            return ++playerIdInt;
        }else{
            return 1;
        }

    }
    public List<Country> getCountriesLeftCurrentPlayer(){
        List<Country> countries=new ArrayList<>();
        HashSet<Integer> countryIdPlayersLocal=new HashSet<Integer>();
        for(Country country:GameMap.getInstance().getCountriesOfCurrentPlayer()){
            if(country.numOfArmies==0){
                countryIdPlayersLocal.add(country.id);
                countries.add(country);
            }
        }
        countryIdPlayers=new Integer[countryIdPlayersLocal.size()];
        countryIdPlayersLocal.toArray(countryIdPlayers);
        return countries;
    }
    public Integer[] getArmies(){
        int armiesInCountries=0;
        Integer[] armyArray;
        for(Country country:GameMap.getInstance().getCountriesOfCurrentPlayer()){
            if(country.numOfArmies!=0){
                armiesInCountries+=country.numOfArmies;
            }
        }
        int armies=playerNumArmies[view.getNumOfPlayers()-2]-getCountriesLeftCurrentPlayer().size()-armiesInCountries;
        armyArray=new Integer[armies];
        for(int loopArmy=0;loopArmy<armies;loopArmy++){
            armyArray[loopArmy]=loopArmy+1;
        }
        return armyArray;

    }
    @Override
    public void actionPerformed(ActionEvent e){
        String btnName=((JButton)e.getSource()).getName();
        if(btnName!=null && btnName.trim().equalsIgnoreCase("submit")){
            GameMap.getInstance().players.clear();
            for (int loopPlayer = 0; loopPlayer < Integer.valueOf((Integer)view.getNumOfPlayers() ); loopPlayer++) {
                Player player = new Player(loopPlayer+ 1, "Player" + loopPlayer);
                if(loopPlayer==0){
                    GameMap.getInstance().currentPlayer=player;
                }
                GameMap.getInstance().players.put(player.id, player);
            }

            GameMap.getInstance().assignCountriesToPlayers();
        }else if(btnName!=null && btnName.trim().equalsIgnoreCase("Assign")){
            GameMap.getInstance().currentPlayer=GameMap.getInstance().players.get(getPlayerId());
            /*if(getCountriesLeftCurrentPlayer().size()==0){
                for(Map.Entry player:GameMap.getInstance().players.entrySet()){

                }
            }*/
            Country country=GameMap.getInstance().countries.get(countryIdPlayers[view.getjComboBoxCountry()]);
            country.numOfArmies+=view.getNumberOfArmies();
            GameMap.getInstance().countries.replace(country.id,country);
        }
        view.updateCountries(getCountriesLeftCurrentPlayer());
    }

}
