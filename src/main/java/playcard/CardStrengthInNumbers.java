package playcard;

import module.Minion;
import playlogic.PlayHandler;
import playlogic.PlayerInfo;

public class CardStrengthInNumbers extends QuestCard {
    public CardStrengthInNumbers(Minion minion, PlayerInfo playerInfo, PlayHandler playHandler, String id) {
        super(minion, playerInfo, playHandler, id);
        //setManaUsedFirst(playerInfo.getManaUsed);
    }

    @Override
    public void checkProgress() {
//        if(playerInfo.getManaUsed - getManaUsedFirst() >= 10) {
//            playHandler.summonRandomMinion(playerInfo);
//        }
    }
}
