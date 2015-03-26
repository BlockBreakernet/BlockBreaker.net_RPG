package net.blockbreaker.rpg.api.mysql;

import net.blockbreaker.rpg.system.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

import java.sql.*;

/**
 * Created by Lukas on 26.03.2015.
 */
public class MySQL {

    public static String host;
    public static String port;
    public static String database;
    public static String username;
    public static String password;
    public static Connection con;

    static ConsoleCommandSender console = Bukkit.getConsoleSender();

    //Connect to MySQL
    public static void connect() {
        if(!isConnected()) {
            try {
                console.sendMessage(" ");
                console.sendMessage(Main.consolePrefix + ChatColor.GREEN + "Initialisiere MySQL Backend...");
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
                System.out.println(Main.consolePrefix + ChatColor.GREEN + "MySQL Verbindung aufgebaut!");
            } catch (SQLException e) {
                System.out.println(Main.consolePrefix + "Verbindung konnte nicht hergestellt werden!");
                System.out.println(Main.consolePrefix + "SQLException: " + e.getMessage());
                System.out.println(Main.consolePrefix + "SQLState: " + e.getSQLState());
                System.out.println(Main.consolePrefix + "VendorError: " + e.getErrorCode());
            }
        }
    }

    //Disconnect MySQL
    public static void disconnect() {
        if(isConnected()) {
            try {
                con.close();
                System.out.println(Main.consolePrefix + "MySQL Verbindung geschlossen!");
            } catch (SQLException e) {
                System.out.println(Main.consolePrefix + "Verbindung konnte nicht geschlossen werden!");
                System.out.println(Main.consolePrefix + "SQLException: " + e.getMessage());
                System.out.println(Main.consolePrefix + "SQLState: " + e.getSQLState());
                System.out.println(Main.consolePrefix + "VendorError: " + e.getErrorCode());
            }
        }
    }

    //Returns Connection
    public static boolean isConnected() {
        return (con == null ? false : true);
    }

    //Process a Statement
    public static void update(String qry) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(qry);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Returns a Result
    public static ResultSet getResult(String qry) {
        try {
            PreparedStatement ps = con.prepareStatement(qry);
            return ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
