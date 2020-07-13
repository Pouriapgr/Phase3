package playUI;

import constants.GraphicConstants;
import playcard.PlayCard;
import userInterface.MyButton;

public class HandMover extends Thread {
    private static boolean lock = false;

    private MyButton myButton;
    private HandButton destButton;
    private long speed;
    private PlayCard playCard;

    public HandMover(MyButton myButton, HandButton destButton, long speed, PlayCard playCard) {
        this.myButton = myButton;
        this.destButton = destButton;
        this.speed = speed;
        this.playCard = playCard;
    }

    public static boolean isLock() {
        return lock;
    }

    public static void setLock(boolean lock) {
        HandMover.lock = lock;
    }

    public synchronized void moveButton() {
        myButton.changeIcon(playCard.getName() + ".png");
        myButton.setVisible(true);
        destButton.getCardButton().setVisible(false);
        destButton.setNewCard(playCard);

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
            int xDis = destButton.getCardButton().getX();
            int yDis = destButton.getCardButton().getY();
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
        myButton.setVisible(false);
        destButton.getCardButton().setVisible(true);
        myButton.setBounds(initX, initY, myButton.getWidth(), myButton.getHeight());
    }

    @Override
    public void run() {
        while (lock == true) {
            try {
                sleep(50L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lock = true;
        moveButton();
        lock = false;
    }

}
