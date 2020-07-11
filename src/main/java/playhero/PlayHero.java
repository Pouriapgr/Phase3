package playhero;

import module.Hero;
import playlogic.PlayHandler;
import playlogic.PlayerInfo;

public abstract class PlayHero {
    protected final PlayerInfo playerInfo;
    protected final PlayHandler playHandler = PlayHandler.getInstance();

    protected final String name;

    protected final int initialHp = 30;
    protected int hp = 30;

    protected int skillCost = 2;

    protected boolean useSkill = true;

    public PlayHero(Hero hero, PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
        name = hero.getHeroName();
    }

    protected abstract void doSkill();

    protected abstract void doSpecial();

    public String getName() {
        return name;
    }

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

    public boolean isUseSkill() {
        return useSkill;
    }

    public void setUseSkill(boolean useSkill) {
        this.useSkill = useSkill;
    }
}
