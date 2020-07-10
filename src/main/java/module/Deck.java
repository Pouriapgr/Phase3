package module;

import java.util.ArrayList;

public class Deck {
    private String deckName;
    private Hero deckHero;
    private ArrayList<Card> deckCards;
    private Card mostUsedCard;
    private int deckNoOfLosses;
    private int deckNoOfWins;
    private int deckNoOfGames;

    public Deck(Hero deckHero, ArrayList<Card> cards, String deckName) {
        this.deckName = deckName;
        this.deckHero = deckHero;
        deckCards = cards;
        mostUsedCard = null;
        deckNoOfGames = 0;
        deckNoOfLosses = 0;
        deckNoOfWins = 0;
    }

    public static Deck copy(Deck deck) {
        ArrayList<Card> cards = new ArrayList<>();
        for (Card card : deck.getDeckCards()) {
            cards.add(Card.copy(card));
        }
        Deck newDeck = new Deck(deck.getDeckHero(), cards, deck.getDeckName());
        return newDeck;
    }

    public double getAverageCost() {
        if (deckCards.size() == 0)
            return 0;
        double sum = 0;
        double num = deckCards.size();
        for (Card card : deckCards)
            sum += card.getCardCost();

        double value = sum / num;
        return Double.parseDouble(String.format("%.1f", sum / num));
    }

    public void addCard(Card card) {
        deckCards.add(card);
    }

    public Hero getDeckHero() {
        return deckHero;
    }

    public void setDeckHero(Hero deckHero) {
        this.deckHero = deckHero;
    }

    public ArrayList<Card> getDeckCards() {
        return deckCards;
    }

    public void setDeckCards(ArrayList<Card> deckCards) {
        this.deckCards = deckCards;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public Card getMostUsedCard() {
        return mostUsedCard;
    }

    public void setMostUsedCard(Card mostUsedCard) {
        this.mostUsedCard = mostUsedCard;
    }

    public int getDeckNoOfLosses() {
        return deckNoOfLosses;
    }

    public void setDeckNoOfLosses(int deckNoOfLosses) {
        this.deckNoOfLosses = deckNoOfLosses;
    }

    public int getDeckNoOfWins() {
        return deckNoOfWins;
    }

    public void setDeckNoOfWins(int deckNoOfWins) {
        this.deckNoOfWins = deckNoOfWins;
    }

    public int getDeckNoOfGames() {
        return deckNoOfGames;
    }

    public void setDeckNoOfGames(int deckNoOfGames) {
        this.deckNoOfGames = deckNoOfGames;
    }
}