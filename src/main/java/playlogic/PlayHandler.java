package playlogic;

import game.UIController;
import playUI.PlayPage;
import userInterface.MenuPage;

public class PlayHandler {
    private UIController uiController = UIController.getInstance();

    public void startPlayPage(PlayerInfo player1, PlayerInfo player2, MenuPage menuPage) {
        GameState gameState = GameState.getInstance(1, player1, player2);
        initializeFrame(menuPage);
    }

    public void initializeFrame(MenuPage menuPage) {
        uiController.changeFrame();
        uiController.changeState(menuPage, new PlayPage());
    }
}
