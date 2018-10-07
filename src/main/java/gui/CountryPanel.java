package gui;

import controller.CountryController;
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

    /**
     * controller for the view
     */
    CountryController controller1;

    /**
     * panel to display the list of countries
     */
    JPanel contentPanel1;

    /**
     * Contructor
     * Sets up the panel for country list
     */
    public CountryPanel() {
        controller1 = new CountryController(this);

        setBackground(Color.LIGHT_GRAY);
        setBorder(new LineBorder(Color.BLACK, 2));
        setLayout(new BoxLayout(this, Y_AXIS));

        JLabel jLabelCountriesName = new JLabel("Countries");
        add(jLabelCountriesName);

        contentPanel1 = new JPanel();
        contentPanel1.setBackground(Color.LIGHT_GRAY);
        contentPanel1.setLayout(new BoxLayout(contentPanel1, BoxLayout.Y_AXIS));
        add(contentPanel1);

        controller1.updateCountryList();

    }

    /**
     * update the countries list and owner name in panel
     *
     * @param countries contain data of all countries
     */

    public void updateCountries(HashMap<Integer, Country> countries) {
        contentPanel1.removeAll();
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

        contentPanel1.revalidate();
    }

}
