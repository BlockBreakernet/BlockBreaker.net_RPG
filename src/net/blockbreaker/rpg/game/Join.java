package net.blockbreaker.rpg.game;

import net.blockbreaker.rpg.api.mysql.MySQLManagementMethods;
import net.blockbreaker.rpg.api.player.FlyingItems.FlyingItem;
import net.blockbreaker.rpg.game.inventory.CoinItem;
import net.blockbreaker.rpg.system.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

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


        MySQLManagementMethods.createData(p);

        FlyingItem item = new FlyingItem();
        item.setLocation(p.getLocation());
        item.setMaterial(Material.DIAMOND_SWORD);
        item.setHeight(2);
        item.setText("Hallo " + p.getDisplayName());
        item.spawn();

        CoinItem.getCoinItem(p);
    }

}
