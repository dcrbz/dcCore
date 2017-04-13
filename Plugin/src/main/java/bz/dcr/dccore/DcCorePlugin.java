package bz.dcr.dccore;

import org.bukkit.plugin.java.JavaPlugin;

public class DcCorePlugin extends JavaPlugin {

    private static DcCorePlugin plugin;


    @Override
    public void onEnable() {
        plugin = this;
    }

    @Override
    public void onDisable() {
    }


    public static DcCorePlugin getPlugin() {
        return plugin;
    }

}
