package gui;

import entity.GameMap;
import entity.Player;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StartUpFrame extends JFrame {

    private JPanel mainStartPanel;
    private JLabel jlabel = new JLabel("Welcome to the RISK Game\n");
    private JLabel jlabel2 = new JLabel("Choose number of players:");
    JComboBox  numOfPlayers;
    private JButton buttonSubmit = new JButton("Submit");

    public StartUpFrame() {
        super("Welcome to the Game");

        mainStartPanel = new JPanel();
        mainStartPanel.setLayout(new FlowLayout());
        add(mainStartPanel);

        mainStartPanel.setBorder(new LineBorder(Color.BLACK));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900,900);
        setVisible(true);





        buttonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameMap.getInstance().players.clear();
                for (int i = 0; i<Integer.valueOf((Integer) numOfPlayers.getSelectedItem()); i++){
                    Player player = new Player(i+1, "Player"+i);
                    GameMap.getInstance().players.put(player.id,player);
                }

                GameMap.getInstance().assignCountriesToPlayers();
            }
        });
        numOfPlayers = new JComboBox();
        for (int i = 0; i < GameMap.getInstance().countries.size(); i++) {
            numOfPlayers.addItem(i+1);
        }

        //jlabel2.setLabelFor(nuOfPlayers);
        mainStartPanel.add(new JLabel("adada"));
        mainStartPanel.add(jlabel);
        mainStartPanel.add(jlabel2);
       mainStartPanel.add(numOfPlayers);
        mainStartPanel.add(buttonSubmit);
        mainStartPanel.revalidate();



    }


}

