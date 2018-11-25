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
    TournamentModeController tournamentModeController;
    GridBagLayout gridBagLayoutMain;
    GridBagConstraints bagConstraintsMain;
    JPanel jPanelForPlayer;
    JPanel jPanelForStrategyOfPlayer;
    JPanel jPanelForMaps;
    JPanel jPanelForNumberOfGamesOnMap;
    JPanel mainPanel;
    JPanel jPanelProceedButton;
    JButton jButtonStartTournament;
    JPanel jPanelForStartTournamentButton;
    JLabel jLabelTournament;


    /**
     * This is constructor for tournament view
     *This setup panel for view
     */
    public TournamentModeFrame(){
        tournamentModeController=new TournamentModeController(this);
        mainPanel=new JPanel();
        gridBagLayoutMain = new GridBagLayout();
        bagConstraintsMain = new GridBagConstraints();
        mainPanel.setLayout(gridBagLayoutMain);
        mainPanel.setBackground(Color.LIGHT_GRAY);
        mainPanel.setBorder(new LineBorder(Color.BLACK, 2));
        //mainPanel.setSize(800,600);
        //mainPanel.setVisible(true);
        add(mainPanel);
        setSize(800,600);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jLabelTournament = new JLabel("Tournament");
        jLabelTournament.setFont(new Font("Tournament", Font.BOLD, 20));
        bagConstraintsMain.fill = GridBagConstraints.VERTICAL;
        bagConstraintsMain.gridx = 0;
        bagConstraintsMain.gridy = 0;
        mainPanel.add(jLabelTournament, bagConstraintsMain);
        playerPanels();



    }
    public void playerPanels(){
        jPanelForPlayer=new JPanel();
        JButton jButtonForNumberOfPlayers=new JButton();
        jButtonForNumberOfPlayers.setText("Submit");
        bagConstraintsMain.fill = GridBagConstraints.VERTICAL;
        bagConstraintsMain.gridx = 0;
        bagConstraintsMain.gridy = 1;
        mainPanel.add(jPanelForPlayer,bagConstraintsMain);

        jPanelForPlayer.add(new JLabel("Number Of Players:"));
        JComboBox<Integer> comboBoxNumberOfPlayer=new JComboBox<>();
        for(int i=2;i<=4;i++){
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
        jPanelForStrategyOfPlayer=new JPanel();
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
        mainPanel.add(jPanelForStrategyOfPlayer,bagConstraintsMain);
        mainPanel.revalidate();
        mainPanel.repaint();
        jPanelForStrategyOfPlayer.revalidate();
        jPanelForStrategyOfPlayer.repaint();
        jPanelForPlayer.setVisible(false);
        jPanelProceedButton=new JPanel();
        bagConstraintsMain.fill = GridBagConstraints.VERTICAL;
        bagConstraintsMain.gridx = 0;
        bagConstraintsMain.gridy = 3;
        mainPanel.add(jPanelProceedButton,bagConstraintsMain);
        JButton jButtonProceed=new JButton("Proceed");
        jPanelProceedButton.add(jButtonProceed);
        jButtonProceed.addActionListener(tournamentModeController);


    }
    public void proceedToMaps()
    {
        jPanelForStrategyOfPlayer.setVisible(false);
        jPanelProceedButton.setVisible(false);
        jPanelForMaps=new JPanel();
        jPanelForMaps.add(new JLabel("Number Of Maps:"));
        JComboBox<Integer> jComboBoxNumberOfMaps=new JComboBox<>();
        for(int i=1;i<=5;i++){
           jComboBoxNumberOfMaps.addItem(i);
        }
        jComboBoxNumberOfMaps.setName("ComboboxForMaps");
        jComboBoxNumberOfMaps.addItemListener(tournamentModeController);
        jPanelForMaps.add(jComboBoxNumberOfMaps);
        JButton jButtonForNumberOfMaps=new JButton("Submit Number");
        jPanelForMaps.add(jButtonForNumberOfMaps);
        jButtonForNumberOfMaps.addActionListener(tournamentModeController);
        bagConstraintsMain.fill = GridBagConstraints.VERTICAL;
        bagConstraintsMain.gridx = 0;
        bagConstraintsMain.gridy = 1;
        mainPanel.add(jPanelForMaps,bagConstraintsMain);
        jPanelForMaps.revalidate();
        mainPanel.revalidate();
        mainPanel.repaint();
        revalidate();

    }
    public void updateMapsPanel(int count) {
        jPanelForMaps.setVisible(false);
        jLabelTournament.setVisible(false);

        JPanel jPanelForLabelSelectionOfGameOnMap=new JPanel();
        bagConstraintsMain.fill = GridBagConstraints.VERTICAL;
        bagConstraintsMain.gridx = 0;
        bagConstraintsMain.gridy = 0;
        mainPanel.add(jPanelForLabelSelectionOfGameOnMap,bagConstraintsMain);
        jPanelForLabelSelectionOfGameOnMap.add(new JLabel("Select Number of games on each map"));

        System.out.println("fsdfsdfsdfsd");
        JList<Integer> jlistForNumberOfMaps=new JList<Integer>();


        jPanelForNumberOfGamesOnMap=new JPanel();
        int currentSize = jPanelForNumberOfGamesOnMap.getComponents().length;
        int diff = Math.abs(currentSize - count);
        if (currentSize <= count) {
            for (int i = 0; i < diff; i++) {
                int x = jPanelForNumberOfGamesOnMap.getComponentCount() + 1;
                JPanel panel = new JPanel();
                panel.setLayout(new FlowLayout());

                JTextField nameField = new JTextField();
                nameField.setText("Maps" + x);
                panel.add(nameField);
                JComboBox<Integer> type=new JComboBox<>();
                for(int maps=1;maps<=5;maps++){
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
        mainPanel.add(jPanelForNumberOfGamesOnMap,bagConstraintsMain);
        jPanelForStartTournamentButton=new JPanel();

        bagConstraintsMain.fill = GridBagConstraints.VERTICAL;
        bagConstraintsMain.gridx = 0;
        bagConstraintsMain.gridy = 3;
        mainPanel.add(jPanelForStartTournamentButton,bagConstraintsMain);
        jButtonStartTournament=new JButton("Start Tournament");
        jPanelForStartTournamentButton.add(jButtonStartTournament);
        jButtonStartTournament.addActionListener(tournamentModeController);
        jPanelForStartTournamentButton.revalidate();

        mainPanel.revalidate();
        mainPanel.repaint();
        revalidate();




    }


}
