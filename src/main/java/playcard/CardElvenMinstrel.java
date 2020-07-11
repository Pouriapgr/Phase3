package playcard;

import module.Card;
import playlogic.PlayerInfo;

public class CardElvenMinstrel extends MinionCard {
    public CardElvenMinstrel(Card card, PlayerInfo playerInfo, String id) {
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
        //        for(int i = 0; i <= 2; i++) {
//            PlayCard playCard = .draw(player);
//            .addCard(player, playCard);
//        }
    }

    @Override
    public void doBattlecry() {
        return;
    }
}
