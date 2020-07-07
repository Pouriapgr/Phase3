package file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import module.Passive;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PassiveCreater {
    public PassiveCreater(){
        Scanner sc = new Scanner(System.in);
        System.out.println("name:");
        String name = sc.nextLine();
        System.out.println("description:");
        String description = sc.nextLine();
        Passive passive = new Passive(name, description);

        try {
            File myFile = FileAssistance.findPassiveJSON(name);
            myFile.createNewFile();
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.setPrettyPrinting().create();
            FileWriter writer = new FileWriter(myFile);
            writer.write(gson.toJson(passive));
            writer.close();
        }
        catch (IOException e){
            System.out.println("CANT MAKE PASSIVE JSON");
        }
    }
}

