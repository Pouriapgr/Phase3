package module;

import com.google.gson.Gson;
import file.FileAssistance;
import playcard.*;
import playlogic.PlayHandler;
import playlogic.PlayerInfo;

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
        for (int i = 1; i <= 35 - getCardName().length(); i++)
            out += " ";
        out += "ManaCost: " + getCardCost() + "   Class: " + getCardClass();
        return out;
    }

    public PlayCard playCopy(PlayerInfo playerInfo, PlayHandler playHandler) {
        String id = Long.toString(System.currentTimeMillis());

        if (cardShowName.equals("Arena Treasure Chest"))
            return new CardArenaTreasureChest((Minion) this, playerInfo, playHandler, id);
        if (cardShowName.equals("Ashbringer"))
            return new CardAshbringer((Weapon) this, playerInfo, playHandler, id);
        if (cardShowName.equals("Battle Axe"))
            return new CardBattleAxe((Weapon) this, playerInfo, playHandler, id);
        if (cardShowName.equals("Blessed Champion"))
            return new CardBlessedChampion((Spell) this, playerInfo, playHandler, id);
        if (cardShowName.equals("Blood Imp"))
            return new CardBloodImp((Minion) this, playerInfo, playHandler, id);
        if (cardShowName.equals("Book of Specters"))
            return new CardBookOfSpecters((Spell) this, playerInfo, playHandler, id);
        if (cardShowName.equals("Curio Collector"))
            return new CardCurioCollector((Minion) this, playerInfo, playHandler, id);
        if (cardShowName.equals("Divine Sweets"))
            return new CardDivineSweets((Spell) this, playerInfo, playHandler, id);
        if (cardShowName.equals("Dreadscale"))
            return new CardDreadscale((Minion) this, playerInfo, playHandler, id);
        if (cardShowName.equals("Elven Minstrel"))
            return new CardElvenMinstrel((Minion) this, playerInfo, playHandler, id);
        if (cardShowName.equals("Evasive Drakonid"))
            return new CardEvasiveDrakonid((Minion) this, playerInfo, playHandler, id);
        if (cardShowName.equals("Fiery Burst"))
            return new CardFieryBurst((Spell) this, playerInfo, playHandler, id);
        if (cardShowName.equals("Flare"))
            return new CardFlare((Spell) this, playerInfo, playHandler, id);
        if (cardShowName.equals("Forbidden Flame"))
            return new CardForbiddenFlame((Spell) this, playerInfo, playHandler, id);
        if (cardShowName.equals("Friendly Smith"))
            return new CardFriendlySmith((Spell) this, playerInfo, playHandler, id);
        if (cardShowName.equals("Gnomish Army Knife"))
            return new CardGnomishArmyKnife((Spell) this, playerInfo, playHandler, id);
        if (cardShowName.equals("Gnomish Inventor"))
            return new CardGnomishInventor((Minion) this, playerInfo, playHandler, id);
        if (cardShowName.equals("Learn Draconic"))
            return new CardLearnDraconic((Quest) this, playerInfo, playHandler, id);
        if (cardShowName.equals("Nightblade"))
            return new CardNightBlade((Minion) this, playerInfo, playHandler, id);
        if (cardShowName.equals("Octosari"))
            return new CardOctosari((Minion) this, playerInfo, playHandler, id);
        if (cardShowName.equals("Orb of the Untold"))
            return new CardOrbOfTheUntold((Spell) this, playerInfo, playHandler, id);
        if (cardShowName.equals("Party Elemental"))
            return new CardPartyElemental((Minion) this, playerInfo, playHandler, id);
        if (cardShowName.equals("Pharaoh's Blessing"))
            return new CardPharaohsBlessing((Spell) this, playerInfo, playHandler, id);
        if (cardShowName.equals("Poisoned Daggers"))
            return new CardPoisonedDaggers((Weapon) this, playerInfo, playHandler, id);
        if (cardShowName.equals("Polymorph"))
            return new CardPolymorph((Spell) this, playerInfo, playHandler, id);
        if (cardShowName.equals("Sathrovarr"))
            return new CardSathrovarr((Minion) this, playerInfo, playHandler, id);
        if (cardShowName.equals("Security Rover"))
            return new CardSecurityRover((Minion) this, playerInfo, playHandler, id);
        if (cardShowName.equals("Skydiving Instructor"))
            return new CardSkydivingInstructor((Minion) this, playerInfo, playHandler, id);
        if (cardShowName.equals("Sow the Seeds"))
            return new CardSowTheSeeds((Spell) this, playerInfo, playHandler, id);
        if (cardShowName.equals("Sprint"))
            return new CardSprint((Spell) this, playerInfo, playHandler, id);
        if (cardShowName.equals("Stone Fox Statue"))
            return new CardStoneFoxStatue((Spell) this, playerInfo, playHandler, id);
        if (cardShowName.equals("Stranglethorn Tiger"))
            return new CardStranglethornTiger((Minion) this, playerInfo, playHandler, id);
        if (cardShowName.equals("Strength in Numbers"))
            return new CardStrengthInNumbers((Quest) this, playerInfo, playHandler, id);
        if (cardShowName.equals("Swamp King Dred"))
            return new CardSwampKingDred((Minion) this, playerInfo, playHandler, id);
        if (cardShowName.equals("Swarm of Locusts"))
            return new CardSwarmOfLocusts((Spell) this, playerInfo, playHandler, id);
        if (cardShowName.equals("Tomb Warden"))
            return new CardTombWarden((Minion) this, playerInfo, playHandler, id);
        if (cardShowName.equals("Will of Mukla"))
            return new CardWillOfMukla((Spell) this, playerInfo, playHandler, id);
        System.out.println(cardShowName);
        return null;
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