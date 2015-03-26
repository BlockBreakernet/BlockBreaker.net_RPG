package net.blockbreaker.rpg.api.player;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

/**
 * Created by Lukas on 26.03.2015.
 */
public class ClearInventory {

    public static void clearInv(Player p) {
        p.getInventory().clear();
    }

    public static void clearEffects(Player p) {
        p.getActivePotionEffects().clear();
    }

    public static void joinPlayer(Player p) {
        clearEffects(p);
        clearInv(p);
        p.getInventory().setHelmet(null);
        p.getInventory().setChestplate(null);
        p.getInventory().setLeggings(null);
        p.getInventory().setBoots(null);
        p.setLevel(0);
        p.setExp(0.0F);
        p.setAllowFlight(false);
        p.setFlying(false);
        p.setGameMode(GameMode.SURVIVAL);
        p.setHealth(20.0D);
        p.setFoodLevel(20);
        p.setFireTicks(0);
    }

}
