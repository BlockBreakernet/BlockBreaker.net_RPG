package net.blockbreaker.rpg.api.dailyrewards;

import net.blockbreaker.rpg.api.mysql.MySQL;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Locale;

/**
 * Created by Janne on 03.04.2015.
 */

public class Dailyrewards extends JavaPlugin{

    public static void getReward(OfflinePlayer player) {
        String uuid = player.getUniqueId().toString();
        DateFormat dmy = DateFormat.getDateInstance(DateFormat.SHORT, Locale.GERMANY);

        //wir brauchen noch ne Spalte logincounter damit man die anzahl der folgelogins auflisten kann

        ResultSet lastlogin = MySQL.getResult("SELECT lastlogin FROM data WHERE uuid = '" + uuid + "'");
        String today = dmy.format(System.currentTimeMillis());

    }


    public static void setLastLogin(OfflinePlayer player) {
        String uuid = player.getUniqueId().toString();

        DateFormat dmy = DateFormat.getDateInstance(DateFormat.SHORT, Locale.GERMANY);
        String lastLoginAsString = dmy.format(System.currentTimeMillis());

        MySQL.update("UPDATE data SET lastlogin = ' LastLoginAsString ' WHERE uuid = '" + uuid + "')");

    }


    public static boolean isDailyLogin(OfflinePlayer player) {
        String uuid = player.getUniqueId().toString();

        DateFormat dmy = DateFormat.getDateInstance(DateFormat.SHORT, Locale.GERMANY);
        String today = dmy.format(System.currentTimeMillis());
        ResultSet lastlogin = MySQL.getResult("SELECT lostlogin FROM data WHERE uuid = '" + uuid + "')");
        java.sql.Date.valueOf(today + 1);

//        if() {

  //            }

          return false;
         }
    }
