package playcard;

import module.Minion;
import playlogic.PlayHandler;
import playlogic.PlayerInfo;

public class CardArenaTreasureChest extends MinionCard {
    public CardArenaTreasureChest(Minion minion, PlayerInfo playerInfo, PlayHandler playHandler, String id) {
        super(minion, playerInfo, playHandler, id);
    }

    @Override
    public void doDeathrattle() {
//        for (int i = 0; i <= 1; i++) {
//            PlayCard playCard = playHandler.drawCard(player);
//            playHandler.addCard(player, playCard);
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

    }
}
