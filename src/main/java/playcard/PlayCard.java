package playcard;

import module.Card;
import playlogic.PlayHandler;
import playlogic.PlayerInfo;

public abstract class PlayCard {
    protected final PlayerInfo playerInfo;
    protected final PlayHandler playHandler;

    protected final String name;
    protected final String id;

    protected final int initialManaCost;
    protected int manaCost;

    protected String cardClass;

    public PlayCard(Card card, PlayerInfo playerInfo, PlayHandler playHandler, String id) {
        this.playerInfo = playerInfo;
        this.playHandler = playHandler;

        name = card.getCardShowName();
        this.id = id;

        initialManaCost = card.getCardCost();
        manaCost = initialManaCost;

        cardClass = card.getCardClass();
    }


    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }

    public int getInitialManaCost() {
        return initialManaCost;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PlayHandler getPlayHandler() {
        return playHandler;
    }

    public String getCardClass() {
        return cardClass;
    }
}