package playcard;

import module.Card;
import playlogic.PlayerInfo;

public class CardBlessedChampion extends SpellCard {
    public CardBlessedChampion(Card card, PlayerInfo playerInfo, String id) {
        super(card, playerInfo, id);
    }


    @Override
    public void doOperation() {
        //    CardCard cardCard = .getFriendlyCard(player);
        //    cardCard.setAttack(cardCard.getAttack() * 2);
    }
}
