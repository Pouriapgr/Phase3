package userInterface;

import module.Card;
import module.Passive;
import file.FileAssistance;
import Constants.GraphicConstants;
import module.Player;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class StoreButton extends JComponent {
    private Player player;
    private Card card;
    private Passive passive;
    private String type;

    private int x,y;
    private JButton cardButton ;
    private JButton valueButton;

    private boolean isOccupied = false;
    private boolean needValue = false;
    private boolean doOpp = false;

    public StoreButton(Player player, int x, int y, String type, Boolean needValue) {
        this.player = player;
        this.x = x;
        this.y = y;
        this.type = type;
        this.needValue = needValue;
        setCardButton(x, y);
        if(needValue)
          setValueButton(x, y);
    }
    private void setCardButton(int x,int y) {
        cardButton = new JButton();
        cardButton.setBounds(x, y, GraphicConstants.STORE_BUTTON_WIDTH, GraphicConstants.STORE_BUTTON_HEIGHT);
        cardButton.setBorder(null);
        cardButton.setContentAreaFilled(false);
    }
    private void setValueButton(int x,int y) {
        x += GraphicConstants.STORE_BS_BUTTON_PLUS_X;
        y += GraphicConstants.STORE_BS_BUTTON_PLUS_Y;
        valueButton = new JButton("S");
        valueButton.setBounds(x, y, GraphicConstants.STORE_BS_BUTTON_WIDTH, GraphicConstants.STORE_BS_BUTTON_HEIGHT);
        valueButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event){
                player.writeLog("Click", "ValueButton");
                setDoOpp(true);
            }
        });
    }

    public void setNewPassive(Passive passive) {
        ImageIcon imageIcon = new ImageIcon(FileAssistance.getScaledImage("passives", passive.getPassiveName()
                        + ".png", GraphicConstants.STORE_BUTTON_WIDTH, GraphicConstants.STORE_BUTTON_HEIGHT));
        cardButton.setIcon(imageIcon);
        cardButton.setBorder(null);
        cardButton.setContentAreaFilled(false);
    }
    public void setNewCard(Card card, Boolean available) {
        this.card = card;
        isOccupied = true;
        ImageIcon imageIcon ;
        if(available) {
            imageIcon = new ImageIcon(FileAssistance.getScaledImage("cards", card.getCardName() + ".png",
                    GraphicConstants.STORE_BUTTON_WIDTH, GraphicConstants.STORE_BUTTON_HEIGHT));
        }
        else{
            imageIcon = new ImageIcon(FileAssistance.getScaledImage("cards", card.getCardName() + "-lock.png",
                    GraphicConstants.STORE_BUTTON_WIDTH, GraphicConstants.STORE_BUTTON_HEIGHT));
        }
        cardButton.setBorder(null);
        cardButton.setContentAreaFilled(false);
        cardButton.setIcon(imageIcon);
        if(!needValue)
            return;

        if(type.equals("DO YOU WANT TO BUY THIS CARD")) {
            valueButton.setForeground(Color.RED);
            valueButton.setText("- " + card.getCardValue() + "$");
        }
        if(type.equals("DO YOU WANT TO SELL THIS CARD")) {
            valueButton.setForeground(Color.blue);
            valueButton.setText("+ " + card.getCardValue() + "$");
        }
    }
    public void setEmpty() {
        card = null;
        isOccupied = false;
        cardButton.setIcon(null);
    }
    public void setBound(int width, int height) {
        cardButton.setBounds(x, y, width, height);
        cardButton.setIcon(new ImageIcon(FileAssistance.getScaledImage("cards", card.getCardName() + ".png",
                width, height)));
    }

    public JButton getCardButton() { return cardButton; }
    public JButton getValueButton() { return valueButton; }
    public Card getCard() { return card; }
    public boolean isOccupied() { return isOccupied; }
    public boolean isDoOpp() { return doOpp; }

    public void setDoOpp(Boolean doOpp) { this.doOpp = doOpp; }
}