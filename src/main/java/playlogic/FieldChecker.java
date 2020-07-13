package playlogic;


import playUI.PlayPage;
import playhero.Hunter;
import userInterface.MyButton;

import java.awt.*;

public class FieldChecker extends Thread {
    private PlayPage playPage;
    private GameState gameState = GameState.getInstance();

    public FieldChecker(PlayPage playPage) {
        this.playPage = playPage;
    }

    public void checkFields() {
        checkHeroHp();
        checkMana();
        checkHeroPower();
        checkDeckRemains();
        checkWeapons();
    }

    private void checkAndSet(MyButton myButton, String text) {
        if (myButton.getText().equals(text))
            return;
        myButton.setText(text);
    }


    private void checkAndSet(MyButton myButton, Color color) {
        if (myButton.getBackground().equals(color))
            return;
        myButton.setBackground(color);
    }

    private void checkWeapons() {
        if (gameState.getPlayer1().getWeaponCard() != null) {
            checkAndSet(playPage.getMyButton("1Weapon"), gameState.getPlayer1().getWeaponCard().getAttack() +
                    " / " + gameState.getPlayer1().getWeaponCard().getDurability());
            playPage.getMyButton("1Weapon").setVisible(true);
        }
        if (gameState.getPlayer2().getWeaponCard() != null) {
            checkAndSet(playPage.getMyButton("2Weapon"), gameState.getPlayer2().getWeaponCard().getAttack() +
                    " / " + gameState.getPlayer2().getWeaponCard().getDurability());
            playPage.getMyButton("2Weapon").setVisible(true);
        }
    }

    private void checkHeroHp() {
        checkAndSet(playPage.getMyButton("1HeroHp"), Integer.toString(gameState.getPlayer1().getDeck().getHero().getHp()));
        checkAndSet(playPage.getMyButton("2HeroHp"), Integer.toString(gameState.getPlayer2().getDeck().getHero().getHp()));
    }

    private void checkMana() {
        checkAndSet(playPage.getMyButton("1Mana"), gameState.getPlayer1().getManaToString());
        checkAndSet(playPage.getMyButton("2Mana"), gameState.getPlayer2().getManaToString());
    }

    private void checkHeroPower() {
        if (gameState.getPlayer1().getDeck().getHero() instanceof Hunter) {
            checkAndSet(playPage.getMyButton("1Skill"), Color.GRAY.brighter());
        } else {
            if (gameState.getPlayer1().getDeck().getHero().isUseSkill())
                checkAndSet(playPage.getMyButton("1Skill"), Color.YELLOW.darker());
            else
                checkAndSet(playPage.getMyButton("1Skill"), Color.GRAY.brighter());
        }

        if (gameState.getPlayer2().getDeck().getHero() instanceof Hunter) {
            checkAndSet(playPage.getMyButton("2Skill"), Color.GRAY.brighter());
        } else {
            if (gameState.getPlayer1().getDeck().getHero().isUseSkill())
                checkAndSet(playPage.getMyButton("2Skill"), Color.YELLOW.darker());
            else
                checkAndSet(playPage.getMyButton("2Skill"), Color.GRAY.brighter());
        }
    }

    private void checkDeckRemains() {
        checkAndSet(playPage.getMyButton("1Deck"), gameState.getPlayer1().getDeck().getCards().size() + " CARDS REMAINED");
        checkAndSet(playPage.getMyButton("2Deck"), gameState.getPlayer2().getDeck().getCards().size() + " CARDS REMAINED");
    }

    @Override
    public void run() {
        while (true) {
            checkFields();
            try {
                sleep(500);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
