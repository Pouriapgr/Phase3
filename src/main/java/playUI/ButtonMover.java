package playUI;

import constants.GraphicConstants;

import javax.swing.*;

public class ButtonMover {

    private static boolean lock = false;

    public static synchronized void moveButton(JButton jButton, int xDis, int yDis, long speed) {
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
        System.out.println(Thread.activeCount());
    }

    public static boolean isLock() {
        return lock;
    }

    public static void setLock(boolean lock) {
        ButtonMover.lock = lock;
    }
}
