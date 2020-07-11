package playUI;

import constants.GraphicConstants;
import constants.LogicConstants;
import file.FileAssistance;
import userInterface.MyButton;
import userInterface.StatePanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayPage extends StatePanel {
    private ArrayList<HandButton> handButtonsPlayer1 = new ArrayList<>();
    private ArrayList<HandButton> handButtonsPlayer2 = new ArrayList<>();
    private ArrayList<PlayButton> playedButtonsPlayer1 = new ArrayList<>();
    private ArrayList<PlayButton> playedButtonsPlayer2 = new ArrayList<>();


    public PlayPage() {
    }

    public void setInitials() {
        setHandsInitials();
        setPlayedInitials();

        setWeapons();
        setEnd();
        setManas();
        setHeroes();
        setHeroesHps();
        setDecks();
        setSkills();

        setPanel();
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
            HandButton handButton = new HandButton(GraphicConstants.PLAY_HAND_BUTTON_FIRST_X +
                    i * GraphicConstants.PLAY_HAND_BUTTON_X_SEPARATOR, GraphicConstants.PLAY_HAND_BUTTON_FIRST_Y);
            handButton.setHover(+100);
//            handButton.setNewCard(Card.loadCard(FileAssistance.findCardJSON("Flare")));
            handButtonsPlayer1.add(handButton);

            add(handButton.getCardButton());
        }

        handButtonsPlayer2 = new ArrayList<>();
        for (int i = 0; i <= LogicConstants.MAX_HAND_CARDS; i++) {
            HandButton handButton = new HandButton(GraphicConstants.PLAY_HAND_BUTTON_FIRST_X +
                    i * GraphicConstants.PLAY_HAND_BUTTON_X_SEPARATOR, GraphicConstants.PLAY_HAND_BUTTON_SECOND_Y);
            handButton.setHover(-100);
//            handButton.setNewCard(Card.loadCard(FileAssistance.findCardJSON("Flare")));
            handButtonsPlayer2.add(handButton);
            add(handButton.getCardButton());
        }
    }

    private void setPlayedInitials() {
        playedButtonsPlayer1 = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            PlayButton playButton = new PlayButton(GraphicConstants.PLAY_MINION_BUTTON_X +
                    i * GraphicConstants.PLAY_MINION_BUTTON_SEPARATOR, GraphicConstants.PLAY_MINION_BUTTON_FIRST_Y);
//            playButton.setNewCard(Card.loadCard(FileAssistance.findCardJSON("Evasive Drakonid")));
            playedButtonsPlayer1.add(playButton);
            add(playButton.getCardButton());
        }

        playedButtonsPlayer2 = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            PlayButton playButton = new PlayButton(GraphicConstants.PLAY_MINION_BUTTON_X +
                    i * GraphicConstants.PLAY_MINION_BUTTON_SEPARATOR, GraphicConstants.PLAY_MINION_BUTTON_SECOND_Y);

//            playButton.setNewCard(Card.loadCard(FileAssistance.findCardJSON("Evasive Drakonid")));
            playedButtonsPlayer2.add(playButton);
            add(playButton.getCardButton());
        }
    }

    private void setHeroes() {
        addMyButton("", "1Hero", GraphicConstants.PLAY_HERO_BUTTON_FIRST_X,
                GraphicConstants.PLAY_HERO_BUTTON_FIRST_Y, GraphicConstants.PLAY_HERO_BUTTON_WIDTH,
                GraphicConstants.PLAY_HERO_BUTTON_HEIGHT);

        addMyButton("", "2Hero", GraphicConstants.PLAY_HERO_BUTTON_SECOND_X,
                GraphicConstants.PLAY_HERO_BUTTON_SECOND_Y, GraphicConstants.PLAY_HERO_BUTTON_WIDTH,
                GraphicConstants.PLAY_HERO_BUTTON_HEIGHT);

    }

    private void setHeroesHps() {
        MyButton myButton = addMyButton("", "1HeroHp", GraphicConstants.PLAY_HEROHP_BUTTON_FIRST_X,
                GraphicConstants.PLAY_HEROHP_BUTTON_FIRST_Y, GraphicConstants.PLAY_HEROHP_BUTTON_WIDTH,
                GraphicConstants.PLAY_HEROHP_BUTTON_HEIGHT);
        myButton.setBackground(Color.RED);

        myButton = addMyButton("", "2HeroHp", GraphicConstants.PLAY_HEROHP_BUTTON_SECOND_X,
                GraphicConstants.PLAY_HEROHP_BUTTON_SECOND_Y, GraphicConstants.PLAY_HEROHP_BUTTON_WIDTH,
                GraphicConstants.PLAY_HEROHP_BUTTON_HEIGHT);
        myButton.setBackground(Color.RED);
    }

    private void setManas() {
        MyButton myButton = addMyButton("", "1Mana", GraphicConstants.PLAY_MANA_BUTTON_FIRST_X,
                GraphicConstants.PLAY_MANA_BUTTON_FIRST_Y, GraphicConstants.PLAY_MANA_BUTTON_WIDTH,
                GraphicConstants.PLAY_MANA_BUTTON_HEIGHT);
        myButton.setText("5/5");
        myButton.setBackground(Color.blue.brighter());

        myButton = addMyButton("", "2Mana", GraphicConstants.PLAY_MANA_BUTTON_SECOND_X,
                GraphicConstants.PLAY_MANA_BUTTON_SECOND_Y, GraphicConstants.PLAY_MANA_BUTTON_WIDTH,
                GraphicConstants.PLAY_MANA_BUTTON_HEIGHT);
        myButton.setText("5/5");
        myButton.setBackground(Color.blue.brighter());
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
        myButton.setContentAreaFilled(true);
        myButton.setVisible(false);

        myButton = addMyButton("", "2Weapon", GraphicConstants.PLAY_WEAPON_BUTTON_SECOND_X,
                GraphicConstants.PLAY_WEAPON_BUTTON_SECOND_Y, GraphicConstants.PLAY_WEAPON_BUTTON_WIDTH,
                GraphicConstants.PLAY_WEAPON_BUTTON_HEIGHT);
        myButton.setContentAreaFilled(true);
        myButton.setVisible(false);
    }

    private void setSkills() {
        MyButton myButton = addMyButton("HERO POWER", "1Skill", GraphicConstants.PLAY_SKILL_BUTTON_FIRST_X,
                GraphicConstants.PLAY_SKILL_BUTTON_FIRST_Y, GraphicConstants.PLAY_SKILL_BUTTON_WIDTH,
                GraphicConstants.PLAY_SKILL_BUTTON_HEIGHT);
        myButton.setBackground(Color.YELLOW.darker());

        myButton = addMyButton("HERO POWER", "2Skill", GraphicConstants.PLAY_SKILL_BUTTON_SECOND_X,
                GraphicConstants.PLAY_SKILL_BUTTON_SECOND_Y, GraphicConstants.PLAY_SKILL_BUTTON_WIDTH,
                GraphicConstants.PLAY_SKILL_BUTTON_HEIGHT);
        myButton.setBackground(Color.YELLOW.darker());
    }

    private void setEnd() {
        MyButton myButton = addMyButton("END", "End", GraphicConstants.PLAY_FRAME_WIDTH - 103,
                GraphicConstants.PLAY_FRAME_HEIGHT / 2 - 35, 90, 30);
        myButton.setBackground(Color.ORANGE);
    }

    @Override
    public boolean runState() {
        return true;
    }

    @Override
    public void updateState() {
        return;
    }
}