package controller;


import model.Country;
import model.Player;
import view.StartUpFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;


public class StartUpController extends BaseController<StartUpFrame> implements ActionListener {

    HashSet<Integer> completedPlayers;

    public StartUpController(StartUpFrame view) {
        super(view);
        completedPlayers = new HashSet<>();
        model.addObserver(view);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("submit")) {
            model.players.clear();
            completedPlayers.clear();
            for (int i = 1; i <= Integer.valueOf((Integer) view.numOfPlayers.getSelectedItem()); i++) {
                Player player = new Player(i, "Player" + i);
                model.players.put(player.id, player);
            }
            model.assignCountriesToPlayers();
            changeCurrentPlayer();
        } else if (e.getActionCommand().equalsIgnoreCase("assign")) {
            if (view.playerCountries.getSelectedItem() != null) {
                Country country = (Country) view.playerCountries.getSelectedItem();
                int armies = ((Integer) view.numOfArmies.getSelectedItem());
                model.assignArmyToCountry(country, armies);
            }
            if (view.playerCountries.getItemCount() == 1)
                completedPlayers.add(model.currentPlayer.id);

            if (!changeCurrentPlayer()) {
                stateChangeListener.onStartUpCompleted();
                view.dispose();
            }
        }
    }

    private boolean changeCurrentPlayer() {
        if (completedPlayers.size() == model.players.size())
            return false;

        model.changeToNextPlayer(false);
        if (!completedPlayers.contains(model.currentPlayer.id)) {
            return true;
        } else {
            return changeCurrentPlayer();
        }
    }
}
