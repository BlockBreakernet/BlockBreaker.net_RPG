package net.blockbreaker.rpg.api.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * Created by Lukas on 28.03.2015.
 */
public class LoadBar {

    Player player;
    float time;
    float count;
    Plugin plugin;
    int scheduler;

    public LoadBar(Plugin plugin, Player player, float time){
        this.player = player;
        this.time = time;
        this.plugin = plugin;
        this.count = time;
        launch();
    }

    public void launch(){
        player.setExp(0.99f);
        player.setLevel((int)count);

        scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {

            public void run() {
                count--;
                float exp = player.getExp();
                float remove = (float)1/time;
                float newExp = exp - remove;
                player.setExp(newExp);
                player.setLevel((int)count);

                if(newExp <= 0){
                    Bukkit.getScheduler().cancelTask(scheduler);
                }
            }
        }, 0, 20);
    }
}
