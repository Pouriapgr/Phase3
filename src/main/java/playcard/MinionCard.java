package playcard;

import module.Minion;
import playlogic.PlayHandler;
import playlogic.PlayerInfo;

public abstract class MinionCard extends PlayCard {
    protected final int initialHp;
    protected final int initialAttack;
    protected int hp;
    protected int attack;

    protected boolean stealth = false;
    protected boolean taunt = false;
    protected boolean beTargetedBySpellAndPower = true;

    public MinionCard(Minion minion, PlayerInfo playerInfo, PlayHandler playHandler, String id) {
        super(minion, playerInfo, playHandler, id);

        initialHp = minion.getCardHp();
        hp = initialHp;
        initialAttack = minion.getCardAttack();
        attack = initialAttack;
    }

    public abstract void doDeathrattle();

    public abstract void endTurn();

    public abstract void die();

    public abstract void doWhenSummon();

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getInitialAttack() {
        return initialAttack;
    }

    public int getInitialHp() {
        return initialHp;
    }

    public boolean isStealth() {
        return stealth;
    }

    public void setStealth(boolean stealth) {
        this.stealth = stealth;
    }

    public boolean isTaunt() {
        return taunt;
    }

    public void setTaunt(boolean taunt) {
        this.taunt = taunt;
    }

    public boolean isBeTargetedBySpellAndPower() {
        return beTargetedBySpellAndPower;
    }

    public void setBeTargetedBySpellAndPower(boolean beTargetedBySpellAndPower) {
        this.beTargetedBySpellAndPower = beTargetedBySpellAndPower;
    }
}
