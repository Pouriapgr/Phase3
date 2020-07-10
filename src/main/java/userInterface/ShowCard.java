package userInterface;

import constants.GraphicConstants;
import module.Card;

public class ShowCard extends StatePanel {
    private Card card;

    public ShowCard(Card card) {
        init(card);
    }

    public void init(Card card) {
        this.card = card;
        removeAllThings();
        setCard();
        setPanel();
    }

    private void setCard() {
        addMyButton("", card.getCardName(), GraphicConstants.SHOW_CARD_X, GraphicConstants.SHOW_CARD_Y,
                GraphicConstants.SHOW_CARD_WIDTH, GraphicConstants.SHOW_CARD_HEIGHT,
                "cards", card.getCardName() + ".png");
    }


    @Override
    public boolean runState() {
        uiController.setContentPane(this);

        while (true) {
            uiController.validate();
            if (newAction("Back")) {
                return true;
            }
        }
    }

    @Override
    public void updateState() {
        init(card);
    }
}