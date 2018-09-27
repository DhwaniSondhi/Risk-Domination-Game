package gui;

import entity.Continent;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ContinentPanel extends JPanel {

    private List<Continent> continents;

    public ContinentPanel() {
        setBackground(Color.LIGHT_GRAY);
        setBorder(new LineBorder(Color.BLACK, 2));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Continents"));

        setContinents(getDummyContinents());
    }

    @Override
    public void updateUI() {
        updateUI(continents);
        super.updateUI();
    }

    public void setContinents(List<Continent> continents) {
        this.continents = continents;
        updateUI();
    }

    private List<Continent> getDummyContinents() {
        List<Continent> data = new ArrayList<>();
        data.add(new Continent(1, "C1", 5));
        data.add(new Continent(2, "C2", 10));
        data.add(new Continent(3, "C3", 15));
        data.add(new Continent(4, "C4", 7));
        data.add(new Continent(5, "C5", 7));
        data.add(new Continent(6, "C6", 7));
        data.add(new Continent(7, "C7", 7));
        return data;
    }

    private void updateUI(List<Continent> continents) {
        if (continents != null) {
            for (Continent continent : continents) {
                JLabel label = new JLabel();
                label.setText(continent.id + ". " + continent.name + " (" + continent.controlValue + ")");
                add(label);
            }
        }
    }
}
