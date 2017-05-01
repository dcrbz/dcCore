package bz.dcr.dccore;

import bz.dcr.dccore.commons.db.MongoDB;
import bz.dcr.dccore.constants.ConfigKey;
import bz.dcr.dccore.identification.IdentificationProvider;
import bz.dcr.dccore.listeners.LoginListener;
import bz.dcr.dccore.player.PlayerManager;
import com.mongodb.MongoClientURI;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

public class DcCorePlugin extends JavaPlugin {

    private final ExecutorService executor = Executors.newCachedThreadPool();

    private MongoDB mongoDB;

    private IdentificationProvider identificationProvider;
    private PlayerManager playerManager;


    // Plugin methods

    @Override
    public void onEnable() {
        // Load plugin config
        try {
            loadConfig();
        } catch (IOException ex) {
            getLogger().log(Level.WARNING, ex, () -> "Failed to load configuration!");
            getServer().getPluginManager().disablePlugin(this);
        }

        // Connect to database
        initDatabase();

        // Additional initialization
        identificationProvider = new IdentificationProvider(getMongoDB().getDatastore());
        playerManager = new PlayerManager(getMongoDB().getDatastore());

        // Register listeners
        registerListeners();
    }

    @Override
    public void onDisable() {
        if(mongoDB != null) {
            mongoDB.close();
        }
    }


    // Plugin initialization

    private void loadConfig() throws IOException {
        saveDefaultConfig();
    }

    private void initDatabase() {
        final MongoClientURI uri = new MongoClientURI(
                getConfig().getString(ConfigKey.MONGODB_URI)
        );

        mongoDB = new MongoDB(uri, getClass().getClassLoader());
        mongoDB.getMorphia().mapPackage(getClass().getPackage().getName(), true);
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new LoginListener(this), this);
    }


    // Getters

    public ExecutorService getExecutor() {
        return executor;
    }

    public MongoDB getMongoDB() {
        return mongoDB;
    }

    public IdentificationProvider getIdentificationProvider() {
        return identificationProvider;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

}
