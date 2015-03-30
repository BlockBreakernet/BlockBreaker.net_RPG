package net.blockbreaker.rpg.api.mysql;

import java.sql.ResultSet;

/**
 * Created by Lukas on 26.03.2015.
 */
public class MySQLManagementMethods {

    public static void createTableIfNotExists() {
        MySQL.update("CREATE TABLE IF NOT EXISTS LevelCoins (Playername VARCHAR(100), UUID VARCHAR(100), Ep INTEGER, Coins INTEGER)"); // TODO: Tabelle mit notigen Spalten bestucken
        MySQL.update("CREATE TABLE IF NOT EXISTS ??????????? (Playername VARCHAR(100), UUID VARCHAR(100), ?????????????????????????)");
    }

    public static void getEP(String uuid) {
        ResultSet ep = MySQL.getResult("SELECT Ep FROM LevelCoins WHERE UUID='"+uuid+"'");
        return
    }

}
