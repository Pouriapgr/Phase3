package userInterface;

import constants.GraphicConstants;
import module.Deck;
import playlogic.PlayerInfo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ChooseDeckPage extends StatePanel {
    private ArrayList<Deck> decks;
    private DeckButton showDeck;
    private PlayerInfo playerInfo;

    public ChooseDeckPage(PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
        init();
    }

    private void init() {
        decks = player.getPlayerAllDecks();
        showDeck = null;

        removeAllThings();
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
            if (checkSelectNewDeck())
                return true;
        }
    }

    private boolean checkSelectNewDeck() {
        if (showDeck != null) {
            if (player.canPlayDeck(showDeck.getDeck()).equals("YES")) {
                playerInfo.setDeck(showDeck.getDeck());
                uiController.changeState(this, new ChoosePassivesPage(playerInfo));
                return true;
            } else {
                String pre = showDeck.getText();
                showError(pre, player.canPlayDeck(showDeck.getDeck()), showDeck);
                uiController.changeState(this, new DeckPage(showDeck.getDeck()));
            }
            showDeck = null;
            return false;
        }
        return false;
    }

    @Override
    public void updateState() {
        init();
    }
}