package controller;


import model.Country;
import model.Player;
import view.StartUpFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

/**
 * This class contain functionality to start up game
 */
public class StartUpController extends BaseController<StartUpFrame> implements ActionListener {
    /**
     * HashSet for number of submitted player
     */
    HashSet<Integer> completedPlayers;

    /**
     * This is controller constructor for Startup
     *
     * @param view associated with controller
     */
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
                country.setNumOfArmies( armies);
            }
            if (view.playerCountries.getItemCount() == 1)
                completedPlayers.add(model.currentPlayer.id);

            if (!changeCurrentPlayer()) {
                stateChangeListener.onStartUpCompleted();
                view.dispose();
            }
        }
    }

    /**
     * Function for current player change
     *
     * @return true,false
     */
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
