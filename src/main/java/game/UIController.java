package game;

import constants.GraphicConstants;
import userInterface.GameJFrame;
import userInterface.State;

import java.awt.*;

public class UIController {
    private static UIController uiController;
    private GameJFrame gameJFrame = GameJFrame.getInstance();

    private UIController() {
    }

    public static UIController getInstance() {
        if (uiController == null)
            uiController = new UIController();
        return uiController;
    }

    public void changeState(State pre, State next) {
        if (next.runState()) {
            pre.updateState();
            uiController.setContentPane(pre);
            uiController.validate();
        }
    }

    public void changeFrame() {
        gameJFrame.setSize(GraphicConstants.PLAY_FRAME_WIDTH, GraphicConstants.PLAY_FRAME_HEIGHT);
        gameJFrame.setLocationRelativeTo(null);
    }

    public void setContentPane(Container contentPane) {
        gameJFrame.setContentPane(contentPane);
    }

    public void validate() {
        gameJFrame.validate();
    }
}
