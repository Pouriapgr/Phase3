package playUI;

import constants.GraphicConstants;
import playcard.PlayCard;
import playlogic.PlayerInfo;
import userInterface.MyButton;
import userInterface.State;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ChangeCardPage extends State {
    private ArrayList<PlayCard> startingHand = new ArrayList<>();
    private PlayerInfo playerInfo;

    public ChangeCardPage(PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
        removeAllThings();

        setStartingHand();
        setCardButtons();
        setDoneButton();

        setPanel();
    }

    private void setStartingHand() {
        for (int i = 0; i < 3; i++) {
            startingHand.add(playerInfo.getDeck().getCards().get(0));
            playerInfo.getDeck().getCards().remove(0);
        }
    }

    private void setDoneButton() {
        MyButton myButton = addMyButton("DONE CHOOSING CARDS", "Done", GraphicConstants.SHOW_CARD_ASSURE_X,
                GraphicConstants.SHOW_CARD_ASSURE_Y - 50, GraphicConstants.SHOW_CARD_ASSURE_WIDTH,
                GraphicConstants.SHOW_CARD_ASSURE_HEIGHT);
        myButton.setBackground(Color.YELLOW.darker());
    }

    private void setCardButtons() {
        for (int i = 0; i < 3; i++) {
            PlayCard card = startingHand.get(i);
            MyButton myButton = addMyButton("", card.getName(), GraphicConstants.PASSIVE_PAGE_X +
                            i * GraphicConstants.PASSIVE_PAGE_SEPARATOR, GraphicConstants.PASSIVE_PAGE_Y,
                    GraphicConstants.PASSIVE_PAGE_WIDTH, GraphicConstants.PASSIVE_PAGE_HEIGHT, "cards",
                    card.getName() + ".png");
            myButton.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent event) {
                    if (myButton.getY() == GraphicConstants.PASSIVE_PAGE_Y)
                        myButton.setLocation(myButton.getX(), myButton.getY() + 25);
                }
            });
        }
    }

    @Override
    public boolean runState() {
        uiController.setContentPane(this);

        while (true) {
            if (newAction("Done")) {
                checkNewSelect();
                return true;
            }
            uiController.validate();
        }
    }

    private void checkNewSelect() {
        ArrayList<PlayCard> newCards = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (!newAction(startingHand.get(i).getName())) {
                newCards.add(startingHand.get(i));
            } else {
                newCards.add(playerInfo.getDeck().getCards().get(0));
                playerInfo.getDeck().getCards().remove(0);
                playerInfo.getDeck().getCards().add(startingHand.get(i));
            }
        }
        for (PlayCard playCard : newCards)
            playerInfo.addToHand(playCard);
    }

    @Override
    public void updateState() {
    }
}
