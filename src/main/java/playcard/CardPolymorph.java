package playcard;

import module.Card;
import playlogic.PlayerInfo;

public class CardPolymorph extends SpellCard {
    public CardPolymorph(Card card, PlayerInfo playerInfo, String id) {
        super(card, playerInfo, id);
    }


    @Override
    public void doOperation() {
        //  .transformToSheep();
    }
}
