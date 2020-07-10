package playcard;

import module.Minion;
import playlogic.PlayHandler;
import playlogic.PlayerInfo;

public class CardOctosari extends MinionCard {
    public CardOctosari(Minion minion, PlayerInfo playerInfo, PlayHandler playHandler, String id) {
        super(minion, playerInfo, playHandler, id);
    }

    @Override
    public void doDeathrattle() {
//        for (int i = 0; i <= 7; i++) {
//            PlayCard playCard = playHandler.draw(playerInfo);
//            playHandler.addCard(playCard);
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
