package bz.dcr.dccore.item;

import bz.dcr.dccore.DcCorePlugin;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public class ItemManager {

    private DcCorePlugin plugin;


    public ItemManager(DcCorePlugin plugin) {
        this.plugin = plugin;
    }


    public ItemStack getPlayerHead(String playerName) {
        ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();

        skullMeta.setOwner(playerName);
        skullMeta.setDisplayName("§e" + playerName);

        itemStack.setItemMeta(skullMeta);

        return itemStack;
    }

    public ItemStack getPlayerHead(UUID playerId) {
        final String playerName = plugin.getIdentificationProvider().getName(playerId);

        if (playerName == null) {
            return null;
        }

        return getPlayerHead(playerName);
    }

}
