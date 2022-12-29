package crackpixel.murdermystery;

import crackpixel.murdermystery.Commands.AdminCommands;
import crackpixel.murdermystery.Game.Roles;
import crackpixel.murdermystery.Testing.SendTitle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class Main extends JavaPlugin {

    public HashMap<UUID, Roles> players = new HashMap<>();

    @Override
    public void onEnable() {

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getCommand("sendtitle").setExecutor(new SendTitle());
        getCommand("murder-mystery").setExecutor(new AdminCommands(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
