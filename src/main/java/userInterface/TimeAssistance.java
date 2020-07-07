package userInterface;

public class TimeAssistance {
    public static void waitFor(Long waitTime) {
        Long nowTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - nowTime <= waitTime){}
    }
}