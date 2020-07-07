package module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;
import file.FileAssistance;

import java.io.*;
import java.util.ArrayList;

public class PlayersAll {
    private ArrayList<PlayersId> allPlayers;
    private int numberOfAll;

    private PlayersAll(PlayersAll playersAll) {
        allPlayers = playersAll.getAllPlayers();
        numberOfAll = playersAll.getNumberOfAll();
    }

    public static PlayersAll loadAllPlayers() {
        File myFile = FileAssistance.getAllPlayersJSON();

        try {
            FileReader myReader = new FileReader(myFile);
            Gson gson = new Gson();
            PlayersAll newPlayersAll = gson.fromJson(myReader, PlayersAll.class);
            return newPlayersAll;
        } catch (FileNotFoundException e) {
            System.out.println("CANT FIND ALLPLAYERS FILE");
        }
        return null;
    }

    private void updateAllPlayersJSON(File myFile) {
        try {
            FileWriter fileWriter = new FileWriter(myFile);
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.setPrettyPrinting().create();
            JsonWriter jsonWriter = new JsonWriter(fileWriter);
            gson.toJson(this, PlayersAll.class, jsonWriter);
            jsonWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("CANT UPDATE ALLPLAYERS JSON");
        }
    }

    public boolean isValidUser(String username, String password) {
        for (PlayersId playerId : getAllPlayers())
            if (playerId.getPlayerUsername().equals(username) && playerId.getPlayerPassword().equals(password))
                return true;
        return false;
    }

    public boolean checkIfUsernameExist(String username) {
        for (PlayersId playerId : getAllPlayers())
            if (playerId.getPlayerUsername().equals(username))
                return true;
        return false;
    }

    public void addAllPlayers(PlayersId newId) {
        allPlayers.add(newId);
        setNumberOfAll(getNumberOfAll() + 1);
        updateAllPlayersJSON(FileAssistance.getAllPlayersJSON());
    }

    public void deletePlayer(PlayersId playersId) {
        for (PlayersId playersId1 : allPlayers) {
            if (playersId1.getPlayerId() == playersId.getPlayerId())
                allPlayers.remove(playersId1);
        }
        updateAllPlayersJSON(FileAssistance.getAllPlayersJSON());
    }

    public int getNumberOfAll() {
        return numberOfAll;
    }

    public void setNumberOfAll(int x) {
        numberOfAll = x;
    }

    public ArrayList<PlayersId> getAllPlayers() {
        return allPlayers;
    }
}