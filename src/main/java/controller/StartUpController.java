package controller;

import entity.GameMap;
import entity.Player;
import gui.StartUpFrame;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class StartUpController extends BaseController<StartUpFrame> implements ActionListener {
    Set<Integer> playerIds;

    public StartUpController(StartUpFrame view){
        super(view);
        playerIds=GameMap.getInstance().players.keySet();
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
    @Override
    public void actionPerformed(ActionEvent e){
        String btnName=((JButton)e.getSource()).getName();
        if(btnName!=null && btnName.trim().equalsIgnoreCase("submit")){
            GameMap.getInstance().players.clear();
            for (int i = 0; i < Integer.valueOf((Integer)view.getNumOfPlayers() ); i++) {
                Player player = new Player(i + 1, "Player" + i);
                if(i==0){
                    GameMap.getInstance().currentPlayer=player;
                }
                GameMap.getInstance().players.put(player.id, player);
            }

            GameMap.getInstance().assignCountriesToPlayers();
        }else if(btnName!=null && btnName.trim().equalsIgnoreCase("Assign")){

            GameMap.getInstance().currentPlayer=GameMap.getInstance().players.get(getPlayerId());

        }
        view.updateCountries(GameMap.getInstance().getCountriesOfCurrentPlayer());



        //model.getCountriesOfCurrentPlayer();

    }

}
