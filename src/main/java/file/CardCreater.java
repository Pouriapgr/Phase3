package file;

import module.Minion;
import module.Spell;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import module.Weapon;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CardCreater {
    public CardCreater(){
        ArrayList<String> strings = new ArrayList<>();
        ArrayList<Integer> integers = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        System.out.println("name:");
        String name = sc.nextLine();
        System.out.println("type:");
        String type = sc.nextLine();
        System.out.println("class:");
        String cclass = sc.nextLine();
        System.out.println("rarity:");
        String rarity = sc.nextLine();
        System.out.println("showname:");
        String showname = sc.nextLine();
        System.out.println("description:");
        String description = sc.nextLine();
        System.out.println("cost:");
        int cost = sc.nextInt();
        System.out.println("value:");
        int value = sc.nextInt();
        strings.add(name);
        strings.add(type);
        strings.add(cclass);
        strings.add(rarity);
        strings.add(showname);
        strings.add(description);
        integers.add(cost);
        integers.add(value);


        if(type.equals("Minion")){
            System.out.println("hp:");
            int hp = sc.nextInt();
            System.out.println("attack:");
            int attack = sc.nextInt();
            integers.add(hp);
            integers.add(attack);

            Minion card = new Minion(strings, integers);
            try {
                File myFile = FileAssistance.findCardJSON(name);
                myFile.createNewFile();
                FileWriter writer = new FileWriter(myFile);
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.setPrettyPrinting().create();
                writer.write(gson.toJson(card));
                writer.close();
            }
            catch (IOException e){
                System.out.println("CANT MAKE CARD JSON");
            }
        }
        else if(type.equals("Spell") || type.equals("Quest")) {
            int hp = 0;
            int attack = 0;
            integers.add(hp);
            integers.add(attack);
            Spell card = new Spell(strings, integers);
            try {
                File myFile = FileAssistance.findCardJSON(name);
                myFile.createNewFile();
                FileWriter writer = new FileWriter(myFile);
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.setPrettyPrinting().create();
                writer.write(gson.toJson(card));
                writer.close();
            }
            catch (IOException e){
                System.out.println("CANT MAKE CARD JSON");
            }
        }
        else if(type.equals("Weapon")){
            System.out.println("durability:");
            int durability = sc.nextInt();
            System.out.println("attack:");
            int attack = sc.nextInt();
            integers.add(durability);
            integers.add(attack);

            Weapon card = new Weapon(strings, integers);
            try {
                File myFile = FileAssistance.findCardJSON(name);
                myFile.createNewFile();
                FileWriter writer = new FileWriter(myFile);
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.setPrettyPrinting().create();
                writer.write(gson.toJson(card));
                writer.close();
            }
            catch (IOException e){
                System.out.println("CANT MAKE CARD JSON");
            }
        }
    }
}