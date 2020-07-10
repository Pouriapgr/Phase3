package playcard;

import module.Weapon;
import playlogic.PlayHandler;
import playlogic.PlayerInfo;

public abstract class WeaponCard extends PlayCard {
    protected final int initialDurability;
    protected final int initialAttack;
    protected int durability;
    protected int attack;

    public WeaponCard(Weapon weapon, PlayerInfo playerInfo, PlayHandler playHandler, String id) {
        super(weapon, playerInfo, playHandler, id);

        initialDurability = weapon.getCardHp();
        durability = initialDurability;

        initialAttack = weapon.getCardAttack();
        attack = initialAttack;
    }

    public int getInitialAttack() {
        return initialAttack;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public int getInitialDurability() {
        return initialDurability;
    }
}
