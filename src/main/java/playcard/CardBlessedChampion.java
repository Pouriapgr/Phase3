package playcard;

import module.Spell;
import playlogic.PlayHandler;
import playlogic.PlayerInfo;

public class CardBlessedChampion extends SpellCard {
    public CardBlessedChampion(Spell spell, PlayerInfo playerInfo, PlayHandler playHandler, String id) {
        super(spell, playerInfo, playHandler, id);
    }


    @Override
    public void doOperation() {
        //    MinionCard minionCard = playHandler.getFriendlyMinion(player);
        //    minionCard.setAttack(minionCard.getAttack() * 2);
    }
}
