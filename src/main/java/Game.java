import entity.GameMap;
import gui.MainFrame;
import gui.MapCreatorFrame;

public class Game {

    public static void main(String[] args) {
        System.out.println("Starting the game...");
        GameMap config = GameMap.getInstance();
        config.setDummyData();

        MainFrame frame = new MainFrame();
        new MapCreatorFrame("Create Form", false);
    }
}
