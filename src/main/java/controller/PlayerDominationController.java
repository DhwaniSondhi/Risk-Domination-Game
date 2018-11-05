package controller;

import model.Country;
import model.Player;
import view.PlayerDominationView;

public class PlayerDominationController extends BaseController<PlayerDominationView> {

    /**
     * This is the constructor for the Controller
     *
     * @param view View associated with the Controller
     */
    public PlayerDominationController(PlayerDominationView view) {
        super(view);
        for (Country country : model.countries.values()) {
            country.addObserver(view);
        }
        for (Player player : model.players.values()) {
            player.addObserver(view);
        }
    }
}
