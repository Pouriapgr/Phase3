package playcard;

import module.Minion;
import playlogic.PlayHandler;
import playlogic.PlayerInfo;

public class CardBloodImp extends MinionCard {
    public CardBloodImp(Minion minion, PlayerInfo playerInfo, PlayHandler playHandler, String id) {
        super(minion, playerInfo, playHandler, id);
    }

    @Override
    public void doDeathrattle() {
        return;
    }

    @Override
    public void endTurn() {
        //   MinionCard minionCard = playHandler.getRandomFriendly(player);
        //   minionCard.setHp(minionCard.getHp() + 1);
    }

    @Override
    public void die() {
        return;
    }

    @Override
    public void doWhenSummon() {
        setStealth(true);
    }
}
