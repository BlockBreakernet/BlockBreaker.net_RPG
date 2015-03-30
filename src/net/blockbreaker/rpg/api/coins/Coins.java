package net.blockbreaker.rpg.api.coins;

import net.blockbreaker.rpg.api.mysql.MySQL;
import org.bukkit.OfflinePlayer;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Lukas on 30.03.2015.
 */
public class Coins {

    public static int getCoins(OfflinePlayer player) {
        String uuid = player.getUniqueId().toString();

        int coins = 0;

        ResultSet ep = MySQL.getResult("SELECT coins FROM data WHERE uuid ='" + uuid + "'");

        try {
            if(ep.next()){
                coins = ep.getInt("coins");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return coins;
    }

    public static void setCoins(OfflinePlayer player, int coins) {
        String uuid = player.getUniqueId().toString();

        MySQL.update("UPDATE data SET coins = " + coins + " WHERE uuid = '" + uuid + "'");
    }

    public static void addCoins(OfflinePlayer player, int addcoins) {
        String uuid = player.getUniqueId().toString();
        int newcoins = 0;

        ResultSet oldep = MySQL.getResult("SELECT coins FROM data WHERE uuid = '" + uuid + "'");
        try {
            if(oldep.next()) {
                newcoins = oldep.getInt("coins") + addcoins;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        MySQL.update("UPDATE data SET coins = " + newcoins + " WHERE uuid = '" + uuid + "'");
    }

    public static void removeCoins(OfflinePlayer player, int removecoins) {
        String uuid = player.getUniqueId().toString();

        int newcoins = 0;

        ResultSet oldcoins = MySQL.getResult("SELECT coins FROM data WHERE uuid = '" + uuid + "'");
        try {
            if(oldcoins.next()) {
                newcoins = oldcoins.getInt("coins") - removecoins;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        MySQL.update("UPDATE data SET coins = " + newcoins + " WHERE uuid = '" + uuid + "'");
    }
}
