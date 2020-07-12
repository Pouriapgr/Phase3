package playUI;

import game.TimeAssistance;
import userInterface.MyButton;

public class ButtonFader {
    private static boolean lock = false;

    public static synchronized void fadeButton(MyButton myButton, String name) {
        myButton.changeIcon(name + ".png");
        myButton.setVisible(true);
        TimeAssistance.waitFor(1500L);
        myButton.changeIcon(name + "-lock.png");
        TimeAssistance.waitFor(1000L);
        myButton.setVisible(false);
    }

    public static boolean isLock() {
        return lock;
    }

    public static void setLock(boolean lock) {
        ButtonFader.lock = lock;
    }
}
