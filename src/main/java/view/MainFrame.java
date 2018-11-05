package view;

import controller.MainFrameController;
import model.GameMap;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Main window of the game
 */
public class MainFrame extends JFrame implements Observer {

    /**
     * Reference to continentPanel class
     */
    public ContinentPanel continentPanel;
    /**
     * Reference to countryPanel class
     */
    public CountryPanel countryPanel;
    /**
     * Reference to reinforcementPanel class
     */
    public ReinforcementPanel reinforcementPanel;
    /**
     * Reference to attackPanel class
     */
    public AttackPanel attackPanel;
    /**
     * Reference to fortifyPanel class
     */
    public FortifyPanel fortifyPanel;
    /**
     * Label that display current playing player
     */
    public JLabel currentPlayer;
    /**
     * Label that display current phase
     */
    public JLabel currentPhase;
    /**
     * Label that display recentMove
     */
    public JLabel recentMove;

    /**
     * Panel that will contain all other panel
     */
    private JPanel mainPanel;
    /**
     * Reference for MainFrameController
     */
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
        mainPanel.removeAll();
        continentPanel = new ContinentPanel();
        mainPanel.add(continentPanel, getConstraints(0, 1));

        countryPanel = new CountryPanel();
        mainPanel.add(countryPanel, getConstraints(0, 2));


        currentPlayer = new JLabel();
        currentPhase = new JLabel();
        recentMove = new JLabel();
        JPanel phaseViewPanel = new JPanel();
        phaseViewPanel.setLayout(new BoxLayout(phaseViewPanel, BoxLayout.Y_AXIS));
        phaseViewPanel.add(currentPhase);
        phaseViewPanel.add(currentPlayer);
        phaseViewPanel.add(recentMove);
        GridBagConstraints constraints = getConstraints(1, 0);
        mainPanel.add(phaseViewPanel, constraints);

        reinforcementPanel = new ReinforcementPanel(controller);
        mainPanel.add(reinforcementPanel, getConstraints(1, 1));

        attackPanel = new AttackPanel(controller);
        attackPanel.setVisible(false);
        mainPanel.add(attackPanel, getConstraints(1, 1));

        fortifyPanel = new FortifyPanel(controller);
        fortifyPanel.setVisible(false);
        mainPanel.add(fortifyPanel, getConstraints(1, 1));

        mainPanel.revalidate();
        mainPanel.repaint();
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

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof GameMap) {
            if (currentPhase != null) {
                GameMap instance = GameMap.getInstance();
                currentPhase.setText(instance.currentPhase.toString());
                currentPlayer.setText(instance.currentPlayer.name);
                recentMove.setText(instance.recentMove);
            }
        }
    }
}
