package playhero;

import module.Hero;
import playlogic.PlayerInfo;

public class Rogue extends PlayHero {
    public Rogue(Hero hero, PlayerInfo playerInfo) {
        super(hero, playerInfo);
        setSkillCost(3);
    }

    @Override
    protected void doSkill() {
//        if(upgraded())
//            .stealEnemyHand();
//        .stealEnemyDeck()
    }

    @Override
    protected void doSpecial() {
//        if(cardMismatch)
//            cost-2
    }
}
