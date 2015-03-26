package net.blockbreaker.rpg.system;

import net.blockbreaker.rpg.api.mysql.MySQL;
import net.blockbreaker.rpg.api.mysql.MySQLFile;
import net.blockbreaker.rpg.api.mysql.MySQLManagementMethods;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Lukas on 26.03.2015.
 */
public class Main extends JavaPlugin {

    public static String consolePrefix = "[RPG] ";

    @Override
    public void onEnable() {
        registerCommands();
        registerEvents();

        MySQLFile file = new MySQLFile();
        file.setStandard();
        file.readData();

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
    }

    @Override
    public void onDisable() {
        MySQL.disconnect();
    }


    //Register Methods
    private void registerCommands() {

    }

    //Register Events
    private void registerEvents() {
    }
}
