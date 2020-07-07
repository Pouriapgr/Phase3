package userInterface;

import card.Card;
import constants.GraphicConstants;
import file.FileAssistance;
import game.InfoHolder;
import logic.Admin;
import module.Player;

import javax.swing.*;

public abstract class StatePanel extends State {
    protected InfoHolder infoHolder = InfoHolder.getInstance();
    protected Admin admin = Admin.getInstance();
    protected Player player = infoHolder.getPlayer();

    public StatePanel() {
    }

    protected void setPanel() {
        setBack();
        setLayout(null);
        setBounds(0, 0, GraphicConstants.FRAME_WIDTH, GraphicConstants.FRAME_HEIGHT);
        setBackground();
    }

    private void setBackground() {
        JLabel jLabel = new JLabel();
        jLabel.setBounds(0, 0, GraphicConstants.FRAME_WIDTH, GraphicConstants.FRAME_HEIGHT);
        ImageIcon imageIcon = new ImageIcon(FileAssistance.getScaledImage("status", "background.jpg",
                GraphicConstants.FRAME_WIDTH, GraphicConstants.FRAME_HEIGHT));
        jLabel.setIcon(imageIcon);
        add(jLabel);
    }

    private void setBack() {
        MyButton myButton = addMyButton("Back", "Back",
                GraphicConstants.FRAME_WIDTH - 103, 0, 90, 30);
        myButton.setBorder(null);
    }

    protected void setCard(Card card) {
        JButton jButton = new JButton();
        jButton.setIcon(new ImageIcon(FileAssistance.getImage("cards", card.getCardName() + ".png")));
        jButton.setBorder(null);
        jButton.setBounds(GraphicConstants.SHOW_CARD_X, GraphicConstants.SHOW_CARD_Y, GraphicConstants.SHOW_CARD_WIDTH,
                GraphicConstants.SHOW_CARD_HEIGHT);
        jButton.setContentAreaFilled(false);
        add(jButton);
    }

    public abstract boolean runState();

    public abstract void updateState();

    public Player getPlayer() {
        return player;
    }
}