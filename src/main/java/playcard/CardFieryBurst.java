package playcard;

import module.Card;
import playlogic.PlayerInfo;

public class CardFieryBurst extends SpellCard {
    public CardFieryBurst(Card card, PlayerInfo playerInfo, String id) {
        super(card, playerInfo, id);
    }


    @Override
    public void doOperation() {
        //.damageAllEnemies(this, playerInfo, 2);
    }
}
