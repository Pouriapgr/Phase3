package userInterface;

import card.Card;
import constants.GraphicConstants;
import file.FileAssistance;

import javax.swing.*;

public class CollectionButton {

    private Card card;
    private int x, y;
    private JButton cardButton;

    private boolean isOccupied = false;
    private boolean doOpp = false;

    public CollectionButton(int x, int y) {
        this.x = x;
        this.y = y;
        setCardButton(x, y);
    }

    private void setCardButton(int x, int y) {
        cardButton = new JButton();
        cardButton.setBounds(x, y, GraphicConstants.STORE_BUTTON_WIDTH, GraphicConstants.STORE_BUTTON_HEIGHT);
        cardButton.setBorder(null);
        cardButton.setContentAreaFilled(false);
    }

    public void setNewCard(Card card, boolean available) {
        isOccupied = true;
        this.card = card;

        String cardName = card.getCardName();
        if (!available)
            cardName = cardName + "-lock";
        cardName = cardName + ".png";

        ImageIcon imageIcon = new ImageIcon(FileAssistance.getScaledImage("cards", cardName,
                GraphicConstants.STORE_BUTTON_WIDTH, GraphicConstants.STORE_BUTTON_HEIGHT));
        cardButton.setBorder(null);
        cardButton.setContentAreaFilled(false);
        cardButton.setIcon(imageIcon);
    }

    public void setEmpty() {
        card = null;
        isOccupied = false;
        cardButton.setIcon(null);
    }

    public Card getCard() {
        return card;
    }

    public JButton getCardButton() {
        return cardButton;
    }
}
