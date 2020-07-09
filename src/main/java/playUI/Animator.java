package playUI;

import game.TimeAssistance;

import javax.swing.*;

public class Animator extends Thread {

    private JButton jButton;
    private int xDis, yDis;
    private int x, y;
    private Long speed;
    private String type;

    public Animator(JButton jButton, int xDis, int yDis, Long speed, int x, int y, String type) {
        this.jButton = jButton;
        this.xDis = xDis;
        this.yDis = yDis;
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void moveHandButton() {
        if (ButtonMover.isLock())
            return;
        ButtonMover.setLock(true);
        ButtonMover.moveButton(jButton, xDis, yDis, speed);
        TimeAssistance.waitFor(3000L);
        ButtonMover.moveButton(jButton, x, y, speed);
        ButtonMover.setLock(false);
    }

    @Override
    public void run() {
        if (type.equals("HandMover")) {
            moveHandButton();
            return;
        }
    }
}
