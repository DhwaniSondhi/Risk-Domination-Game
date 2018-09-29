package controller;

import gui.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the Controller for the MainFrame. see {@link BaseController}
 * implements {@link ActionListener} for MenuItems
 * */
public class MainFrameController extends BaseController<MainFrame> implements ActionListener {

    public MainFrameController(MainFrame view) {
        super(view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("Load Map")) {

        } else if (e.getActionCommand().equalsIgnoreCase("Create Map")) {

        } else if (e.getActionCommand().equalsIgnoreCase("exit")) {
            System.exit(0);
        }
    }
}
