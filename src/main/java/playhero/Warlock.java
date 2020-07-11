package playhero;

import module.Hero;
import playlogic.PlayerInfo;

public class Warlock extends PlayHero {
    public Warlock(Hero hero, PlayerInfo playerInfo) {
        super(hero, playerInfo);
        setHp(35);
    }

    @Override
    protected void doSkill() {
        //   .drawCard or givaRandomMinion(+1,+1)
    }

    @Override
    protected void doSpecial() {
        return;
    }
}
