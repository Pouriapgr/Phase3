package file;

import org.imgscalr.Scalr;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;

public class FileAssistance {

    public static File findCardJSON(String name){
        name += ".json";
        File[] files = getFolder("cards").listFiles();
        for (File file : files)
            if (file.getName().equals(name))
                return file;
        return new File(getResources() + "/cards/" + name);
    }
    public static File findHeroJSON(String name){
        name += ".json";
        File[] files = getFolder("heroes").listFiles();
        for (File file : files)
            if (file.getName().equals(name))
                return file;
        return new File(getResources() + "/heroes/" + name);
    }
    public static File findPassiveJSON(String name){
        name += ".json";
        File[] files = getFolder("passives").listFiles();
        for (File file : files)
            if (file.getName().equals(name))
                return file;
        return new File(getResources() + "/passives/" + name);
    }
    public static File findPlayerJSON(String name){
        name += ".json";
        File[] files = getFolder("players").listFiles();
        for (File file : files)
            if (file.getName().equals(name))
                return file;
        return new File(getResources() + "/players/" + name);
    }
    public static File getAllPlayersJSON(){
        File myFile = getFolder("players/allPlayers.json");
        return myFile;
    }

    public static BufferedImage getImage(String pack, String name){
        String destination = getResources() + "/assets/" + pack + "/" + name;
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File(destination));
            return bufferedImage;
        }
        catch (IOException e){
            System.out.println("CANT LOAD IMAGE" + name);
        }
        return null;
    }
    public static BufferedImage getScaledImage(String pack, String name,int width, int height){
        BufferedImage bufferedImage = getImage(pack, name);
        BufferedImageOp[] bufferedImageOp = new BufferedImageOp[0];
        BufferedImage scaledImage = Scalr.resize(bufferedImage, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_EXACT,
                width, height, bufferedImageOp);
        return scaledImage;
    }

    public static File getFolder(String name){ return new File(getResources() + "/" + name); }
    public static String getResources(){ return System.getProperty("user.dir") + "/src/main/resources"; }
}