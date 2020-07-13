package playlogic;

import game.TimeAssistance;
import game.UIController;
import playUI.*;
import playcard.*;
import userInterface.MenuPage;
import userInterface.MyButton;

import java.util.ArrayList;

public class PlayHandler {
    private static PlayHandler playHandler;
    private static PlayPage playPage;
    private UIController uiController = UIController.getInstance();
    private GameState gameState;
    private FieldChecker fieldChecker;
    private boolean isRunning = true;

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
            if (!isRunning) {
                gameState.changeTurn();
                isRunning = true;
            }
            if (playPage.newAction("Back")) {
                endPlay();
                return;
            }
            if (playPage.newAction("End")) {
                endTurn();
                continue;
            }

            HandButton handButton = checkChooseHand();
            if (handButton != null) {
                PlayCard playCard = handButton.getCard();
                if (playCard instanceof MinionCard) {
                    System.out.println("XXX");
                    tryPlayMinion(handButton);
                    continue;
                }
                if (playCard instanceof SpellCard) {

                    continue;
                }

                if (playCard instanceof WeaponCard) {
                    tryPlayWeapon(handButton);
                    continue;
                }
                if (playCard instanceof QuestCard) {
                    continue;
                }
                continue;
            }

            PlayButton playButton = checkChoosePlace();
            if (playButton != null) {

            }
        }
    }

    private void tryPlayMinion(HandButton handButton) {
        while (isRunning) {
            if (playPage.newAction("End")) {
                endTurn();
                return;
            }
            PlayButton playButton = checkChoosePlace();
            if (playButton == null)
                continue;
            playMinionInit(handButton, playButton);
            return;
        }
    }

    private void tryPlayWeapon(HandButton handButton) {
        while (isRunning) {
            if (playPage.newAction("End")) {
                endTurn();
                return;
            }
            if (!handButton.isSelectCard())
                continue;
            playWeaponInit(handButton);
            return;
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

        ButtonFader buttonFader = new ButtonFader(myButton, playCard.getName());
        buttonFader.start();
    }

    private void draw(PlayerInfo playerInfo) {
        PlayCard playCard = playerInfo.getDeck().getCards().get(0);
        playerInfo.getDeck().getCards().remove(0);

        MyButton myButton = playPage.getMyButton(playerInfo.getId() + "Draw");

        playerInfo.addToHand(playCard);
        HandMover handMover = new HandMover(myButton, getHandPlace(playerInfo), 20L, playCard);
        handMover.start();
    }

    private void draw(PlayerInfo playerInfo, PlayCard playCard) {
        MyButton myButton = playPage.getMyButton(playerInfo.getId() + "Draw");

        playerInfo.addToHand(playCard);
        HandMover handMover = new HandMover(myButton, getHandPlace(playerInfo), 20L, playCard);
        handMover.start();
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

    private HandButton checkChooseHand() {
        ArrayList<HandButton> handButtons;
        if (gameState.getPlayerTurn() == 1) {
            handButtons = playPage.getHandButtonsPlayer1();
        } else {
            handButtons = playPage.getHandButtonsPlayer2();
        }
        HandButton handButton = null;
        for (HandButton button : handButtons) {
            if (button.isSelectCard()) {
                handButton = button;
                handButton.setSelectCard(false);
                break;
            }
        }
        return handButton;
    }

    private PlayButton checkChoosePlace() {
        ArrayList<PlayButton> playButtons;
        if (gameState.getPlayerTurn() == 1) {
            playButtons = playPage.getPlayedButtonsPlayer1();
        } else {
            playButtons = playPage.getPlayedButtonsPlayer2();
        }
        PlayButton playButton = null;
        for (PlayButton button : playButtons) {
            if (button.isSelectPlace()) {
                playButton = button;
                button.setSelectPlace(false);
            }
        }
        return playButton;
    }

    private PlayButton checkChooseCard() {
        ArrayList<PlayButton> playButtons;
        if (gameState.getPlayerTurn() == 1) {
            playButtons = playPage.getPlayedButtonsPlayer1();
        } else {
            playButtons = playPage.getPlayedButtonsPlayer2();
        }
        PlayButton playButton = null;
        for (PlayButton button : playButtons) {
            if (button.isSelectCard()) {
                playButton = button;
                button.setSelectCard(false);
            }
        }
        return playButton;
    }

    private void playMinionInit(HandButton handButton, PlayButton playButton) {
        PlayCard playCard = handButton.getCard();
        if (gameState.getPlayerTurn() == 1) {
            if (gameState.canPlayCard(gameState.getPlayer1(), playCard))
                gameState.playMinion(playCard, playButton, handButton);
        } else if (gameState.getPlayerTurn() == 2) {
            if (gameState.canPlayCard(gameState.getPlayer2(), playCard))
                gameState.playMinion(playCard, playButton, handButton);
        }
    }


    private void playWeaponInit(HandButton handButton) {
        PlayCard playCard = handButton.getCard();
        if (gameState.getPlayerTurn() == 1) {
            if (gameState.canPlayCard(gameState.getPlayer1(), playCard))
                gameState.playWeapon(playCard, handButton);
        } else if (gameState.getPlayerTurn() == 2) {
            if (gameState.canPlayCard(gameState.getPlayer2(), playCard))
                gameState.playWeapon(playCard, handButton);
        }
    }

    private void endTurn() {
        isRunning = false;
    }

    private void endPlay() {
        fieldChecker.interrupt();
    }

    public PlayPage getPlayPage() {
        return playPage;
    }
}