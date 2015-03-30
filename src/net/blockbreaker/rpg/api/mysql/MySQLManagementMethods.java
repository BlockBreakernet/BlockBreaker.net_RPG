package net.blockbreaker.rpg.api.mysql;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Lukas on 26.03.2015.
 */
public class MySQLManagementMethods {

    public static void createTableIfNotExists() {
        MySQL.update("CREATE TABLE IF NOT EXISTS data (playername VARCHAR(100), uuid VARCHAR(100), ep INTEGER, coins INTEGER, campaignprogress INTEGER)");
    }

    public static void createData(String player) {
        OfflinePlayer pl = Bukkit.getOfflinePlayer(player);
        String uuid = pl.getUniqueId().toString();

        int ep = 0;
        int coins = 0;
        int campaignprogress = 0;

        ResultSet rs = MySQL.getResult("SELECT uuid FROM data WHERE uuid = '" + uuid + "'");

        try {
            if(!rs.next()) {
                MySQL.update("INSERT INTO data VALUES('" + pl.getName() + "', '" + uuid + "', '" + ep + "', '" + coins + "', '" + campaignprogress + "')");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isInDataBase(OfflinePlayer target) {
        String uuid = target.getUniqueId().toString();

        boolean isInDatabase = false;

        ResultSet rs = MySQL.getResult("SELECT uuid FROM data WHERE uuid = '" + uuid + "'");

        try {
            if(rs.next()) {
                isInDatabase = true;
            } else {
                isInDatabase = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Boolean.valueOf(isInDatabase).booleanValue();
    }

    public static int getEP(String player) {
        OfflinePlayer pl = Bukkit.getOfflinePlayer(player);
        String uuid = pl.getUniqueId().toString();

        int exp = 0;

        ResultSet ep = MySQL.getResult("SELECT ep FROM data WHERE uuid ='" + uuid + "'");

        try {
            if(ep.next()){
                exp= ep.getInt("ep");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exp;
    }

}
