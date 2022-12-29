package crackpixel.murdermystery.Game;

import crackpixel.murdermystery.Main;
import org.bukkit.entity.Player;

public class GameHandler {

    private Main main;

    public void addMap(Main main, String name){
        this.main = main;
        main.getConfig().createSection("maps." + name);
        main.getConfig().createSection("maps." + name + ".spawns");
        main.getConfig().createSection("maps." + name + ".nbspawns");
        main.getConfig().set("maps." + name + ".nbspawns", 0);
        main.saveConfig();
    }

    public int getSpawn(Main main, String name){
        this.main = main;
        return main.getConfig().getInt("maps." + name + ".nbspawns");
    }

    public void addSpawn(Main main, String mapname, int spawnName, double x, double y, double z){
        this.main = main;
        main.getConfig().set("maps." + mapname + ".nbspawns", getSpawn(main, mapname) + 1);
        main.getConfig().createSection("maps." + mapname + ".spawns." + spawnName + ".x");
        main.getConfig().createSection("maps." + mapname + ".spawns." + spawnName + ".y");
        main.getConfig().createSection("maps." + mapname + ".spawns." + spawnName + ".z");

        main.getConfig().set("maps." + mapname + ".spawns." + spawnName + ".x", x);
        main.getConfig().set("maps." + mapname + ".spawns." + spawnName + ".y", y);
        main.getConfig().set("maps." + mapname + ".spawns." + spawnName + ".z", z);
        main.saveConfig();
    }

}
