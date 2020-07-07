package userInterface;

import module.Player;
import file.FileAssistance;
import Constants.GraphicConstants;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public abstract class StatePanel extends UIState {
    protected boolean goBack = false;
    protected Player player;

    public StatePanel() {}
    protected void setPanel() {
        setBack();
        setLayout(null);
        setBounds(0, 0, GraphicConstants.FRAME_WIDTH, GraphicConstants.FRAME_HEIGHT);
        setBackground();
    }
    private void setBackground() {
        JLabel jLabel = new JLabel();
        jLabel.setBounds(0,0, GraphicConstants.FRAME_WIDTH, GraphicConstants.FRAME_HEIGHT);
        ImageIcon imageIcon = new ImageIcon(FileAssistance.getScaledImage("status", "background.jpg",
                GraphicConstants.FRAME_WIDTH, GraphicConstants.FRAME_HEIGHT));
        jLabel.setIcon(imageIcon);
        add(jLabel);
    }
    private void setBack() {
        JButton jButton = new JButton("BACK");
        jButton.setBounds(GraphicConstants.FRAME_WIDTH - 103, 0, 90, 30);
        jButton.setBorder(null);
        jButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event){
                player.writeLog("Click","BackButton");
                setGoBack(true);
            }
        });
        add(jButton);
    }

    public abstract boolean runState();
    public abstract void getRenew();

    public Player getPlayer() { return player; }
    public boolean getGoBack() { return goBack; }
    public void setGoBack(boolean goBack) { this.goBack = goBack; }
}