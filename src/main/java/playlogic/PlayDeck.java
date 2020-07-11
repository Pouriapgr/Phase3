package playlogic;

import module.Card;
import module.Deck;
import playcard.PlayCard;
import playhero.PlayHero;

import java.util.ArrayList;

public class PlayDeck {
    private PlayHero hero;
    private ArrayList<PlayCard> cards = new ArrayList<>();


    public PlayDeck(Deck deck, PlayerInfo playerInfo) {
        hero = deck.getDeckHero().playCopy(playerInfo);
        for (Card card : deck.getDeckCards()) {
            cards.add(card.playCopy(playerInfo));
        }
    }

    public PlayHero getHero() {
        return hero;
    }

    public ArrayList<PlayCard> getCards() {
        return cards;
    }
}
