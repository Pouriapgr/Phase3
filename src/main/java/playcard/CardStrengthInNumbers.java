package playcard;

import module.Quest;
import playlogic.PlayHandler;
import playlogic.PlayerInfo;

public class CardStrengthInNumbers extends QuestCard {
    public CardStrengthInNumbers(Quest quest, PlayerInfo playerInfo, PlayHandler playHandler, String id) {
        super(quest, playerInfo, playHandler, id);
        //setManaUsedFirst(playerInfo.getManaUsed);
    }

    @Override
    public void checkProgress() {
//        if(playerInfo.getManaUsed - getManaUsedFirst() >= 10) {
//            playHandler.summonRandomMinion(playerInfo);
//        }
    }
}
