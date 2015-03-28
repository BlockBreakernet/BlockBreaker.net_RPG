package net.blockbreaker.rpg.api.log.events;

import net.blockbreaker.rpg.api.log.Logger;
import net.blockbreaker.rpg.api.log.LoggerState;
import net.blockbreaker.rpg.system.Main;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;

/**
 * Created by Lukas on 27.03.2015.
 */
public class ServerCommand implements Listener {

    private final Main plugin;

    public ServerCommand(Main main) {
        this.plugin = main;
        plugin.getServer().getPluginManager().registerEvents(this, main);
    }

    public void onReload(ServerCommandEvent e) {
        if(e.getCommand().equalsIgnoreCase("reload") || e.getCommand().equalsIgnoreCase("rl")) {
            Logger.log("Succesfully reloaded Server!", LoggerState.INFO);
        } else {
            Logger.log(e.getSender() + " used Command: " + e.getCommand().toString(), LoggerState.WARNING);
        }
    }
}
