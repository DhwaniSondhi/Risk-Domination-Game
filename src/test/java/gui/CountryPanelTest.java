package gui;

public class CountryPanelTest {

    /*CountryPanel view;

    Jframe frame;
    List<Country> countries;

    @Before
     public void setUp() throws Exception {
        view = new CountryPanel();
        countries = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Country country = new Country(i, "Country" + i, i * 2);
            countries.add(country);
        }
        frame = new JFrame("test");
        frame.add(view);
    }

    @After
    public void tearDown() throws Exception {
        if (frame != null) {
            frame.dispose();
            frame = null;
        }
    }

    @Test
    public void updateCountries() throws Exception {
        view.updateCountries(countries);
        Assert.assertEquals(view.contentPanel1.getComponents().length, countries.size());
        for (int i = 0; i < countries.size(); i++) {
            JLabel label = (JLabel) view.contentPanel1.getComponent(i);
            Country country = countries.get(i);
            String text = String.format("%d. %s | CV = %d | countries = %d", country.id, country.name,
                    country.controlValue, country.id());
            Assert.assertEquals(label.getText(), text);
        }
    }
     */
}
