package playhero;

import module.Hero;
import playlogic.PlayerInfo;

public class Hunter extends PlayHero {
    public Hunter(Hero hero, PlayerInfo playerInfo) {
        super(hero, playerInfo);
        setSkillCost(0);
    }

    @Override
    protected void doSkill() {
        return;
    }

    @Override
    protected void doSpecial() {
        // .setAllRush();
    }
}
