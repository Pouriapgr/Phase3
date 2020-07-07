package userInterface;

import module.Deck;
import module.Player;
import Constants.GraphicConstants;

import java.util.ArrayList;

public class StatusPage extends StatePanel {
    private ArrayList<Deck> decks;
    private ArrayList<StatusButton> statusButtons;

    public StatusPage(Player player){
        init(player);
    }
    private void init(Player player) {
        this.player = player;
        decks = player.getPlayerAllDecks();

        removeAll();
        setDecks();

        setPanel();
    }

    private void setDecks() {
        statusButtons = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            if(i >= player.getPlayerAllDecks().size())
                break;
            Deck deck = player.getPlayerAllDecks().get(i);
            StatusButton statusButton = new StatusButton(GraphicConstants.STATUS_BUTTON_FIRST_X +
                    (i % 5) * GraphicConstants.STATUS_BUTTON_X_SEPARATOR, GraphicConstants.STATUS_BUTTON_FIRST_Y +
                    (i / 5) * GraphicConstants.STATUS_BUTTON_Y_SEPARATOR, deck, player);
            add(statusButton.getDeckName());
            add(statusButton.getHeroButton());
            statusButtons.add(statusButton);
        }
    }

    @Override
    public boolean runState() {
        player.writeLog("Navigate", "Status");
        StatusPage statusPage = this;
        gameJFrame.setContentPane(statusPage);

        while (true) {
            gameJFrame.validate();
            if(statusPage.getGoBack()){
                return true;
            }
            checkNewStatusReq();
        }
    }
    private boolean checkNewStatusReq() {
        StatusPage statusPage = this;
        ArrayList<StatusButton> statusButtons = statusPage.getStatusButtons();
        for(StatusButton statusButton: statusButtons){
            if(statusButton.getShowDeck()){
                gameJFrame.changeState(this, new DeckPage(statusButton.getDeck(), player));
                statusButton.setShowDeck(false);
                return true;
            }
        }
        return false;
    }

    @Override
    public void getRenew(){
        init(player);
    }
    public ArrayList<StatusButton> getStatusButtons() { return statusButtons; }
}