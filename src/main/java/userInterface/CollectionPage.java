package userInterface;

import card.Card;
import constants.GraphicConstants;
import constants.LogicConstants;
import module.Deck;
import module.Hero;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CollectionPage extends StatePanel {
    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Deck> decks = new ArrayList<>();

    private int manaFilter = -1;
    private int cardShowStart = 0;
    private String searchName = "";
    private String containFilter = "All Cards";
    private String heroFilter = LogicConstants.NEUTRAL_CARD;

    private Card showCard = null;
    private Deck showDeck = null;


    private ArrayList<CollectionButton> cardButtons;
    private ArrayList<DeckButton> deckButtons;


    public CollectionPage() {
        init();
    }

    private void init() {
        manaFilter = -1;
        cardShowStart = 0;
        searchName = "";
        containFilter = "All Cards";
        heroFilter = LogicConstants.NEUTRAL_CARD;

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
        initializeCards();
    }

    private void setHeroFilters() {
        ArrayList<String> names = Hero.getAllClasses();
        for (int i = 0; i < names.size(); i++) {
            MyButton myButton = addMyButton(names.get(i), names.get(i), GraphicConstants.COLLECTION_HEROFILTER_FIRST_X +
                            i * GraphicConstants.COLLECTION_HEROFILTER_SEPARATOR,
                    GraphicConstants.COLLECTION_HEROFILTER_FIRST_Y, GraphicConstants.COLLECTION_HEROFILTER_WIDTH,
                    GraphicConstants.COLLECTION_HEROFILTER_HEIGHT);
            myButton.setForeground(Color.ORANGE.darker());
            myButton.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent event) {
                    if (myButton.getText().equals(heroFilter))
                        return;
                    heroFilter = myButton.getText();
                    cardShowStart = 0;
                    initializeCards();
                }
            });
        }
    }

    private void setManaFilters() {
        for (int i = -1; i <= 10; i++) {
            MyButton myButton = addMyButton(Integer.toString(i), "Mana" + i,
                    GraphicConstants.COLLECTION_MANAFILTER_FIRST_X + i * GraphicConstants.COLLECTION_MANAFILTER_SEPARATOR,
                    GraphicConstants.COLLECTION_MANAFILTER_FIRST_Y, GraphicConstants.COLLECTION_MANAFILTER_WIDTH,
                    GraphicConstants.COLLECTION_MANAFILTER_HEIGHT);
            myButton.setForeground(Color.BLUE);

            myButton.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent event) {
                    if (Integer.parseInt(myButton.getText()) == manaFilter)
                        return;
                    manaFilter = Integer.parseInt(myButton.getText());
                    cardShowStart = 0;
                    initializeCards();
                }
            });
        }
    }

    private void setContainFilters() {
        setContainFilters("All Cards", 0);
        setContainFilters("Available Cards", GraphicConstants.COLLECTION_TYPEFILTER_SEPARATOR);
        setContainFilters("Locked Cards", 2 * GraphicConstants.COLLECTION_TYPEFILTER_SEPARATOR);
    }

    private void setContainFilters(String type, int y) {
        MyButton myButton = addMyButton(type, type, GraphicConstants.COLLECTION_TYPEFILTER_FIRST_X,
                GraphicConstants.COLLECTION_TYPEFILTER_FIRST_Y + y, GraphicConstants.COLLECTION_TYPEFILTER_WIDTH,
                GraphicConstants.COLLECTION_TYPEFILTER_HEIGHT);
        myButton.setForeground(Color.BLACK);

        myButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                if (type.equals(containFilter))
                    return;
                containFilter = myButton.getText();
                cardShowStart = 0;
                initializeCards();
            }
        });
    }

    private void setSearch() {
        HintTextField hintTextField = addMyTextFiled("Card Name", "CardName", GraphicConstants.COLLECTION_TYPEFILTER_FIRST_X,
                GraphicConstants.COLLECTION_TYPEFILTER_FIRST_Y + 3 * GraphicConstants.COLLECTION_TYPEFILTER_SEPARATOR,
                GraphicConstants.COLLECTION_TYPEFILTER_WIDTH, GraphicConstants.COLLECTION_TYPEFILTER_HEIGHT);

        MyButton myButton = addMyButton("SEARCH", "Search", GraphicConstants.COLLECTION_TYPEFILTER_FIRST_X,
                GraphicConstants.COLLECTION_TYPEFILTER_FIRST_Y + 4 * GraphicConstants.COLLECTION_TYPEFILTER_SEPARATOR,
                GraphicConstants.COLLECTION_TYPEFILTER_WIDTH, GraphicConstants.COLLECTION_TYPEFILTER_HEIGHT);

        myButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                searchName = hintTextField.getText();
                cardShowStart = 0;
                initializeCards();
                searchName = "";
            }
        });
    }

    private void setNextPage() {
        MyButton myButton = addMyButton("Next Page", "NextPage", GraphicConstants.COLLECTION_TYPEFILTER_FIRST_X,
                GraphicConstants.COLLECTION_TYPEFILTER_FIRST_Y + 5 * GraphicConstants.COLLECTION_TYPEFILTER_SEPARATOR + 20,
                GraphicConstants.COLLECTION_TYPEFILTER_WIDTH, GraphicConstants.COLLECTION_TYPEFILTER_HEIGHT);
        myButton.setForeground(Color.GREEN.darker());
        myButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                cardShowStart += 4;
                initializeCards();
            }
        });
    }

    private void setPrePage() {
        MyButton myButton = addMyButton("Previous Page", "PreviousPage", GraphicConstants.COLLECTION_TYPEFILTER_FIRST_X,
                GraphicConstants.COLLECTION_TYPEFILTER_FIRST_Y + 6 * GraphicConstants.COLLECTION_TYPEFILTER_SEPARATOR + 20,
                GraphicConstants.COLLECTION_TYPEFILTER_WIDTH, GraphicConstants.COLLECTION_TYPEFILTER_HEIGHT);
        myButton.setForeground(Color.GREEN.darker());
        myButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                cardShowStart -= 4;
                initializeCards();
            }
        });
    }

    private void setInitialButtons() {
        cardButtons = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            CollectionButton collectionButton = new CollectionButton(55 + GraphicConstants.STORE_BUTTON_FIRST_X +
                    i * GraphicConstants.STORE_BUTTON_X_SEPARATOR, 110 + GraphicConstants.STORE_BUTTON_FIRST_Y);

            collectionButton.getCardButton().addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent event) {
                    showCard = collectionButton.getCard();
                }
            });
            cardButtons.add(collectionButton);
            add(collectionButton.getCardButton());
        }
    }

    private void initializeCards() {
        ArrayList<Card> cards = new ArrayList<>();
        if (containFilter.equals("All Cards"))
            cards = Card.getAllCards();
        if (containFilter.equals("Available Cards"))
            cards = player.getPlayerCards();
        if (containFilter.equals("Locked Cards"))
            cards = player.getBuyCards();
        cards = applyFilters(cards);
        if (!searchName.equals(""))
            cards = player.searchName(searchName, cards);
        ShowCards(cards);
    }

    private ArrayList<Card> applyFilters(ArrayList<Card> cards) {
        ArrayList<Card> newCards = new ArrayList<>();
        for (int j = 0; j < 2; j++) {
            for (int k = 0; k < 11; k++) {
                for (Card card : cards) {
                    if (manaFilter != -1 && card.getCardCost() != manaFilter)
                        continue;
                    if (!heroFilter.equals("") && !card.getCardClass().equals(heroFilter))
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

    private void ShowCards(ArrayList<Card> cards) {
        for (CollectionButton collectionButton : cardButtons)
            collectionButton.setEmpty();
        if (cardShowStart >= cards.size()) {
            cardShowStart -= 4;
        }
        if (cardShowStart < 0) {
            cardShowStart = 0;
        }
        for (int i = 0; i < 4; i++) {
            if (cards.size() <= cardShowStart + i)
                break;
            cardButtons.get(i).setNewCard(cards.get(cardShowStart + i),
                    player.containCardInAllCards(cards.get(cardShowStart + i).getCardName()));
        }
        uiController.validate();
    }

    private void setDeckEssentials() {
        setDeckInitialButtons();
        showDeck();
        setNewDeck();
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
                        showDeck = deckButton.getDeck();
                    }
                });
                deckButtons.add(deckButton);
                add(deckButton);
            }
        }
    }

    private void setNewDeck() {
        MyButton myButton = addMyButton("CREATE NEW DECK", "NewDeck", GraphicConstants.COLLECTION_TYPEFILTER_FIRST_X,
                GraphicConstants.COLLECTION_TYPEFILTER_FIRST_Y + 8 * GraphicConstants.COLLECTION_TYPEFILTER_SEPARATOR,
                GraphicConstants.COLLECTION_TYPEFILTER_WIDTH, GraphicConstants.COLLECTION_TYPEFILTER_HEIGHT);
    }

    private void showDeck() {
        for (DeckButton deckButton : deckButtons)
            deckButton.setEmpty();
        decks = player.getPlayerAllDecks();
        for (int i = 0; i < 12; i++) {
            if (decks.size() == i)
                break;
            deckButtons.get(i).setNewDeck(decks.get(i));
        }
    }

    @Override
    public boolean runState() {
        uiController.setContentPane(this);

        while (true) {
            uiController.validate();
            if (newAction("Back")) {
                return true;
            }
            checkNewShowDeck();
            checkNewCreateDeck();
            checkNewShowCard();
        }
    }

    private void checkNewShowDeck() {
        if (showDeck != null) {
            uiController.changeState(this, new DeckPage(showDeck));
            showDeck = null;
        }
    }

    private void checkNewCreateDeck() {
        if (newAction("NewDeck")) {
            Deck newDeck = new Deck(player.getPlayerHeroes().get(0), new ArrayList<Card>(),
                    Integer.toString(player.getPlayerAllDecks().size()));
            player.addDeck(newDeck);
            uiController.changeState(this, new DeckPage(newDeck));
        }
    }

    private void checkNewShowCard() {
        if (showCard != null) {
            uiController.changeState(this, new ShowCard(showCard));
            showCard = null;
        }
    }

    @Override
    public void updateState() {
        init();
    }
}