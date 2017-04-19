package bz.dcr.dccore;

import bz.dcr.commons.db.MongoDB;
import bz.dcr.dccore.constants.ConfigKey;
import bz.dcr.dccore.identification.IdentificationProvider;
import bz.dcr.dccore.listeners.LoginListener;
import bz.dcr.dccore.player.PlayerManager;
import bz.dcr.dccore.utils.ConfigUtil;
import com.mongodb.MongoClientURI;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

public class DcCorePlugin extends Plugin {

    private final ExecutorService executor = Executors.newCachedThreadPool();

    private Configuration config;

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
        config = ConfigUtil.loadConfig(new File(getDataFolder(), "config.yml"), getResourceAsStream("config.yml"));
    }

    private void initDatabase() {
        final MongoClientURI uri = new MongoClientURI(
                config.getString(ConfigKey.MONGODB_URI)
        );

        mongoDB = new MongoDB(uri, getClass().getClassLoader());
        mongoDB.getMorphia().mapPackage(getClass().getPackage().getName(), true);
    }

    private void registerListeners() {
        getProxy().getPluginManager().registerListener(this, new LoginListener(this));
    }


    // Getters

    public Configuration getConfig() {
        return config;
    }

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
