package bz.dcr.dccore.cmd;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

final class CmdConfig {
    private static File FILE;
    private static YamlConfiguration config;

    /**
     * Load the config.
     */
    static void loadConfig(Plugin plugin) {
        FILE = new File(plugin.getDataFolder(), "messages.yml");

        if (!FILE.exists())
        {
            InputStream link = (CmdConfig.class.getResourceAsStream("/messages.yml"));
            try
            {
                //noinspection ResultOfMethodCallIgnored
                FILE.getParentFile().mkdirs();

                Files.copy(link, FILE.getAbsoluteFile().toPath());
            }
            catch (IOException e)
            {
                throw new RuntimeException("Cannot load messages", e);
            }
        }

        config = YamlConfiguration.loadConfiguration(FILE);
    }

    /**
     * Get a message from its key.
     * @param key the key
     * @return the message
     */
    static String getMessage(String key) {
        return config.getString(key, key);
    }

	/*
	 * Messages
	 */

    static String getHelpHeader() {
        return getMessage("helpHeader");
    }

    static String getHelpEntry() {
        return getMessage("helpEntry");
    }

    static String getOptional() {
        return getMessage("optional");
    }

    static String getSimpleUsageCompound() {
        return getMessage("simpleUsageCompound");
    }

    static String getSimpleUsageRequired() {
        return getMessage("simpleUsageRequired");
    }

    static String getSimpleUsageOptional() {
        return getMessage("simpleUsageOptional");
    }

    static String getCompoundSuffix() {
        return getMessage("compoundSuffix");
    }

    static String getSeparator() {
        return getMessage("separator");
    }

    static String getDescriptionSeparator() {
        return getMessage("descriptionSeparator");
    }

    static String getStringSerializer() {
        return getMessage("serializerNames.string");
    }

    static String getInvalidSenderTypeMessage() {
        return getMessage("invalidSenderTypeMessage");
    }

    static String getNoPermissionMessage() {
        return getMessage("noPermissionMessage");
    }

    static String getSerializationError() {
        return getMessage("serializationError");
    }

    private CmdConfig() {}

}
