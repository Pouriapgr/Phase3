package playhero;

import module.Hero;
import playlogic.PlayHandler;
import playlogic.PlayerInfo;

public class Paladin extends PlayHero {
    public Paladin(Hero hero, PlayerInfo playerInfo, PlayHandler playHandler) {
        super(hero, playerInfo, playHandler);
    }

    @Override
    protected void doSkill() {
        //summonSheep(2)
    }

    @Override
    protected void doSpecial() {
        //playHandler.giveRandomMinion(+1,+1);
    }
}
