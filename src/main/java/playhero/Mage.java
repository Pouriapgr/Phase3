package playhero;

import module.Hero;
import playlogic.PlayHandler;
import playlogic.PlayerInfo;

public class Mage extends PlayHero {
    public Mage(Hero hero, PlayerInfo playerInfo, PlayHandler playHandler) {
        super(hero, playerInfo, playHandler);
    }

    @Override
    protected void doSkill() {
        // playHandler.damageMinion(1);
    }

    @Override
    protected void doSpecial() {
        //playerInfo.setSpellCost(-2);
    }
}
