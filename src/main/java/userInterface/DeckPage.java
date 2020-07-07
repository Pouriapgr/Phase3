package userInterface;

import card.Card;
import constants.GraphicConstants;
import file.FileAssistance;
import module.Deck;

import java.awt.*;
import java.util.ArrayList;

public class DeckPage extends StatePanel {
    private Deck deck;

    public DeckPage(Deck deck) {
        init(deck);
    }

    private void init(Deck deck) {
        this.deck = deck;

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
        addMyTextFiled("Name", "AddCard", GraphicConstants.SHOW_DECK_ADDCARD_X,
                GraphicConstants.SHOW_DECK_ADDCARD_Y, GraphicConstants.SHOW_DECK_ADDCARD_WIDTH,
                GraphicConstants.SHOW_DECK_ADDCARD_HEIGHT);
        addMyButton("ADD CARD", "AddCard", GraphicConstants.SHOW_DECK_ADDCARD_X,
                GraphicConstants.SHOW_DECK_ADDCARD_Y + GraphicConstants.SHOW_DECK_ADDCARD_SEPARATOR,
                GraphicConstants.SHOW_DECK_ADDCARD_WIDTH, GraphicConstants.SHOW_DECK_ADDCARD_HEIGHT);
    }

    private void setRemoveCard() {
        addMyTextFiled("Name", "RemoveCard", GraphicConstants.SHOW_DECK_ADDCARD_X,
                GraphicConstants.SHOW_DECK_ADDCARD_Y + 2 * GraphicConstants.SHOW_DECK_ADDCARD_SEPARATOR + 10,
                GraphicConstants.SHOW_DECK_ADDCARD_WIDTH, GraphicConstants.SHOW_DECK_ADDCARD_HEIGHT);
        addMyButton("REMOVE CARD", "RemoveCard", GraphicConstants.SHOW_DECK_ADDCARD_X,
                GraphicConstants.SHOW_DECK_ADDCARD_Y + 3 * GraphicConstants.SHOW_DECK_ADDCARD_SEPARATOR + 10,
                GraphicConstants.SHOW_DECK_ADDCARD_WIDTH, GraphicConstants.SHOW_DECK_ADDCARD_HEIGHT);
    }

    private void setRename() {
        addMyTextFiled("Name", "Rename", GraphicConstants.SHOW_DECK_ADDCARD_X,
                GraphicConstants.SHOW_DECK_ADDCARD_Y + 4 * GraphicConstants.SHOW_DECK_ADDCARD_SEPARATOR + 20,
                GraphicConstants.SHOW_DECK_ADDCARD_WIDTH, GraphicConstants.SHOW_DECK_ADDCARD_HEIGHT);
        addMyButton("RENAME", "Rename", GraphicConstants.SHOW_DECK_ADDCARD_X,
                GraphicConstants.SHOW_DECK_ADDCARD_Y + 5 * GraphicConstants.SHOW_DECK_ADDCARD_SEPARATOR + 20,
                GraphicConstants.SHOW_DECK_ADDCARD_WIDTH, GraphicConstants.SHOW_DECK_ADDCARD_HEIGHT);
    }

    private void setChangeHero() {
        addMyTextFiled("Name", "NewHero", GraphicConstants.SHOW_DECK_ADDCARD_X,
                GraphicConstants.SHOW_DECK_ADDCARD_Y + 6 * GraphicConstants.SHOW_DECK_ADDCARD_SEPARATOR + 30,
                GraphicConstants.SHOW_DECK_ADDCARD_WIDTH, GraphicConstants.SHOW_DECK_ADDCARD_HEIGHT);
        addMyButton("CHANGE HERO", "NewHero", GraphicConstants.SHOW_DECK_ADDCARD_X,
                GraphicConstants.SHOW_DECK_ADDCARD_Y + 7 * GraphicConstants.SHOW_DECK_ADDCARD_SEPARATOR + 30,
                GraphicConstants.SHOW_DECK_ADDCARD_WIDTH, GraphicConstants.SHOW_DECK_ADDCARD_HEIGHT);
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
        addMyButton("", "Hero", GraphicConstants.SHOW_DECK_HERO_X, GraphicConstants.SHOW_DECK_HERO_Y,
                GraphicConstants.SHOW_DECK_HERO_WIDTH, GraphicConstants.SHOW_DECK_HERO_HEIGHT, "heroes",
                deck.getDeckHero().getHeroName() + ".png");
    }

    private void setDeckName() {
        MyButton myButton = addMyButton(deck.getDeckName(), "DeckName", GraphicConstants.SHOW_DECK_NAME_X,
                GraphicConstants.SHOW_DECK_NAME_Y,
                GraphicConstants.SHOW_DECK_NAME_WIDTH, GraphicConstants.SHOW_DECK_NAME_HEIGHT);
        myButton.setForeground(Color.GRAY);
    }

    private void setWinRate() {
        String text;
        Color textColor;

        if (deck.getDeckNoOfGames() > 0) {
            textColor = Color.green;
            text = "WINRATE : " + deck.getDeckNoOfWins() / deck.getDeckNoOfGames() + "%";
        } else {
            textColor = Color.RED;
            text = "WIN-RATE : 0%";
        }
        MyButton myButton = addMyButton("", "WinRate", GraphicConstants.SHOW_DECK_WINRATE_X,
                GraphicConstants.SHOW_DECK_WINRATE_Y, GraphicConstants.SHOW_DECK_WINRATE_WIDTH,
                GraphicConstants.SHOW_DECK_WINRATE_HEIGHT);
        myButton.setText(text);
        myButton.setForeground(textColor);
    }

    private void setNoOfGames() {
        String text = deck.getDeckNoOfGames() + " Games played";
        addMyButton(text, "NoOfGames", GraphicConstants.SHOW_DECK_GAMES_X, GraphicConstants.SHOW_DECK_GAMES_Y,
                GraphicConstants.SHOW_DECK_GAMES_WIDTH, GraphicConstants.SHOW_DECK_GAMES_HEIGHT);
    }

    private void setNoOfWins() {
        String text = deck.getDeckNoOfWins() + " Games won";
        MyButton myButton = addMyButton(text, "NoOfWins", GraphicConstants.SHOW_DECK_WINS_X,
                GraphicConstants.SHOW_DECK_WINS_Y, GraphicConstants.SHOW_DECK_WINS_WIDTH,
                GraphicConstants.SHOW_DECK_WINS_HEIGHT);
        myButton.setForeground(Color.GREEN);
    }

    private void setNoOfLosses() {
        String text = deck.getDeckNoOfLosses() + " Games lost";
        MyButton myButton = addMyButton(text, "NoOfLosses", GraphicConstants.SHOW_DECK_LOSSES_X,
                GraphicConstants.SHOW_DECK_LOSSES_Y, GraphicConstants.SHOW_DECK_LOSSES_WIDTH,
                GraphicConstants.SHOW_DECK_LOSSES_HEIGHT);
        myButton.setForeground(Color.RED);
    }

    private void setAverageMana() {
        String text = "Avg Mana  : " + deck.getAverageCost();
        MyButton myButton = addMyButton(text, "AvgMana", GraphicConstants.SHOW_DECK_AVERAGE_X,
                GraphicConstants.SHOW_DECK_AVERAGE_Y, GraphicConstants.SHOW_DECK_AVERAGE_WIDTH,
                GraphicConstants.SHOW_DECK_AVERAGE_HEIGHT);
        myButton.setForeground(Color.BLUE);
    }

    private void setMostUsed() {
        String text = "VIC  : Something";
        MyButton myButton = addMyButton(text, "MostUsedCard", GraphicConstants.SHOW_DECK_MUSED_X,
                GraphicConstants.SHOW_DECK_MUSED_Y, GraphicConstants.SHOW_DECK_MUSED_WIDTH,
                GraphicConstants.SHOW_DECK_MUSED_HEIGHT);
        myButton.setForeground(Color.BLACK);
    }

    private void setCards() {
        ArrayList<Card> cards = deck.getDeckCards();
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            MyButton myButton = addMyButton(card.printCardString(), card.getCardName(),
                    GraphicConstants.SHOW_DECK_CARD_FIRST_X, GraphicConstants.SHOW_DECK_CARD_FIRST_Y +
                            i * GraphicConstants.SHOW_DECK_CARD_SEPARATOR, GraphicConstants.SHOW_DECK_CARD_WIDTH
                    , GraphicConstants.SHOW_DECK_CARD_HEIGHT);
            myButton.setForeground(Color.BLACK);
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

            checkNewRemoveCard();
            checkNewAddCard();
            checkChangeName();
            checkChangeHero();
        }
    }

    private void checkChangeHero() {
        if (newAction("NewHero")) {
            String heroName = getMyTextField("NewHero").getText();
            if (player.canChangeDeckHero(heroName, deck).equals("YES")) {
                player.changeDeckHero(heroName, deck);
                updateState();
                uiController.setContentPane(this);
            } else {
                MyButton myButton = getMyButton("NewHero");
                showError("CHANGE HERO", player.canChangeDeckHero(heroName, deck), myButton);
            }
            getMyTextField("NewHero").setText("");
        }
    }

    private void checkChangeName() {
        if (newAction("Rename")) {
            String newName = getMyTextField("Rename").getText();
            if (player.canChangeDeckName(newName).equals("YES")) {
                player.changeDeckName(newName, deck);
                updateState();
                uiController.setContentPane(this);
            } else {
                MyButton myButton = getMyButton("Rename");
                showError("RENAME", player.canChangeDeckName(newName), myButton);
            }
            getMyTextField("Rename").setText("");
        }
    }

    private void checkNewRemoveCard() {
        if (newAction("RemoveCard")) {
            String cardName = getMyTextField("RemoveCard").getText();
            if (player.canRemoveCardFromDeck(cardName, deck).equals("YES")) {
                player.removeCardFromDeck(Card.loadCard(FileAssistance.findCardJSON(cardName)), deck);
                updateState();
                uiController.setContentPane(this);
            } else {
                MyButton myButton = getMyButton("RemoveCard");
                showError("REMOVE CARD", player.canRemoveCardFromDeck(cardName, deck), myButton);

            }
            getMyTextField("RemoveCard").setText("");
        }
    }

    private void checkNewAddCard() {
        if (newAction("AddCard")) {
            String cardName = getMyTextField("AddCard").getText();
            if (player.canAddCardToDeck(cardName, deck).equals("YES")) {
                player.addCardToDeck(Card.loadCard(FileAssistance.findCardJSON(cardName)), deck);
                updateState();
                uiController.setContentPane(this);
            } else {
                MyButton myButton = getMyButton("AddCard");
                showError("ADD CARD", player.canAddCardToDeck(cardName, deck), myButton);
            }
            getMyTextField("AddCard").setText("");
        }
    }

    @Override
    public void updateState() {
        init(deck);
    }
}