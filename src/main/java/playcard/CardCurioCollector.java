package playcard;

import module.Card;
import playlogic.PlayerInfo;

public class CardCurioCollector extends MinionCard {
    public CardCurioCollector(Card card, PlayerInfo playerInfo, String id) {
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
//        playerInfo.setAddToDrawAttack(playerInfo.getAddToDrawAttack() - 1);
//        playerInfo.setAddToDrawHp(playerInfo.getAddToDrawHp() - 1);
    }

    @Override
    public void doWhenSummon() {
//        playerInfo.setAddToDrawAttack(playerInfo.getAddToDrawAttack() + 1);
//        playerInfo.setAddToDrawHp(playerInfo.getAddToDrawHp() + 1);
    }

    @Override
    public void doBattlecry() {
        return;
    }
}
