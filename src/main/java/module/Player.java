package module;

import file.FileAssistance;
import Constants.LogicConstants;
import file.Log;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import com.google.gson.Gson;
import java.util.Collections;
import com.google.gson.GsonBuilder;

public class Player {
	final private String playerUsername;
	final private String playerPassword;
	final private PlayersId playerIdentification;

	private int playerId;
	private int playerCoins;
	
	private Deck playerCurrentDeck;
	private ArrayList<Deck> playerAllDecks = new ArrayList<>();
	private ArrayList<Hero> playerAllHeroes = new ArrayList<>();
	private ArrayList<Card> playerAllCards = new ArrayList<>();

	public static Player loadPlayer(File myFile){
		if(myFile == null){
			System.out.println("CANT LOAD PLAYER");
			return null;
		}
		try {
			FileReader myReader = new FileReader(myFile);
			Gson gson = new Gson();
			Player newPlayer = gson.fromJson(myReader, Player.class);
			return new Player(newPlayer);
		}
		catch (FileNotFoundException e){
			System.out.println("CANT FIND CARD FILE");
			return null;
		}
	}
	private Player(Player newPlayer){
        playerUsername = newPlayer.getPlayerUserName();
        playerPassword = newPlayer.getPlayerPassword();
        playerId = newPlayer.getPlayerId();
        playerIdentification = newPlayer.getPlayerIdentification();
        playerCoins = newPlayer.getPlayerCoins();
        playerAllDecks = newPlayer.getPlayerAllDecks();
        playerAllHeroes = newPlayer.getPlayerHeroes();
        playerAllCards = newPlayer.getPlayerCards();
	}
	public Player(String newPlayerUsername, String newPlayerPassword){
        playerUsername = newPlayerUsername;
        playerPassword = newPlayerPassword;
		AllPlayers allPlayers = AllPlayers.loadAllPlayers();
        playerId = allPlayers.getNumberOfAll() + 1;
        playerIdentification = new PlayersId(playerUsername, playerPassword, playerId);
		allPlayers.addAllPlayers(playerIdentification);

        playerCoins = LogicConstants.INITIAL_COINS;

		initializeNewPlayerHeroes();
		initializeNewPlayerCards();
		saveNewPlayerJSON();
		getPlayerlogFile();
	}

	private void initializeNewPlayerHeroes(){
		ArrayList<Hero> allHeroes = Hero.getAllHeroes();
		for(Hero hero: allHeroes)
            addHero(hero);
	}
	private void initializeNewPlayerCards(){
		ArrayList<Card> allCards = Card.getAllCards();
		Collections.shuffle(allCards);
		for (int i = 0; i < LogicConstants.numOfStartingCards; i++)
            addCard(allCards.get(i));
	}
	private void saveNewPlayerJSON(){
		try {
			File myFile = FileAssistance.findPlayerJSON(playerUsername);
			myFile.createNewFile();
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.setPrettyPrinting().create();
			FileWriter writer = new FileWriter(myFile);
			writer.write(gson.toJson(this));
			writer.close();
		}
		catch (IOException e){
			System.out.println("CANT MAKE NEW PLAYER FILE");
		}
	}
	private void updatePlayerJSON(){
		try {
			File myFile = FileAssistance.findPlayerJSON(playerUsername);
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.setPrettyPrinting().create();
			FileWriter writer = new FileWriter(myFile);
			writer.write(gson.toJson(this));
			writer.close();
		}
		catch (IOException e){
			System.out.println("CANT MAKE NEW PLAYER FILE");
		}
	}

	public boolean deletePlayer() {
		Scanner input = new Scanner(System.in);
		System.out.print("password: ");
		String password = input.nextLine();

		if(password.equals(playerPassword)) {
			Log.writeLogDeletion(getPlayerlogFile());
			AllPlayers allPlayers = AllPlayers.loadAllPlayers();
			allPlayers.deletePlayer(playerIdentification);
			File playerfile = getPlayerFile();
			System.gc();
			if(playerfile.delete()) {
				System.out.println("DELETED SUCCESSFULLY");
				return true;
			}
			else {
				System.out.println("CANT DELETE PLAYER");
				return false;
			}
		}
		else {
			Log.writeLog(getPlayerlogFile() , "invalid_delete" , "password:" + password);
			System.out.println("Wrong password , Please try again");
			return false;
		}
	}

	public boolean containHero(String name) {
		for(Hero hero : playerAllHeroes)
			if(name.equals(hero.toString()) || name.equals(hero.getHeroName()))
				return true;
		return false;
	}
	public boolean containCardInAllCards(String name) {
		for(Card card : playerAllCards)
			if(card.getCardName().equals(name))
				return true;
		return false;
	}

	public int countCardInDeck(String name, Deck deck) {
		int counter = 0;
		for(Card card : deck.getDeckCards())
			if(card.getCardName().equals(name))
				counter++;
		return counter;
	}

	public String canAddCardToDeck(String name, Deck deck) {
		if(deck.getDeckCards().size() >= LogicConstants.MAX_DECK_SIZE) {
			this.writeLog("Error", "MaxDeckSize");
			return "NO MORE ALLOWED";
		}
		else if(countCardInDeck(name, deck) >= LogicConstants.MAX_COUNT_CARD) {
			this.writeLog("Error", "MaxCountCard");
			return "MAX COPIES REACHED";
		}
		else {
			if(containCardInAllCards(name) == false) {
				this.writeLog("Error", "NoCard");
				return "NO SUCH CARD";
			}
			Card card = Card.loadCard(FileAssistance.findCardJSON(name));
			if(checkCardClass(card, deck.getDeckHero()))
				return "YES";
			this.writeLog("Error", "CardMismatch");
			return "CLASS MISMATCH";
		}
	}
	public String canRemoveCardFromDeck(String name, Deck deck) {
		if(countCardInDeck(name, deck) == 0) {
			this.writeLog("Error", "NoCard");
			System.out.println("You dont have this card in your current deck");
			return "NO SUCH CARD";
		}
		return "YES";
	}
	public String canChangeDeckName(String name){
		for(Deck deck: playerAllDecks)
			if(deck.getDeckName().equals(name)) {
				this.writeLog("Error", "NameUsed");
				return "NAME USED";
			}
		return "YES";
	}
	public String canChangeDeckHero(String name, Deck deck){
		Hero hero = Hero.loadHero(FileAssistance.findHeroJSON(name));
		if(hero == null) {
			this.writeLog("Error", "WrongName");
			return "NO SUCH HERO";
		}
		for(Card card: deck.getDeckCards()) {
			if (!card.getCardClass().equals(LogicConstants.NEUTRAL_CARD) && !card.getCardClass().equals(hero.getHeroClass())) {
				this.writeLog("Error", "CardMismatch");
				return "CARD MISMATCH";
			}
		}
		return "YES";
	}
	public String canPlayDeck(Deck deck){
		if(deck.getDeckCards().size() < LogicConstants.MIN_NUMBER_OF_DECK_CARDS){
			this.writeLog("Error", "BadDeck");
			return "NOT ENOUGH CARDS";
		}
		return "YES";
	}

	public boolean checkCardClass(Card card, Hero hero) {
		return card.getCardClass().equals(LogicConstants.NEUTRAL_CARD) || card.getCardClass().equals(hero.getHeroClass());
	}
	public boolean checkIfCardInAllDeck(String name) {
		for(Deck deck : playerAllDecks)
			if(checkIfCardInAnyDeck(name, deck))
				return true;
		return false;
	}
	public boolean checkIfCardInAnyDeck(String name, Deck deck){
		for(Card card : deck.getDeckCards())
			if(card.getCardName().equals(name))
				return true;
		return false;
	}

	public void changeDeckName(String name, Deck deck){
		for(Deck deck1: playerAllDecks){
			if(deck1.getDeckName().equals(deck.getDeckName()))
				deck1.setDeckName(name);
		}
		updatePlayerJSON();
	}
	public void changeDeckHero(String name, Deck deck){
		Hero hero = Hero.loadHero(FileAssistance.findHeroJSON(name));
		for(Deck deck1: playerAllDecks)
			if(deck.getDeckName().equals(deck1.getDeckName()))
				deck1.setDeckHero(hero);
		updatePlayerJSON();
	}
	public void removeCardFromDeck(Card card, Deck deck) {
		for(int i = 0 ; i < deck.getDeckCards().size() ; i++){
			if(deck.getDeckCards().get(i).getCardName().equals(card.getCardName())) {
				deck.getDeckCards().remove(i);
				break;
			}
		}
		updatePlayerJSON();
	}
	public void removeCardFromAllCards(Card card) {
		if(checkIfCardInAllDeck(card.getCardName())) {
			System.out.println("Card is in a deck");
			this.writeLog("Error","CardInDeck");
			return;
		}
		else {
			for(Card card1 : playerAllCards) {
				if (card1.getCardName().equals(card.getCardName())) {
					playerAllCards.remove(card1);
					break;
				}
			}
		}
		this.writeLog("RemoveCard", card.getCardName());
		updatePlayerJSON();
	}
	public void addCard(Card card) {
		this.writeLog("AddCard", card.getCardName());
		playerAllCards.add(card);
		updatePlayerJSON();
	}
	public void addCardToDeck(Card card, Deck deck) {
		deck.addCard(card);
		updatePlayerJSON();
	}
	public void addHero(Hero hero) {
        playerAllHeroes.add(hero);
		updatePlayerJSON();
	}
	public void addDeck(Deck newDeck){
		playerAllDecks.add(newDeck);
		int cnt = 0;
		for (Card card: playerAllCards) {
			if (cnt == 9)
				break;
			if (canAddCardToDeck(card.getCardName(), newDeck).equals("YES")) {
				newDeck.addCard(card);
				cnt++;
			}
		}
		updatePlayerJSON();
	}

	public ArrayList<Card> getSellCards(){
		ArrayList<Card> sellCards = new ArrayList<>();
		for(Card card: playerAllCards)
			if(!checkIfCardInAllDeck(card.getCardName()))
				sellCards.add(card);

		return sellCards;
	}
	public ArrayList<Card> getBuyCards(){
		ArrayList<Card> buyCards = new ArrayList<>();
		for(Card card: Card.getAllCards())
			if(!containCardInAllCards(card.getCardName()))
				buyCards.add(card);

		return buyCards;
	}

	public ArrayList<Card> searchName(String pattern, ArrayList<Card> cards){
		ArrayList<Card> ans = new ArrayList<>();
		for(Card card: Card.getAllCards()){
			String name = card.getCardName();
			if(name.contains(pattern)) {
				ans.add(card);
			}
		}
		return ans;
	}

	public void writeLog(String event, String description){
		Log.writeLog(getPlayerlogFile(), event, description);
	}

	public String getPlayerUserName() { return playerUsername; }
	public String getPlayerPassword() { return playerPassword; }
	public int getPlayerCoins() { return playerCoins; }
	public int getPlayerId() { return playerId; }
	public ArrayList<Card> getPlayerCards() { return playerAllCards; }
	public ArrayList<Hero> getPlayerHeroes() { return playerAllHeroes; }
	public ArrayList<Deck> getPlayerAllDecks() { return playerAllDecks; }
	public Deck getPlayerCurrentDeck() { return playerCurrentDeck; }
	public PlayersId getPlayerIdentification() { return playerIdentification;}
	public File getPlayerlogFile() {
		File myFile = new File(FileAssistance.getResources() + "/logs/" + playerUsername + "-" + playerId);
		try {
			if (!myFile.exists()) {
				myFile.createNewFile();
				Log.writeLogHeader(myFile, playerPassword, playerUsername);
			}
			return myFile;
		}
		catch (Exception e){
			System.out.println("CANT MAKE NEW LOG FILE;");
		}
		return null;
	}
	public File getPlayerFile(){
		return new File(FileAssistance.getResources() + "/players/" + playerUsername);
	}
	public void setPlayerCoins(int coins) {
		playerCoins = coins;
		ArrayList<String> a = new ArrayList<String>();
		updatePlayerJSON();
	}
}