package gui;

public class ContinentPanelTest {

    /*ContinentPanel view;

    JFrame frame;
    List<Continent> continents;

    @Before
    public void setUp() throws Exception {
        view = new ContinentPanel();
        continents = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Continent continent = new Continent(i, "Continent" + i, i * 2);
            continents.add(continent);
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
    public void updateContinentList() throws Exception {
        view.updateContinentList(continents);
        Assert.assertEquals(view.contentPanel.getComponents().length, continents.size());
        for (int i = 0; i < continents.size(); i++) {
            JLabel label = (JLabel) view.contentPanel.getComponent(i);
            Continent continent = continents.get(i);
            String text = String.format("%d. %s | CV = %d | countries = %d", continent.id, continent.name,
                    continent.controlValue, continent.countries.size());
            Assert.assertEquals(label.getText(), text);
        }
    }*/

}