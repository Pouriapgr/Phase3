package playcard;

import module.Card;
import playlogic.PlayerInfo;

public abstract class WeaponCard extends PlayCard {
    protected final int initialDurability;
    protected final int initialAttack;
    protected int durability;
    protected int attack;

    public WeaponCard(Card card, PlayerInfo playerInfo, String id) {
        super(card, playerInfo, id);

        initialDurability = card.getCardHp();
        durability = initialDurability;

        initialAttack = card.getCardAttack();
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
