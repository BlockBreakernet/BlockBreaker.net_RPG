package net.blockbreaker.rpg.game;

import net.blockbreaker.rpg.api.player.ActionBar;
import net.blockbreaker.rpg.api.player.FlyingItems.FlyingItem;
import net.blockbreaker.rpg.api.player.Holograms;
import net.blockbreaker.rpg.api.player.LoadBar;
import net.blockbreaker.rpg.system.Main;
import org.bukkit.Location;
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

        String[] Text = {"Test", "Test"};
        Location loc = p.getEyeLocation();

        Holograms holo = new Holograms(Text, loc);
        holo.showAll();


        FlyingItem fi = new FlyingItem();
        fi.setLocation(p.getLocation());
        fi.setMaterial(Material.DIAMOND_SWORD);
        fi.setText("Test");
        fi.spawn();

        ActionBar.sendStaticActionBar(p, "Test");

        LoadBar lb = new LoadBar(plugin, p, 20*30);
        lb.launch();
    }
}
