package net.blockbreaker.rpg.api.ep;

import net.blockbreaker.rpg.api.mysql.MySQL;
import org.bukkit.OfflinePlayer;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Lukas on 30.03.2015.
 */
public class Ep {

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

        ResultSet oldep = MySQL.getResult("SELECT ep FROM data WHERE uuid = '" + uuid + "'");
        try {
            if(oldep.next()) {
                newep = oldep.getInt("ep") + addep;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        MySQL.update("UPDATE data SET ep = " + newep + " WHERE uuid = '" + uuid + "'");
    }

    public static void removeEp(OfflinePlayer player, int removeep) {
        String uuid = player.getUniqueId().toString();

        int newep = 0;

        ResultSet oldep = MySQL.getResult("SELECT ep FROM data WHERE uuid = '" + uuid + "'");
        try {
            if(oldep.next()) {
                newep = oldep.getInt("ep") - removeep;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        MySQL.update("UPDATE data SET ep = " + newep + " WHERE uuid = '" + uuid + "'");
    }
}
