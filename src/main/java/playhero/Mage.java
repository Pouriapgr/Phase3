package playhero;

import module.Hero;
import playlogic.PlayerInfo;

public class Mage extends PlayHero {
    public Mage(Hero hero, PlayerInfo playerInfo) {
        super(hero, playerInfo);
    }

    @Override
    protected void doSkill() {
        // .damageMinion(1);
    }

    @Override
    protected void doSpecial() {
        //playerInfo.setSpellCost(-2);
    }
}
