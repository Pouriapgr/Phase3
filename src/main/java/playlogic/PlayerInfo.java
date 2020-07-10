package playlogic;

import module.Deck;
import module.Passive;

public class PlayerInfo {
    private Deck initDeck;
    private Deck deck;
    private Passive initPassive;
    private Passive passive;

    private int addToDrawAttack;
    private int addToDrawHp;


    public void setDeck(Deck deck) {
        initDeck = deck;
        this.deck = Deck.copy(initDeck);
    }

    public void setPassive(Passive passive) {
        initPassive = passive;
        this.passive = Passive.copy(initPassive);
    }

    public Deck getDeck() {
        return deck;
    }

    public Passive getPassive() {
        return passive;
    }

    public int getAddToDrawAttack() {
        return addToDrawAttack;
    }

    public void setAddToDrawAttack(int addToDrawAttack) {
        this.addToDrawAttack = addToDrawAttack;
    }

    public int getAddToDrawHp() {
        return addToDrawHp;
    }

    public void setAddToDrawHp(int addToDrawHp) {
        this.addToDrawHp = addToDrawHp;
    }
}
