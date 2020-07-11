package playhero;

import module.Hero;
import playlogic.PlayHandler;
import playlogic.PlayerInfo;

public class Hunter extends PlayHero {
    public Hunter(Hero hero, PlayerInfo playerInfo, PlayHandler playHandler) {
        super(hero, playerInfo, playHandler);
        setSkillCost(0);
    }

    @Override
    protected void doSkill() {
        return;
    }

    @Override
    protected void doSpecial() {
        // playHandler.setAllRush();
    }
}
