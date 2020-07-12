package playUI;

import constants.GraphicConstants;
import file.FileAssistance;
import playcard.PlayCard;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class PlayButton {
    private PlayCard card;
    private int x, y;
    private JButton cardButton;

    private boolean selectCard = false;
    private boolean showCard = false;
    private boolean selectPlace = false;

    public PlayButton(int x, int y) {
        this.x = x;
        this.y = y;
        setCardButton(x, y);
    }

    private void setCardButton(int x, int y) {
        cardButton = new JButton();
        cardButton.setBounds(x, y, GraphicConstants.PLAY_MINION_BUTTON_WIDTH, GraphicConstants.PLAY_MINION_BUTTON_HEIGHT);
        cardButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                if (card == null) {
                    selectPlace = true;
                    return;
                }
                if (event.equals(MouseEvent.BUTTON3)) {
                    showCard = true;
                } else {
                    selectCard = true;
                }
            }
        });
        cardButton.setBorder(null);
        cardButton.setContentAreaFilled(false);
    }

    public void setNewCard(PlayCard card) {
        this.card = card;

        String cardName = card.getName() + ".png";

        BufferedImage bufferedImage = FileAssistance.getScaledImage("cards", cardName,
                GraphicConstants.PLAY_MINION_BUTTON_WIDTH, GraphicConstants.PLAY_MINION_BUTTON_HEIGHT);
        ImageIcon imageIcon = new ImageIcon(bufferedImage);
        cardButton.setBorder(null);
        cardButton.setContentAreaFilled(false);
        cardButton.setIcon(imageIcon);
    }

    public void setEmpty() {
        card = null;
        cardButton.setIcon(null);
    }

    public PlayCard getCard() {
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

    public boolean isSelectPlace() {
        return selectPlace;
    }

    public void setSelectPlace(boolean selectPlace) {
        this.selectPlace = selectPlace;
    }

    public void setShowCard(boolean showCard) {
        this.showCard = showCard;
    }

    public void setSelectCard(boolean selectCard) {
        this.selectCard = selectCard;
    }
}
