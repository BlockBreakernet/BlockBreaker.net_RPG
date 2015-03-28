package net.blockbreaker.rpg.system;

import net.blockbreaker.rpg.api.log.Logger;
import net.blockbreaker.rpg.api.log.LoggerFile;
import net.blockbreaker.rpg.api.log.LoggerState;
import net.blockbreaker.rpg.api.log.events.MapLoad;
import net.blockbreaker.rpg.api.log.events.ServerCommand;
import net.blockbreaker.rpg.api.mysql.MySQL;
import net.blockbreaker.rpg.api.mysql.MySQLFile;
import net.blockbreaker.rpg.api.mysql.MySQLManagementMethods;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Lukas on 26.03.2015.
 */
public class Main extends JavaPlugin {

    public static String consolePrefix = "[RPG] ";
    public static Plugin plugin;

    @Override
    public void onEnable() {
        this.plugin = this;

        registerCommands();
        registerEvents();
        loadConfigs();

        //Connect to MySQL
        MySQL.connect();
        MySQLManagementMethods.createTableIfNotExists();

        //Enable Message
        Server server = Bukkit.getServer();
        ConsoleCommandSender console = server.getConsoleSender();

        console.sendMessage(" ");
        console.sendMessage(ChatColor.BLUE + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        console.sendMessage(" ");
        console.sendMessage(ChatColor.AQUA + getDescription().getName());
        console.sendMessage(ChatColor.AQUA + "Version: " + getDescription().getVersion());
        console.sendMessage(" ");
        console.sendMessage(ChatColor.BLUE + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        console.sendMessage(" ");

        Logger.log("Loaded all important things sucessfully!", LoggerState.INFO);
    }

    @Override
    public void onDisable() {
        MySQL.disconnect();

        Logger.log("Disabled all functions sucessfully!", LoggerState.INFO);
    }


    //Register Methods
    private void registerCommands() {
        Logger.logConsole("Loading Commands...", LoggerState.INFO);

        //Hier Commands zum Registrieren rein

        Logger.logConsole("Sucessfully loaded Commands!", LoggerState.INFO);
    }

    //Register Events
    private void registerEvents() {
        Logger.logConsole("Loading Events...", LoggerState.INFO);

        //Hier Events zum Registrieren rein
        new MapLoad(this);
        new ServerCommand(this);

        Logger.logConsole("Sucessfully loaded Events!", LoggerState.INFO);
    }

    //Load Config
    private void loadConfigs() {
        Logger.logConsole("Loading Configs...", LoggerState.INFO);

        //Erstellt MySQl Cfg
        MySQLFile file = new MySQLFile();
        file.setStandard();
        file.readData();

        //Erstellt Log
        LoggerFile log = new LoggerFile();
        log.setStandard();

        Logger.logConsole("All Configs loaded!", LoggerState.INFO);
    }

}
