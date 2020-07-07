package userInterface;

import constants.GraphicConstants;
import game.TimeAssistance;
import module.Deck;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ChooseDeckPage extends StatePanel {
    private ArrayList<Deck> decks;
    private DeckButton showDeck;

    public ChooseDeckPage() {
        init();
    }

    private void init() {
        decks = player.getPlayerAllDecks();
        showDeck = null;

        removeAll();
        setDecks();
        setPanel();
    }

    private void setDecks() {
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 2; i++) {
                if (2 * j + i >= decks.size())
                    return;
                DeckButton deckButton = new DeckButton();
                deckButton.setNewDeck(decks.get(2 * j + i));
                deckButton.setBounds(GraphicConstants.PLAY_DECK_FIRST_X + i *
                                GraphicConstants.PLAY_DECK_X_SEPARATOR,
                        GraphicConstants.PLAY_DECK_FIRST_Y + j * GraphicConstants.PLAY_DECK_Y_SEPARATOR,
                        GraphicConstants.PLAY_DECK_WIDTH, GraphicConstants.PLAY_DECK_HEIGHT);

                deckButton.addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent event) {
                        showDeck = deckButton;
                    }
                });
                add(deckButton);
            }
        }
    }

    @Override
    public boolean runState() {
        uiController.setContentPane(this);

        while (true) {
            uiController.validate();
            if (newAction("Back")) {
                return true;
            }
            checkSelectNewDeck();
        }
    }

    private void checkSelectNewDeck() {
        if (showDeck != null) {
            if (!player.canPlayDeck(showDeck.getDeck()).equals("YES")) {
                String pre = showDeck.getText();
                showDeck.setText(player.canPlayDeck(showDeck.getDeck()));
                showDeck.setForeground(Color.RED);
                TimeAssistance.waitFor(2000L);
                showDeck.setText(pre);
                showDeck.setForeground(Color.black);

                uiController.changeState(this, new DeckPage(showDeck.getDeck()));
            } else {
                uiController.changeState(this, new ChoosePassivesPage());
            }
            showDeck = null;
        }
    }

    @Override
    public void updateState() {
        init();
    }
}