package net.blockbreaker.rpg.api.log;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Created by Lukas on 27.03.2015.
 */
public class LoggerFile {

    public void setStandard() {
        FileConfiguration cfg = getFileConfiguration();

        try {
            cfg.save(getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static File getFile() {
        return new File("plugins/RPG/Log", "log.yml");
    }

    private static FileConfiguration getFileConfiguration() {
        return YamlConfiguration.loadConfiguration(getFile());
    }

    public static void logData(String cfgsection, String logmsg1) {
        FileConfiguration cfg = getFileConfiguration();

        cfg.set(cfgsection ,logmsg1);

        try {
            cfg.save(getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
