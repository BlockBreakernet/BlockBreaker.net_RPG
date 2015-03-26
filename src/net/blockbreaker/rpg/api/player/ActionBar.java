package net.blockbreaker.rpg.api.player;

import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Created by Lukas on 26.03.2015.
 */
public class ActionBar {

    public static void sendStaticActionBar(Player p, String actionbartext) {
        PacketPlayOutChat packet = new PacketPlayOutChat(ChatSerializer.a("{ text: \"" + actionbartext + "\" }"), (byte)2);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
    }

}
