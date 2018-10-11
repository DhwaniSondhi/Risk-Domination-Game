package gui;

import controller.MainFrameController;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {

    public ContinentPanel continentPanel;
    public CountryPanel countryPanel;
    public ReinforcementPanel reinforcementPanel;
    public AttackPanel attackPanel;
    public FortifyPanel fortifyPanel;
    public JLabel currentPlayer;
    private JPanel mainPanel;
    private MainFrameController controller;

    public MainFrame() {
        super("Risk Game - SOEN 6441 - Team 19");

        controller = new MainFrameController(this);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        add(mainPanel);
        setUpMenuBar();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900, 700);
        setVisible(true);
    }

    /**
     * Adds all necessary panel components to the main panel
     */
    public void setUpGamePanels() {
        continentPanel = new ContinentPanel();
        mainPanel.add(continentPanel, getConstraints(0, 1));

        countryPanel = new CountryPanel();
        mainPanel.add(countryPanel, getConstraints(0, 2));

        currentPlayer = new JLabel();
        GridBagConstraints constraints = getConstraints(1, 0);
        mainPanel.add(currentPlayer, constraints);

        reinforcementPanel = new ReinforcementPanel(controller);
        mainPanel.add(reinforcementPanel, getConstraints(1, 1));

        attackPanel = new AttackPanel(controller);
        attackPanel.setVisible(false);
        mainPanel.add(attackPanel, getConstraints(1, 1));

        fortifyPanel = new FortifyPanel(controller);
        fortifyPanel.setVisible(false);
        mainPanel.add(fortifyPanel, getConstraints(1, 1));

        mainPanel.revalidate();
    }


    /**
     * Creates {@link GridBagConstraints} with provided gridX and gridY values
     *
     * @param x value for constraints gridx (row in the grid)
     * @param y value for constraints gridY (col in the grid)
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
     * create countryGraph | load countryGraph | exit
     */
    private void setUpMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");

        JMenuItem load = new JMenuItem("Load GameMap");
        load.addActionListener(controller);
        menuFile.add(load);
        JMenuItem create = new JMenuItem("Create GameMap");
        menuFile.add(create);
        create.addActionListener(controller);
        JMenuItem edit = new JMenuItem("Edit GameMap");
        menuFile.add(edit);
        edit.addActionListener(controller);
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(controller);
        menuFile.add(exit);

        menuBar.add(menuFile);

        setJMenuBar(menuBar);
    }


}
