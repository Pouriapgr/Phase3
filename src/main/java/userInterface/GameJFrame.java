package userInterface;

import file.FileAssistance;
import Constants.GraphicConstants;

import javax.swing.*;

public class GameJFrame extends JFrame {
    private static GameJFrame gameJFrame ;

    private GameJFrame() {
        super("MemeStone");
        setSize(GraphicConstants.FRAME_WIDTH, GraphicConstants.FRAME_HEIGHT);
        setIconImage(FileAssistance.getImage("icon", "icon.jpg"));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    public static synchronized GameJFrame getInstance() {
        if(gameJFrame == null)
            gameJFrame = new GameJFrame();
        return gameJFrame;
    }

    public void changeState(UIState pre, UIState next) {
        if(next.runState()) {
            pre.getRenew();
            gameJFrame.setContentPane(pre);
            gameJFrame.validate();
        }
    }
}