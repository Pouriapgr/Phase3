package game;

import constants.GraphicConstants;
import playUI.PlayPage;
import userInterface.ChooseDeckPage;
import userInterface.GameJFrame;
import userInterface.MenuPage;
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
            if (!(next instanceof ChooseDeckPage))
                uiController.setContentPane(pre);
            uiController.validate();
        }
    }

    public void changeFrame(PlayPage playPage) {
        gameJFrame.setSize(GraphicConstants.PLAY_FRAME_WIDTH, GraphicConstants.PLAY_FRAME_HEIGHT);
        gameJFrame.setLocationRelativeTo(null);
        gameJFrame.setContentPane(playPage);
    }

    public void rechangeFrame(MenuPage menuPage) {
        gameJFrame.setSize(GraphicConstants.FRAME_WIDTH, GraphicConstants.FRAME_HEIGHT);
        gameJFrame.setLocationRelativeTo(null);
        gameJFrame.setContentPane(menuPage);
    }

    public void setContentPane(Container contentPane) {
        gameJFrame.setContentPane(contentPane);
    }

    public void validate() {
        gameJFrame.validate();
    }
}
