package playcard;

import module.Card;
import playlogic.PlayerInfo;

public abstract class MinionCard extends PlayCard {
    protected final int initialHp;
    protected final int initialAttack;
    protected int hp;
    protected int attack;

    protected boolean stealth = false;
    protected boolean taunt = false;
    protected boolean beTargetedByCardAndPower = true;
    protected boolean charge = false;
    protected boolean windfury = false;
    protected boolean divineShield = false;
    protected boolean lifesteal = false;
    protected boolean poisonous = false;
    protected boolean rush = false;


    public MinionCard(Card card, PlayerInfo playerInfo, String id) {
        super(card, playerInfo, id);

        initialHp = card.getCardHp();
        hp = initialHp;
        initialAttack = card.getCardAttack();
        attack = initialAttack;
    }

    public abstract void doDeathrattle();

    public abstract void endTurn();

    public abstract void die();

    public abstract void doWhenSummon();

    public abstract void doBattlecry();

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

    public boolean isBeTargetedByCardAndPower() {
        return beTargetedByCardAndPower;
    }

    public void setBeTargetedByCardAndPower(boolean beTargetedByCardAndPower) {
        this.beTargetedByCardAndPower = beTargetedByCardAndPower;
    }

    public boolean isCharge() {
        return charge;
    }

    public void setCharge(boolean charge) {
        this.charge = charge;
    }

    public boolean isWindfury() {
        return windfury;
    }

    public void setWindfury(boolean windfury) {
        this.windfury = windfury;
    }

    public boolean isDivineShield() {
        return divineShield;
    }

    public void setDivineShield(boolean divineShield) {
        this.divineShield = divineShield;
    }

    public boolean isLifesteal() {
        return lifesteal;
    }

    public void setLifesteal(boolean lifesteal) {
        this.lifesteal = lifesteal;
    }

    public boolean isPoisonous() {
        return poisonous;
    }

    public void setPoisonous(boolean poisonous) {
        this.poisonous = poisonous;
    }

    public boolean isRush() {
        return rush;
    }

    public void setRush(boolean rush) {
        this.rush = rush;
    }
}
