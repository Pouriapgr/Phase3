package playcard;

import module.Card;
import playlogic.PlayerInfo;

public class CardOctosari extends MinionCard {
    public CardOctosari(Card card, PlayerInfo playerInfo, String id) {
        super(card, playerInfo, id);
    }

    @Override
    public void doDeathrattle() {
//        for (int i = 0; i <= 7; i++) {
//            PlayCard playCard = .draw(playerInfo);
//            .addCard(playCard);
//        }
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
        return;
    }
}
