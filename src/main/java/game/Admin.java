package game;

import card.Card;
import module.Player;

public class Admin {
    private static Admin admin = null;
    private UIController uiController = UIController.getInstance();
    private InfoHolder infoHolder = InfoHolder.getInstance();
    private Player player = infoHolder.getPlayer();

    private Admin() {
    }

    public static Admin getInstance() {
        if (admin == null)
            return new Admin();
        return admin;
    }

    public boolean buyCard(Card card) {
        if (card.getCardValue() > player.getPlayerCoins())
            return false;

        player.setPlayerCoins(player.getPlayerCoins() - card.getCardValue());
        player.addCard(card);
        return true;
    }

    public void sellCard(Card card) {
        player.setPlayerCoins(player.getPlayerCoins() + card.getCardValue());
        player.removeCardFromAllCards(card);
    }
}
