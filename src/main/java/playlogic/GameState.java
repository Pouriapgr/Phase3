package playlogic;

import game.TimeAssistance;
import playUI.HandButton;
import playUI.PlayButton;
import playcard.PlayCard;
import playcard.WeaponCard;

public class GameState {
    private static GameState gameState = null;
    private int noOfGame;
    private PlayHandler playHandler = PlayHandler.getInstance();

    private PlayerInfo player1;
    private PlayerInfo player2;

    private int turnNo = 0;
    private int playerTurn = 0;

    private GameState(int noOfGame, PlayerInfo player1, PlayerInfo player2) {
        this.noOfGame = noOfGame;
        this.player1 = player1;
        this.player2 = player2;
    }

    public static GameState getInstance() {
        return gameState;
    }

    public static GameState getInstance(int noOfGame, PlayerInfo player1, PlayerInfo player2) {
        gameState = new GameState(noOfGame, player1, player2);
        return gameState;
    }

    public void changeTurn() {
        if (playerTurn == 1) {
            playerTurn = 2;
            player2.setTurnMana(player2.getTurnMana() + 1);
            player2.setMana(player2.getTurnMana());
            playHandler.drawCard(player2);
        } else {
            playerTurn = 1;
            player1.setTurnMana(player1.getTurnMana() + 1);
            player1.setMana(player1.getTurnMana());
            playHandler.drawCard(player1);
        }
        turnNo++;
        Timer.setExit(true);
        TimeAssistance.waitFor(500L);
        Timer timer = new Timer(playHandler.getPlayPage());
        timer.start();
    }

    public boolean canPlayCard(PlayerInfo playerInfo, PlayCard playCard) {
        return playerInfo.getFinalMana(playCard) <= playerInfo.getMana();
    }

    public void playMinion(PlayCard playCard, PlayButton playButton, HandButton handButton) {
        PlayerInfo playerInfo = playCard.getPlayerInfo();
        playerInfo.setMana(playerInfo.getMana() - playerInfo.getFinalMana(playCard));
        playerInfo.removeFromHand(playCard);
        playButton.setNewCard(playCard);
        handButton.setEmpty();
    }

    public void playWeapon(PlayCard playCard, HandButton handButton) {
        PlayerInfo playerInfo = playCard.getPlayerInfo();
        playerInfo.setMana(playerInfo.getMana() - playerInfo.getFinalMana(playCard));
        playerInfo.removeFromHand(playCard);
        playerInfo.setWeaponCard((WeaponCard) playCard);
        handButton.setEmpty();
    }

    public PlayerInfo getPlayer1() {
        return player1;
    }

    public PlayerInfo getPlayer2() {
        return player2;
    }

    public int getTurnNo() {
        return turnNo;
    }

    public void setTurnNo(int turnNo) {
        this.turnNo = turnNo;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }
}
