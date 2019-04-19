package bz.dcr.dccore.item;

import bz.dcr.dccore.DcCorePlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public class ItemManager {

    private DcCorePlugin plugin;


    public ItemManager(DcCorePlugin plugin) {
        this.plugin = plugin;
    }


    @Deprecated
    public ItemStack getPlayerHead(String playerName) {
        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();

        skullMeta.setOwner(playerName);
        skullMeta.setDisplayName("§e" + playerName);

        itemStack.setItemMeta(skullMeta);

        return itemStack;
    }

    public ItemStack getPlayerHead(UUID playerId) {
        final OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(playerId);

        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();

        skullMeta.setOwningPlayer(offlinePlayer);
        skullMeta.setDisplayName("§e" + offlinePlayer.getName());

        itemStack.setItemMeta(skullMeta);

        return itemStack;
    }

}
