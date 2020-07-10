package playcard;

import module.Spell;
import playlogic.PlayHandler;
import playlogic.PlayerInfo;

public class CardSowTheSeeds extends SpellCard {
    public CardSowTheSeeds(Spell spell, PlayerInfo playerInfo, PlayHandler playHandler, String id) {
        super(spell, playerInfo, playHandler, id);
    }


    @Override
    public void doOperation() {
        //playHandler.giveAllDeck(+3, +3, playerInfo);
    }
}
