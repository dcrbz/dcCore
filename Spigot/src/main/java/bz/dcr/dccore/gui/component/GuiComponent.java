package bz.dcr.dccore.gui.component;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public abstract class GuiComponent {

    protected ItemStack item;


    public GuiComponent() {
        item = new ItemStack(Material.AIR);
    }


    public ItemStack getItem() {
        return item;
    }

}
