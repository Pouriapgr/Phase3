package playUI;

import card.Card;
import constants.GraphicConstants;
import file.FileAssistance;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;


public class HandButton {
    private Card card;
    private int x, y;
    private JButton cardButton;

    private boolean selectCard = false;
    private boolean showCard = false;

    public HandButton(int x, int y) {
        this.x = x;
        this.y = y;
        setCardButton(x, y);
    }

    private void setCardButton(int x, int y) {
        cardButton = new JButton();
        cardButton.setBounds(x, y, GraphicConstants.PLAY_HAND_BUTTON_WIDTH, GraphicConstants.PLAY_HAND_BUTTON_HEIGHT);
        cardButton.setBorder(null);
        cardButton.setContentAreaFilled(false);
        cardButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                if (card == null) {
                    return;
                }
                if (event.equals(MouseEvent.BUTTON3)) {
                    showCard = true;
                } else {
                    selectCard = true;
                }
            }
        });
    }

    public void setHover(int dy) {
        cardButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                if (card != null) {
                    Animator animator = new Animator(cardButton, x, y - dy, 20L, x, y, "HandMover");
                    animator.start();
                }
            }
        });
    }

    public void setNewCard(Card card) {
        this.card = card;

        String cardName = card.getCardName();
        cardName = cardName + ".png";

        BufferedImage bufferedImage = FileAssistance.getScaledImage("cards", cardName,
                GraphicConstants.PLAY_HAND_BUTTON_WIDTH, GraphicConstants.PLAY_HAND_BUTTON_HEIGHT);
        ImageIcon imageIcon = new ImageIcon(bufferedImage);
        cardButton.setBorder(null);
        cardButton.setContentAreaFilled(false);
        cardButton.setIcon(imageIcon);
    }

    public void setEmpty() {
        card = null;
        cardButton.setIcon(null);
    }

    public Card getCard() {
        return card;
    }

    public JButton getCardButton() {
        return cardButton;
    }

    public boolean isShowCard() {
        return showCard;
    }

    public boolean isSelectCard() {
        return selectCard;
    }
}
