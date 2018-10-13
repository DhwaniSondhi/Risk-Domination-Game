import entity.GameMap;
import gui.MainFrame;
import gui.StartUpFrame;

public class Game {

    public static void main(String[] args) {
        System.out.println("Starting the game...");
        GameMap config = GameMap.getInstance();
        config.setDummyData();

        StartUpFrame frame = new StartUpFrame();
    }
}
