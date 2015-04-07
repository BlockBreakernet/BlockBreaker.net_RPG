package net.blockbreaker.rpg.game;

import net.blockbreaker.rpg.api.dailyrewards.Dailyrewards;
import net.blockbreaker.rpg.api.ep.Ep;
import net.blockbreaker.rpg.api.player.FlyingItems.FlyingItem;
import net.blockbreaker.rpg.game.inventory.CoinItem;
import net.blockbreaker.rpg.system.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.text.DateFormat;
import java.util.Locale;

/**
 * Created by Lukas on 30.03.2015.
 */
public class Join implements Listener {

    private final Main plugin;

    public Join(Main main) {
        this.plugin = main;
        plugin.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        // ==> Items bekommen
            FlyingItem item = new FlyingItem();
            CoinItem.getCoinItem(p);


        DateFormat dmy = DateFormat.getDateInstance(DateFormat.SHORT, Locale.GERMANY);
        String today = dmy.format(System.currentTimeMillis());
        p.sendMessage(java.sql.Date.valueOf(today + 1).toString());

        // ==> Ep etc. getten
            p.setExp(Ep.getEP(p));


        // ==> Daily Login abfragen
            Dailyrewards.getReward(p);
            Dailyrewards.setLastLogin(p);


        //Ausrüstung und Inventar getten
    }

}
