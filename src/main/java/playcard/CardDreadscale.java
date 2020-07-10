package playcard;

import module.Minion;
import playlogic.PlayHandler;
import playlogic.PlayerInfo;

public class CardDreadscale extends MinionCard {
    public CardDreadscale(Minion minion, PlayerInfo playerInfo, PlayHandler playHandler, String id) {
        super(minion, playerInfo, playHandler, id);
    }

    @Override
    public void doDeathrattle() {
        return;
    }

    @Override
    public void endTurn() {
        //playHandler.damageAll(this, 1);
    }

    @Override
    public void die() {
        return;
    }

    @Override
    public void doWhenSummon() {
        return;
    }
}
