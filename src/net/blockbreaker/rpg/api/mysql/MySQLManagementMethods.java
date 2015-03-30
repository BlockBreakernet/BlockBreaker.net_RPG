package net.blockbreaker.rpg.api.mysql;

import net.blockbreaker.rpg.api.log.Logger;
import net.blockbreaker.rpg.api.log.LoggerState;
import org.bukkit.OfflinePlayer;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Lukas on 26.03.2015.
 */
public class MySQLManagementMethods {

    public static void createTableIfNotExists() {
        Logger.log("Create MySQL Table 'data'...", LoggerState.MYSQLINSERT);
        MySQL.update("CREATE TABLE IF NOT EXISTS data (playername VARCHAR(100), uuid VARCHAR(100), ep INTEGER, coins INTEGER, campaignprogress INTEGER)");
        Logger.log("Created MySQL Table 'data' successfully!", LoggerState.MYSQLINSERT);
    }

    public static void createData(OfflinePlayer player) {
        String uuid = player.getUniqueId().toString();

        int ep = 0;
        int coins = 0;
        int campaignprogress = 0;

        ResultSet rs = MySQL.getResult("SELECT uuid FROM data WHERE uuid = '" + uuid + "'");

        try {
            if(!rs.next()) {
                Logger.log("Create Data for Player: " + player.getName() + "...", LoggerState.MYSQLINSERT);
                MySQL.update("INSERT INTO data VALUES('" + player.getName() + "', '" + uuid + "', '" + ep + "', '" + coins + "', '" + campaignprogress + "')");
                Logger.log("Data for " + player.getName() + " was created!", LoggerState.MYSQLINSERT);
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

    public static int getEP(OfflinePlayer player) {
        String uuid = player.getUniqueId().toString();

        int exp = 0;

        ResultSet ep = MySQL.getResult("SELECT ep FROM data WHERE uuid ='" + uuid + "'");

        try {
            if(ep.next()){
                exp = ep.getInt("ep");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exp;
    }

    public static void setEp(OfflinePlayer player, int ep) {
        String uuid = player.getUniqueId().toString();

        MySQL.update("UPDATE data SET ep = " + ep + " WHERE uuid = '" + uuid + "'");
    }

    public static void addEp(OfflinePlayer player, int addep) {
        String uuid = player.getUniqueId().toString();
        int newep = 0;
        int oldep = 0;

        oldep = getEP(player);

        newep = oldep + addep;

        MySQL.update("UPDATE data SET ep = " + newep + " WHERE uuid = '" + uuid + "'");
    }
}
