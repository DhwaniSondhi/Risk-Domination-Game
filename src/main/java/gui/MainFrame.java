package gui;

import entity.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private JPanel mainPanel;

    public MainFrame() {
        super("Risk Game - SOEN6441 - Team 19");
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        add(mainPanel);
        setUpMenuBar();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900, 900);
        setVisible(true);

        setUpGamePanels();
    }

    /**
     * Adds all necessary panel components to the main panel
     * */
    private void setUpGamePanels() {
        // TODO: Add your panels here...
        ContinentPanel panel = new ContinentPanel();
        mainPanel.add(panel, getConstraints(0, 0));
        panel.updateContents(Config.getInstance().continents);
    }


    /**
     * Creates {@link GridBagConstraints} with provided gridX and gridY values
     *
     * @param x value for constraints gridx (row in the grid)
     * @param y value for constraints gridY (col in the grid)
     *
     * @return default constraints (see {@link GridBagConstraints}) with provided x,y values
     */
    private GridBagConstraints getConstraints(int x, int y) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weighty = 1;
        constraints.weightx = 1;
        return constraints;
    }

    /**
     * Adds menu bar to the frame with following options:\n
     * create map | load map | exit
     */
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


    /**
     * see {@link ActionListener}
     * ActionListener for the menu bar items
     * */
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

    /**
     * Enum class to differentiate menu item for the ActionListener
     * */
    enum MENU_ITEM {
        EXIT, LOAD, CREATE
    }
}
