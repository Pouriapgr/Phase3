package userInterface;

public class RunGame {
    private static GameJFrame gameJFrame = GameJFrame.getInstance();
    public static void main(String[] args) {
        gameJFrame.changeState(null, new SignPage());
    }
}