package gui;

import controller.MainFrameController;
import entity.Config;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {

    private JPanel mainPanel;
    private MainFrameController controller;

    public MainFrame() {
        super("Risk Game - SOEN6441 - Team 19");

        controller = new MainFrameController(this);

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
        create.addActionListener(controller);
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(controller);
        menuFile.add(exit);

        menuBar.add(menuFile);

        setJMenuBar(menuBar);
    }



}
