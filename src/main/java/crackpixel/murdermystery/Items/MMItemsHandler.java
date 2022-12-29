package crackpixel.murdermystery.Items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MMItemsHandler {

    public ItemStack MurderSword(){
        ItemStack murder_sword = new ItemStack(Material.IRON_SWORD);
        ItemMeta ms_meta = murder_sword.getItemMeta();
        ms_meta.setDisplayName("§cMurderer's Sword");
        murder_sword.setItemMeta(ms_meta);
        return murder_sword;
    }
}
