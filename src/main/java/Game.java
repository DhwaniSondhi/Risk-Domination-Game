import entity.Config;
import gui.MainFrame;

public class Game {

    public static void main(String[] args) {
        System.out.println("Starting the game...");
        Config config = Config.getInstance();
        config.setDummyData();

        MainFrame frame = new MainFrame();
    }
}
