package userInterface;

import constants.GraphicConstants;
import module.Deck;

import java.util.ArrayList;

public class StatusPage extends StatePanel {
    private ArrayList<Deck> decks;
    private ArrayList<StatusButton> statusButtons;

    public StatusPage() {
        init();
    }

    private void init() {
        decks = player.getPlayerAllDecks();

        removeAllThings();
        setDecks();
        setPanel();
    }

    private void setDecks() {
        statusButtons = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (i >= player.getPlayerAllDecks().size())
                break;
            Deck deck = player.getPlayerAllDecks().get(i);
            StatusButton statusButton = new StatusButton(GraphicConstants.STATUS_BUTTON_FIRST_X +
                    (i % 5) * GraphicConstants.STATUS_BUTTON_X_SEPARATOR, GraphicConstants.STATUS_BUTTON_FIRST_Y +
                    (i / 5) * GraphicConstants.STATUS_BUTTON_Y_SEPARATOR, deck);
            add(statusButton.getDeckName());
            add(statusButton.getHeroButton());
            statusButtons.add(statusButton);
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
            Deck showDeck = checkNewStatusReq();
            if (showDeck != null)
                uiController.changeState(this, new DeckPage(showDeck));

        }
    }

    private Deck checkNewStatusReq() {
        ArrayList<StatusButton> statusButtons = getStatusButtons();
        for (StatusButton statusButton : statusButtons) {
            if (statusButton.getShowDeck()) {
                statusButton.setShowDeck(false);
                return statusButton.getDeck();
            }
        }
        return null;
    }

    @Override
    public void updateState() {
        init();
    }

    public ArrayList<StatusButton> getStatusButtons() {
        return statusButtons;
    }
}