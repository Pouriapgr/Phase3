package playcard;

import module.Card;
import playlogic.PlayerInfo;

public class CardOrbOfTheUntold extends SpellCard {
    public CardOrbOfTheUntold(Card card, PlayerInfo playerInfo, String id) {
        super(card, playerInfo, id);
    }


    @Override
    public void doOperation() {
        //.dealRandomDamage(playerInfo, 20);
    }
}
