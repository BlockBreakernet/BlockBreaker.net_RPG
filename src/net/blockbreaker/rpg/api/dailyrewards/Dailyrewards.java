package net.blockbreaker.rpg.api.dailyrewards;

import net.blockbreaker.rpg.api.mysql.MySQL;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.DateFormat;
import java.util.Locale;

/**
 * Created by Janne on 03.04.2015.
 */

public class Dailyrewards extends JavaPlugin{

    public static void setLastLogin(OfflinePlayer player) {
        String uuid = player.getUniqueId().toString();

        DateFormat dmy = DateFormat.getDateInstance(DateFormat.SHORT, Locale.GERMANY);
        String lastLoginAsString = dmy.format(System.currentTimeMillis());

        MySQL.update("UPDATE data SET lastlogin WHERE uuid = '" + uuid + "' VALUES('" + lastLoginAsString + "')");

    }

}
