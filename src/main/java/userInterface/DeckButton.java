package userInterface;

import module.Deck;

import javax.swing.*;

public class DeckButton extends JButton {
    private Deck deck = null;
    private String text = "";

    public DeckButton() {
        setVisible(false);
    }

    public void setEmpty() {
        deck = null;
        text = "";
        setVisible(false);
    }

    public void setNewDeck(Deck deck) {
        this.deck = deck;
        text = deck.getDeckName() + " ( " + deck.getDeckHero().getHeroName() + " )";
        setText(text);
        setVisible(true);
    }

    public Deck getDeck() {
        return deck;
    }
}