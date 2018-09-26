package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {


    public MainFrame() {
        super("Risk Game - SOEN6441 - Team 19");
        setLayout(new GridLayout(3, 2, 10, 20));
        setUpMenuBar();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900, 900);
        setVisible(true);
    }

    private void setUpMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");

        JMenuItem load = new JMenuItem("Load Map");
        menuFile.add(load);
        JMenuItem create = new JMenuItem("Create Map");
        menuFile.add(create);
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new MenuActionListener(MENU_ITEM.EXIT));
        menuFile.add(exit);

        menuBar.add(menuFile);

        setJMenuBar(menuBar);
    }

    private class MenuActionListener implements ActionListener {

        private MENU_ITEM item;

        public MenuActionListener(MENU_ITEM item) {
            this.item = item;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (item) {
                case CREATE:
                    break;
                case LOAD:
                    break;
                case EXIT:
                default:
                    System.exit(0);
                    break;
            }
        }
    }

    enum MENU_ITEM {
        EXIT, LOAD, CREATE
    }
}
