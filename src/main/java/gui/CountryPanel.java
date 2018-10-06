package gui;

import entity.Country;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.HashMap;

import static javax.swing.BoxLayout.Y_AXIS;

/**
 * Class containing functions and GUI for the country panel
 **/

public class CountryPanel extends JPanel {

    public CountryPanel() {
        setBackground(Color.LIGHT_GRAY);
        setBorder(new LineBorder(Color.BLACK, 2));
        setLayout(new BoxLayout(this, Y_AXIS));

        JLabel jLabelCountriesName = new JLabel("Countries");
        add(jLabelCountriesName);

    }

    /**
     * Shows all countries and owner name in panel
     *
     * @param countries contain data of all countries
     */

    public void updateCountries(HashMap<Integer, Country> countries) {
        removeAll();
        int index = 0;
        String[] countriesAll = new String[countries.size()];
        if (countries != null) {
            for (Country countryname : countries.values()) {
                countriesAll[index] = countryname.id + ". " + countryname.name + " (" + countryname.owner.name + ")";
                index++;
            }
        }
        JList list = new JList(countriesAll);
        JScrollPane jScrollPaneCountries = new JScrollPane(list);
        add(jScrollPaneCountries);

    }
}
