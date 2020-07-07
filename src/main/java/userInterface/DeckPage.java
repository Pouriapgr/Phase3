package userInterface;

import module.Card;
import module.Deck;
import module.Player;
import file.FileAssistance;
import Constants.GraphicConstants;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class DeckPage extends StatePanel {
    private Deck deck;

    private String addCard = "";
    private String removeCard = "";
    private String changeName = "";
    private String changeHero = "";
    private JButton requestButton = null;

    public DeckPage(Deck deck, Player player){
        init(deck, player);
    }
    private void init(Deck deck, Player player) {
        this.deck = deck;
        this.player = player;

        addCard = "";
        removeCard = "";
        changeHero = "";
        changeName = "";
        requestButton = null;

        removeAll();

        setInfo();
        setCards();
        setAbility();
        setPanel();
    }

    private void setAbility() {
        setAddCard();
        setRemoveCard();
        setRename();
        setChangeHero();
    }
    private void setAddCard() {
        HintTextField hintTextField = new HintTextField("Name");
        hintTextField.setBounds(GraphicConstants.SHOW_DECK_ADDCARD_X , GraphicConstants.SHOW_DECK_ADDCARD_Y,
                GraphicConstants.SHOW_DECK_ADDCARD_WIDTH, GraphicConstants.SHOW_DECK_ADDCARD_HEIGHT);
        add(hintTextField);
        JButton jButton = new JButton("ADD CARD");
        jButton.setBounds(GraphicConstants.SHOW_DECK_ADDCARD_X , GraphicConstants.SHOW_DECK_ADDCARD_Y +
                        GraphicConstants.SHOW_DECK_ADDCARD_SEPARATOR,
                GraphicConstants.SHOW_DECK_ADDCARD_WIDTH, GraphicConstants.SHOW_DECK_ADDCARD_HEIGHT);
        jButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event){
                player.writeLog("Click", "AddButton");
                setAddCard(hintTextField.getText());
                setRequestButton(jButton);
            }});
        add(jButton);
    }
    private void setRemoveCard() {
        HintTextField hintTextField = new HintTextField("Name");
        hintTextField.setBounds(GraphicConstants.SHOW_DECK_ADDCARD_X , GraphicConstants.SHOW_DECK_ADDCARD_Y +
                        2 * GraphicConstants.SHOW_DECK_ADDCARD_SEPARATOR + 10,
                GraphicConstants.SHOW_DECK_ADDCARD_WIDTH, GraphicConstants.SHOW_DECK_ADDCARD_HEIGHT);
        add(hintTextField);
        JButton jButton = new JButton("REMOVE CARD");
        jButton.setBounds(GraphicConstants.SHOW_DECK_ADDCARD_X , GraphicConstants.SHOW_DECK_ADDCARD_Y +
                        3 * GraphicConstants.SHOW_DECK_ADDCARD_SEPARATOR + 10,
                GraphicConstants.SHOW_DECK_ADDCARD_WIDTH, GraphicConstants.SHOW_DECK_ADDCARD_HEIGHT);
        jButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event){
                player.writeLog("Click", "RemoveButton");
                setRemoveCard(hintTextField.getText());
                setRequestButton(jButton);
            }});
        add(jButton);
    }
    private void setRename() {
        HintTextField hintTextField = new HintTextField("Name");
        hintTextField.setBounds(GraphicConstants.SHOW_DECK_ADDCARD_X , GraphicConstants.SHOW_DECK_ADDCARD_Y +
                        4 * GraphicConstants.SHOW_DECK_ADDCARD_SEPARATOR + 20,
                GraphicConstants.SHOW_DECK_ADDCARD_WIDTH, GraphicConstants.SHOW_DECK_ADDCARD_HEIGHT);
        add(hintTextField);
        JButton jButton = new JButton("RENAME");
        jButton.setBounds(GraphicConstants.SHOW_DECK_ADDCARD_X , GraphicConstants.SHOW_DECK_ADDCARD_Y +
                        5 * GraphicConstants.SHOW_DECK_ADDCARD_SEPARATOR + 20,
                GraphicConstants.SHOW_DECK_ADDCARD_WIDTH, GraphicConstants.SHOW_DECK_ADDCARD_HEIGHT);
        jButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event){
                player.writeLog("Click", "RenameButton");
                setChangeName(hintTextField.getText());
                setRequestButton(jButton);
            }});
        add(jButton);
    }
    private void setChangeHero() {
        HintTextField hintTextField = new HintTextField("Name");
        hintTextField.setBounds(GraphicConstants.SHOW_DECK_ADDCARD_X , GraphicConstants.SHOW_DECK_ADDCARD_Y +
                        6 * GraphicConstants.SHOW_DECK_ADDCARD_SEPARATOR + 30,
                GraphicConstants.SHOW_DECK_ADDCARD_WIDTH, GraphicConstants.SHOW_DECK_ADDCARD_HEIGHT);
        add(hintTextField);
        JButton jButton = new JButton("CHANGE HERO");
        jButton.setBounds(GraphicConstants.SHOW_DECK_ADDCARD_X , GraphicConstants.SHOW_DECK_ADDCARD_Y +
                        7 * GraphicConstants.SHOW_DECK_ADDCARD_SEPARATOR + 30,
                GraphicConstants.SHOW_DECK_ADDCARD_WIDTH, GraphicConstants.SHOW_DECK_ADDCARD_HEIGHT);
        jButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event){
                player.writeLog("Click", "HeroButton");
                setChangeHero(hintTextField.getText());
                setRequestButton(jButton);
            }});
        add(jButton);
    }

    private void setInfo() {
        setHeroButton();
        setDeckName();
        setWinRate();
        setNoOfGames();
        setNoOfWins();
        setNoOfLosses();
        setAverageMana();
        setMostUsed();
    }
    private void setHeroButton() {
        JButton heroButton = new JButton();
        heroButton.setBounds(GraphicConstants.SHOW_DECK_HERO_X, GraphicConstants.SHOW_DECK_HERO_Y,
                GraphicConstants.SHOW_DECK_HERO_WIDTH, GraphicConstants.SHOW_DECK_HERO_HEIGHT);
        heroButton.setBorder(null);
        heroButton.setContentAreaFilled(false);
        heroButton.setIcon(new ImageIcon(FileAssistance.getScaledImage("heroes",
                deck.getDeckHero().getHeroName() + ".png", GraphicConstants.SHOW_DECK_HERO_WIDTH,
                GraphicConstants.SHOW_DECK_HERO_HEIGHT)));
        add(heroButton);
    }
    private void setDeckName() {
        JButton jButton = new JButton(deck.getDeckName());
        jButton.setForeground(Color.GRAY);
        jButton.setBounds(GraphicConstants.SHOW_DECK_NAME_X, GraphicConstants.SHOW_DECK_NAME_Y,
                GraphicConstants.SHOW_DECK_NAME_WIDTH, GraphicConstants.SHOW_DECK_NAME_HEIGHT);
        add(jButton);
    }
    private void setWinRate() {
        JButton jButton = new JButton();
        if(deck.getDeckNoOfGames() > 0) {
            jButton.setForeground(Color.GREEN);
            jButton.setText("WINRATE : " +
                    deck.getDeckNoOfWins() / deck.getDeckNoOfGames() + "%");
        }
        else {
            jButton.setForeground(Color.RED);
            jButton.setText("WIN-RATE : 0%");
        }
        jButton.setBounds(GraphicConstants.SHOW_DECK_WINRATE_X, GraphicConstants.SHOW_DECK_WINRATE_Y,
                GraphicConstants.SHOW_DECK_WINRATE_WIDTH, GraphicConstants.SHOW_DECK_WINRATE_HEIGHT);
        add(jButton);
    }
    private void setNoOfGames() {
        JButton jButton = new JButton(deck.getDeckNoOfGames() + " Games played");
        jButton.setBounds(GraphicConstants.SHOW_DECK_GAMES_X, GraphicConstants.SHOW_DECK_GAMES_Y,
                GraphicConstants.SHOW_DECK_GAMES_WIDTH, GraphicConstants.SHOW_DECK_GAMES_HEIGHT);
        add(jButton);
    }
    private void setNoOfWins() {
        JButton jButton = new JButton(deck.getDeckNoOfWins() + " Games won");
        jButton.setForeground(Color.GREEN);
        jButton.setBounds(GraphicConstants.SHOW_DECK_WINS_X, GraphicConstants.SHOW_DECK_WINS_Y,
                GraphicConstants.SHOW_DECK_WINS_WIDTH, GraphicConstants.SHOW_DECK_WINS_HEIGHT);
        add(jButton);
    }
    private void setNoOfLosses() {
        JButton jButton = new JButton(deck.getDeckNoOfLosses() + " Games lost");
        jButton.setForeground(Color.RED);
        jButton.setBounds(GraphicConstants.SHOW_DECK_LOSSES_X, GraphicConstants.SHOW_DECK_LOSSES_Y,
                GraphicConstants.SHOW_DECK_LOSSES_WIDTH, GraphicConstants.SHOW_DECK_LOSSES_HEIGHT);
        add(jButton);
    }
    private void setAverageMana() {
        JButton jButton = new JButton("Avg Mana  : " + deck.getAverageCost());
        jButton.setForeground(Color.BLUE);
        jButton.setBounds(GraphicConstants.SHOW_DECK_AVERAGE_X, GraphicConstants.SHOW_DECK_AVERAGE_Y,
                GraphicConstants.SHOW_DECK_AVERAGE_WIDTH, GraphicConstants.SHOW_DECK_AVERAGE_HEIGHT);
        add(jButton);
    }
    private void setMostUsed() {
        JButton jButton = new JButton("VIC  : Something");
        jButton.setForeground(Color.BLACK);
        jButton.setBounds(GraphicConstants.SHOW_DECK_MUSED_X, GraphicConstants.SHOW_DECK_MUSED_Y,
                GraphicConstants.SHOW_DECK_MUSED_WIDTH, GraphicConstants.SHOW_DECK_MUSED_HEIGHT);
        add(jButton);
    }

    private void setCards() {
        ArrayList<Card> cards = deck.getDeckCards();
        for(int i = 0; i < cards.size(); i++){
            Card card = cards.get(i);
            JButton jButton = new JButton(card.printCardString());
            jButton.setForeground(Color.BLACK);
            jButton.setBounds(GraphicConstants.SHOW_DECK_CARD_FIRST_X, GraphicConstants.SHOW_DECK_CARD_FIRST_Y +
                            i * GraphicConstants.SHOW_DECK_CARD_SEPARATOR, GraphicConstants.SHOW_DECK_CARD_WIDTH
                    , GraphicConstants.SHOW_DECK_CARD_HEIGHT);
            add(jButton);
        }
    }

    @Override
    public boolean runState() {
        player.writeLog("Navigate", "Deck");
        DeckPage deckPage = this;
        gameJFrame.setContentPane(deckPage);

        while (true) {
            gameJFrame.validate();
            if(deckPage.getGoBack()){
                return true;
            }

            checkNewRemoveCard(deckPage);
            checkNewAddCard(deckPage);
            checkChangeName(deckPage);
            checkChangeHero(deckPage);
        }
    }
    private void checkChangeHero(DeckPage deckPage) {
        if(!deckPage.getChangeHero().equals("")){
            if(player.canChangeDeckHero(deckPage.getChangeHero(), deck).equals("YES")){
                player.changeDeckHero(deckPage.getChangeHero(), deck);
                deckPage.getRenew();
                gameJFrame.setContentPane(deckPage);
            }
            else{
                JButton jButton = deckPage.getRequestButton();
                jButton.setText(player.canChangeDeckHero(deckPage.getChangeHero(), deck));
                jButton.setForeground(Color.red);
                TimeAssistance.waitFor(2000L);
                jButton.setText("CHANGE HERO");
                jButton.setForeground(Color.black);
            }
            deckPage.setRequestButton(null);
            deckPage.setChangeHero("");
        }
    }
    private void checkChangeName(DeckPage deckPage) {
        if(!deckPage.getChangeName().equals("")){
            if(player.canChangeDeckName(deckPage.getChangeName()).equals("YES")){
                player.changeDeckName(deckPage.getChangeName(), deck);
                deckPage.getRenew();
                gameJFrame.setContentPane(deckPage);
            }
            else{
                JButton jButton = deckPage.getRequestButton();
                jButton.setText(player.canChangeDeckName(deckPage.getChangeName()));
                jButton.setForeground(Color.red);
                TimeAssistance.waitFor(2000L);
                jButton.setText("RENAME");
                jButton.setForeground(Color.black);
            }
            deckPage.setRequestButton(null);
            deckPage.setChangeName("");
        }
    }
    private void checkNewRemoveCard(DeckPage deckPage) {
        if(!deckPage.getRemoveCard().equals("")){
            if(player.canRemoveCardFromDeck(deckPage.getRemoveCard(), deck).equals("YES")) {
                player.removeCardFromDeck(Card.loadCard(FileAssistance.findCardJSON(deckPage.getRemoveCard())), deck);
                deckPage.getRenew();
                gameJFrame.setContentPane(deckPage);
            }
            else {
                JButton jButton = deckPage.getRequestButton();
                jButton.setText(player.canRemoveCardFromDeck(deckPage.getRemoveCard(), deck));
                jButton.setForeground(Color.red);
                TimeAssistance.waitFor(2000L);
                jButton.setText("REMOVE CARD");
                jButton.setForeground(Color.black);
            }
            deckPage.setRemoveCard("");
            deckPage.setRequestButton(null);
        }
    }
    private void checkNewAddCard(DeckPage deckPage) {
        if(!deckPage.getAddCard().equals("")){
            if(player.canAddCardToDeck(deckPage.getAddCard(), deck).equals("YES")) {
                player.addCardToDeck(Card.loadCard(FileAssistance.findCardJSON(deckPage.getAddCard())), deck);
                deckPage.getRenew();
                gameJFrame.setContentPane(deckPage);
            }
            else {
                JButton jButton = deckPage.getRequestButton();
                jButton.setText(player.canAddCardToDeck(deckPage.getAddCard(), deck));
                jButton.setForeground(Color.red);
                TimeAssistance.waitFor(2000L);
                jButton.setText("ADD CARD");
                jButton.setForeground(Color.black);
            }
            deckPage.setRequestButton(null);
            deckPage.setAddCard("");
        }
    }

    @Override
    public void getRenew(){
        init(deck, player);
    }

    public String getAddCard() { return addCard; }
    public String getChangeHero() { return changeHero; }
    public String getRemoveCard() { return removeCard; }
    public String getChangeName() { return changeName; }
    public JButton getRequestButton() { return requestButton; }

    public void setAddCard(String addCard) { this.addCard = addCard; }
    public void setRemoveCard(String removeCard) { this.removeCard = removeCard; }
    public void setChangeName(String changeName) { this.changeName = changeName; }
    public void setChangeHero(String changeHero) { this.changeHero = changeHero; }
    public void setRequestButton(JButton requestButton) { this.requestButton = requestButton; }
}