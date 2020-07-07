package userInterface;

import card.Card;
import constants.GraphicConstants;

import java.awt.*;
import java.util.ArrayList;

public class StorePage extends StatePanel {
    private ArrayList<StoreButton> storeBuyButtons;
    private ArrayList<StoreButton> storeSellButtons;
    private ArrayList<Card> buyCards;
    private ArrayList<Card> sellCards;

    public StorePage() {
        init();
    }

    private void init() {
        removeAll();

        setCoins();
        setBuyInitial();
        setSellInitial();
        setBuy();
        setSell();
        setPanel();
    }

    private void setCoins() {
        MyButton myButton = addMyButton("", "Coin",
                GraphicConstants.FRAME_WIDTH - 103, 64, 90, 30);
        myButton.setForeground(Color.ORANGE);
        myButton.setText(player.getPlayerCoins() + " $");
        myButton.setBorder(null);
    }

    private void setBuyInitial() {
        storeBuyButtons = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            StoreButton storeButton = new StoreButton(GraphicConstants.STORE_BUTTON_FIRST_X +
                    i * GraphicConstants.STORE_BUTTON_X_SEPARATOR, GraphicConstants.STORE_BUTTON_FIRST_Y);
            storeBuyButtons.add(storeButton);
        }
    }

    private void setSellInitial() {
        storeSellButtons = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            StoreButton storeButton = new StoreButton(GraphicConstants.STORE_BUTTON_FIRST_X +
                    i * GraphicConstants.STORE_BUTTON_X_SEPARATOR, GraphicConstants.STORE_BUTTON_FIRST_Y +
                    GraphicConstants.STORE_BUTTON_Y_SEPARATOR);
            storeSellButtons.add(storeButton);
        }
    }

    private void setBuy() {
        buyCards = player.getBuyCards();
        for (int i = 0; i < storeBuyButtons.size(); i++) {
            if (i >= buyCards.size())
                break;
            StoreButton storeButton = storeBuyButtons.get(i);
            changeComp(storeButton, buyCards.get(i));
        }
    }

    private void setSell() {
        sellCards = player.getSellCards();
        for (int i = 0; i < storeSellButtons.size(); i++) {
            if (i >= sellCards.size())
                break;
            StoreButton storeButton = storeSellButtons.get(i);
            changeComp(storeButton, sellCards.get(i));
        }
    }

    private void changeComp(StoreButton storeButton, Card card) {
        if (!storeButton.isOccupied())
            addComp(storeButton);
        storeButton.setNewCard(card);
        validate();
    }

    private void addComp(StoreButton storeButton) {
        add(storeButton.getCardButton());
        add(storeButton.getValueButton());
    }

    @Override
    public boolean runState() {
        uiController.setContentPane(this);

        while (true) {
            uiController.validate();
            if (newAction("Back")) {
                return true;
            }
            checkNewBuy();
            checkNewSell();
        }
    }

    private Boolean checkNewBuy() {
        ArrayList<StoreButton> storeButtons = getStoreBuyButtons();
        for (StoreButton storeButton : storeButtons) {
            if (storeButton.isDoOpp()) {
                getSure(storeButton, "BUY CARD");
                return true;
            }
        }
        return false;
    }

    private Boolean checkNewSell() {
        ArrayList<StoreButton> storeButtons = getStoreSellButtons();
        for (StoreButton storeButton : storeButtons) {
            if (storeButton.isDoOpp()) {
                getSure(storeButton, "SELL CARD");
                return true;
            }
        }
        return false;
    }

    private void getSure(StoreButton storeButton, String op) {
        storeButton.setDoOpp(false);
        Card card = storeButton.getCard();
        uiController.changeState(this, new BSGetSurePage(card, op));
    }

    @Override
    public void updateState() {
        init();
    }

    public ArrayList<StoreButton> getStoreBuyButtons() {
        return storeBuyButtons;
    }

    public ArrayList<StoreButton> getStoreSellButtons() {
        return storeSellButtons;
    }

}