package userInterface;

import constants.GraphicConstants;
import file.FileAssistance;

import javax.swing.*;

public class GameJFrame extends JFrame {
    private static GameJFrame gameJFrame;

    private GameJFrame() {
        super("MemeStone");
        setIconImage(FileAssistance.getImage("icon", "icon.jpg"));

        setSize(GraphicConstants.FRAME_WIDTH, GraphicConstants.FRAME_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }

    public static synchronized GameJFrame getInstance() {
        if (gameJFrame == null)
            gameJFrame = new GameJFrame();
        return gameJFrame;
    }

}