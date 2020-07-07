package game;

import userInterface.GameJFrame;
import userInterface.SignPage;
import userInterface.State;

import java.awt.*;

public class UIController {
    private static UIController uiController;
    private GameJFrame gameJFrame = GameJFrame.getInstance();

    private UIController() {
        changeState(null, new SignPage());
    }

    public static UIController getInstance() {
        if (uiController == null)
            uiController = new UIController();
        return uiController;
    }

    public void changeState(State pre, State next) {
        if (next.runState()) {
            pre.updateState();
            gameJFrame.setContentPane(pre);
            gameJFrame.validate();
        }
    }

    public void setContentPane(Container contentPane) {
        gameJFrame.setContentPane(contentPane);
    }

    public void validate() {
        gameJFrame.validate();
    }
}
