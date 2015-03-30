package net.blockbreaker.rpg.api.player.FlyingItems;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Lukas on 28.03.2015.
 */
public class FlyingItem {

    private ArmorStand armorstand;
    private Location location;
    private String text = null;
    private Boolean h = false;
    private Material material;
    private double height = -1.3;
    public FlyingItem(){

    }

    public void setLocation(Location location){
        this.location = location;
    }

    public void setText(String text){
        this.text = text;
    }

    public void setMaterial(Material material){
        this.material = material;
    }

    public void setHeight(double height){
        this.height = height - 1.3;
        if(this.location != null){
            this.location.setY(this.location.getY()+this.height);
            h = true;
        }
    }

    public void remove(){
        this.location = null;
        this.armorstand.remove();
        this.armorstand.getPassenger().remove();
        this.armorstand = null;
        this.h = false;
        this.height = 0;
        this.text = null;
        this.material = null;
    }

    public void teleport(Location location){
        if(this.location != null){
            armorstand.teleport(location);
            this.location = location;
        }
    }

    public void spawn(){
        if(!h){
            this.location.setY(this.location.getY()+this.height);
            h = true;
        }
        armorstand = (ArmorStand)this.location.getWorld().spawn(this.location, ArmorStand.class);
        armorstand.setGravity(false);
        armorstand.setVisible(false);
        Item i = this.location.getWorld().dropItem(this.getLocation(), new ItemStack(this.getMaterial()));
        i.setPickupDelay(2147483647);
        if(this.text != null){
            i.setCustomName(this.text);
            i.setCustomNameVisible(true);
        }
        armorstand.setPassenger(i);
    }

    public Location getLocation(){
        return this.location;
    }

    public Material getMaterial(){
        return this.material;
    }

    public double getHeight(){
        return this.height;
    }

    public String getText(){
        return this.text;
    }
}
