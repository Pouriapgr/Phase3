package playlogic;

import game.TimeAssistance;
import game.UIController;
import playUI.Animator;
import playUI.ChangeCardPage;
import playUI.HandButton;
import playUI.PlayPage;
import playcard.PlayCard;
import userInterface.MenuPage;
import userInterface.MyButton;

public class PlayHandler {
    private static PlayHandler playHandler;
    private static PlayPage playPage;
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
        playPage = new PlayPage();
        playPage.setInitials();
        uiController.changeFrame(playPage);
        runPlay();
        uiController.rechangeFrame(menuPage);
    }

    public void runPlay() {
        doLogicInitials();

        while (true) {
            uiController.validate();
            if (playPage.newAction("Back")) {
                endPlay();
                return;
            }
            if (playPage.newAction("End")) {
                gameState.changeTurn();
                TimeAssistance.waitFor(500L);
            }
        }
    }

    private void doLogicInitials() {
        fieldChecker = new FieldChecker(playPage);
        fieldChecker.start();
        for (PlayCard playCard : gameState.getPlayer1().getStart()) {
            draw(gameState.getPlayer1(), playCard);
            TimeAssistance.waitFor(3500L);
        }
        for (PlayCard playCard : gameState.getPlayer2().getStart()) {
            draw(gameState.getPlayer2(), playCard);
            TimeAssistance.waitFor(3500L);
        }


        gameState.changeTurn();
    }

    public void drawCard(PlayerInfo playerInfo) {
        if (playerInfo.getDeck().getCards().size() == 0)
            return;
        if (playerInfo.getHand().size() == 7) {
            fadeDraw(playerInfo);
            return;
        }
        draw(playerInfo);
    }

    private void fadeDraw(PlayerInfo playerInfo) {
        PlayCard playCard = playerInfo.getDeck().getCards().get(0);
        playerInfo.getDeck().getCards().remove(0);

        MyButton myButton = playPage.getMyButton(playerInfo.getId() + "Draw");

        Animator animator = new Animator(myButton, "CardFade", playCard);
        animator.start();
    }

    private void draw(PlayerInfo playerInfo) {
        PlayCard playCard = playerInfo.getDeck().getCards().get(0);
        playerInfo.getDeck().getCards().remove(0);

        MyButton myButton = playPage.getMyButton(playerInfo.getId() + "Draw");

        Animator animator = new Animator(myButton, getHandPlace(playerInfo), playCard, 20L, "MoveToHand");
        animator.start();

        playerInfo.addToHand(playCard);
    }

    private void draw(PlayerInfo playerInfo, PlayCard playCard) {
        MyButton myButton = playPage.getMyButton(playerInfo.getId() + "Draw");

        Animator animator = new Animator(myButton, getHandPlace(playerInfo), playCard, 20L, "MoveToHand");
        animator.start();

        playerInfo.addToHand(playCard);
    }

    private HandButton getHandPlace(PlayerInfo playerInfo) {
        if (playerInfo.getId() == 1) {
            for (HandButton handButton : playPage.getHandButtonsPlayer1())
                if (handButton.getCard() == null)
                    return handButton;
        } else {
            for (HandButton handButton : playPage.getHandButtonsPlayer2())
                if (handButton.getCard() == null)
                    return handButton;
        }
        return null;
    }

    private void endPlay() {
        fieldChecker.interrupt();
    }

    public PlayPage getPlayPage() {
        return playPage;
    }
}
