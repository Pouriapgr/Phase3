package playlogic;

import module.Deck;
import module.Passive;
import playcard.PlayCard;
import playcard.WeaponCard;

import java.util.ArrayList;
import java.util.Collections;

public class PlayerInfo {
    private int id;
    private Deck initDeck;
    private Passive initPassive;
    private PlayHandler playHandler;

    private ArrayList<PlayCard> start;
    private ArrayList<PlayCard> hand = new ArrayList<>();
    private PlayDeck deck;
    private Passive passive;

    private WeaponCard weaponCard;

    private int turnMana;
    private int mana;

    private int addToMana;
    private int addToDrawAttack;
    private int addToDrawHp;

    public PlayerInfo(int id) {
        playHandler = PlayHandler.getInstance();
        this.id = id;
    }

    public void addToHand(PlayCard playCard) {
        hand.add(playCard);
    }

    public void addToDeck(PlayCard playCard) {
        deck.getCards().add(playCard);
    }

    public void removeFromHand(PlayCard playCard) {
        for (int i = 0; i <= hand.size(); i++) {
            if (hand.get(i).equals(playCard)) {
                hand.remove(i);
                return;
            }
        }
    }

    public void removeFromDeck(PlayCard playCard) {
        for (int i = 0; i < deck.getCards().size(); i++) {
            if (deck.getCards().get(i).equals(playCard)) {
                deck.getCards().remove(i);
                return;
            }
        }
    }

    public int getFinalMana(PlayCard playCard) {
        return playCard.getManaCost() + addToMana;
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

    public void setPassive(Passive passive) {
        initPassive = passive;
        this.passive = Passive.copy(initPassive);
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

    public ArrayList<PlayCard> getHand() {
        return hand;
    }

    public int getId() {
        return id;
    }

    public ArrayList<PlayCard> getStart() {
        return start;
    }

    public void setStart(ArrayList<PlayCard> start) {
        this.start = start;
    }

    public int getAddToMana() {
        return addToMana;
    }

    public void setAddToMana(int addToMana) {
        this.addToMana = addToMana;
    }

    public WeaponCard getWeaponCard() {
        return weaponCard;
    }

    public void setWeaponCard(WeaponCard weaponCard) {
        this.weaponCard = weaponCard;
    }
}
