package module;

import com.google.gson.Gson;
import constants.LogicConstants;
import file.FileAssistance;
import playhero.*;
import playlogic.PlayerInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Hero {
    final private String heroName;
    final private String heroClass;
    final private String heroPower;
    final private String heroShowName;
    final private String heroSpecialPower;
    private int heroHp;
    private int heroAttack;

    private Hero(Hero newHero) {
        heroName = newHero.getHeroName();
        heroClass = newHero.getHeroClass();
        heroPower = newHero.getHeroPower();
        heroSpecialPower = newHero.getHeroSpecialPower();
        heroShowName = newHero.getHeroShowName();
        heroHp = 30;
        heroAttack = 0;
    }

    public Hero(String heroName, String heroClass, String heroPower, String heroShowName, String heroSpecialPower) {
        this.heroName = heroName;
        this.heroClass = heroClass;
        this.heroPower = heroPower;
        this.heroShowName = heroShowName;
        this.heroSpecialPower = heroSpecialPower;
        heroHp = 30;
        heroAttack = 0;
    }

    public static Hero loadHero(File myFile) {
        if (myFile == null) {
            System.out.println("CANT LOAD HERO");
            return null;
        }
        try {
            FileReader myReader = new FileReader(myFile);
            Gson gson = new Gson();
            Hero newHero = gson.fromJson(myReader, Hero.class);
            return new Hero(newHero);
        } catch (FileNotFoundException e) {
            System.out.println("CANT FIND CARD FILE");
            return null;
        }
    }

    public static ArrayList<Hero> getAllHeroes() {
        ArrayList<Hero> allHeroes = new ArrayList<Hero>();
        File[] contents = FileAssistance.getFolder("heroes").listFiles();
        for (File content : contents)
            allHeroes.add(loadHero(content));
        return allHeroes;
    }

    public static ArrayList<String> getAllClasses() {
        ArrayList<String> names = new ArrayList<>();
        names.add(LogicConstants.NEUTRAL_CARD);
        names.add("Mage");
        names.add("Rogue");
        names.add("Warlock");
        names.add("Paladin");
        names.add("Hunter");

        return names;
    }

    public PlayHero playCopy(PlayerInfo playerInfo) {
        if (heroClass.equals("Mage"))
            return new Mage(this, playerInfo);
        if (heroClass.equals("Rogue"))
            return new Rogue(this, playerInfo);
        if (heroClass.equals("Paladin"))
            return new Paladin(this, playerInfo);
        if (heroClass.equals("Warlock"))
            return new Warlock(this, playerInfo);
        if (heroClass.equals("Hunter"))
            return new Hunter(this, playerInfo);

        System.out.println(heroName);
        return null;
    }

    public String getHeroName() {
        return heroName;
    }

    public String getHeroClass() {
        return heroClass;
    }

    public String getHeroPower() {
        return heroPower;
    }

    public String getHeroShowName() {
        return heroShowName;
    }

    public String getHeroSpecialPower() {
        return heroSpecialPower;
    }

    public int getHeroHp() {
        return heroHp;
    }

    public void setHeroHp(int hp) {
        heroHp = hp;
    }

    public int getHeroAttack() {
        return heroAttack;
    }

    public void setHeroAttack(int attack) {
        heroAttack = attack;
    }
}
