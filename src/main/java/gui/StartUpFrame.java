package gui;

import entity.Country;
import entity.GameMap;
import entity.Player;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;


public class StartUpFrame extends JFrame {

    private JPanel mainStartPanel, countriesPanel;
    private JLabel jlabel = new JLabel("Welcome to the RISK Game\n");
    private JLabel jlabel2 = new JLabel("Choose number of players:");
    JComboBox numOfPlayers;
    private JButton buttonSubmit = new JButton("Submit");
    private JTextField addArmies;

    public StartUpFrame() {
        super("Welcome to the Game");

        mainStartPanel = new JPanel();
        mainStartPanel.setLayout(new BoxLayout(mainStartPanel, BoxLayout.Y_AXIS));
        add(mainStartPanel);

        mainStartPanel.setBorder(new LineBorder(Color.BLACK));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900, 900);
        setVisible(true);


        buttonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameMap.getInstance().players.clear();
                for (int i = 0; i < Integer.valueOf((Integer) numOfPlayers.getSelectedItem()); i++) {
                    Player player = new Player(i + 1, "Player" + i);
                    GameMap.getInstance().players.put(player.id, player);
                }

                GameMap.getInstance().assignCountriesToPlayers();
                updateCountries(GameMap.getInstance().countries);
            }
        });
        numOfPlayers = new JComboBox();
        for (int i = 0; i < GameMap.getInstance().countries.size(); i++) {
            numOfPlayers.addItem(i + 1);
        }


        countriesPanel = new JPanel();
        countriesPanel.setLayout(new GridBagLayout());
        //jlabel2.setLabelFor(nuOfPlayers);
        mainStartPanel.add(jlabel);
        mainStartPanel.add(jlabel2);
        mainStartPanel.add(numOfPlayers);
        mainStartPanel.add(buttonSubmit);
        mainStartPanel.add(countriesPanel);

        mainStartPanel.revalidate();

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

    public void updateCountries(HashMap<Integer, Country> countries) {
        int index = 0;
        addArmies = new JTextField(10);

        String[] countriesAll = new String[countries.size()];
        if (countries != null) {
            for (Country countryname : countries.values()) {
                countriesPanel.add(new JLabel(countryname.name + ": "), getConstraints(0, index));
                JTextField field = new JTextField();
                countriesPanel.add(field, getConstraints(1, index));
                index++;
            }
        }
        countriesPanel.revalidate();
        /*JList list = new JList(countriesAll);
        JScrollPane jScrollPaneCountries = new JScrollPane(list);
        mainStartPanel.add(jScrollPaneCountries);
        mainStartPanel.revalidate();*/
    }
}

