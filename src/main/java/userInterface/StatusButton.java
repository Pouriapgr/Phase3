package userInterface;

import module.Deck;
import file.FileAssistance;
import Constants.GraphicConstants;
import module.Player;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class StatusButton extends JComponent {
    private Player player;

    private int x,y;
    private Deck deck;
    private JButton deckName;
    private JButton heroButton;

    private Boolean showDeck = false;

    public StatusButton(int x, int y, Deck deck, Player player) {
        this.player = player;
        this.deck = deck;

        setHeroButton(x, y);
        setDeckName(x, y);
    }

    private void setHeroButton(int x,int y) {
        heroButton = new JButton();
        heroButton.setBounds(x, y, GraphicConstants.STATUS_BUTTON_WEIDTH, GraphicConstants.STATUS_BUTTON_HEIGHT);
        heroButton.setBorder(null);
        heroButton.setContentAreaFilled(false);
        heroButton.setIcon(new ImageIcon(FileAssistance.getScaledImage("heroes",
                deck.getDeckHero().getHeroName() + ".png", GraphicConstants.STATUS_BUTTON_WEIDTH,
                GraphicConstants.STATUS_BUTTON_HEIGHT)));
    }
    private void setDeckName(int x,int y) {
        x += GraphicConstants.STATUS_NAME_BUTTON_PLUS_X;
        y += GraphicConstants.STATUS_NAME_BUTTON_PLUS_Y;

        deckName = new JButton(deck.getDeckName());
        deckName.setForeground(Color.darkGray);
        deckName.setBounds(x, y, GraphicConstants.STATUS_NAME_BUTTON_WEIDTH, GraphicConstants.STATUS_NAME_BUTTON_HEIGHT);
        deckName.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event){
                player.writeLog("Click", "ShowDeckButton");
                setShowDeck(true);
            }
        });
    }

    public Deck getDeck() { return deck; }
    public JButton getHeroButton() { return heroButton; }
    public JButton getDeckName() { return deckName; }
    public boolean getShowDeck() { return showDeck; }

    public void setShowDeck(Boolean showDeck) { this.showDeck = showDeck; }
}