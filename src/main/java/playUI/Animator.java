package playUI;

import file.FileAssistance;
import game.TimeAssistance;
import module.Card;
import playcard.PlayCard;
import userInterface.MyButton;

import javax.swing.*;

public class Animator extends Thread {
    private PlayCard playCard;
    private MyButton myButton;
    private HandButton desButton;
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

    public Animator(MyButton myButton, String type, PlayCard playCard) {
        this.myButton = myButton;
        this.type = type;
        this.playCard = playCard;
    }

    public Animator(MyButton myButton, HandButton desButton, PlayCard playCard, Long speed, String type) {
        this.type = type;
        this.speed = speed;
        this.myButton = myButton;
        this.desButton = desButton;
        this.playCard = playCard;
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

    public void moveToHand() {
        String name = playCard.getName();
        Card card = Card.loadCard(FileAssistance.findCardJSON(name));
        PlayCard newCard = card.playCopy(playCard.getPlayerInfo());

        while (ButtonFader.isLock() || ButtonFader.isLock()) {
            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        HandMover.setLock(true);
        HandMover.moveButton(myButton, desButton, speed, name, newCard);
    }

    public void fadeCard() {
        String name = playCard.getName();
        while (ButtonFader.isLock() || ButtonFader.isLock()) {
            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ButtonFader.setLock(true);
        ButtonFader.fadeButton(myButton, name);
        ButtonFader.setLock(false);
    }

    @Override
    public void run() {
        if (type.equals("HandMover")) {
            moveHandButton();
            return;
        }
        if (type.equals("CardFade")) {
            fadeCard();
            return;
        }
        if (type.equals("MoveToHand")) {
            moveToHand();
            return;
        }
    }
}
