package game;

import module.Player;

public class InfoHolder {
    private static InfoHolder infoHolder;
    private Player player;

    private InfoHolder(Player player) {
        this.player = player;
    }

    public static InfoHolder getInstance(Player player) {
        infoHolder = new InfoHolder(player);
        return infoHolder;
    }

    public static InfoHolder getInstance() {
        return infoHolder;
    }

    public Player getPlayer() {
        return player;
    }
}
