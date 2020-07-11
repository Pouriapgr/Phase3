package playlogic;

import constants.LogicConstants;
import playUI.PlayPage;
import userInterface.MyButton;

public class Timer extends Thread {
    private static boolean exit = false;
    private PlayPage playPage;

    public Timer(PlayPage playPage) {
        this.playPage = playPage;
        exit = false;
    }

    public static void setExit(boolean exiti) {
        exit = exiti;
    }

    @Override
    public void run() {
        MyButton myButton = playPage.getMyButton("Timer");
        long now = System.currentTimeMillis();
        while (!exit) {
            long after = System.currentTimeMillis() - now;
            int time = (int) (after / 1000L);
            if (myButton.getText().equals(Integer.toString(LogicConstants.TURN_TIME - time)))
                continue;
            myButton.setText(Integer.toString(LogicConstants.TURN_TIME - time));
            if (time == LogicConstants.TURN_TIME) {
                playPage.getMyButton("End").setPressed(true);
                break;
            }
        }
        return;
    }
}
