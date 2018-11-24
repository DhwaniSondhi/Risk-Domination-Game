package view;

import controller.TournamentModeController;

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

    /**
     * This is constructor for tournament view
     *This setup panel for view
     */
    public TournamentModeFrame(){
        JPanel mainPanel=new JPanel();
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

        JLabel jLabelFortify = new JLabel("Tournament");
        jLabelFortify.setFont(new Font("Tournament", Font.BOLD, 20));
        bagConstraintsMain.fill = GridBagConstraints.VERTICAL;
        bagConstraintsMain.gridx = 0;
        bagConstraintsMain.gridy = 0;
        mainPanel.add(jLabelFortify, bagConstraintsMain);




    }


}
