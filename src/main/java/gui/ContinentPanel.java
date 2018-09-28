package gui;

import entity.Continent;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.HashMap;

public class ContinentPanel extends JPanel {

    public ContinentPanel() {
        setBackground(Color.LIGHT_GRAY);
        setBorder(new LineBorder(Color.BLACK, 2));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void updateContents(HashMap<Integer, Continent> continents) {
        removeAll();
        add(new JLabel("Continents"));
        if (continents != null) {
            for (Continent continent : continents.values()) {
                JLabel label = new JLabel();
                label.setName("label");
                label.setText(continent.id + ". " + continent.name + " (" + continent.controlValue + ")");
                add(label);
            }
        }
    }
}
