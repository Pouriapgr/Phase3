package userInterface;

import constants.GraphicConstants;
import module.Passive;
import playlogic.PlayerInfo;

import java.util.ArrayList;

public class ChoosePassivesPage extends State {
    private ArrayList<Passive> passives = Passive.getAllHPassives();
    private PlayerInfo playerInfo;

    public ChoosePassivesPage(PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;

        removeAllThings();
        setPassiveButtons();
        setPanel();
    }

    private void setPassiveButtons() {
        for (int i = 0; i < 3; i++) {
            Passive passive = passives.get(i);
            addMyButton("", passive.getPassiveName(), GraphicConstants.PASSIVE_PAGE_X +
                            i * GraphicConstants.PASSIVE_PAGE_SEPARATOR, GraphicConstants.PASSIVE_PAGE_Y,
                    GraphicConstants.PASSIVE_PAGE_WIDTH, GraphicConstants.PASSIVE_PAGE_HEIGHT, "passives",
                    passive.getPassiveName() + ".png");
        }
    }

    @Override
    public boolean runState() {
        uiController.setContentPane(this);

        while (true) {
            if (checkNewSelect())
                return true;
            uiController.validate();
        }
    }

    private boolean checkNewSelect() {
        for (Passive passive : passives) {
            if (newAction(passive.getPassiveName())) {
                playerInfo.setPassive(passive);
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateState() {
    }
}