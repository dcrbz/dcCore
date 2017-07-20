package bz.dcr.dccore.gui;

import bz.dcr.dccore.DcCorePlugin;
import bz.dcr.dccore.gui.component.GuiComponent;
import bz.dcr.dccore.listener.SelfRegisteringListener;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class InventoryGui implements SelfRegisteringListener {

    private static final int ROW_WIDTH = 9;

    private DcCorePlugin plugin;
    private Inventory inventory;


    public InventoryGui(DcCorePlugin plugin, int rows) {
        this.plugin = plugin;
        this.inventory = Bukkit.createInventory(null, rows);
    }

    public InventoryGui(DcCorePlugin plugin, int rows, String title) {
        this.plugin = plugin;
        this.inventory = Bukkit.createInventory(null, rows, title);
    }


    public void addComponent(GuiComponent component) {
        inventory.addItem(component.getItem());
    }

    public void addComponent(GuiComponent component, int slot) {
        inventory.setItem(slot, component.getItem());
    }


    @Override
    public void registerListener() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

}
