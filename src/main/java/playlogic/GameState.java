package playlogic;

import game.TimeAssistance;
import playUI.PlayPage;

public class GameState {
    private static GameState gameState = null;
    private int noOfGame;

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

    public void changeTurn(PlayPage playPage) {
        if (playerTurn == 1) {
            playerTurn = 2;
            player2.setTurnMana(player2.getTurnMana() + 1);
            player2.setMana(player2.getTurnMana());
        } else {
            playerTurn = 1;
            player1.setTurnMana(player1.getTurnMana() + 1);
            player1.setMana(player1.getTurnMana());
        }
        turnNo++;
        Timer.setExit(true);
        TimeAssistance.waitFor(500L);
        Timer timer = new Timer(playPage);
        timer.start();
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
