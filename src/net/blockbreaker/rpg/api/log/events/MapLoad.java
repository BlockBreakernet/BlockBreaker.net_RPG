package net.blockbreaker.rpg.api.log.events;

import net.blockbreaker.rpg.api.log.Logger;
import net.blockbreaker.rpg.api.log.LoggerState;
import net.blockbreaker.rpg.system.Main;
import org.bukkit.event.Listener;
import org.bukkit.event.server.MapInitializeEvent;

/**
 * Created by Lukas on 27.03.2015.
 */
public class MapLoad implements Listener {

    private final Main plugin;

    public MapLoad(Main main) {
        this.plugin = main;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public void onMapLoad(MapInitializeEvent e) {
        Logger.log("Loaded World sucessfully, without Errors!", LoggerState.INFO);
    }
}
