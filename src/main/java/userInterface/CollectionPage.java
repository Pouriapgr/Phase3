package userInterface;

import Constants.GraphicConstants;
import Constants.LogicConstants;
import module.Card;
import module.Deck;
import module.Hero;
import module.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CollectionPage extends StatePanel {
    private ArrayList<Card> cards;
    private ArrayList<Deck> decks;

    private int manaFilter = -1;
    private int cardShowStart = 0;
    private String searchName = "";
    private String containFilter = "All Cards";
    private String heroFilter = LogicConstants.NEUTRAL_CARD;

    private ArrayList<StoreButton> cardButtons;
    private ArrayList<DeckButton> deckButtons;

    private Card showCard = null;
    private Deck showDeck = null;
    private boolean newDeck = false;

    public CollectionPage(Player player){
        init(player);
    }
    private void init(Player player) {
        this.player = player;
        cards = new ArrayList<>();
        decks = new ArrayList<>();

        manaFilter = -1;
        cardShowStart = 0;
        searchName = "";
        containFilter = "All Cards";
        heroFilter = LogicConstants.NEUTRAL_CARD;

        newDeck = false;
        showDeck = null;
        showCard = null;

        removeAll();
        setCardEssentials();
        setDeckEssentials();

        setPanel();
    }

    private void setCardEssentials() {
        setHeroFilters();
        setManaFilters();
        setContainFilters();
        setSearch();
        setNextPage();
        setPrePage();

        setInitialButtons();
        showCard();
    }
    private void setDeckEssentials() {
        setDeckInitialButtons();
        showDeck();
        setNewDeck();
    }
    private void setHeroFilters() {
        ArrayList<String> names = Hero.getAllClasses();
        for(int i = 0; i < names.size(); i++){
            JButton jButton = new JButton(names.get(i));
            jButton.setForeground(Color.ORANGE.darker());
            jButton.setBounds(GraphicConstants.COLLECTION_HEROFILTER_FIRST_X +
                            i * GraphicConstants.COLLECTION_HEROFILTER_SEPARATOR,
                    GraphicConstants.COLLECTION_HEROFILTER_FIRST_Y, GraphicConstants.COLLECTION_HEROFILTER_WIDTH,
                    GraphicConstants.COLLECTION_HEROFILTER_HEIGHT);
            jButton.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent event){
                    if(jButton.getText().equals(getHeroFilter()))
                        return;
                    player.writeLog("Click", "HeroButton");
                    setHeroFilter(jButton.getText());
                    cardShowStart = 0;
                    showCard();
                }});
            add(jButton);
        }
    }
    private void setManaFilters() {
        for(int i = -1; i <= 10; i++){
            JButton jButton = new JButton(Integer.toString(i));
            jButton.setForeground(Color.BLUE);
            jButton.setBounds(GraphicConstants.COLLECTION_MANAFILTER_FIRST_X +
                            i * GraphicConstants.COLLECTION_MANAFILTER_SEPARATOR,
                    GraphicConstants.COLLECTION_MANAFILTER_FIRST_Y, GraphicConstants.COLLECTION_MANAFILTER_WIDTH,
                    GraphicConstants.COLLECTION_MANAFILTER_HEIGHT);
            jButton.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent event){
                    if(Integer.parseInt(jButton.getText()) == getManaFilter())
                        return;
                    player.writeLog("Click", "ManaButton");
                    setManaFilter(Integer.parseInt(jButton.getText()));
                    cardShowStart = 0;
                    showCard();
                }});
            add(jButton);
        }
    }

    private void setContainFilters() {
        setContainFilters("All Cards", 0);
        setContainFilters("Available Cards", GraphicConstants.COLLECTION_TYPEFILTER_SEPARATOR);
        setContainFilters("Locked Cards",2 * GraphicConstants.COLLECTION_TYPEFILTER_SEPARATOR);
    }
    private void setContainFilters(String type, int  y) {
        JButton jButton = new JButton(type);
        jButton.setForeground(Color.BLACK);
        jButton.setBounds(GraphicConstants.COLLECTION_TYPEFILTER_FIRST_X ,
                GraphicConstants.COLLECTION_TYPEFILTER_FIRST_Y + y, GraphicConstants.COLLECTION_TYPEFILTER_WIDTH,
                GraphicConstants.COLLECTION_TYPEFILTER_HEIGHT);
        jButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event){
                if(type.equals(getContainFilter()))
                    return;
                player.writeLog("Click", "ContainButton");
                setContainFilter(jButton.getText());
                cardShowStart = 0;
                showCard();
            }});
        add(jButton);
    }
    private void setSearch() {
        HintTextField hintTextField = new HintTextField("Card Name");
        hintTextField.setBounds(GraphicConstants.COLLECTION_TYPEFILTER_FIRST_X ,
                GraphicConstants.COLLECTION_TYPEFILTER_FIRST_Y + 3 * GraphicConstants.COLLECTION_TYPEFILTER_SEPARATOR,
                GraphicConstants.COLLECTION_TYPEFILTER_WIDTH, GraphicConstants.COLLECTION_TYPEFILTER_HEIGHT);
        add(hintTextField);
        JButton jButton = new JButton("SEARCH");
        jButton.setBounds(GraphicConstants.COLLECTION_TYPEFILTER_FIRST_X ,
                GraphicConstants.COLLECTION_TYPEFILTER_FIRST_Y + 4 * GraphicConstants.COLLECTION_TYPEFILTER_SEPARATOR,
                GraphicConstants.COLLECTION_TYPEFILTER_WIDTH, GraphicConstants.COLLECTION_TYPEFILTER_HEIGHT);
        jButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event){
                player.writeLog("Click", "SearchButton");
                setSearchName(hintTextField.getText());
                cardShowStart = 0;
                showCard();
                setSearchName("");
            }});
        add(jButton);
    }

    private void setNextPage() {
        JButton jButton = new JButton("Next Page");
        jButton.setForeground(Color.GREEN.darker());
        jButton.setBounds(GraphicConstants.COLLECTION_TYPEFILTER_FIRST_X ,
                GraphicConstants.COLLECTION_TYPEFILTER_FIRST_Y + 5 * GraphicConstants.COLLECTION_TYPEFILTER_SEPARATOR + 20,
                GraphicConstants.COLLECTION_TYPEFILTER_WIDTH, GraphicConstants.COLLECTION_TYPEFILTER_HEIGHT);
        jButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event){
                player.writeLog("Click", "NextButton");
                cardShowStart += 4;
                showCard();
            }});
        add(jButton);
    }
    private void setPrePage() {
        JButton jButton = new JButton("Previous Page");
        jButton.setForeground(Color.GREEN.darker());
        jButton.setBounds(GraphicConstants.COLLECTION_TYPEFILTER_FIRST_X ,
                GraphicConstants.COLLECTION_TYPEFILTER_FIRST_Y + 6 * GraphicConstants.COLLECTION_TYPEFILTER_SEPARATOR + 20,
                GraphicConstants.COLLECTION_TYPEFILTER_WIDTH, GraphicConstants.COLLECTION_TYPEFILTER_HEIGHT);
        jButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event){
                player.writeLog("Click", "PreButton");
                cardShowStart -= 4;
                showCard();
            }});
        add(jButton);
    }

    private void setInitialButtons() {
        cardButtons = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            StoreButton cardButton = new StoreButton(player, 55 + GraphicConstants.STORE_BUTTON_FIRST_X +
                    i * GraphicConstants.STORE_BUTTON_X_SEPARATOR, 110 + GraphicConstants.STORE_BUTTON_FIRST_Y,
                   "", false);
            cardButton.getCardButton().addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent event){
                    player.writeLog("Click", "CardButton");
                    setShowCard(cardButton.getCard());
                }});

            cardButtons.add(cardButton);
            add(cardButton.getCardButton());
        }
    }

    private void showCard() {
        ArrayList<Card> cards = new ArrayList<>();
        if(getContainFilter().equals("All Cards"))
            cards = Card.getAllCards();
        if(getContainFilter().equals("Available Cards"))
            cards = player.getPlayerCards();
        if(getContainFilter().equals("Locked Cards"))
            cards = player.getBuyCards();
        cards = doFilters(cards);
        if(!getSearchName().equals(""))
            cards = player.searchName(getSearchName(), cards);
        reallyShowCards(cards);
    }
    private ArrayList<Card> doFilters(ArrayList<Card> cards) {
        ArrayList<Card> newCards = new ArrayList<>();
        for(int j = 0; j < 2; j++) {
            for(int k = 0; k < 11; k++){
                for (Card card : cards) {
                    if (getManaFilter() != -1 && card.getCardCost() != getManaFilter())
                        continue;
                    if (!getHeroFilter().equals("") && !card.getCardClass().equals(getHeroFilter()))
                        continue;
                    if (j == 0 && player.containCardInAllCards(card.getCardName()) && card.getCardCost() == k)
                        newCards.add(card);
                    if (j == 1 && !player.containCardInAllCards(card.getCardName()) && card.getCardCost() == k)
                        newCards.add(card);
                }
            }
        }
        return newCards;
    }
    private void reallyShowCards(ArrayList<Card> cards) {
        for(StoreButton jButton: cardButtons)
            jButton.setEmpty();
        if(cardShowStart >= cards.size()){
            cardShowStart -= 4;
        }
        if(cardShowStart < 0){
            cardShowStart = 0;
        }
        for(int i = 0; i < 4; i++){
            if(cards.size() <= cardShowStart + i)
                break;
            cardButtons.get(i).setNewCard(cards.get(cardShowStart + i),
                    player.containCardInAllCards(cards.get(cardShowStart + i).getCardName()));
        }
        validate();
    }

    private void setDeckInitialButtons() {
        deckButtons = new ArrayList<>();
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < 2; i++) {
                DeckButton deckButton = new DeckButton();
                deckButton.setBounds(GraphicConstants.COLLECTION_DECK_FIRST_X + i *
                                GraphicConstants.COLLECTION_DECK_X_SEPARATOR,
                        GraphicConstants.COLLECTION_DECK_FIRST_Y + j * GraphicConstants.COLLECTION_DECK_Y_SEPARATOR,
                        GraphicConstants.COLLECTION_DECK_WIDTH, GraphicConstants.COLLECTION_DECK_HEIGHT);
                deckButton.addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent event) {
                        player.writeLog("Click", "DeckButton");
                        setShowDeck(deckButton.getDeck());
                    }
                });
                deckButtons.add(deckButton);
                add(deckButton);
            }
        }
    }
    private void setNewDeck(){
        JButton jButton = new JButton("CREATE NEW DECK");
        jButton.setBounds(GraphicConstants.COLLECTION_TYPEFILTER_FIRST_X ,
                GraphicConstants.COLLECTION_TYPEFILTER_FIRST_Y + 8 * GraphicConstants.COLLECTION_TYPEFILTER_SEPARATOR,
                GraphicConstants.COLLECTION_TYPEFILTER_WIDTH, GraphicConstants.COLLECTION_TYPEFILTER_HEIGHT);
        jButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event){
                player.writeLog("Click", "NewDeckButton");
                setNewDeck(true);
            }});
        add(jButton);
    }
    private void showDeck(){
        for (DeckButton deckButton: deckButtons)
            deckButton.setEmpty();
        decks = player.getPlayerAllDecks();
        for(int i = 0; i < 12; i++){
            if(decks.size() == i)
                break;
            deckButtons.get(i).setNewDeck(decks.get(i));
        }
    }

    @Override
    public boolean runState() {
        player.writeLog("Navigate", "Collections");
        CollectionPage collectionPage = this;
        gameJFrame.setContentPane(collectionPage);

        while (true) {
            gameJFrame.validate();
            if(collectionPage.getGoBack()){
                return true;
            }
            checkNewShowDeck(collectionPage);
            checkNewCreateDeck(collectionPage);
            checkNewShowCard(collectionPage);
        }
    }
    private void checkNewShowDeck(CollectionPage collectionPage) {
        if(collectionPage.getShowDeck() != null){
            gameJFrame.changeState(this, new DeckPage(collectionPage.getShowDeck(), player));
            collectionPage.setShowDeck(null);
            return;
        }
    }
    private void checkNewCreateDeck(CollectionPage collectionPage) {
        if(collectionPage.isNewDeck()){
            Deck newDeck = new Deck(player.getPlayerHeroes().get(0), new ArrayList<Card>(),
                    Integer.toString(player.getPlayerAllDecks().size()));
            player.addDeck(newDeck);
            gameJFrame.changeState(this, new DeckPage(newDeck, player));
            collectionPage.setNewDeck(false);
            return;
        }
    }
    private void checkNewShowCard(CollectionPage collectionPage){
        if(collectionPage.getShowCard() != null){
            if(player.containCardInAllCards(collectionPage.getShowCard().getCardName())) {
                gameJFrame.changeState(this, new ShowCardPage(collectionPage.getShowCard(), player, "", false, null));
            }
            else{
                gameJFrame.changeState(this, new ShowCardPage(collectionPage.getShowCard(), player,
                        "DO YOU WANT TO BUY THIS CARD", true, null));
            }
            collectionPage.setShowCard(null);
            return;
        }
    }

    @Override
    public void getRenew(){
        init(player);
    }

    public String getSearchName(){ return searchName; }
    public String getHeroFilter(){ return heroFilter; }
    public String getContainFilter(){ return containFilter; }
    public int getManaFilter(){ return manaFilter; }
    public Card getShowCard(){ return showCard; }
    public Deck getShowDeck(){ return showDeck; }
    public boolean isNewDeck(){ return newDeck;}

    public void setSearchName(String searchName){ this.searchName = searchName;}
    public void setHeroFilter(String heroFilter){ this.heroFilter = heroFilter; }
    public void setContainFilter(String containFilter){ this.containFilter = containFilter; }
    public void setManaFilter(int manaFilter){ this.manaFilter = manaFilter; }
    public void setShowCard(Card showCard){ this.showCard = showCard; }
    public void setShowDeck(Deck showDeck){ this.showDeck = showDeck; }
    public void setNewDeck(boolean newDeck){ this.newDeck = newDeck; }
}