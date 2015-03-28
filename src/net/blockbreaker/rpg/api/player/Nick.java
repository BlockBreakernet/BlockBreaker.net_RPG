package net.blockbreaker.rpg.api.player;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_8_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R1.CraftServer;
import org.bukkit.craftbukkit.v1_8_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.*;

/**
 * Created by Lukas on 28.03.2015.
 */
public class Nick {

    private static Plugin plugin;
    public static HashMap<UUID, String> PlayerName = new HashMap<UUID, String>();
    public static HashMap<String, Player> NamePlayer = new HashMap<String, Player>();
    static String NameList ="lau1050,Nightishaman,HDxDGamingxDHD,Dark3LP,Splitfireo,CafeBohne,DeadlySniper13,lilli278,JamesBase,amr00,Earstorm2000,Amal002,bla29,David_Protil,Sengroth,AcexToxik,DerpyAngelos,      Eichhorn02,fifi52,gabimori,HannesLeipzig,iNova77,Jonahweiss1,kill_du_92,Lemoncraft91,maxbrochet,Cliffjumper569,OZZZCAR,pase273,samuelvdv48,The_MonkeyFace,Ultra_Craft_BR,XxRentedxX,zick__barrimor,AaGHOSTaA,bugtail,agua19,bigkhalil,crazyboyjw,daimand_hunter,Earking_17,fami004200,gatuno2,HalfDemonKid,I_like_trans,CainMckendry,Solo2002,TheMinecraftLink,leocommander,BlackPotato8,Ole24567,paal61,FAHAD_K_S_A_,fahad_hg,MasterZain1012,BalexLP,Aaron3210,roxyhund,CapitainSkommer,pinspitLP,SaefSwagger";
    static ArrayList<String> Names = new ArrayList<String>();
    static Random rnd = new Random();

    public Nick(Plugin Plugin) {
        this.plugin = Plugin;
        List<String> Namess = new ArrayList<String>(Arrays.asList(NameList.split(",")));
        for(String s : Namess){
            Names.add(s);
        }

    }

    public static void setNick(final Player player,String Nick){
        PlayerName.put(player.getUniqueId(), player.getName());
        NamePlayer.put(Nick, player);

        MinecraftServer nmsServer = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer nmsWorld = ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle();
        EntityPlayer pNeu = new EntityPlayer(nmsServer, nmsWorld, new GameProfile(player.getUniqueId(), Nick), new PlayerInteractManager(nmsWorld));
        EntityPlayer p = ((CraftPlayer)player).getHandle();




        for(Player players : Bukkit.getOnlinePlayers()){
            PlayerConnection connection = ((CraftPlayer) players).getHandle().playerConnection;
            connection.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.REMOVE_PLAYER, p));
            connection.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.ADD_PLAYER, pNeu));
        }
        player.setDisplayName(Nick);
        player.setCustomName(Nick);
        final Location loc2 = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
        Location loc = new Location(player.getWorld(), player.getLocation().getX() + 500, player.getLocation().getY() + 2000, player.getLocation().getZ()+500);
        player.teleport(loc);

        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {

            @Override
            public void run() {
                player.teleport(loc2);
            }
        }, 5L);


    }

    public static void addNameToRandom(String Name){
        if(Name.length() > 17){
            Names.add(Name);
        }else{
            System.out.println(Name +" ist zulang für einen Namen - addNameToRandom");
        }

    }

    public static void removeNameFromRandom(String Name){
        if(Names.contains(Name)){
            Names.remove(Name);
        }
    }

    public static void setRandomNick(final Player player){
        PlayerName.put(player.getUniqueId(), player.getName());

        int zufall = rnd.nextInt(Names.size());
        String Nick = Names.get(zufall);

        NamePlayer.put(Nick, player);

        MinecraftServer nmsServer = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer nmsWorld = ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle();
        EntityPlayer pNeu = new EntityPlayer(nmsServer, nmsWorld, new GameProfile(player.getUniqueId(), Nick), new PlayerInteractManager(nmsWorld));
        EntityPlayer p = ((CraftPlayer)player).getHandle();

        for(Player players : Bukkit.getOnlinePlayers()){
            PlayerConnection connection = ((CraftPlayer) players).getHandle().playerConnection;
            connection.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.REMOVE_PLAYER, p));
            connection.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.ADD_PLAYER, pNeu));
        }
        player.setDisplayName(Nick);
        player.setCustomName(Nick);
        final Location loc2 = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());

        Location loc = new Location(player.getWorld(), player.getLocation().getX() + 500, player.getLocation().getY() + 2000, player.getLocation().getZ()+500);
        player.teleport(loc);

        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {

            @Override
            public void run() {
                player.teleport(loc2);
            }
        }, 5L);


    }

    public static void removeNick(final Player player){

        String Name = getRealName(player.getCustomName());
        MinecraftServer nmsServer = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer nmsWorld = ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle();
        EntityPlayer pNeu = new EntityPlayer(nmsServer, nmsWorld, new GameProfile(player.getUniqueId(), Name), new PlayerInteractManager(nmsWorld));
        EntityPlayer p = new EntityPlayer(nmsServer, nmsWorld, new GameProfile(player.getUniqueId(), player.getCustomName()), new PlayerInteractManager(nmsWorld));

        for(Player players : Bukkit.getOnlinePlayers()){
            PlayerConnection connection = ((CraftPlayer) players).getHandle().playerConnection;
            connection.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.REMOVE_PLAYER, p));
            connection.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.ADD_PLAYER, pNeu));
        }
        player.setDisplayName(Name);
        player.setCustomName(Name);



        final Location loc2 = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
        Location loc = new Location(player.getWorld(), player.getLocation().getX() + 500, player.getLocation().getY() + 2000, player.getLocation().getZ()+500);
        player.teleport(loc);

        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {

            @Override
            public void run() {
                player.teleport(loc2);

            }
        }, 5L);


    }

    public static String getRealName(String Name){

        if(NamePlayer.containsKey(Name)){
            Player player = NamePlayer.get(Name);
            if(PlayerName.containsKey(player.getUniqueId())){
                return PlayerName.get(player.getUniqueId());
            }

        }
        return "";
    }
}
