package module;

import file.FileAssistance;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.util.ArrayList;

public class AllPlayers {
    private ArrayList<PlayersId> allPlayers;
    private int numberOfAll;

    private AllPlayers(AllPlayers allPlayers){
        this.allPlayers = allPlayers.getAllPlayers();
        numberOfAll = allPlayers.getNumberOfAll();
    }
    public static AllPlayers loadAllPlayers(){
        File myFile = FileAssistance.getAllPlayersJSON();

        try {
            FileReader myReader = new FileReader(myFile);
            Gson gson = new Gson();
            AllPlayers newAllPlayers = gson.fromJson(myReader, AllPlayers.class);
            return newAllPlayers;
        }
        catch (FileNotFoundException e){
            System.out.println("CANT FIND ALLPLAYERS FILE");
        }
        return null;
    }
    private void updateAllPlayersJSON(File myFile){
        try {
            FileWriter fileWriter = new FileWriter(myFile);
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.setPrettyPrinting().create();
            JsonWriter jsonWriter = new JsonWriter(fileWriter);
            gson.toJson(this, AllPlayers.class, jsonWriter);
            jsonWriter.close();
            fileWriter.close();
        }
        catch (IOException e){
            System.out.println("CANT UPDATE ALLPLAYERS JSON");
        }
    }

    public  boolean isValidUser(String username, String password){
        for(PlayersId playerId : getAllPlayers())
            if (playerId.getPlayerUsername().equals(username) && playerId.getPlayerPassword().equals(password))
                return true;
        return false;
    }
    public boolean checkIfUsernameExist(String username){
        for(PlayersId playerId : getAllPlayers())
            if (playerId.getPlayerUsername().equals(username))
                return true;
        return false;
    }
    public void addAllPlayers(PlayersId newId){
        allPlayers.add(newId);
        setNumberOfAll(getNumberOfAll() + 1);
        updateAllPlayersJSON(FileAssistance.getAllPlayersJSON());
    }
    public void deletePlayer(PlayersId playersId){
        for (PlayersId playersId1: allPlayers){
            if (playersId1.getPlayerId() == playersId.getPlayerId())
                allPlayers.remove(playersId1);
        }
        updateAllPlayersJSON(FileAssistance.getAllPlayersJSON());
    }

    public int getNumberOfAll(){ return numberOfAll; }
    public ArrayList<PlayersId> getAllPlayers(){ return allPlayers; }

    public void setNumberOfAll(int x){ numberOfAll = x; }
}