package playcard;

import module.Spell;
import playlogic.PlayHandler;
import playlogic.PlayerInfo;

public class CardSwarmOfLocusts extends SpellCard {
    public CardSwarmOfLocusts(Spell spell, PlayerInfo playerInfo, PlayHandler playHandler, String id) {
        super(spell, playerInfo, playHandler, id);
    }


    @Override
    public void doOperation() {
        //playHandler.summonLocusts();
    }
}
