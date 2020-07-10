package playcard;

import module.Minion;
import playlogic.PlayHandler;
import playlogic.PlayerInfo;

public class CardStranglethornTiger extends MinionCard {
    public CardStranglethornTiger(Minion minion, PlayerInfo playerInfo, PlayHandler playHandler, String id) {
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
