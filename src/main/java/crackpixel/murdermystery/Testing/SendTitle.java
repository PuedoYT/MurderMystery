package crackpixel.murdermystery.Testing;

import crackpixel.murdermystery.Utils.Title;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SendTitle implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        Player player = (Player) sender;
        Title title = new Title();
        title.send(player, "test", "test2");

        return false;
    }
}
