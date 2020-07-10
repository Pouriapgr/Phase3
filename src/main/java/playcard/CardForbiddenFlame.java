package playcard;

import module.Spell;
import playlogic.PlayHandler;
import playlogic.PlayerInfo;

public class CardForbiddenFlame extends SpellCard {
    public CardForbiddenFlame(Spell spell, PlayerInfo playerInfo, PlayHandler playHandler, String id) {
        super(spell, playerInfo, playHandler, id);
    }


    @Override
    public void doOperation() {
        // MinionCard minionCard = playHandler.getEnemyMinion();
        // playHandler.dealAllMana(minionCard, playerInfo);
    }
}
