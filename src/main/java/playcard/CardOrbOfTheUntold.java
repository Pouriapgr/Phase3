package playcard;

import module.Spell;
import playlogic.PlayHandler;
import playlogic.PlayerInfo;

public class CardOrbOfTheUntold extends SpellCard {
    public CardOrbOfTheUntold(Spell spell, PlayerInfo playerInfo, PlayHandler playHandler, String id) {
        super(spell, playerInfo, playHandler, id);
    }


    @Override
    public void doOperation() {
        //playHandler.dealRandomDamage(playerInfo, 20);
    }
}
