package gui;

import controller.StartUpController;
import entity.Country;
import entity.GameMap;
import entity.Player;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;


public class StartUpFrame extends JFrame {

    private JPanel mainStartPanel, countriesPanel;
    private JLabel jlabel = new JLabel("Welcome to the RISK Game\n");
    private JLabel jlabel2 = new JLabel("Choose number of players:");
    JComboBox numOfPlayers;
    private JButton buttonSubmit = new JButton("Submit");
    private JTextField addArmies;
    StartUpController startUpController;
    JLabel jLabelplayers;
    JComboBox jComboBoxCountries;
    JComboBox numberOfArmies;
    JButton jButtonAssignArmy;


    public StartUpFrame() {
        super("Welcome to the Game");
        startUpController=new StartUpController(this);
        mainStartPanel = new JPanel();
        jLabelplayers=new JLabel();
        mainStartPanel.setLayout(new BoxLayout(mainStartPanel, BoxLayout.Y_AXIS));
        add(mainStartPanel);

        mainStartPanel.setBorder(new LineBorder(Color.BLACK));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900, 900);
        setVisible(true);

        buttonSubmit.setName("submit");
        buttonSubmit.addActionListener(startUpController);
        numOfPlayers = new JComboBox();
        for (int i = 1; i < GameMap.getInstance().countries.size(); i++) {
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
        jButtonAssignArmy=new JButton("Assign");
        jButtonAssignArmy.setName("Assign");
        jButtonAssignArmy.addActionListener(startUpController);
    }
    public int getNumOfPlayers(){
        return (Integer)numOfPlayers.getSelectedItem();
    }
    public String getjLabelPlayerValue(){
        return jLabelplayers.getText();
    }
    public void setjLabelPlayerValue(String newPlayerId){
        jLabelplayers.setText(newPlayerId);
    }
    public int getjComboBoxCountry(){

        return jComboBoxCountries.getSelectedIndex();
    }
    public int getNumberOfArmies(){
        return (Integer)numberOfArmies.getSelectedItem();
    }
    public void clickAssignButton(){
        jButtonAssignArmy.doClick();
    }
    public void addProceedButton(){
        mainStartPanel.removeAll();
        mainStartPanel.add(new JButton("Proceed"));
        mainStartPanel.revalidate();
        mainStartPanel.repaint();
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
        constraints.weighty = 0;
        constraints.weightx = 0;
        return constraints;
    }

    public void updateCountries(List<Country> countries) {
        countriesPanel.removeAll();
        int index = 0;
        addArmies = new JTextField(10);
        jComboBoxCountries=new JComboBox();


        String[] countriesAll = new String[countries.size()];
        if (countries != null) {
            for (Country countryname : countries) {
                jComboBoxCountries.addItem(countryname);
                index++;
            }
        }

        countriesPanel.add(new JLabel("Player"),getConstraints(0,0));
        jLabelplayers.setText(String.valueOf(GameMap.getInstance().currentPlayer.id));
        countriesPanel.add(jLabelplayers,getConstraints(0,1));
        countriesPanel.add(jComboBoxCountries,getConstraints(0,2));
        countriesPanel.add(new JLabel("Select Number of armies"),getConstraints(0,3));
        numberOfArmies=new JComboBox(startUpController.getArmies());
        countriesPanel.add(numberOfArmies,getConstraints(0,4));


        countriesPanel.add(jButtonAssignArmy,getConstraints(0,5));
        countriesPanel.revalidate();
        countriesPanel.repaint();
        /*JList list = new JList(countriesAll);
        JScrollPane jScrollPaneCountries = new JScrollPane(list);
        mainStartPanel.add(jScrollPaneCountries);
        mainStartPanel.revalidate();*/
    }
}