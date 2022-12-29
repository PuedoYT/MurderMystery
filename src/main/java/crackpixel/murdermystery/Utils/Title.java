package crackpixel.murdermystery.Utils;

import org.bukkit.entity.Player;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;

public class Title {

    public void send(Player player, String s1, String s2){
        player.sendTitle(s1, s2);
    }
}
