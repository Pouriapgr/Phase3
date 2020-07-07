package userInterface;

import Constants.GraphicConstants;
import module.Deck;
import module.GameState;
import module.Player;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ChooseDeckPage extends StatePanel {
    private ArrayList<Deck> decks;

    private DeckButton showDeck;

    public ChooseDeckPage(Player player){
        init(player);
    }
    private void init(Player player) {
        this.player = player;
        decks = player.getPlayerAllDecks();
        showDeck = null;

        removeAll();

        setDecks();
        setPanel();
    }

    private void setDecks(){
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 2; i++) {
                if(2 * j + i >= decks.size())
                    return;
                DeckButton deckButton = new DeckButton();
                deckButton.setNewDeck(decks.get(2 * j + i));
                deckButton.setBounds(GraphicConstants.PLAY_DECK_FIRST_X + i *
                                GraphicConstants.PLAY_DECK_X_SEPARATOR,
                        GraphicConstants.PLAY_DECK_FIRST_Y + j * GraphicConstants.PLAY_DECK_Y_SEPARATOR,
                        GraphicConstants.PLAY_DECK_WIDTH, GraphicConstants.PLAY_DECK_HEIGHT);

                deckButton.addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent event) {
                        player.writeLog("Click", "DeckButton");
                        setShowDeck(deckButton);
                    }
                });
                add(deckButton);
            }
        }
    }

    @Override
    public boolean runState(){
        player.writeLog("Navigate", "ChooseDeck");
        ChooseDeckPage chooseDeckPage = this;
        gameJFrame.setContentPane(chooseDeckPage);

        while(true){
            gameJFrame.validate();
            if(chooseDeckPage.getGoBack()){
                return true;
            }
            checkSelectNewDeck(chooseDeckPage);
        }
    }
    private void checkSelectNewDeck(ChooseDeckPage chooseDeckPage){
        if(chooseDeckPage.getShowDeck() != null){
            if(!player.canPlayDeck(chooseDeckPage.getShowDeck().getDeck()).equals("YES")){
                chooseDeckPage.getShowDeck().setText(player.canPlayDeck(chooseDeckPage.getShowDeck().getDeck()));
                chooseDeckPage.getShowDeck().setForeground(Color.RED);
                TimeAssistance.waitFor(2000L);
                chooseDeckPage.getShowDeck().setText(chooseDeckPage.getShowDeck().getDeck().getDeckName() + " ( " +
                        chooseDeckPage.getShowDeck().getDeck().getDeckHero().getHeroName() + " )");
                chooseDeckPage.getShowDeck().setForeground(Color.black);

                gameJFrame.changeState(this, new DeckPage(chooseDeckPage.getShowDeck().getDeck(), player));
            }
            else{
                gameJFrame.changeState(this, new PassivesPage(new GameState(player, chooseDeckPage.getShowDeck().getDeck())));
            }
            chooseDeckPage.setShowDeck(null);
        }
    }

    @Override
    public void getRenew(){
        init(player);
    }
    
    public DeckButton getShowDeck() { return showDeck; }
    public void setShowDeck(DeckButton showDeck) { this.showDeck = showDeck; }
}