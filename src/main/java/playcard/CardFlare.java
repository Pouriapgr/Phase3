package playcard;

import module.Card;
import playlogic.PlayerInfo;

public class CardFlare extends SpellCard {
    public CardFlare(Card card, PlayerInfo playerInfo, String id) {
        super(card, playerInfo, id);
    }


    @Override
    public void doOperation() {
        //.allLoseStealth(this, playerInfo, 2);
    }
}
