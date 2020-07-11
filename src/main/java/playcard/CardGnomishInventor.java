package playcard;

import module.Card;
import playlogic.PlayerInfo;

public class CardGnomishInventor extends MinionCard {
    public CardGnomishInventor(Card card, PlayerInfo playerInfo, String id) {
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
        return;
    }

    @Override
    public void doBattlecry() {
//        CardCard cardCard = .draw(playerInfo);
//        .addCard(cardCard);
    }
}
