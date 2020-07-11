package playcard;

import module.Card;
import playlogic.PlayerInfo;

public class CardStoneFoxStatue extends SpellCard {
    public CardStoneFoxStatue(Card card, PlayerInfo playerInfo, String id) {
        super(card, playerInfo, id);
    }


    @Override
    public void doOperation() {
        // .addCopyToHand(2, 0, playerInfo);
    }
}
