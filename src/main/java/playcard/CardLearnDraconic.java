package playcard;

import module.Card;
import playlogic.PlayerInfo;

public class CardLearnDraconic extends QuestCard {
    public CardLearnDraconic(Card card, PlayerInfo playerInfo, String id) {
        super(card, playerInfo, id);
        //setManaUsedFirst(playerInfo.getManaUsed);
    }

    @Override
    public void checkProgress() {
//        if(playerInfo.getManaUsed - getManaUsedFirst() >= 8) {
//            .summon("EvassiveDrakonid");
//        }
    }
}
