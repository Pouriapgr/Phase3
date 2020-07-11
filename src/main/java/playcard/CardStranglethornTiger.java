package playcard;

import module.Card;
import playlogic.PlayerInfo;

public class CardStranglethornTiger extends MinionCard {
    public CardStranglethornTiger(Card card, PlayerInfo playerInfo, String id) {
        super(card, playerInfo, id);
    }

    @Override
    public void doDeathrattle() {
        return;
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
        // setStealth(true);
    }

    @Override
    public void doBattlecry() {
    }
}
