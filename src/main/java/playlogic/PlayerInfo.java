package playlogic;

import card.Passive;
import module.Deck;

public class PlayerInfo {
    private Deck deck;
    private Passive passive;

    public PlayerInfo() {

    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public void setPassive(Passive passive) {
        this.passive = passive;
    }
}
