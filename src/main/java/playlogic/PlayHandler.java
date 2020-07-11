package playlogic;

import game.UIController;
import playUI.ChangeCardPage;
import playUI.PlayPage;
import userInterface.MenuPage;

public class PlayHandler {
    private static PlayHandler playHandler;
    private UIController uiController = UIController.getInstance();
    private GameState gameState;

    private PlayHandler() {
    }

    public static PlayHandler getInstance() {
        if (playHandler == null)
            playHandler = new PlayHandler();
        return playHandler;
    }

    public static PlayHandler newHandler() {
        return new PlayHandler();
    }

    public void startPlayPage(PlayerInfo player1, PlayerInfo player2, MenuPage menuPage) {
        GameState gameState = GameState.getInstance(1, player1, player2);
        uiController.changeState(menuPage, new ChangeCardPage(player1));
        uiController.changeState(menuPage, new ChangeCardPage(player2));
        initializeFrame(menuPage);
    }

    public void initializeFrame(MenuPage menuPage) {
        PlayPage playPage = new PlayPage();
        playPage.setInitials();
        uiController.changeFrame(playPage);
        runPlay(playPage);
        uiController.rechangeFrame(menuPage);
    }

    public void runPlay(PlayPage playPage) {
        playPage.setInitials();
        uiController.setContentPane(playPage);

        while (true) {
            uiController.validate();
            if (playPage.newAction("Back")) {
                return;
            }
        }
    }
}
