package game;

import userInterface.SignPage;

public class RunGame {
    public static void main(String[] args) {
        UIController uiController = UIController.getInstance();
        uiController.changeState(null, new SignPage());
    }
}