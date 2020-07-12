package playUI;

import constants.GraphicConstants;

import javax.swing.*;

public class ButtonMover extends Thread {
    private static boolean lock = false;

    private JButton jButton;
    private int xDis;
    private int yDis;
    private long speed;
    private int initY;

    public ButtonMover(JButton jButton, int xDis, int yDis, long speed) {
        this.jButton = jButton;
        this.xDis = xDis;
        this.yDis = yDis;
        this.speed = speed;
        initY = jButton.getY();
    }

    public synchronized void moveButton() {
        long last = System.currentTimeMillis();

        while (true) {
            long now = System.currentTimeMillis();
            if (now - last < speed) {
                continue;
            }

            last = now;

            int x = jButton.getX();
            int y = jButton.getY();

            if (x < xDis) {
                x += GraphicConstants.MOVE_RATE;
            } else if (x > xDis) {
                x -= GraphicConstants.MOVE_RATE;
            }

            if (y < yDis) {
                y += GraphicConstants.MOVE_RATE;
            } else if (y > yDis) {
                y -= GraphicConstants.MOVE_RATE;
            }

            jButton.setBounds(x, y, jButton.getWidth(), jButton.getHeight());
            if (x - xDis <= GraphicConstants.MOVE_RATE && x - xDis >= -GraphicConstants.MOVE_RATE &&
                    y - yDis <= GraphicConstants.MOVE_RATE && y - yDis >= -GraphicConstants.MOVE_RATE)
                break;
        }
        jButton.setLocation(xDis, yDis);
    }

    @Override
    public void run() {
        if (lock == true)
            return;
        lock = true;
        moveButton();
        yDis = initY;
        try {
            sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        moveButton();
        lock = false;
    }
}
