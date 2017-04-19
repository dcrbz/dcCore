package bz.dcr.dccore.utils;

import com.google.common.io.ByteStreams;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.*;

public class ConfigUtil {

    public static Configuration loadConfig(File file, InputStream inputStream) throws IOException {
        // Create pluginConfig file if not existing
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();

            try (InputStream is = inputStream; OutputStream os = new FileOutputStream(file)) {
                ByteStreams.copy(is, os);
            }
        }

        // Load configuration from file
        return ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
    }

}
