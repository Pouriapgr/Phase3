package userInterface;

import module.Card;
import module.Player;
import module.GameState;
import file.FileAssistance;
import Constants.GraphicConstants;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class ShowCardPage extends StatePanel {
    private GameState gameState;
    private Card card;
    private String op;
    private boolean needAssure;

    private JButton sureButton;
    private Boolean sure = false;

    public ShowCardPage(Card card, Player player, String op, Boolean needAssure, GameState gameState) {
        init(card, player, op, needAssure, gameState);
    }
    public void init(Card card, Player player, String op, Boolean needAssure, GameState gameState) {
        this.gameState = gameState;
        this.player = player;
        this.card = card;
        this.op = op;
        this.needAssure = needAssure;
        sure = false;

        removeAll();

        if(needAssure)
            setAssure(op);
        setCard();

        setPanel();
    }

    private void setCard() {
        JButton jButton = new JButton();
        jButton.setIcon(new ImageIcon(FileAssistance.getImage("cards",card.getCardName() + ".png")));
        jButton.setBorder(null);
        jButton.setBounds(GraphicConstants.SHOW_CARD_X, GraphicConstants.SHOW_CARD_Y, GraphicConstants.SHOW_CARD_WIDTH,
                GraphicConstants.SHOW_CARD_HEIGHT);
        jButton.setContentAreaFilled(false);
        add(jButton);
    }
    private void setAssure(String op) {
        sureButton = new JButton(op);
        sureButton.setBounds(GraphicConstants.SHOW_CARD_ASSURE_X, GraphicConstants.SHOW_CARD_ASSURE_Y,
                GraphicConstants.SHOW_CARD_ASSURE_WIDTH, GraphicConstants.SHOW_CARD_ASSURE_HEIGHT);
        sureButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event){
                player.writeLog("Click", "SureButton");
                setSure(true);
            }
        });
        add(sureButton);
    }

    @Override
    public boolean runState() {
        player.writeLog("Navigate", "ShowCard");
        ShowCardPage showCardPage = this;
        gameJFrame.setContentPane(showCardPage);

        while (true) {
            gameJFrame.validate();
            if (showCardPage.getGoBack()) {
                return true;
            }
            if (showCardPage.getSure()) {
                showCardPage.setSure(false);
                if (op.equals("DO YOU WANT TO BUY THIS CARD"))
                    buyCard(player, card, showCardPage);
                if (op.equals("DO YOU WANT TO SELL THIS CARD"))
                    sellCard(player, card);
                if(op.equals("DO YOU WANT TO PLAY THIS"))
                    playCard(player, card, gameState);
                return true;
            }
        }
    }
    private void buyCard(Player player, Card card, ShowCardPage showCardPage) {
        if(card.getCardValue() > player.getPlayerCoins()) {
            player.writeLog("Error", "LowCoin");
            JButton jButton = showCardPage.getSureButton();
            jButton.setForeground(Color.RED);
            jButton.setText("YOU DON'T HAVE ENOUGH COINS");
            TimeAssistance.waitFor(2000l);
            showCardPage.setSure(false);
            return;
        }
        player.setPlayerCoins(player.getPlayerCoins() - card.getCardValue());
        player.addCard(card);
    }
    private void sellCard(Player player, Card card) {
        player.setPlayerCoins(player.getPlayerCoins() + card.getCardValue());
        player.removeCardFromAllCards(card);
    }
    private void playCard(Player player, Card card, GameState gameState) {
        if(!gameState.canPlayCard(card).equals("YES")) {
            sureButton.setText(gameState.canPlayCard(card));
            sureButton.setForeground(Color.RED);
            TimeAssistance.waitFor(2000L);
            return;
        }
        gameState.playCard(card);
    }

    @Override
    public void getRenew() {
        init(card, player, op, needAssure, gameState); }

    public JButton getSureButton() { return sureButton; }
    public Boolean getSure() { return sure; }

    public void setSure(Boolean sure) { this.sure = sure;}
}