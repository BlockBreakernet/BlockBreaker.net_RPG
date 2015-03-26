package net.blockbreaker.rpg.api.mysql;

/**
 * Created by Lukas on 26.03.2015.
 */
public class MySQLManagementMethods {

    public static void createTableIfNotExists() {
        MySQL.update("CREATE TABLE IF NOT EXISTS Player (UUID VARCHAR(100), Player VARCHAR(100), Coins INTEGER)"); // TODO: Tabelle mit n?tigen Spalten best?cken
    }

}
