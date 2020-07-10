package module;

import com.google.gson.Gson;
import file.FileAssistance;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Passive {
    final private String passiveName;
    final private String description;

    private Passive(Passive newPassive) {
        passiveName = newPassive.getPassiveName();
        description = newPassive.getDescription();
    }

    public static Passive copy(Passive passive) {
        Passive newPassive = new Passive(passive);
        return newPassive;
    }

    public Passive(String passiveName, String description) {
        this.passiveName = passiveName;
        this.description = description;
    }

    public static Passive loadPassive(File myFile) {
        if (myFile == null) {
            System.out.println("CANT LOAD PASSIVE");
            return null;
        }
        try {
            FileReader myReader = new FileReader(myFile);
            Gson gson = new Gson();
            Passive newPassive = gson.fromJson(myReader, Passive.class);
            return new Passive(newPassive);
        } catch (FileNotFoundException e) {
            System.out.println("CANT FIND PASSIVE FILE");
            return null;
        }
    }

    public static ArrayList<Passive> getAllHPassives() {
        ArrayList<Passive> allPassives = new ArrayList<Passive>();
        File[] contents = FileAssistance.getFolder("passives").listFiles();
        for (File content : contents)
            allPassives.add(loadPassive(content));
        return allPassives;
    }

    public String getPassiveName() {
        return passiveName;
    }

    public String getDescription() {
        return description;
    }
}