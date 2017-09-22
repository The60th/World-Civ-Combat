package com.worldciv.events.player;

import com.worldciv.filesystem.CustomItem;
import com.worldciv.the60th.Main;
import net.minecraft.server.v1_11_R1.EntityPlayer;
import net.minecraft.server.v1_11_R1.Item;
import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

//Based off of old new combat stats plugin
public class AttackEvent implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        float t = ((EntityPlayer)(((CraftPlayer)event.getPlayer()).getHandle())).o(1);
        //System.out.print("Interact timer value " + t);
    }
    @EventHandler
    public void onEntityDamageEvent(EntityDamageByEntityEvent event) {

        Entity attacker = event.getDamager(); //Attacker
        Entity defender = event.getEntity(); //Defender
        if (!(attacker instanceof Player) || !(defender instanceof Player)) {
            //Not a PvP action, handle this elsewhere --> Write function to handle this with two Entity as params
            return;
        }
        Player pAttacker = (Player) attacker;
        Player pDefender = (Player) defender;
        double customDamage = getDamageFromArray(getDamageItems(pAttacker));
        double armor = getArmorFromArray(getArmorItems(pDefender));
        double damageScaler = getDamageScale(pAttacker,event.getDamage());
        Bukkit.broadcastMessage("Cause: " + event.getCause());

        //Calculate swing timer shit here.
        customDamage = customDamage*damageScaler;
        Bukkit.broadcastMessage(customDamage + " damage dealt--Armor used: " + armor);
        if(customDamage - armor <= 0){
            //Damage did not overflow armor values.
            //This is were hit tracking will be done later on.
            pDefender.setHealth(pDefender.getHealth());
        }else{
            pDefender.setHealth(pDefender.getHealth()-(customDamage-armor)); //Cant set neg major bug
        }
        event.setDamage(0);
    }

    private CustomItem[] getArmorItems(Player player){
        ItemStack helm;
        ItemStack Chestplate;
        ItemStack Legs;
        ItemStack boots;
        List<String> lore;
        CustomItem[] customItems = new CustomItem[4];

        if(player.getInventory().getHelmet() != null){
            helm = player.getInventory().getHelmet();
            Bukkit.broadcastMessage("Passed not null 1");
            if(helm.getItemMeta().getLore() != null){
                lore = helm.getItemMeta().getLore();
                Bukkit.broadcastMessage("Passed not null 2");
                if(CustomItem.unhideItemUUID(lore.get(lore.size()-1)).startsWith("7UUID: ")){
                    Bukkit.broadcastMessage("Passed not null 3");
                    Bukkit.broadcastMessage(lore.get(lore.size()-1).substring(7));
                    customItems[0] = CustomItem.getCustomItemFromUUID(CustomItem.unhideItemUUID(lore.get(lore.size()-1)).substring(7));
                    Bukkit.broadcastMessage(customItems[0].getArmor() + "0");
                }
            }
        }
        if(player.getInventory().getChestplate() != null){
            Bukkit.broadcastMessage("Passed not null 4");
            Chestplate = player.getInventory().getChestplate();
            if(Chestplate.getItemMeta().getLore() != null){
                Bukkit.broadcastMessage("Passed not null 5");
                lore = Chestplate.getItemMeta().getLore();
                if(CustomItem.unhideItemUUID(lore.get(lore.size()-1)).startsWith("7UUID: ")){
                    Bukkit.broadcastMessage("Passed not null 6");
                    customItems[1] = CustomItem.getCustomItemFromUUID(CustomItem.unhideItemUUID(lore.get(lore.size()-1)).substring(7));
                    Bukkit.broadcastMessage(customItems[1].getArmor() + "1");
                }
            }
        }
        if(player.getInventory().getLeggings() != null){
            Bukkit.broadcastMessage("Passed not null 7");
            Legs = player.getInventory().getLeggings();
            if(Legs.getItemMeta().getLore() != null){
                Bukkit.broadcastMessage("Passed not null 8");
                lore = Legs.getItemMeta().getLore();
                if(CustomItem.unhideItemUUID(lore.get(lore.size()-1)).startsWith("7UUID: ")){
                    Bukkit.broadcastMessage("Passed not null 9");
                    customItems[2] = CustomItem.getCustomItemFromUUID(CustomItem.unhideItemUUID(lore.get(lore.size()-1)).substring(7));
                    Bukkit.broadcastMessage(customItems[2].getArmor() + "2");
                }
            }
        }
        if(player.getInventory().getBoots() != null){
            Bukkit.broadcastMessage("Passed not null 10");
            boots = player.getInventory().getBoots();
            if(boots.getItemMeta().getLore() != null){
                Bukkit.broadcastMessage("Passed not null 11");
                lore = boots.getItemMeta().getLore();
                if(CustomItem.unhideItemUUID(lore.get(lore.size()-1)).startsWith("7UUID: ")){
                    Bukkit.broadcastMessage("Passed not null 12");
                    customItems[3] = CustomItem.getCustomItemFromUUID(CustomItem.unhideItemUUID(lore.get(lore.size()-1)).substring(7));
                    Bukkit.broadcastMessage(customItems[3].getArmor() + "3");
                }
            }
        }
        return customItems;
    }
    private int getArmorFromArray(CustomItem[] customItems){
        int armor = 0;
        for(int i = 0; i < customItems.length; i++){
           // if(customItems[i] != null) {
            //Bukkit.broadcastMessage("getARFA: " +i +":" + customItems[i].getArmor());
            try {
                armor = armor + customItems[i].getArmor();
            }catch (NullPointerException e){

            }
            //}
        }
        Bukkit.broadcastMessage("armor" + armor);
        return armor;
    }

    private CustomItem[] getDamageItems(Player player){
        CustomItem[] customItems = new CustomItem[2];
        List<String> lore;
        ItemStack mainHand;
        ItemStack offHand;
        ItemStack other;

        if(player.getInventory().getItemInMainHand() != null){
            mainHand = player.getInventory().getItemInMainHand();
            if(mainHand.getItemMeta().getLore() != null){
                lore = mainHand.getItemMeta().getLore();
                if(CustomItem.unhideItemUUID(lore.get(lore.size()-1)).startsWith("7UUID: ")){
                    customItems[0] = CustomItem.getCustomItemFromUUID(CustomItem.unhideItemUUID(lore.get(lore.size()-1)).substring(7));
                }
            }
        }

        if(player.getInventory().getItemInOffHand() != null){
            offHand = player.getInventory().getItemInOffHand();
            if(offHand.getItemMeta().getLore() != null){
                lore = offHand.getItemMeta().getLore();
                if(CustomItem.unhideItemUUID(lore.get(lore.size()-1)).startsWith("7UUID: ")){
                    customItems[1] = CustomItem.getCustomItemFromUUID(CustomItem.unhideItemUUID(lore.get(lore.size()-1)).substring(7));
                }
            }
        }
        return customItems;
    }
    private int getDamageFromArray(CustomItem[] customItems){
        int damage = 0;
        //for (CustomItem customItem : customItems) {
        // damage = damage + customItem.getDamage();
        for(int i = 0; i < customItems.length; i++){
            Bukkit.broadcastMessage("getDmgFA:2 " +i +":" + customItems[0].getDamage());
            try {
                damage = damage + customItems[i].getDamage();
            }catch(NullPointerException e){
                
            }
        }
        return damage;
    }

    private double getDamageScale(Player player, Double eventDamage){
        ItemStack item = player.getInventory().getItemInMainHand();
        Material itemM = item.getType();
        double damage;
        if(itemM == Material.WOOD_SWORD){damage=4;}
        else if(itemM == Material.STONE_SWORD){damage =5;}
        else if(itemM == Material.IRON_SWORD){damage =6;}
        else if(itemM == Material.DIAMOND_SWORD){damage =7;}
        else if(itemM == Material.GOLD_SWORD){damage =4;}

        else if(itemM == Material.WOOD_SPADE){damage =2;}
        else if(itemM == Material.STONE_SPADE){damage =3;}
        else if(itemM == Material.IRON_SPADE){damage =4;}
        else if(itemM == Material.DIAMOND_SPADE){damage =5;}
        else if(itemM == Material.GOLD_SPADE){damage =2;}

        else if(itemM == Material.WOOD_PICKAXE){damage =2;}
        else if(itemM == Material.STONE_PICKAXE){damage =3;}
        else if(itemM == Material.IRON_PICKAXE){damage =4;}
        else if(itemM == Material.DIAMOND_PICKAXE){damage =5;}
        else if(itemM == Material.GOLD_PICKAXE){damage =2;}

        else if(itemM == Material.WOOD_AXE){damage =7;}
        else if(itemM == Material.STONE_AXE){damage =9;}
        else if(itemM == Material.IRON_AXE){damage =9;}
        else if(itemM == Material.DIAMOND_AXE){damage =9;}
        else if(itemM == Material.GOLD_AXE){damage =7;}
        else{damage =1;}
        Bukkit.broadcastMessage("Should of dealt: " + damage + " Did deal: " + eventDamage);
        damage = eventDamage/damage;
        Bukkit.broadcastMessage("Diff mod is " + damage);
        return damage;
    }
}












       /* new BukkitRunnable(){public void run(){
            for(Player player : Bukkit.getOnlinePlayers()){
                EntityPlayer ep = ((CraftPlayer)player).getHandle();
               player.sendMessage("o(0) == "+ep.o(1));
            }
        }}.runTaskTimer(Main.getPlugin(), 0, 1);*/


        /* OOO // Flow
        * On attack
        *  >Get attackers normal damage
        *  >Get attackers swing timer
        *  >Find the scale of the damage from the swing timer, to find what % of damage was dealt.
        *  >Keep track of this % scale.
        *  > <Break>
        *  >Get attackers weapon and its damage range. *
        *  >Get defenders armor and armor values. **
        *  >Scale attackers damage to a range based upon the swing timer value we stored before.
        *  >Check if scaled damage is greater then armor
        *  ?If so:
        *   > Take damage - armor
        *   > Deal that damage to defender
        *   > Finish event
        *  ?If not:
        *   > Track the damage dealt and store it and wait for next hit
        *   > On next hit add incoming damage to stored damage if its greater then armor deal damage
        *   > if not repeat above cycle till damage is meet
        *   > Clear stored damage after a amount of time to make new fights even.
        *
        *
        *  * Get attackers weapon and damage.
        *   ?How do this?
        *
        *  ** Get defenders armor and armor value
        *   ?How do this?
        *
        */
