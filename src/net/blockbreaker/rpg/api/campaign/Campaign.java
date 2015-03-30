package net.blockbreaker.rpg.api.campaign;

import net.blockbreaker.rpg.api.mysql.MySQL;
import org.bukkit.OfflinePlayer;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Lukas on 30.03.2015.
 */
public class Campaign {

    public static int getProgress(OfflinePlayer player) {
        String uuid = player.getUniqueId().toString();

        int coins = 0;

        ResultSet ep = MySQL.getResult("SELECT campaignprogress FROM data WHERE uuid ='" + uuid + "'");

        try {
            if(ep.next()){
                coins = ep.getInt("campaignprogress");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return coins;
    }

    public static void increaseProgress(OfflinePlayer player) {
        String uuid = player.getUniqueId().toString();

        int progress = 0;

        ResultSet ep = MySQL.getResult("SELECT campaignprogress FROM data WHERE uuid ='" + uuid + "'");

        try {
            if(ep.next()) {
                progress = ep.getInt("campaignprogress") + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        MySQL.update("UPDATE data SET campaignprogress = " + progress + " WHERE uuid = '" + uuid + "'");
    }
}
