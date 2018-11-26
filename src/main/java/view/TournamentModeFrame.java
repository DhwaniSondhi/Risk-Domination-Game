package view;

import controller.TournamentModeController;
import utility.strategy.PlayerStrategy;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;


/**
 * Gui class for tournament view
 * It controls the view
 */
public class TournamentModeFrame extends JFrame {
    /**
     * Reference to TournamentControllerClass
     */
    TournamentModeController tournamentModeController;
    /**
     * Variable for gridbaglayout
     */
    GridBagLayout gridBagLayoutMain;
    /**
     * Variable for GridBagConstraints
     */
    GridBagConstraints bagConstraintsMain;
    /**
     * Panel for number of player
     */
    JPanel jPanelForPlayer;
    /**
     * Panel for enter strategy for player
     */
    public JPanel jPanelForStrategyOfPlayer;
    /**
     * Panel for number of maps
     */
    JPanel jPanelForMaps;
    /**
     * Panel for number of games in each map
     */
    JPanel jPanelForNumberOfGamesOnMap;
    /**
     * Main panel for adding all other panel
     */
    JPanel mainPanel;
    /**
     * Panel to add proceed button
     */
    JPanel jPanelProceedButton;
    /**
     * Panel to add start tournament button
     */
    JButton jButtonStartTournament;
    /**
     * Panel to add start tournament button
     */
    JPanel jPanelForStartTournamentButton;
    /**
     * Label for tournament text
     */
    JLabel jLabelTournament;


    /**
     * This is constructor for tournament view
     * This setup panel for view
     */
    public TournamentModeFrame() {
        tournamentModeController = new TournamentModeController(this);
        mainPanel = new JPanel();
        gridBagLayoutMain = new GridBagLayout();
        bagConstraintsMain = new GridBagConstraints();
        mainPanel.setLayout(gridBagLayoutMain);
        mainPanel.setBackground(Color.LIGHT_GRAY);
        mainPanel.setBorder(new LineBorder(Color.BLACK, 2));
        add(mainPanel);
        setSize(800, 600);
        setVisible(true);

        jLabelTournament = new JLabel("Tournament");
        jLabelTournament.setFont(new Font("Tournament", Font.BOLD, 20));
        bagConstraintsMain.fill = GridBagConstraints.VERTICAL;
        bagConstraintsMain.gridx = 0;
        bagConstraintsMain.gridy = 0;
        mainPanel.add(jLabelTournament, bagConstraintsMain);
        playerPanels();


    }

    /**
     * Function to add number of player
     */
    public void playerPanels() {
        jPanelForPlayer = new JPanel();
        JButton jButtonForNumberOfPlayers = new JButton();
        jButtonForNumberOfPlayers.setText("Submit");
        bagConstraintsMain.fill = GridBagConstraints.VERTICAL;
        bagConstraintsMain.gridx = 0;
        bagConstraintsMain.gridy = 1;
        mainPanel.add(jPanelForPlayer, bagConstraintsMain);

        jPanelForPlayer.add(new JLabel("Number Of Players:"));
        JComboBox<Integer> comboBoxNumberOfPlayer = new JComboBox<>();
        for (int i = 2; i <= 4; i++) {
            comboBoxNumberOfPlayer.addItem(i);
        }
        jPanelForPlayer.add(comboBoxNumberOfPlayer);
        comboBoxNumberOfPlayer.setName("NumberOfPlayer");

        comboBoxNumberOfPlayer.addItemListener(tournamentModeController);
        jButtonForNumberOfPlayers.addActionListener(tournamentModeController);
        jPanelForPlayer.add(jButtonForNumberOfPlayers);

        mainPanel.revalidate();
        revalidate();

    }

    /**
     * Shows player fields in the panel
     *
     * @param count number of players
     */
    public void updatePlayersPanel(int count) {
        jPanelForStrategyOfPlayer = new JPanel();
        int currentSize = jPanelForStrategyOfPlayer.getComponents().length;
        int diff = Math.abs(currentSize - count);
        if (currentSize <= count) {
            for (int i = 0; i < diff; i++) {
                int x = jPanelForStrategyOfPlayer.getComponentCount() + 1;
                JPanel panel = new JPanel();
                panel.setLayout(new FlowLayout());

                JTextField nameField = new JTextField();
                nameField.setText("Player" + x);
                panel.add(nameField);

                JComboBox<PlayerStrategy.Strategy> type = new JComboBox<>(PlayerStrategy.Strategy.values());
                panel.add(type);

                jPanelForStrategyOfPlayer.add(panel);
            }
        } else {
            for (int i = currentSize - 1; i > currentSize - (diff + 1); i--) {
                jPanelForStrategyOfPlayer.remove(i);
            }
        }


        bagConstraintsMain.fill = GridBagConstraints.VERTICAL;
        bagConstraintsMain.gridx = 0;
        bagConstraintsMain.gridy = 2;
        mainPanel.add(jPanelForStrategyOfPlayer, bagConstraintsMain);
        mainPanel.revalidate();
        mainPanel.repaint();
        jPanelForStrategyOfPlayer.revalidate();
        jPanelForStrategyOfPlayer.repaint();
        jPanelForPlayer.setVisible(false);
        jPanelProceedButton = new JPanel();
        bagConstraintsMain.fill = GridBagConstraints.VERTICAL;
        bagConstraintsMain.gridx = 0;
        bagConstraintsMain.gridy = 3;
        mainPanel.add(jPanelProceedButton, bagConstraintsMain);
        JButton jButtonProceed = new JButton("Proceed");
        jPanelProceedButton.add(jButtonProceed);
        jButtonProceed.addActionListener(tournamentModeController);


    }

    /**
     * Function for number of maps
     */
    public void proceedToMaps() {
        jPanelForStrategyOfPlayer.setVisible(false);
        jPanelProceedButton.setVisible(false);
        jPanelForMaps = new JPanel();
        jPanelForMaps.add(new JLabel("Number Of Maps:"));
        JComboBox<Integer> jComboBoxNumberOfMaps = new JComboBox<>();
        for (int i = 1; i <= 5; i++) {
            jComboBoxNumberOfMaps.addItem(i);
        }
        jComboBoxNumberOfMaps.setName("ComboboxForMaps");
        jComboBoxNumberOfMaps.addItemListener(tournamentModeController);
        jPanelForMaps.add(jComboBoxNumberOfMaps);
        JButton jButtonForNumberOfMaps = new JButton("Submit Number");
        jPanelForMaps.add(jButtonForNumberOfMaps);
        jButtonForNumberOfMaps.addActionListener(tournamentModeController);
        bagConstraintsMain.fill = GridBagConstraints.VERTICAL;
        bagConstraintsMain.gridx = 0;
        bagConstraintsMain.gridy = 1;
        mainPanel.add(jPanelForMaps, bagConstraintsMain);
        jPanelForMaps.revalidate();
        mainPanel.revalidate();
        mainPanel.repaint();
        revalidate();

    }

    /**
     * Function to add number of games to each map
     *
     * @param count number of maps
     */
    public void updateMapsPanel(int count) {
        jPanelForMaps.setVisible(false);
        jLabelTournament.setVisible(false);

        JPanel jPanelForLabelSelectionOfGameOnMap = new JPanel();
        bagConstraintsMain.fill = GridBagConstraints.VERTICAL;
        bagConstraintsMain.gridx = 0;
        bagConstraintsMain.gridy = 0;
        mainPanel.add(jPanelForLabelSelectionOfGameOnMap, bagConstraintsMain);
        jPanelForLabelSelectionOfGameOnMap.add(new JLabel("Select Number of games on each map"));




        jPanelForNumberOfGamesOnMap = new JPanel();
        int currentSize = jPanelForNumberOfGamesOnMap.getComponents().length;
        int diff = Math.abs(currentSize - count);
        if (currentSize <= count) {
            for (int i = 0; i < diff; i++) {
                int x = jPanelForNumberOfGamesOnMap.getComponentCount() + 1;
                JPanel panel = new JPanel();
                panel.setLayout(new FlowLayout());

                JButton nameField = new JButton();
                nameField.setText("Maps" + x);
                nameField.addActionListener(tournamentModeController);
                panel.add(nameField);
                JComboBox<Integer> type = new JComboBox<>();
                for (int maps = 1; maps <= 5; maps++) {
                    type.addItem(maps);
                }

                panel.add(type);

                jPanelForNumberOfGamesOnMap.add(panel);
            }
        } else {
            for (int i = currentSize - 1; i > currentSize - (diff + 1); i--) {
                jPanelForNumberOfGamesOnMap.remove(i);
            }
        }


        bagConstraintsMain.fill = GridBagConstraints.VERTICAL;
        bagConstraintsMain.gridx = 0;
        bagConstraintsMain.gridy = 2;
        mainPanel.add(jPanelForNumberOfGamesOnMap, bagConstraintsMain);
        jPanelForStartTournamentButton = new JPanel();

        bagConstraintsMain.fill = GridBagConstraints.VERTICAL;
        bagConstraintsMain.gridx = 0;
        bagConstraintsMain.gridy = 3;
        mainPanel.add(jPanelForStartTournamentButton, bagConstraintsMain);
        jButtonStartTournament = new JButton("Start Tournament");
        jPanelForStartTournamentButton.add(jButtonStartTournament);
        jButtonStartTournament.addActionListener(tournamentModeController);
        jPanelForStartTournamentButton.revalidate();

        mainPanel.revalidate();
        mainPanel.repaint();
        revalidate();


    }


}
