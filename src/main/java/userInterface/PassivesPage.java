package userInterface;

import module.Passive;
import module.GameState;
import Constants.GraphicConstants;

import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class PassivesPage extends StatePanel {
    GameState gameState;
    ArrayList<Passive> passives = Passive.getAllHPassives();

    ArrayList<StoreButton> passiveButtons;

    public PassivesPage(GameState gameState) {
        this.gameState = gameState;

        setPassiveButtons();
        setPanel();
    }
    private void setPassiveButtons() {
        passiveButtons = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            StoreButton storeButton = new StoreButton(gameState.getPlayer(), GraphicConstants.PASSIVE_PAGE_X +
                    i * GraphicConstants.PASSIVE_PAGE_SEPARATOR, GraphicConstants.PASSIVE_PAGE_Y,
                    "DO YOU WANT TO HAVE THIS PASSIVE", false);
            storeButton.setNewPassive(passives.get(i));
            add(storeButton.getCardButton());
            storeButton.getCardButton().addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent event){
                    gameState.getPlayer().writeLog("Click", "PassiveButton");
                    storeButton.setDoOpp(true);
                }
            });
            passiveButtons.add(storeButton);
        }
    }

    @Override
    public boolean runState() {
        gameState.getPlayer().writeLog("Navigate", "Passive");
        gameJFrame.setContentPane(this);
        while (true){
            if(getGoBack()){
                return true;
            }
            if(checkNewSelect())
                return true;
            gameJFrame.validate();
        }
    }
    private boolean checkNewSelect() {
        for(StoreButton storeButton: passiveButtons){
            if(storeButton.isDoOpp()){
                gameJFrame.changeState(this, new PlayPage(gameState));
                storeButton.setDoOpp(false);
                return true;
            }
        }
        return false;
    }
    @Override
    public void getRenew() {}
}