package crackpixel.murdermystery.Commands;

import crackpixel.murdermystery.Game.GameHandler;
import crackpixel.murdermystery.Game.Roles;
import crackpixel.murdermystery.Items.MMItemsHandler;
import crackpixel.murdermystery.Main;
import crackpixel.murdermystery.Utils.Title;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.UUID;

public class AdminCommands implements CommandExecutor {

    private final Main main;
    private Roles Roles;

    public AdminCommands(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] a) {

        Player player = (Player) sender;
        Title title = new Title();
        GameHandler gh = new GameHandler();
        Roles r = Roles;
        if(sender instanceof Player && player.hasPermission("murdermystery.admin")){
            switch(a[0]){
                case "addmap":
                    gh.addMap(main, a[1].toLowerCase());
                    break;
                case "addspawn":
                    if(a[1] != null){
                        gh.addSpawn(main, a[1], main.getConfig().getInt("maps." + a[1] + ".nbspawns"), player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());
                    } else {
                        player.sendMessage("§cInclude a map name!");
                    }
                    break;
                case "start":
                    int randomness = (int) (Math.random() * 3);
                    MMItemsHandler mmih = new MMItemsHandler();
                    for(Player pl : Bukkit.getOnlinePlayers()){

                        main.players.put(pl.getPlayer().getUniqueId(), crackpixel.murdermystery.Game.Roles.INNOCENT);

                        main.players.replace(main.players.keySet().stream().findAny().get(), Roles.INNOCENT, Roles.MURDER);
                        pl.sendMessage("You are " + main.players.get(pl.getPlayer().getUniqueId()));
                        title.send(pl.getPlayer(), "§cYou are " + main.players.get(pl.getPlayer().getUniqueId()), "");
                        switch(main.players.get(pl.getPlayer().getUniqueId())){
                            case MURDER:
                                pl.getPlayer().getInventory().addItem(mmih.MurderSword());
                                break;
                        }

                        for(String map : main.getConfig().getConfigurationSection("maps.").getKeys(false)){
                            for(String spawns : main.getConfig().getConfigurationSection("maps." + map + ".spawns").getKeys(false)){
                                int spawnRandom = (int) (Math.random() * (main.getConfig().getInt("maps." + map + ".nbspawns")));

                                if(spawnRandom == 0){ spawnRandom = 1;}
                                pl.sendMessage("§c[DEBUG] Spawn choosed: " + spawnRandom);
                                pl.getPlayer().teleport(new Location(
                                        Bukkit.getWorld("world"),
                                        main.getConfig().getDouble("maps." + map + ".spawns." + spawnRandom + ".x"),
                                        main.getConfig().getDouble("maps." + map + ".spawns." + spawnRandom + ".y"),
                                        main.getConfig().getDouble("maps." + map + ".spawns." + spawnRandom + ".z")
                                ));
                                break;
                            }
                        }
                    }
                    break;
                case "myrole":
                    title.send(player, "§cYou are " + main.players.get(player.getUniqueId()), "");
                    break;
                case "setrole":
                    if(main.players.containsKey(player.getUniqueId())) {
                        main.players.remove(player.getUniqueId());
                    }


                    switch(a[1].toUpperCase()) {
                        case "MURDER":
                            /* for(crackpixel.murdermystery.Game.Roles u : main.players.values()){
                                if(main.players.containsValue(crackpixel.murdermystery.Game.Roles.MURDER)){
                                    for(UUID u2 : main.players.keySet()){
                                        if(main.players.get(u2) == Roles.MURDER){
                                            main.players.replace(u2, Roles.MURDER, Roles.INNOCENT);

                                        }
                                    }
                                }
                            } */

                            main.players.put(player.getUniqueId(), crackpixel.murdermystery.Game.Roles.MURDER);
                            player.sendMessage("You are " + main.players.get(player.getUniqueId()));
                            break;
                        case "DETECTIVE":
                            main.players.put(player.getUniqueId(), crackpixel.murdermystery.Game.Roles.DETECTIVE);
                            player.sendMessage("You are " + main.players.get(player.getUniqueId()));
                            break;
                        case "INNOCENT":
                            main.players.put(player.getUniqueId(), crackpixel.murdermystery.Game.Roles.INNOCENT);
                            player.sendMessage("You are " + main.players.get(player.getUniqueId()));
                            break;
                    }
                    break;
            }
        }

        return false;
    }
}
