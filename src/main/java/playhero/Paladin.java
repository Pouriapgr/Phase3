package playhero;

import module.Hero;
import playlogic.PlayerInfo;

public class Paladin extends PlayHero {
    public Paladin(Hero hero, PlayerInfo playerInfo) {
        super(hero, playerInfo);
    }

    @Override
    protected void doSkill() {
        //summonSheep(2)
    }

    @Override
    protected void doSpecial() {
        //.giveRandomMinion(+1,+1);
    }
}
