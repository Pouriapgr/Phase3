package playlogic;

public class GameState {
    private static GameState gameState = null;
    private int noOfGame;

    private PlayerInfo player1;
    private PlayerInfo player2;

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
}
