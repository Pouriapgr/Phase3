package playcard;

import module.Spell;
import playlogic.PlayHandler;
import playlogic.PlayerInfo;

public class CardPolymorph extends SpellCard {
    public CardPolymorph(Spell spell, PlayerInfo playerInfo, PlayHandler playHandler, String id) {
        super(spell, playerInfo, playHandler, id);
    }


    @Override
    public void doOperation() {
        //  playHandler.transformToSheep();
    }
}
