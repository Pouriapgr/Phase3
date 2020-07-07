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

    public Deck(Hero deckHero, ArrayList<Card> cards, String deckName){
        this.deckName = deckName;
        this.deckHero = deckHero;
        deckCards = cards;
        mostUsedCard = null;
        deckNoOfGames = 0;
        deckNoOfLosses = 0;
        deckNoOfWins = 0;
    }

    public double getAverageCost(){
        if(deckCards.size() == 0)
            return 0;
        double sum = 0;
        double num = deckCards.size();
        for(Card card: deckCards)
              sum += card.getCardCost();

        double value =  sum/num;
        return Double.parseDouble(String.format("%.1f", sum/num));
    }
    public void addCard(Card card){
        deckCards.add(card);
    }

    public Hero getDeckHero() { return deckHero; }
    public ArrayList<Card> getDeckCards(){ return deckCards;}
    public String getDeckName() { return deckName; }
    public Card getMostUsedCard() { return mostUsedCard; }
    public int getDeckNoOfLosses() { return deckNoOfLosses; }
    public int getDeckNoOfWins() { return deckNoOfWins; }
    public int getDeckNoOfGames() { return deckNoOfGames; }

    public void setDeckName(String deckName) { this.deckName = deckName; }
    public void setDeckHero(Hero deckHero) { this.deckHero = deckHero; }
    public void setDeckCards(ArrayList<Card> deckCards) { this.deckCards = deckCards; }
    public void setMostUsedCard(Card mostUsedCard) { this.mostUsedCard = mostUsedCard; }
    public void setDeckNoOfLosses(int deckNoOfLosses) { this.deckNoOfLosses = deckNoOfLosses; }
    public void setDeckNoOfWins(int deckNoOfWins) { this.deckNoOfWins = deckNoOfWins; }
    public void setDeckNoOfGames(int deckNoOfGames) { this.deckNoOfGames = deckNoOfGames; }
}