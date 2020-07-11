package playhero;

import module.Hero;
import playlogic.PlayHandler;
import playlogic.PlayerInfo;

public class Warlock extends PlayHero {
    public Warlock(Hero hero, PlayerInfo playerInfo, PlayHandler playHandler) {
        super(hero, playerInfo, playHandler);
        setHp(35);
    }

    @Override
    protected void doSkill() {
        //   playHandler.drawCard or givaRandomMinion(+1,+1)
    }

    @Override
    protected void doSpecial() {
        return;
    }
}
