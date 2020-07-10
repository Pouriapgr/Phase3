package playcard;

import module.Minion;
import playlogic.PlayHandler;
import playlogic.PlayerInfo;

public class CardCurioCollector extends MinionCard {
    public CardCurioCollector(Minion minion, PlayerInfo playerInfo, PlayHandler playHandler, String id) {
        super(minion, playerInfo, playHandler, id);
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
        playerInfo.setAddToDrawAttack(playerInfo.getAddToDrawAttack() - 1);
        playerInfo.setAddToDrawHp(playerInfo.getAddToDrawHp() - 1);
    }

    @Override
    public void doWhenSummon() {
        playerInfo.setAddToDrawAttack(playerInfo.getAddToDrawAttack() + 1);
        playerInfo.setAddToDrawHp(playerInfo.getAddToDrawHp() + 1);
    }

    @Override
    public void doBattlecry() {
        return;
    }
}
