package userInterface;

import card.Passive;
import constants.GraphicConstants;

import java.util.ArrayList;

public class ChoosePassivesPage extends StatePanel {
    ArrayList<Passive> passives = Passive.getAllHPassives();

    public ChoosePassivesPage() {
        setPassiveButtons();
        setPanel();
    }

    private void setPassiveButtons() {
        for (int i = 0; i < 3; i++) {
            Passive passive = passives.get(i);
            addMyButton("", passive.getPassiveName(), GraphicConstants.PASSIVE_PAGE_X +
                            i * GraphicConstants.PASSIVE_PAGE_SEPARATOR, GraphicConstants.PASSIVE_PAGE_Y,
                    GraphicConstants.STORE_BUTTON_WIDTH, GraphicConstants.STORE_BUTTON_HEIGHT);
            addMyButton("SELECT PASSIVE", passive.getPassiveName(), GraphicConstants.PASSIVE_PAGE_X +
                            i * GraphicConstants.PASSIVE_PAGE_SEPARATOR, GraphicConstants.PASSIVE_PAGE_Y +
                            GraphicConstants.PASSIVE_PAGE_SEPARATOR,
                    GraphicConstants.STORE_BS_BUTTON_WIDTH, GraphicConstants.STORE_BS_BUTTON_HEIGHT);
        }
    }

    @Override
    public boolean runState() {
        uiController.setContentPane(this);

        while (true) {
            if (newAction("Back")) {
                return true;
            }
            if (checkNewSelect())
                return true;
            uiController.validate();
        }
    }

    private boolean checkNewSelect() {
        for (Passive passive : passives) {
            if (newAction(passive.getPassiveName())) {
           //     uiController.changeState(this, new PlayPage());
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateState() {
    }
}