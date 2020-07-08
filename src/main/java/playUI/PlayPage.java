package playUI;

import constants.GraphicConstants;
import file.FileAssistance;
import playlogic.GameState;
import userInterface.StatePanel;

import javax.swing.*;

public class PlayPage extends StatePanel {
    GameState gameState = GameState.getInstance();
//    private ArrayList<PlayButton> handButtons;
    //  private ArrayList<PlayButton> playedButtons;


    public PlayPage() {
//        handButtons = new ArrayList<>();
//        playedButtons = new ArrayList<>();

        setAdditionals();

//
//        showHand(gameState);
//        showPlayed(gameState);

        setPanel();
    }

    private void setAdditionals() {
        //    setHandInitials();
        //  setPlayedInitials();

//        setWeapons();
//        setEnds();
//        setManas();
        setHeroes();
//        setHeroHps();
//        setSkills();
//        setDecks();
    }

    @Override
    protected void setPanel() {
        setBack();
        setLayout(null);
        setBounds(0, 0, GraphicConstants.PLAY_FRAME_WIDTH, GraphicConstants.PLAY_FRAME_HEIGHT);
        setBackground();
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

    // private void setHandInitials() {
//        handButtons = new ArrayList<>();
//        for (int i = 0; i <= 5; i++) {
//            StoreButton jButton = new StoreButton(player, GraphicConstants.PLAY_HAND_BUTTON_FIRST_X +
//                    i * GraphicConstants.PLAY_HAND_BUTTON_X_SEPARATOR, GraphicConstants.PLAY_HAND_BUTTON_FIRST_Y, ""
//                    , false);
//            jButton.getCardButton().addMouseListener(new MouseAdapter() {
//                public void mousePressed(MouseEvent event) {
//                    if (jButton.getCard() != null) {
//                        jButton.setDoOpp(true);
//                    }
//                }
//            });
//            handButtons.add(jButton);
//            add(jButton.getCardButton());
//            //jButton.setNewCard(gameState.getFirstHand().get(7 - i), true);
//        }
    // }

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
        addMyButton("", "Hero1", GraphicConstants.PLAY_HERO_BUTTON_FIRST_X,
                GraphicConstants.PLAY_HERO_BUTTON_FIRST_Y, GraphicConstants.PLAY_HERO_BUTTON_WIDTH,
                GraphicConstants.PLAY_HERO_BUTTON_HEIGHT, "heroes",
                gameState.getPlayer1().getDeck().getDeckHero().getHeroName() + ".png");

        addMyButton("", "Hero2", GraphicConstants.PLAY_HERO_BUTTON_SECOND_X,
                GraphicConstants.PLAY_HERO_BUTTON_SECOND_Y, GraphicConstants.PLAY_HERO_BUTTON_WIDTH,
                GraphicConstants.PLAY_HERO_BUTTON_HEIGHT, "heroes",
                gameState.getPlayer2().getDeck().getDeckHero().getHeroName() + ".png");

    }

    //    private void setHeroHp(GameState gameState) {
//        JButton jButton = new JButton();
//        jButton.setBounds(GraphicConstants.PLAY_HEROHP_BUTTON_FIRST_X, GraphicConstants.PLAY_HEROHP_BUTTON_FIRST_Y,
//                GraphicConstants.PLAY_HEROHP_BUTTON_WIDTH, GraphicConstants.PLAY_HEROHP_BUTTON_HEIGHT);
//        jButton.setText(Integer.toString(gameState.getFirstHero().getHeroHp()));
//        jButton.setForeground(Color.RED);
//        add(jButton);
//        hpButton = jButton;
//    }
//
//    private void setMana(GameState gameState) {
//        JButton jButton = new JButton();
//        jButton.setBounds(GraphicConstants.PLAY_MANA_BUTTON_FIRST_X, GraphicConstants.PLAY_MANA_BUTTON_FIRST_Y,
//                GraphicConstants.PLAY_MANA_BUTTON_WIDTH, GraphicConstants.PLAY_MANA_BUTTON_HEIGHT);
//        jButton.setText(gameState.manaToMax());
//        jButton.setForeground(Color.BLUE);
//        add(jButton);
//        manaButton = jButton;
//    }
//
//    private void setDeckCards(GameState gameState) {
//        JButton jButton = new JButton();
//        jButton.setBounds(GraphicConstants.PLAY_DECK_BUTTON_FIRST_X, GraphicConstants.PLAY_DECK_BUTTON_FIRST_Y,
//                GraphicConstants.PLAY_DECK_BUTTON_WIDTH, GraphicConstants.PLAY_DECK_BUTTON_HEIGHT);
//        jButton.setText(gameState.getFirstDeck().size() + " CARDS REMAINED");
//        add(jButton);
//        deckButton = jButton;
//    }
//
//    private void setWeapon(GameState gameState) {
//        JButton jButton = new JButton();
//        jButton.setBounds(GraphicConstants.PLAY_WEAPON_BUTTON_FIRST_X, GraphicConstants.PLAY_WEAPON_BUTTON_FIRST_Y,
//                GraphicConstants.PLAY_WEAPON_BUTTON_WIDTH, GraphicConstants.PLAY_WEAPON_BUTTON_HEIGHT);
//        add(jButton);
//
//        if (gameState.getWeapon() == null) {
//            jButton.setContentAreaFilled(false);
//            jButton.setBorder(null);
//        } else {
//            jButton.setText(gameState.getWeapon().getCardHp() + "/" + gameState.getWeapon().getCardAttack());
//            jButton.setContentAreaFilled(true);
//        }
//    }
//
//    private void setSkill(GameState gameState) {
//        JButton jButton = new JButton();
//        jButton.setBounds(GraphicConstants.PLAY_SKILL_BUTTON_FIRST_X, GraphicConstants.PLAY_SKILL_BUTTON_FIRST_Y,
//                GraphicConstants.PLAY_SKILL_BUTTON_WIDTH, GraphicConstants.PLAY_SKILL_BUTTON_HEIGHT);
//        jButton.setText("HERO POWER");
//        add(jButton);
//    }
//
//    private void setEnd() {
//        JButton jButton = new JButton();
//        jButton.setBounds(GraphicConstants.FRAME_WIDTH - 103, 33, 90, 30);
//        jButton.setText("END");
//        jButton.setBackground(Color.ORANGE);
//        jButton.addMouseListener(new MouseAdapter() {
//            public void mousePressed(MouseEvent event) {
//                setEnd(true);
//            }
//        });
//        add(jButton);
//    }
//
//
//    private void showHand(GameState gameState) {
//        for (int i = 0; i <= gameState.getFirstHand().size() - 1; i++) {
//            if (handButtons.get(i).getCard() != null) {
//                if (handButtons.get(i).getCard().getCardName().equals(gameState.getFirstHand().get(i).getCardName()))
//                    continue;
//            }
//            handButtons.get(i).setNewCard(gameState.getFirstHand().get(i), true);
//        }
//        for (int i = gameState.getFirstHand().size(); i < 6; i++)
//            handButtons.get(i).setEmpty();
//    }
//
//    private void showPlayed(GameState gameState) {
//        for (int i = 0; i <= gameState.getFirstPlayed().size() - 1; i++) {
//            if (playedButtons.get(i).getCard() != null) {
//                if (playedButtons.get(i).getCard().getCardName().equals(gameState.getFirstPlayed().get(i).getCardName()))
//                    continue;
//            }
//            playedButtons.get(i).setNewCard(gameState.getFirstPlayed().get(i), true);
//            playedButtons.get(i).setBound(GraphicConstants.PLAY_MINION_BUTTON_WIDTH, GraphicConstants.PLAY_MINION_BUTTON_HEIGHT);
//        }
//        for (int i = gameState.getFirstPlayed().size(); i < 7; i++)
//            playedButtons.get(i).setEmpty();
//    }
//
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