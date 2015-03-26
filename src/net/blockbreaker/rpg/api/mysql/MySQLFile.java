package net.blockbreaker.rpg.api.mysql;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Created by Lukas on 26.03.2015.
 */
public class MySQLFile {

    public void setStandard() {
        FileConfiguration cfg = getFileConfiguration();

        cfg.options().copyDefaults(true);

        cfg.addDefault("host", "host");
        cfg.addDefault("port", "3306");
        cfg.addDefault("database", "database");
        cfg.addDefault("user", "username");
        cfg.addDefault("password", "password");

        try {
            cfg.save(getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File getFile() {
        return new File("plugins/RPG/MySQL", "config.yml");
    }

    private FileConfiguration getFileConfiguration() {
        return YamlConfiguration.loadConfiguration(getFile());
    }

    public void readData() {
        FileConfiguration cfg = getFileConfiguration();

        MySQL.host = cfg.getString("host");
        MySQL.port = cfg.getString("port");
        MySQL.database = cfg.getString("database");
        MySQL.username = cfg.getString("user");
        MySQL.password = cfg.getString("password");
    }
}
