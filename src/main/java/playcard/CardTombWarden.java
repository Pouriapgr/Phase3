package playcard;

import module.Minion;
import playlogic.PlayHandler;
import playlogic.PlayerInfo;

public class CardTombWarden extends MinionCard {
    public CardTombWarden(Minion minion, PlayerInfo playerInfo, PlayHandler playHandler, String id) {
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
        setTaunt(true);
    }

    @Override
    public void doBattlecry() {
        //playHandler.summonCopy(this);
    }
}
