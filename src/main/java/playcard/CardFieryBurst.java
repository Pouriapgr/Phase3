package playcard;

import module.Spell;
import playlogic.PlayHandler;
import playlogic.PlayerInfo;

public class CardFieryBurst extends SpellCard {
    public CardFieryBurst(Spell spell, PlayerInfo playerInfo, PlayHandler playHandler, String id) {
        super(spell, playerInfo, playHandler, id);
    }


    @Override
    public void doOperation() {
        //playHandler.damageAllEnemies(this, playerInfo, 2);
    }
}
