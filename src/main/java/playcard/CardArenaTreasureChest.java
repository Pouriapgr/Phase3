package playcard;

import module.Card;
import playlogic.PlayerInfo;

public class CardArenaTreasureChest extends MinionCard {
    public CardArenaTreasureChest(Card card, PlayerInfo playerInfo, String id) {
        super(card, playerInfo, id);
    }

    @Override
    public void doDeathrattle() {
//        for (int i = 0; i <= 1; i++) {
//            PlayCard playCard = .drawCard(player);
//            .addCard(player, playCard);
//        }
    }

    @Override
    public void endTurn() {
        return;
    }

    @Override
    public void die() {
        return;
    }

    @Override
    public void doWhenSummon() {
        return;
    }

    @Override
    public void doBattlecry() {
        return;
    }
}
