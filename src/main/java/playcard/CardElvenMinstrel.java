package playcard;

import module.Minion;
import playlogic.PlayHandler;
import playlogic.PlayerInfo;

public class CardElvenMinstrel extends MinionCard {
    public CardElvenMinstrel(Minion minion, PlayerInfo playerInfo, PlayHandler playHandler, String id) {
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
        //        for(int i = 0; i <= 2; i++) {
//            PlayCard playCard = PlayHandler.draw(player);
//            playHandler.addCard(player, playCard);
//        }
    }

    @Override
    public void doBattlecry() {
        return;
    }
}
