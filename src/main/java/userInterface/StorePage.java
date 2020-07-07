package userInterface;

import module.Card;
import module.Player;
import Constants.GraphicConstants;

import java.awt.*;
import javax.swing.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class StorePage extends StatePanel {
    private ArrayList<StoreButton> storeBuyButtons;
    private ArrayList<StoreButton> storeSellButtons;
    private ArrayList<Card> buyCards;
    private ArrayList<Card> sellCards;

    public StorePage(Player player){
        init(player);
    }
    private void init(Player player){
        this.player = player;

        removeAll();
        setRand();
        setCoins();
        setBuyInitial();
        setSellInitial();
        setRandBS();
        setBuy();
        setSell();

        setPanel();
    }

    private void setRand() {
        JButton jButton = new JButton("Rand");
        jButton.setBounds(GraphicConstants.FRAME_WIDTH - 103, 32, 90, 30);
        jButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event){
                player.writeLog("Click", "RandButton");
                setRandBS();
                setBuy();
                setSell();
            }
        });
        jButton.setBorder(null);
        add(jButton);
    }
    private void setCoins() {
        JButton jButton = new JButton();
        jButton.setForeground(Color.ORANGE);
        jButton.setText(player.getPlayerCoins() + " $");
        jButton.setBounds(GraphicConstants.FRAME_WIDTH - 103, 64, 90, 30);
        jButton.setBorder(null);
        add(jButton);
    }
    private void setRandBS() {
        sellCards = player.getSellCards();
        buyCards = player.getBuyCards();
        Collections.shuffle(sellCards, new Random(System.currentTimeMillis()));
        Collections.shuffle(buyCards, new Random(System.currentTimeMillis()));
    }
    private void setBuyInitial() {
        storeBuyButtons = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            StoreButton storeButton = new StoreButton(player, GraphicConstants.STORE_BUTTON_FIRST_X +
                    i * GraphicConstants.STORE_BUTTON_X_SEPARATOR, GraphicConstants.STORE_BUTTON_FIRST_Y,
                    "DO YOU WANT TO BUY THIS CARD", true);
            storeBuyButtons.add(storeButton);
        }
    }
    private void setSellInitial() {
        storeSellButtons = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            StoreButton storeButton = new StoreButton(player, GraphicConstants.STORE_BUTTON_FIRST_X +
                    i * GraphicConstants.STORE_BUTTON_X_SEPARATOR, GraphicConstants.STORE_BUTTON_FIRST_Y +
                    GraphicConstants.STORE_BUTTON_Y_SEPARATOR, "DO YOU WANT TO SELL THIS CARD", true);
            storeSellButtons.add(storeButton);
        }
    }

    private void setBuy() {
        for(int i = 0; i < storeBuyButtons.size(); i++){
            if(i >= buyCards.size())
                break;
            StoreButton storeButton = storeBuyButtons.get(i);
            changeComp(storeButton, buyCards.get(i));
        }
    }
    private void setSell() {
        for(int i = 0; i < storeSellButtons.size(); i++){
            if(i >= sellCards.size())
                break;
            StoreButton storeButton = storeSellButtons.get(i);
            changeComp(storeButton, sellCards.get(i));
        }
    }
    private void changeComp(StoreButton storeButton, Card card) {
        if(!storeButton.isOccupied())
            addComp(storeButton);
        storeButton.setNewCard(card, true);
        validate();
    }
    private void addComp(StoreButton storeButton) {
        add(storeButton.getCardButton());
        add(storeButton.getValueButton());
    }

    @Override
    public boolean runState() {
        player.writeLog("Navigate", "Store");
        StorePage storePage = this;
        gameJFrame.setContentPane(storePage);

        while (true){
            gameJFrame.validate();
            if(storePage.getGoBack()) {
                return true;
            }
            checkNewBuy();
            checkNewSell();
        }
    }
    private Boolean checkNewBuy() {
        StorePage storePage = this;
        ArrayList<StoreButton> storeButtons = storePage.getStoreBuyButtons();
        for(StoreButton storeButton: storeButtons) {
            if (storeButton.isDoOpp()) {
                getSure(storeButton, player, "DO YOU WANT TO BUY THIS CARD");
                return true;
            }
        }
        return false;
    }
    private Boolean checkNewSell() {
        StorePage storePage = this;
        ArrayList<StoreButton> storeButtons = storePage.getStoreSellButtons();
        for(StoreButton storeButton: storeButtons) {
            if (storeButton.isDoOpp()) {
                getSure(storeButton, player, "DO YOU WANT TO SELL THIS CARD");
                return true;
            }
        }
        return false;
    }
    private void getSure(StoreButton storeButton, Player player, String op) {
        storeButton.setDoOpp(false);
        Card card = storeButton.getCard();
        gameJFrame.changeState(this, new ShowCardPage(card, player, op, true, null));
    }
    @Override
    public void getRenew(){
        init(player);
    }

    public ArrayList<StoreButton> getStoreBuyButtons() { return storeBuyButtons; }
    public ArrayList<StoreButton> getStoreSellButtons() { return storeSellButtons; }

    public boolean getGoBack() { return goBack; }
    public void setGoBack(boolean goBack) { this.goBack = goBack; }
}