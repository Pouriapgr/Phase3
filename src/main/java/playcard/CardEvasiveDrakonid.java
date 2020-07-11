package playcard;

import module.Card;
import playlogic.PlayerInfo;

public class CardEvasiveDrakonid extends MinionCard {
    public CardEvasiveDrakonid(Card card, PlayerInfo playerInfo, String id) {
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
//        setTaunt(true);
//        setBeTargetedByCardAndPower(false);
    }

    @Override
    public void doBattlecry() {
        return;
    }
}
