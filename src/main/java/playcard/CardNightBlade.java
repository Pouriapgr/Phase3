package playcard;

import module.Card;
import playlogic.PlayerInfo;

public class CardNightBlade extends MinionCard {
    public CardNightBlade(Card card, PlayerInfo playerInfo, String id) {
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
        //.dealDamageToEnemyHero(playerInfo, 3);
    }

    @Override
    public void doBattlecry() {
        return;
    }
}
