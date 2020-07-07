package card;

import com.google.gson.Gson;
import file.FileAssistance;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Card {
    final protected String cardName;
    final protected String cardType;
    final protected String cardClass;
    final protected String cardRarity;
    final protected String cardShowName;
    final protected String cardDescription;
    final protected int cardCost;
    final protected int cardValue;
    private int cardHp;
    private int cardAttack;

    protected Card(Card newCard) {
        cardName = newCard.getCardName();
        cardType = newCard.getCardType();
        cardClass = newCard.getCardClass();
        cardRarity = newCard.getCardRarity();
        cardShowName = newCard.getCardShowName();
        cardDescription = newCard.getCardDescription();
        cardCost = newCard.getCardCost();
        cardValue = newCard.getCardValue();
        cardAttack = newCard.getCardAttack();
        cardHp = newCard.getCardHp();
    }

    public Card(ArrayList<String> strings, ArrayList<Integer> integers) {
        cardName = strings.get(0);
        cardType = strings.get(1);
        cardClass = strings.get(2);
        cardRarity = strings.get(3);
        cardShowName = strings.get(4);
        cardDescription = strings.get(5);
        cardCost = integers.get(0);
        cardValue = integers.get(1);
        cardHp = integers.get(2);
        cardAttack = integers.get(3);
    }

    public static Card loadCard(File myFile) {
        if (myFile == null) {
            System.out.println("CANT LOAD CARD");
            return null;
        }
        try {
            FileReader myReader = new FileReader(myFile);
            Gson gson = new Gson();
            Card newCard = gson.fromJson(myReader, Card.class);
            return new Card(newCard);
        } catch (FileNotFoundException e) {
            System.out.println("NOO");
        }
        return null;
    }

    static public ArrayList<Card> getAllCards() {
        ArrayList<Card> allCards = new ArrayList<Card>();
        File[] contents = FileAssistance.getFolder("cards").listFiles();
        for (File content : contents)
            allCards.add(loadCard(content));
        return allCards;
    }

    public String printCardString() {
        String out = getCardName();
        for (int i = 1; i <= 25 - getCardName().length(); i++)
            out += " ";
        out += "ManaCost: " + getCardCost() + "   Class: " + getCardClass();
        return out;
    }

    public String getCardName() {
        return cardName;
    }

    public String getCardType() {
        return cardType;
    }

    public String getCardClass() {
        return cardClass;
    }

    public String getCardRarity() {
        return cardRarity;
    }

    public String getCardShowName() {
        return cardShowName;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public int getCardCost() {
        return cardCost;
    }

    public int getCardValue() {
        return cardValue;
    }

    public int getCardHp() {
        return cardHp;
    }

    public void setCardHp(int hp) {
        cardHp = hp;
    }

    public int getCardAttack() {
        return cardAttack;
    }

    public void setCardAttack(int attack) {
        cardAttack = attack;
    }
}