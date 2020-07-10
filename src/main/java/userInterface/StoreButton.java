package userInterface;

import constants.GraphicConstants;
import file.FileAssistance;
import module.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StoreButton extends JComponent {
    private Card card;
    private int x, y;
    private int type;
    private JButton cardButton;
    private JButton valueButton;

    private boolean isOccupied = false;
    private boolean doOpp = false;

    public StoreButton(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
        setCardButton(x, y);
        setValueButton(x, y);
    }

    private void setCardButton(int x, int y) {
        cardButton = new JButton();
        cardButton.setBounds(x, y, GraphicConstants.STORE_BUTTON_WIDTH, GraphicConstants.STORE_BUTTON_HEIGHT);
        cardButton.setBorder(null);
        cardButton.setContentAreaFilled(false);
    }

    private void setValueButton(int x, int y) {
        x += GraphicConstants.STORE_BS_BUTTON_PLUS_X;
        y += GraphicConstants.STORE_BS_BUTTON_PLUS_Y;
        valueButton = new JButton();
        valueButton.setBounds(x, y, GraphicConstants.STORE_BS_BUTTON_WIDTH, GraphicConstants.STORE_BS_BUTTON_HEIGHT);
        valueButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                setDoOpp(true);
            }
        });
    }


    public void setNewCard(Card card) {
        this.card = card;
        isOccupied = true;

        String cardName = card.getCardShowName() + ".png";

        ImageIcon imageIcon = new ImageIcon(FileAssistance.getScaledImage("cards", cardName,
                GraphicConstants.STORE_BUTTON_WIDTH, GraphicConstants.STORE_BUTTON_HEIGHT));

        cardButton.setBorder(null);
        cardButton.setContentAreaFilled(false);
        cardButton.setIcon(imageIcon);

        setNewValue();
    }

    public void setNewValue() {
        String text = "";
        if (type == 1)
            text = "+";
        else
            text = "-";
        text = text + card.getCardValue() + "$";
        valueButton.setText(text);
        if (type == 1)
            valueButton.setForeground(Color.GREEN.darker().darker());
        else
            valueButton.setForeground(Color.RED.darker());
    }


    public JButton getCardButton() {
        return cardButton;
    }

    public JButton getValueButton() {
        return valueButton;
    }

    public Card getCard() {
        return card;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public boolean isDoOpp() {
        return doOpp;
    }

    public void setDoOpp(Boolean doOpp) {
        this.doOpp = doOpp;
    }
}