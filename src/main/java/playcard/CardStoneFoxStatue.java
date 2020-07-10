package playcard;

import module.Spell;
import playlogic.PlayHandler;
import playlogic.PlayerInfo;

public class CardStoneFoxStatue extends SpellCard {
    public CardStoneFoxStatue(Spell spell, PlayerInfo playerInfo, PlayHandler playHandler, String id) {
        super(spell, playerInfo, playHandler, id);
    }


    @Override
    public void doOperation() {
        // playHandler.addCopyToHand(2, 0, playerInfo);
    }
}
