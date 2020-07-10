package playcard;

import module.Spell;
import playlogic.PlayHandler;
import playlogic.PlayerInfo;

public class CardPharaohsBlessing extends SpellCard {
    public CardPharaohsBlessing(Spell spell, PlayerInfo playerInfo, PlayHandler playHandler, String id) {
        super(spell, playerInfo, playHandler, id);
    }


    @Override
    public void doOperation() {
//        MinionCard minionCard = playHandler.getMinion();
//        minionCard.setAttack(minionCard.getAttack() + 4);
//        minionCard.setHp(minionCard.getHp() + 4);
//        minionCard.setDivineShield(true);
//        minionCard.setTaunt(true);
    }
}
