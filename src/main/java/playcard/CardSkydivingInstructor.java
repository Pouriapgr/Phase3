package playcard;

import module.Minion;
import playlogic.PlayHandler;
import playlogic.PlayerInfo;

public class CardSkydivingInstructor extends MinionCard {
    public CardSkydivingInstructor(Minion minion, PlayerInfo playerInfo, PlayHandler playHandler, String id) {
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
        return;
    }

    @Override
    public void doBattlecry() {
        //playHandler.summonRandomMinionFromDeck(playerInfo, 1);
    }
}
