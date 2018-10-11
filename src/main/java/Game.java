import entity.GameMap;
import gui.MainFrame;

public class Game {

    public static void main(String[] args) {
        System.out.println("Starting the game...");
        GameMap.getInstance();
        MainFrame frame = new MainFrame();
    }
}
