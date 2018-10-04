package controller;

import gui.FortifyPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for FortifyPanel
 */
public class FortifyController extends BaseController<FortifyPanel> implements ActionListener {
    /**
     * This is the constructor for the Controller
     *
     * @param view View associated with the Controller
     */
    public FortifyController(FortifyPanel view) {
        super(view);
    }

   public void updateCountryListFortify(){
        view.showCountriesFortify(model.getCountriesOfCurrentPlayer());
        view.showNeighbouringCountriesFortify(model.getCountriesOfCurrentPlayer());
        view.TransferFortify();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
