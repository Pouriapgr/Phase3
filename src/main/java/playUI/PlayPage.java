package playUI;

import card.Card;
import constants.GraphicConstants;
import constants.LogicConstants;
import file.FileAssistance;
import playlogic.GameState;
import userInterface.MyButton;
import userInterface.StatePanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayPage extends StatePanel {
    GameState gameState = GameState.getInstance();
    private ArrayList<PlayButton> handButtonsPlayer1 = new ArrayList<>();
    private ArrayList<PlayButton> handButtonsPlayer2 = new ArrayList<>();
    private ArrayList<PlayButton> playedButtonsPlayer1 = new ArrayList<>();
    private ArrayList<PlayButton> playedButtonsPlayer2 = new ArrayList<>();


    public PlayPage() {
        setAdditionals();
        setPanel();
    }

    private void setAdditionals() {
        setHandsInitials();
        //setPlayedInitials();

        setWeapons();
        setEnd();
        setManas();
        setHeroes();
        setHeroesHps();
        setDecks();
        setSkills();
    }

    @Override
    protected void setPanel() {
        setBack();
        setLayout(null);
        setBounds(0, 0, GraphicConstants.PLAY_FRAME_WIDTH, GraphicConstants.PLAY_FRAME_HEIGHT);
        setBackground();
    }

    @Override
    protected void setBack() {
        MyButton myButton = addMyButton("Back", "Back",
                GraphicConstants.PLAY_FRAME_WIDTH - 103, 0, 90, 30);
        myButton.setBorder(null);
    }

    @Override
    protected void setBackground() {
        JLabel jLabel = new JLabel();
        jLabel.setBounds(0, 0, GraphicConstants.PLAY_FRAME_WIDTH, GraphicConstants.PLAY_FRAME_HEIGHT);
        ImageIcon imageIcon = new ImageIcon(FileAssistance.getScaledImage("background", "background.jpg",
                GraphicConstants.PLAY_FRAME_WIDTH, GraphicConstants.PLAY_FRAME_HEIGHT));
        jLabel.setIcon(imageIcon);
        add(jLabel);
    }

    private void setHandsInitials() {
        handButtonsPlayer1 = new ArrayList<>();
        for (int i = 0; i <= LogicConstants.MAX_HAND_CARDS; i++) {
            PlayButton jButton = new PlayButton(GraphicConstants.PLAY_HAND_BUTTON_FIRST_X +
                    i * GraphicConstants.PLAY_HAND_BUTTON_X_SEPARATOR, GraphicConstants.PLAY_HAND_BUTTON_FIRST_Y);
            jButton.setHover(+100);
            jButton.setNewCard(Card.loadCard(FileAssistance.findCardJSON("Flare")));
            handButtonsPlayer1.add(jButton);

            add(jButton.getCardButton());
        }

        handButtonsPlayer2 = new ArrayList<>();
        for (int i = 0; i <= LogicConstants.MAX_HAND_CARDS; i++) {
            PlayButton jButton = new PlayButton(GraphicConstants.PLAY_HAND_BUTTON_FIRST_X +
                    i * GraphicConstants.PLAY_HAND_BUTTON_X_SEPARATOR, GraphicConstants.PLAY_HAND_BUTTON_SECOND_Y);
            jButton.setHover(-100);
            jButton.setNewCard(Card.loadCard(FileAssistance.findCardJSON("Flare")));
            handButtonsPlayer2.add(jButton);
            add(jButton.getCardButton());
        }
    }

//        private void setPlayedInitials (GameState gameState){
//            playedButtons = new ArrayList<>();
//            for (int i = 0; i <= 6; i++) {
//                StoreButton jButton = new StoreButton(player, GraphicConstants.PLAY_MINION_BUTTON_X +
//                        i * GraphicConstants.PLAY_MINION_BUTTON_SEPARATOR, GraphicConstants.PLAY_MINION_BUTTON_Y, ""
//                        , false);
//                jButton.getCardButton().addMouseListener(new MouseAdapter() {
//                    public void mousePressed(MouseEvent event) {
//                        if (jButton.getCard() != null) {
//                        jButton.setDoOpp(true);
//                    }
//                }
//            });
//            playedButtons.add(jButton);
//            add(jButton.getCardButton());
//        }
//    }

    private void setHeroes() {
        addMyButton("", "1Hero", GraphicConstants.PLAY_HERO_BUTTON_FIRST_X,
                GraphicConstants.PLAY_HERO_BUTTON_FIRST_Y, GraphicConstants.PLAY_HERO_BUTTON_WIDTH,
                GraphicConstants.PLAY_HERO_BUTTON_HEIGHT, "heroes",
                gameState.getPlayer1().getDeck().getDeckHero().getHeroName() + ".png");

        addMyButton("", "2Hero", GraphicConstants.PLAY_HERO_BUTTON_SECOND_X,
                GraphicConstants.PLAY_HERO_BUTTON_SECOND_Y, GraphicConstants.PLAY_HERO_BUTTON_WIDTH,
                GraphicConstants.PLAY_HERO_BUTTON_HEIGHT, "heroes",
                gameState.getPlayer2().getDeck().getDeckHero().getHeroName() + ".png");

    }

    private void setHeroesHps() {
        MyButton myButton = addMyButton("", "1HeroHp", GraphicConstants.PLAY_HEROHP_BUTTON_FIRST_X,
                GraphicConstants.PLAY_HEROHP_BUTTON_FIRST_Y, GraphicConstants.PLAY_HEROHP_BUTTON_WIDTH,
                GraphicConstants.PLAY_HEROHP_BUTTON_HEIGHT);
        myButton.setText(Integer.toString(gameState.getPlayer1().getDeck().getDeckHero().getHeroHp()));
        myButton.setForeground(Color.RED);

        myButton = addMyButton("", "2HeroHp", GraphicConstants.PLAY_HEROHP_BUTTON_SECOND_X,
                GraphicConstants.PLAY_HEROHP_BUTTON_SECOND_Y, GraphicConstants.PLAY_HEROHP_BUTTON_WIDTH,
                GraphicConstants.PLAY_HEROHP_BUTTON_HEIGHT);
        myButton.setText(Integer.toString(gameState.getPlayer2().getDeck().getDeckHero().getHeroHp()));
        myButton.setForeground(Color.RED);
    }

    private void setManas() {
        MyButton myButton = addMyButton("", "1Mana", GraphicConstants.PLAY_MANA_BUTTON_FIRST_X,
                GraphicConstants.PLAY_MANA_BUTTON_FIRST_Y, GraphicConstants.PLAY_MANA_BUTTON_WIDTH,
                GraphicConstants.PLAY_MANA_BUTTON_HEIGHT);
        myButton.setText("5/5");
        myButton.setForeground(Color.BLUE);

        myButton = addMyButton("", "2Mana", GraphicConstants.PLAY_MANA_BUTTON_SECOND_X,
                GraphicConstants.PLAY_MANA_BUTTON_SECOND_Y, GraphicConstants.PLAY_MANA_BUTTON_WIDTH,
                GraphicConstants.PLAY_MANA_BUTTON_HEIGHT);
        myButton.setText("5/5");
        myButton.setForeground(Color.BLUE);
    }

    private void setDecks() {
        MyButton myButton = addMyButton("", "1Deck", GraphicConstants.PLAY_DECK_BUTTON_FIRST_X,
                GraphicConstants.PLAY_DECK_BUTTON_FIRST_Y, GraphicConstants.PLAY_DECK_BUTTON_WIDTH,
                GraphicConstants.PLAY_DECK_BUTTON_HEIGHT);
        myButton.setText(0 + " CARDS REMAINED");

        myButton = addMyButton("", "2Deck", GraphicConstants.PLAY_DECK_BUTTON_SECOND_X,
                GraphicConstants.PLAY_DECK_BUTTON_SECOND_Y, GraphicConstants.PLAY_DECK_BUTTON_WIDTH,
                GraphicConstants.PLAY_DECK_BUTTON_HEIGHT);
        myButton.setText(0 + " CARDS REMAINED");
    }

    private void setWeapons() {
        MyButton myButton = addMyButton("", "1Weapon", GraphicConstants.PLAY_WEAPON_BUTTON_FIRST_X,
                GraphicConstants.PLAY_WEAPON_BUTTON_FIRST_Y, GraphicConstants.PLAY_WEAPON_BUTTON_WIDTH,
                GraphicConstants.PLAY_WEAPON_BUTTON_HEIGHT);

        myButton = addMyButton("", "2Weapon", GraphicConstants.PLAY_WEAPON_BUTTON_SECOND_X,
                GraphicConstants.PLAY_WEAPON_BUTTON_SECOND_Y, GraphicConstants.PLAY_WEAPON_BUTTON_WIDTH,
                GraphicConstants.PLAY_WEAPON_BUTTON_HEIGHT);

    }

    private void setSkills() {
        MyButton myButton = addMyButton("HERO POWER", "1Skill", GraphicConstants.PLAY_SKILL_BUTTON_FIRST_X,
                GraphicConstants.PLAY_SKILL_BUTTON_FIRST_Y, GraphicConstants.PLAY_SKILL_BUTTON_WIDTH,
                GraphicConstants.PLAY_SKILL_BUTTON_HEIGHT);

        myButton = addMyButton("HERO POWER", "2Skill", GraphicConstants.PLAY_SKILL_BUTTON_SECOND_X,
                GraphicConstants.PLAY_SKILL_BUTTON_SECOND_Y, GraphicConstants.PLAY_SKILL_BUTTON_WIDTH,
                GraphicConstants.PLAY_SKILL_BUTTON_HEIGHT);
    }

    private void setEnd() {
        MyButton myButton = addMyButton("END", "End", GraphicConstants.PLAY_FRAME_WIDTH - 103,
                GraphicConstants.PLAY_FRAME_HEIGHT / 2 - 35, 90, 30);
        myButton.setBackground(Color.ORANGE);
    }

    @Override
    public boolean runState() {
//        gameJFrame.setContentPane(this);
//
//        while (true) {
//            gameJFrame.validate();
//            if (getGoBack()) {
//                return true;
//            }
//            checkShowHand();
//            checkShowPlayed();
//            checkEnd();
//        }
        uiController.setContentPane(this);
        while (true) {
            if (gameState.getPlayer1() == null)
                break;
        }
        return true;
    }
//
//    private void checkShowHand() {
//        for (StoreButton storeButton : handButtons) {
//            if (storeButton.isDoOpp()) {
//                gameJFrame.changeState(this, new ShowCard(storeButton.getCard(), player,
//                        "DO YOU WANT TO PLAY THIS", true, gameState));
//                showPlayed(gameState);
//                storeButton.setDoOpp(false);
//            }
//        }
//    }
//
//    private void checkShowPlayed() {
//        for (StoreButton storeButton : playedButtons) {
//            if (storeButton.isDoOpp()) {
//                gameJFrame.changeState(this, new ShowCard(storeButton.getCard(), player, "", false,
//                        //             gameState));
//                        //       storeButton.setDoOpp(false);
//            }
//        }
//    }
//
//    private void checkEnd() {
//        if (!end)
//            return;
//        setEnd(false);
//        gameState.nextTurn();
//        updateState();
//    }
//
//@Override
public void updateState() {
    System.out.println();
//        manaButton.setText(gameState.manaToMax());
//        hpButton.setText(Integer.toString(gameState.getFirstHero().getHeroHp()));
//        deckButton.setText(gameState.getFirstDeck().size() + " CARDS REMAINED");
//        showHand(gameState);
//        showPlayed(gameState);
}
//
//    public void setEnd(boolean end) {
//        this.end = end;
//    }
}