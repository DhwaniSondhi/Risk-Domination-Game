package gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import static javax.swing.BoxLayout.Y_AXIS;

/**
 * Class containing fuctions and GUI for the country panel
 **/

public class CountryPanel extends JPanel {

    public CountryPanel() {
        setBackground(Color.LIGHT_GRAY);
        setBorder(new LineBorder(Color.BLACK, 2));
        setLayout(new BoxLayout(this, Y_AXIS));
        String labels[] = {"India", "Pakistan", "China", "Afganistan", "Nepal", "Bangladesh", "Sri Lanka", "Malasyia", "Mangol", "Russia"};
        JList list = new JList(labels);
        JScrollPane scrollPane12 = new JScrollPane(list);
        add(scrollPane12);
    }
}