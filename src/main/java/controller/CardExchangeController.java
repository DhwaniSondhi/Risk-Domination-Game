package controller;

import view.CardExchangeFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardExchangeController extends BaseController<CardExchangeFrame> implements ActionListener {
    /**
     * Constructor for Card Exchange Controller
     * <p>
     * To initialize attributes
     * </p>
     *
     * @param cardExchangeFrame the card exchange view attached to it
     */
    public CardExchangeController(CardExchangeFrame cardExchangeFrame) {
        super(cardExchangeFrame);

    }

    public void initialize() {
        model.currentPlayer.addObserver(view);
        model.currentPlayer.setUnSelectedCards();
        model.currentPlayer.setArmiesForReinforcement();
        model.currentPlayer.emptySelectedCards();

    }


    /**
     * Invoked on any action performed on Add, Reset and Update buttons for Card Section in Reinforcement Panel
     *
     * @param e {@link ActionEvent}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonName = ((JButton) e.getSource()).getName();

        if (buttonName.substring(0, 3).equalsIgnoreCase("ADD")) {
            model.currentPlayer.addInSelectedCards(buttonName.substring(3));
        } else if (buttonName.equalsIgnoreCase("Update")) {
            model.currentPlayer.getUpdatedArmiesOnCardsExchange();
        } else if (buttonName.equalsIgnoreCase("Reset")) {
            model.currentPlayer.resetSelectedCards();
        } else if (buttonName.equalsIgnoreCase("exit")) {
            view.setVisible(false);
            model.currentPlayer.emptySelectedCards();
            model.currentPlayer.updateReinforcementPanel = true;
            model.currentPlayer.setArmiesForReinforcement();
            initialize();
        }

    }
}
