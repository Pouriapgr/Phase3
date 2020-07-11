package playcard;

import module.Card;
import playlogic.PlayerInfo;

public abstract class SpellCard extends PlayCard {
    public SpellCard(Card card, PlayerInfo playerInfo, String id) {
        super(card, playerInfo, id);
    }

    public abstract void doOperation();
}
