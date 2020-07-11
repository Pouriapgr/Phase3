package playlogic;

import game.UIController;
import playUI.ChangeCardPage;
import playUI.PlayPage;
import userInterface.MenuPage;

public class PlayHandler {
    private static PlayHandler playHandler;
    private UIController uiController = UIController.getInstance();
    private GameState gameState;
    private FieldChecker fieldChecker;

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
        gameState = GameState.getInstance(1, player1, player2);
        uiController.changeState(menuPage, new ChangeCardPage(player1));
        uiController.changeState(menuPage, new ChangeCardPage(player2));
        initializeFrame(menuPage);
    }

    private void initializeFrame(MenuPage menuPage) {
        PlayPage playPage = new PlayPage();
        playPage.setInitials();
        uiController.changeFrame(playPage);
        runPlay(playPage);
        uiController.rechangeFrame(menuPage);
    }

    public void runPlay(PlayPage playPage) {
        playPage.setInitials();
        doLogicInitials(playPage);

        while (true) {
            uiController.validate();
            if (playPage.newAction("Back")) {
                endPlay();
                return;
            }

            if (playPage.newAction("End")) {
                gameState.changeTurn(playPage);
            }
        }
    }

    private void doLogicInitials(PlayPage playPage) {
        fieldChecker = new FieldChecker(playPage);
        fieldChecker.start();
        uiController.setContentPane(playPage);
        gameState.changeTurn(playPage);
    }

    private void endPlay() {
        fieldChecker.interrupt();
    }

}
