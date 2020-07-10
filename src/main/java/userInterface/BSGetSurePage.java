package userInterface;

import constants.GraphicConstants;
import module.Card;

public class BSGetSurePage extends StatePanel {
    private Card card;
    private String op;

    public BSGetSurePage(Card card, String op) {
        this.card = card;
        this.op = op;
        init(card, op);
    }

    public void init(Card card, String op) {
        removeAllThings();
        setAssure(op);
        setCard(card);
        setPanel();
    }

    private void setAssure(String op) {
        addMyButton(op, "Sure", GraphicConstants.SHOW_CARD_ASSURE_X, GraphicConstants.SHOW_CARD_ASSURE_Y,
                GraphicConstants.SHOW_CARD_ASSURE_WIDTH, GraphicConstants.SHOW_CARD_ASSURE_HEIGHT);
    }

    @Override
    public boolean runState() {
        uiController.setContentPane(this);

        while (true) {
            uiController.validate();
            if (newAction("Back")) {
                return true;
            }
            if (newAction("Sure")) {
                if (op.equals("BUY CARD")) {
                    if (!admin.buyCard(card))
                        showBuyError();
                }
                if (op.equals("SELL CARD"))
                    admin.sellCard(card);
                return true;
            }
        }
    }

    private void showBuyError() {
        MyButton myButton = getMyButton("Sure");
        showError(myButton.getText(), "YOU DON'T HAVE ENOUGH COINS", myButton);
    }


    @Override
    public void updateState() {
        init(card, op);
    }
}