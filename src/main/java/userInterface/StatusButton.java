package userInterface;

import constants.GraphicConstants;
import file.FileAssistance;
import module.Deck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StatusButton extends JComponent {
    private int x, y;
    private Deck deck;
    private JButton deckName;
    private JButton heroButton;

    private Boolean showDeck = false;

    public StatusButton(int x, int y, Deck deck) {
        this.x = x;
        this.y = y;
        this.deck = deck;

        setHeroButton(x, y);
        setDeckName(x, y);
    }

    private void setHeroButton(int x, int y) {
        heroButton = new JButton();
        heroButton.setBounds(x, y, GraphicConstants.STATUS_BUTTON_WEIDTH, GraphicConstants.STATUS_BUTTON_HEIGHT);
        heroButton.setBorder(null);
        heroButton.setContentAreaFilled(false);
        heroButton.setIcon(new ImageIcon(FileAssistance.getScaledImage("heroes",
                deck.getDeckHero().getHeroName() + ".png", GraphicConstants.STATUS_BUTTON_WEIDTH,
                GraphicConstants.STATUS_BUTTON_HEIGHT)));
    }

    private void setDeckName(int x, int y) {
        x += GraphicConstants.STATUS_NAME_BUTTON_PLUS_X;
        y += GraphicConstants.STATUS_NAME_BUTTON_PLUS_Y;

        deckName = new JButton(deck.getDeckName());
        deckName.setForeground(Color.darkGray);
        deckName.setBounds(x, y, GraphicConstants.STATUS_NAME_BUTTON_WEIDTH, GraphicConstants.STATUS_NAME_BUTTON_HEIGHT);
        deckName.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                setShowDeck(true);
            }
        });
    }

    public Deck getDeck() {
        return deck;
    }

    public JButton getHeroButton() {
        return heroButton;
    }

    public JButton getDeckName() {
        return deckName;
    }

    public boolean getShowDeck() {
        return showDeck;
    }

    public void setShowDeck(Boolean showDeck) {
        this.showDeck = showDeck;
    }
}