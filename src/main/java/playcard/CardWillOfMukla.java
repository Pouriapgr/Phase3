package playcard;

import module.Spell;
import playlogic.PlayHandler;
import playlogic.PlayerInfo;

public class CardWillOfMukla extends SpellCard {
    public CardWillOfMukla(Spell spell, PlayerInfo playerInfo, PlayHandler playHandler, String id) {
        super(spell, playerInfo, playHandler, id);
    }


    @Override
    public void doOperation() {
        //playHandler.restoreHp(8, playerInfo);
    }
}
