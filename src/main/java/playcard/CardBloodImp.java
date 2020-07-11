package playcard;

import module.Card;
import playlogic.PlayerInfo;

public class CardBloodImp extends MinionCard {
    public CardBloodImp(Card card, PlayerInfo playerInfo, String id) {
        super(card, playerInfo, id);
    }

    @Override
    public void doDeathrattle() {
        return;
    }

    @Override
    public void endTurn() {
        //   CardCard cardCard = .getRandomFriendly(player);
        //   cardCard.setHp(cardCard.getHp() + 1);
    }

    @Override
    public void die() {
        return;
    }

    @Override
    public void doWhenSummon() {
        setStealth(true);
    }

    @Override
    public void doBattlecry() {
        return;
    }
}
