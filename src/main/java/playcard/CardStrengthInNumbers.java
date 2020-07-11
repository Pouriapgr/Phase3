package playcard;

import module.Card;
import playlogic.PlayerInfo;

public class CardStrengthInNumbers extends QuestCard {
    public CardStrengthInNumbers(Card card, PlayerInfo playerInfo, String id) {
        super(card, playerInfo, id);
        //setManaUsedFirst(playerInfo.getManaUsed);
    }

    @Override
    public void checkProgress() {
//        if(playerInfo.getManaUsed - getManaUsedFirst() >= 10) {
//            .summonRandomCard(playerInfo);
//        }
    }
}
