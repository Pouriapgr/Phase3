package playlogic;

import module.Deck;
import module.Passive;
import playcard.PlayCard;

import java.util.ArrayList;
import java.util.Collections;

public class PlayerInfo {
    private Deck initDeck;
    private Passive initPassive;
    private PlayHandler playHandler;

    private ArrayList<PlayCard> hand = new ArrayList<>();
    private PlayDeck deck;
    private Passive passive;

    private int turnMana;
    private int mana;

    private int addToDrawAttack;
    private int addToDrawHp;

    public PlayerInfo() {
        playHandler = PlayHandler.getInstance();
    }

    public void addToHand(PlayCard playCard) {
        hand.add(playCard);
    }

    public void setPassive(Passive passive) {
        initPassive = passive;
        this.passive = Passive.copy(initPassive);
    }

    public PlayDeck getDeck() {
        return deck;
    }

    public void setDeck(Deck myDeck) {
        initDeck = myDeck;
        deck = new PlayDeck(myDeck, this);
        Collections.shuffle(deck.getCards());
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

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getTurnMana() {
        return turnMana;
    }

    public void setTurnMana(int turnMana) {
        if (turnMana <= 10)
            this.turnMana = turnMana;
        else
            turnMana = 10;
    }

    public String getManaToString() {
        return getMana() + "/" + getTurnMana();
    }
}
