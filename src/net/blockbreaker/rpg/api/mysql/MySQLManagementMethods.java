package net.blockbreaker.rpg.api.mysql;

import org.bukkit.OfflinePlayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Lukas on 26.03.2015.
 */
public class MySQLManagementMethods {

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
}
