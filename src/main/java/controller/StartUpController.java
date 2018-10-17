package controller;

import model.Country;
import model.GameMap;
import model.Player;
import view.StartUpFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StartUpController extends BaseController<StartUpFrame> implements ActionListener {
    Set<Integer> playerIds;
    Integer[] playerNumArmies = new Integer[]{40, 35, 30, 25, 20, 15, 10};
    Integer[] countryIdPlayers;
    int checkAllPlayers;

    public StartUpController(StartUpFrame view) {
        super(view);
        playerIds = GameMap.getInstance().players.keySet();
        checkAllPlayers = 0;
    }

    public int getPlayerId() {
        String playerId = view.getjLabelPlayerValue();
        if (playerId != null && !playerId.trim().equalsIgnoreCase("")) {
            int playerIdInt = Integer.parseInt(playerId);
            if (GameMap.getInstance().players.size() == playerIdInt) {
                playerIdInt = 0;
            }
            return ++playerIdInt;
        } else {
            return 1;
        }

    }

    public List<Country> getCountriesLeftCurrentPlayer() {
        List<Country> countries = new ArrayList<>();
        HashSet<Integer> countryIdPlayersLocal = new HashSet<Integer>();
        for (Country country : GameMap.getInstance().getCountriesOfCurrentPlayer()) {
            if (country.numOfArmies == 0) {
                countryIdPlayersLocal.add(country.id);
                countries.add(country);
            }
        }
        countryIdPlayers = new Integer[countryIdPlayersLocal.size()];
        countryIdPlayersLocal.toArray(countryIdPlayers);
        return countries;
    }

    public Integer[] getArmies() {
        int armiesInCountries = 0;
        Integer[] armyArray;
        for (Country country : GameMap.getInstance().getCountriesOfCurrentPlayer()) {
            if (country.numOfArmies != 0) {
                armiesInCountries += country.numOfArmies;
            }
        }
        int armies = playerNumArmies[view.getNumOfPlayers() - 2] - getCountriesLeftCurrentPlayer().size() + 1 - armiesInCountries;
        if (getCountriesLeftCurrentPlayer().size() == 1) {
            armyArray = new Integer[1];
            armyArray[0] = armies;
        } else {
            armyArray = new Integer[armies];
            for (int loopArmy = 0; loopArmy < armies; loopArmy++) {
                armyArray[loopArmy] = loopArmy + 1;
            }
        }
        return armyArray;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String btnName = ((JButton) e.getSource()).getName();
        int playerId = getPlayerId();
        if (btnName != null && btnName.trim().equalsIgnoreCase("submit")) {
            GameMap.getInstance().players.clear();
            for (int loopPlayer = 1; loopPlayer <= Integer.valueOf(view.getNumOfPlayers()); loopPlayer++) {
                Player player = new Player(loopPlayer, "Player" + loopPlayer);
                if (loopPlayer == 1) {
                    GameMap.getInstance().currentPlayer = player;
                }
                GameMap.getInstance().players.put(player.id, player);
            }

            GameMap.getInstance().assignCountriesToPlayers();
        } else if (btnName != null && btnName.trim().equalsIgnoreCase("Assign")) {


            if (getCountriesLeftCurrentPlayer().size() > 0) {
                Country country = GameMap.getInstance().countries.get(countryIdPlayers[view.getjComboBoxCountry()]);
                country.numOfArmies += view.getNumberOfArmies();
                GameMap.getInstance().countries.replace(country.id, country);
            }
            GameMap.getInstance().currentPlayer = GameMap.getInstance().players.get(playerId);
        }
        if (getCountriesLeftCurrentPlayer().size() == 0) {
            checkAllPlayers++;
            if (++playerId > view.getNumOfPlayers()) {
                playerId = 1;
                view.setjLabelPlayerValue(String.valueOf(playerId));
            }
            if (!(checkAllPlayers >= view.getNumOfPlayers())) {
                view.clickAssignButton();
            }
            view.dispose();
            stateChangeListener.onStartUpCompleted();
        } else {
            view.updateCountries(getCountriesLeftCurrentPlayer());
        }
    }

}
