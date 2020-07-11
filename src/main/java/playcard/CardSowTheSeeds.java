package playcard;

import module.Card;
import playlogic.PlayerInfo;

public class CardSowTheSeeds extends SpellCard {
    public CardSowTheSeeds(Card card, PlayerInfo playerInfo, String id) {
        super(card, playerInfo, id);
    }


    @Override
    public void doOperation() {
        //.giveAllDeck(+3, +3, playerInfo);
    }
}
