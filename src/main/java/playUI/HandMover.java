package playUI;

import constants.GraphicConstants;
import playcard.PlayCard;
import userInterface.MyButton;

public class HandMover {
    private static boolean lock = false;

    public static synchronized void moveButton(MyButton myButton, HandButton desButton, long speed, String name, PlayCard playCard) {
        myButton.changeIcon(name + ".png");
        myButton.setVisible(true);

        long last = System.currentTimeMillis();
        int initX = myButton.getX();
        int initY = myButton.getY();

        while (true) {
            long now = System.currentTimeMillis();
            if (now - last < speed) {
                continue;
            }
            last = now;

            int x = myButton.getX();
            int y = myButton.getY();
            int xDis = desButton.getCardButton().getX();
            int yDis = desButton.getCardButton().getY();
            if (x < xDis - GraphicConstants.MOVE_RATE) {
                x += 2 * GraphicConstants.MOVE_RATE;
            } else if (x > xDis + GraphicConstants.MOVE_RATE) {
                x -= 2 * GraphicConstants.MOVE_RATE;
            }

            if (y < yDis - GraphicConstants.MOVE_RATE) {
                y += 2 * GraphicConstants.MOVE_RATE;
            } else if (y > yDis + GraphicConstants.MOVE_RATE) {
                y -= 2 * GraphicConstants.MOVE_RATE;
            }

            myButton.setBounds(x, y, myButton.getWidth(), myButton.getHeight());
            if (x - xDis <= 2 * GraphicConstants.MOVE_RATE && x - xDis >= -2 * GraphicConstants.MOVE_RATE &&
                    y - yDis <= 2 * GraphicConstants.MOVE_RATE && y - yDis >= -2 * GraphicConstants.MOVE_RATE)
                break;
        }
        desButton.setNewCard(playCard);
        myButton.setVisible(false);
        myButton.setBounds(initX, initY, myButton.getWidth(), myButton.getHeight());
    }

    public static boolean isLock() {
        return lock;
    }

    public static void setLock(boolean lock) {
        HandMover.lock = lock;
    }

}
