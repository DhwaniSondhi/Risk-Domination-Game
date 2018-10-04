package gui;


import controller.ReinforcementController;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Gui part of Reinforcement Panel
 * extends {@Link JPanel}
 */
public class ReinforcementPanel extends JPanel {
    JPanel cardSection;
    ReinforcementController reinforcementController;

    /**
     * Constructor
     * <p>
     * To create the Panel for Reinforcement components
     * </p>
     */
    public ReinforcementPanel(){
        setLayout(new GridBagLayout());
        reinforcementController=new ReinforcementController(this);
        cardSection=new JPanel();
        cardSection.setLayout(new GridBagLayout());
        getCardSection();
    }

    /**
     * Add Panel for cards selection, updation and reset
     */
    public void getCardSection(){
        cardSection.removeAll();
        reinforcementController.getCards();
        addButtons();
        cardSection.revalidate();
        add(cardSection);
    }

    /**
     * Add panel for Update and Reset buttons in Cards section
     */
    public void addButtons(){
        JPanel buttons=new JPanel();
        buttons.setLayout(new GridLayout(2,1));
        JButton update=new JButton("Update");
        update.setName("Update");
        update.addActionListener(reinforcementController);
        buttons.add(update);
        JButton reset=new JButton("Reset");
        reset.setName("Reset");
        reset.addActionListener(reinforcementController);
        buttons.add(reset);
        cardSection.add(buttons,getGridContraints(2,0));

    }

    /**
     * Add panel for unselected cards and add buttons
     */
    public void getUnselectedCardGrid(HashMap<String,Integer> cardsOfCurrentPlayer){
        JPanel cardsUnselected=new JPanel();
        cardsUnselected.setLayout(new GridLayout(3,1));
        Iterator itForCards=cardsOfCurrentPlayer.entrySet().iterator();
        while(itForCards.hasNext()){
            Map.Entry cardPair=(Map.Entry)itForCards.next();
            JPanel cardButtonPanel=new JPanel();
            cardButtonPanel.setLayout(new GridLayout(1,2));
            String label=cardPair.getValue()+" "+cardPair.getKey();
            JLabel cardLabel=new JLabel(label);
            cardButtonPanel.add(cardLabel);
            JButton add=new JButton("Add");
            add.addActionListener(reinforcementController);
            add.setName("Add"+cardPair.getKey());
            if(Integer.valueOf(label.substring(0,1))<1){
                add.setEnabled(false);
            }else{
                add.setEnabled(true);
            }
            cardButtonPanel.add(add);
            cardsUnselected.add(cardButtonPanel);
        }
        cardSection.add(cardsUnselected,getGridContraints(0,0));
    }

    /**
     * Add panel for selected cards
     */
    public void getSelectedCardGrid(HashMap<String,Integer> selectedCards){
        JPanel cardsSelected=new JPanel();
        cardsSelected.setLayout(new GridLayout(3,1));

        Iterator itForCards=selectedCards.entrySet().iterator();
        while(itForCards.hasNext()){
            Map.Entry cardPair=(Map.Entry)itForCards.next();
            JPanel cardButtonPanel=new JPanel();
            cardButtonPanel.setLayout(new GridLayout(1,1));
            JLabel cardLabel=new JLabel(cardPair.getValue()+" "+cardPair.getKey());
            cardButtonPanel.add(cardLabel);
            cardsSelected.add(cardButtonPanel);
        }
        cardSection.add(cardsSelected,getGridContraints(1,0));

    }

    /**
     * Set the constraints for GridBagLayout used
     */
    public GridBagConstraints getGridContraints(int x,int y){
        GridBagConstraints gridBagConstraints=new GridBagConstraints();
        gridBagConstraints.gridx=x;
        gridBagConstraints.gridy=y;
        gridBagConstraints.fill=GridBagConstraints.BOTH;
       /* gridBagConstraints.weightx=1;
        gridBagConstraints.weighty=1*/;
        return gridBagConstraints;
    }

}
