package net.blockbreaker.rpg.game.inventory;

import net.blockbreaker.rpg.api.coins.Coins;
import net.blockbreaker.rpg.system.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by Janne on 30.03.2015.
 */
public class CoinItem implements Listener {

    private final Main plugin;

    public CoinItem(Main main) {
        this.plugin = main;
        plugin.getServer().getPluginManager().registerEvents(this, main);
    }

    public static void getCoinItem(Player p) {

        ItemStack coins = new ItemStack(Material.GOLD_INGOT, 1);
        ItemMeta coinsmeta = coins.getItemMeta();
        coinsmeta.setDisplayName("§bGeldbeutel");
        coinsmeta.addEnchant(Enchantment.KNOCKBACK, 1, true);

        coins.setItemMeta(coinsmeta);

        p.getInventory().setItem(8, coins);
        p.updateInventory();
    }

    @EventHandler
    public void onHover(PlayerItemHeldEvent e) {
        Player p = e.getPlayer();

        if(e.getPreviousSlot() == 8) {
            p.sendMessage(ChatColor.GOLD + "Du hast " + ChatColor.AQUA + Coins.getCoins(p) + " Coins");
        }
    }
}
