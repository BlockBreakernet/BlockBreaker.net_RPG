package net.blockbreaker.rpg.game;

import net.blockbreaker.rpg.api.ep.Ep;
import net.blockbreaker.rpg.api.mysql.MySQLManagementMethods;
import net.blockbreaker.rpg.system.Main;
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

        p.sendMessage("Deine EP: " + String.valueOf(Ep.getEP(p)));
        Ep.setEp(p, 20);
        p.sendMessage("Deine EP: " + String.valueOf(Ep.getEP(p)));
        Ep.addEp(p, 1);
        p.sendMessage("Deine EP: " + String.valueOf(Ep.getEP(p)));

        if(MySQLManagementMethods.isInDataBase(p) == true) {
            p.sendMessage("Boss in Datenbank");
        }
    }
}
