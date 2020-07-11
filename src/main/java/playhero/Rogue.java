package playhero;

import module.Hero;
import playlogic.PlayHandler;
import playlogic.PlayerInfo;

public class Rogue extends PlayHero {
    public Rogue(Hero hero, PlayerInfo playerInfo, PlayHandler playHandler) {
        super(hero, playerInfo, playHandler);
        setSkillCost(3);
    }

    @Override
    protected void doSkill() {
//        if(upgraded())
//            playHandler.stealEnemyHand();
//        playHandler.stealEnemyDeck()
    }

    @Override
    protected void doSpecial() {
//        if(cardMismatch)
//            cost-2
    }
}
