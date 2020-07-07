package file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import module.Hero;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HeroCreater {
    public HeroCreater(){
        Scanner sc = new Scanner(System.in);
        System.out.println("name:");
        String name = sc.nextLine();
        System.out.println("class:");
        String hclass = sc.nextLine();
        System.out.println("power:");
        String power = sc.nextLine();
        System.out.println("showname:");
        String showname = sc.nextLine();
        System.out.println("speciallpower:");
        String specialpower = sc.nextLine();
        Hero hero = new Hero(name,hclass,power,showname,specialpower);

        try {
            File myFile = FileAssistance.findHeroJSON(name);
            myFile.createNewFile();
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.setPrettyPrinting().create();
            FileWriter writer = new FileWriter(myFile);
            writer.write(gson.toJson(hero));
            writer.close();
        }
        catch (IOException e){
            System.out.println("CANT MAKE HERO JSON");
        }
    }
}
