package module;

import Constants.LogicConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameState {
    Hero firstHero;
    ArrayList<Card> firstDeck;
    ArrayList<Card> firstHand;
    ArrayList<Card> firstPlayed;
    Player player;
    int turn = 1;
    int mana = 1;
    int firstMana = 1;

    Weapon weapon = null;

    public GameState(Player player, Deck deck){
        this.player = player;
        firstHero = deck.getDeckHero();
        firstDeck = new ArrayList<>();
        for(Card card: deck.getDeckCards())
            firstDeck.add(card);
        Collections.shuffle(firstDeck,  new Random(System.currentTimeMillis()));
        firstHand = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            firstHand.add(firstDeck.get(0));
            firstDeck.remove(0);
        }
        firstPlayed = new ArrayList<>();
    }

    public String canPlayCard(Card card){
        if(card.getCardCost() > firstMana) {
            player.writeLog("Error", "LowMana");
            return "YOU DON'T HAVE ENOUGH MANA";
        }

        if(card.getCardType().equals("Minion")){
            if(firstPlayed.size() >= LogicConstants.MAX_PLAYED_CARDS) {
                player.writeLog("Error", "MaxCardsPlayed");
                return "MAX MINIONS PLAYED";
            }
            return "YES";
        }
        else if(card.getCardType().equals("Weapon")){
            return "YES";
        }
        else if(card.getCardType().equals("Spell") || card.getCardType().equals("Quest")){
            return "YES";
        }
        return "BAD ://";
    }

    public void playCard(Card card) {
        player.writeLog("PlayCard", card.getCardName());
        removeFromHand(card);
        if(card.getCardType().equals("Minion"))
            addToPlayed(card);
        calculateMana(card);
    }
    private void removeFromHand(Card card) {
        for(int i = 0; i < firstHand.size(); i++) {
            if (firstHand.get(i).getCardName().equals(card.getCardName())) {
                firstHand.remove(i);
                break;
            }
        }
    }
    private void addToPlayed(Card card){
        firstPlayed.add(card);
    }
    private void calculateMana(Card card){
        firstMana -= card.getCardCost();
    }
    public void nextTurn(){
        if(mana < 10)
            mana++;
        firstMana = mana;
        turn++;
        if(firstDeck.size() == 0)
            return;
        if(firstHand.size() >= LogicConstants.MAX_HAND_CARDS) {
            firstDeck.remove(0);
            return;
        }
        firstHand.add(firstDeck.get(0));
        firstDeck.remove(0);
    }

    public Hero getFirstHero() { return firstHero; }
    public ArrayList<Card> getFirstDeck() { return firstDeck; }
    public ArrayList<Card> getFirstHand() { return firstHand; }
    public ArrayList<Card> getFirstPlayed() { return firstPlayed; }
    public int getFirstMana() { return firstMana; }
    public int getMana() { return mana; }
    public int getTurn() { return turn; }
    public String manaToMax(){ return getFirstMana() + "/" + getMana(); }
    public Weapon getWeapon() { return weapon; }
    public Player getPlayer() { return player; }
}