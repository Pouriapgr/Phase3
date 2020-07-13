package playUI;

import game.TimeAssistance;
import userInterface.MyButton;

public class ButtonFader extends Thread {
    private static boolean lock = false;

    private MyButton myButton;
    private String name;

    public ButtonFader(MyButton myButton, String name) {
        this.myButton = myButton;
        this.name = name;
    }

    public static boolean isLock() {
        return lock;
    }

    public static void setLock(boolean lock) {
        ButtonFader.lock = lock;
    }

    public synchronized void fadeButton() {
        myButton.changeIcon(name + ".png");
        myButton.setVisible(true);
        TimeAssistance.waitFor(1500L);
        myButton.changeIcon(name + "-lock.png");
        TimeAssistance.waitFor(1000L);
        myButton.setVisible(false);
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
        fadeButton();
        lock = false;
    }
}
