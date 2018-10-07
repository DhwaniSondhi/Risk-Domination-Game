import entity.GameMap;
import gui.MainFrame;

public class Game {

    public static void main(String[] args) {
        System.out.println("Starting the game...");
        GameMap config = GameMap.getInstance();
        config.setDummyData();

        MainFrame frame = new MainFrame();
    }
}
