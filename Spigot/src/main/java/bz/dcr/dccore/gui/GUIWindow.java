package bz.dcr.dccore.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class GUIWindow {

    private static Map<String, GUIWindow> windows = new HashMap<>();

    private Inventory inv;
    private Map<Integer, GUIItem> items;
    private Consumer<InventoryOpenEvent> onOpen = null;
    private Consumer<InventoryCloseEvent> onClose = null;

    private boolean registered;


    public GUIWindow(String name, int rows) {
        name = getValidName(name);

        this.inv = Bukkit.createInventory(null, rows * 9, name);
        this.items = new HashMap<>(rows * 9);


        windows.put(name, this);

        this.registered = true;
    }


    public void setItem(int slot, GUIItem item) {
        this.items.put(slot, item);
        this.inv.setItem(slot, item.getBukkitItem());
    }

    public void setItem(int x, int y, GUIItem item) {
        setItem(x + y * 9, item);
    }

    public GUIItem getItem(int slot) {
        return this.items.get(slot);
    }

    public GUIItem getItem(int x, int y) {
        return getItem(x * 9 + y);
    }

    public void setOpenEvent(Consumer<InventoryOpenEvent> e) {
        this.onOpen = e;
    }

    void callOpen(InventoryOpenEvent e) {
        if (onOpen != null) onOpen.accept(e);
    }

    public void setCloseEvent(Consumer<InventoryCloseEvent> e) {
        this.onClose = e;
    }

    void callClosed(InventoryCloseEvent e) {
        if (onClose != null) onClose.accept(e);
    }

    public Inventory getBukkitInventory() {
        return this.inv;
    }

    public void show(HumanEntity h) {
        Inventory inv = Bukkit.createInventory(h, getBukkitInventory().getSize(), getBukkitInventory().getTitle());
        inv.setContents(getBukkitInventory().getContents());
        h.openInventory(inv);
    }

    public void unregister() {
        windows.remove(this.getBukkitInventory().getTitle());
        this.items.clear();
        this.registered = false;
    }

    static GUIWindow getWindow(String inv) {
        return windows.get(inv);
    }

    private String getValidName(String name) {
        if (windows.containsKey(name)) return getValidName(name + ChatColor.RESET);
        else return name;
    }

}
