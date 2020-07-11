package playcard;

import module.Minion;
import playlogic.PlayHandler;
import playlogic.PlayerInfo;

public class CardPartyElemental extends MinionCard {
    public CardPartyElemental(Minion minion, PlayerInfo playerInfo, PlayHandler playHandler, String id) {
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
        // playHandler.doParty();
    }

    @Override
    public void doBattlecry() {
        return;
    }
}
