package module;

public class PlayersId {
    final private String playerUsername;
    final private String playerPassword;
    final private int playerId;

    public PlayersId(String newPlayerUsername, String newPlayerPassword, int newPlayerId) {
        playerPassword = newPlayerPassword;
        playerUsername = newPlayerUsername;
        playerId = newPlayerId;
    }

    public String getPlayerUsername() {
        return playerUsername;
    }

    public String getPlayerPassword() {
        return playerPassword;
    }

    public int getPlayerId() {
        return playerId;
    }
}