package playhero;

import module.Hero;
import playlogic.PlayHandler;
import playlogic.PlayerInfo;

public abstract class PlayHero {
    protected final PlayerInfo playerInfo;
    protected final PlayHandler playHandler;

    protected final String name;

    protected final int initialHp = 30;
    protected int hp = 30;

    protected int skillCost = 2;

    public PlayHero(Hero hero, PlayerInfo playerInfo, PlayHandler playHandler) {
        this.playerInfo = playerInfo;
        this.playHandler = playHandler;
        name = hero.getHeroName();
    }

    protected abstract void doSkill();

    protected abstract void doSpecial();

    public int getInitialHp() {
        return initialHp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getSkillCost() {
        return skillCost;
    }

    public void setSkillCost(int skillCost) {
        this.skillCost = skillCost;
    }
}
