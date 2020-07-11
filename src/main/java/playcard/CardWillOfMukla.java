package playcard;

import module.Card;
import playlogic.PlayerInfo;

public class CardWillOfMukla extends SpellCard {
    public CardWillOfMukla(Card card, PlayerInfo playerInfo, String id) {
        super(card, playerInfo, id);
    }


    @Override
    public void doOperation() {
        //.restoreHp(8, playerInfo);
    }
}
