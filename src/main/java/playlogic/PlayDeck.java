package playlogic;

import module.Card;
import module.Deck;
import playcard.PlayCard;
import playhero.PlayHero;

import java.util.ArrayList;

public class PlayDeck {
    private PlayHero hero;
    private ArrayList<PlayCard> cards;


    public PlayDeck(Deck deck, PlayerInfo playerInfo, PlayHandler playHandler) {
        hero = deck.getDeckHero().playCopy(playerInfo, playHandler);
        for (Card card : deck.getDeckCards()) {
            cards.add(card.playCopy(playerInfo, playHandler));
        }
    }
}
