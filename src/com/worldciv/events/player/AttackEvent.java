package com.worldciv.events.player;

import com.worldciv.filesystem.CustomItem;
import com.worldciv.the60th.Main;
import net.minecraft.server.v1_11_R1.EntityPlayer;
import net.minecraft.server.v1_11_R1.Item;
import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
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

        double preDmgDealt = event.getDamage(); //Damage pre mod.
        double finalDamage = event.getFinalDamage(); //Is post mod IE armor / res.
        //Calculate swing timer shit here.
        Bukkit.broadcastMessage(customDamage + " damage dealt--Armor used: " + armor);
        event.setDamage(customDamage-armor); //Temp
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
            if(helm.getItemMeta().getLore() != null){
                lore = helm.getItemMeta().getLore();
                if(CustomItem.unhideItemUUID(lore.get(lore.size()-1)).startsWith("7UUID: ")){
                    customItems[0] = CustomItem.getCustomItemFromUUID(CustomItem.unhideItemUUID(lore.get(lore.size()-1)).substring(6));
                }
            }
        }
        if(player.getInventory().getChestplate() != null){
            Chestplate = player.getInventory().getChestplate();
            if(Chestplate.getItemMeta().getLore() != null){
                lore = Chestplate.getItemMeta().getLore();
                if(CustomItem.unhideItemUUID(lore.get(lore.size()-1)).startsWith("7UUID: ")){
                    customItems[1] = CustomItem.getCustomItemFromUUID(CustomItem.unhideItemUUID(lore.get(lore.size()-1)).substring(6));
                }
            }
        }
        if(player.getInventory().getLeggings() != null){
            Legs = player.getInventory().getLeggings();
            if(Legs.getItemMeta().getLore() != null){
                lore = Legs.getItemMeta().getLore();
                if(CustomItem.unhideItemUUID(lore.get(lore.size()-1)).startsWith("7UUID: ")){
                    customItems[2] = CustomItem.getCustomItemFromUUID(CustomItem.unhideItemUUID(lore.get(lore.size()-1)).substring(6));
                }
            }
        }
        if(player.getInventory().getBoots() != null){
            boots = player.getInventory().getBoots();
            if(boots.getItemMeta().getLore() != null){
                lore = boots.getItemMeta().getLore();
                if(CustomItem.unhideItemUUID(lore.get(lore.size()-1)).startsWith("7UUID: ")){
                    customItems[3] = CustomItem.getCustomItemFromUUID(CustomItem.unhideItemUUID(lore.get(lore.size()-1)).substring(6));
                }
            }
        }
        return customItems;
    }
    private int getArmorFromArray(CustomItem[] customItems){
        int armor = 0;
        for(int i = 0; i < customItems.length; i++){
            if(customItems[i] != null) {
                armor = armor + customItems[i].getDamage();
            }
        }
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
                Bukkit.broadcastMessage(CustomItem.unhideItemUUID(lore.get(lore.size()-1)).substring(6));
                Bukkit.broadcastMessage(CustomItem.unhideItemUUID(lore.get(lore.size()-1)));
                if(CustomItem.unhideItemUUID(lore.get(lore.size()-1)).startsWith("7UUID: ")){
                    Bukkit.broadcastMessage(CustomItem.unhideItemUUID(lore.get(lore.size()-1)).substring(6));
                    Bukkit.broadcastMessage(CustomItem.unhideItemUUID(lore.get(lore.size()-1)));
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
        Bukkit.broadcastMessage("item1: " + customItems[0].getId().toString());
        Bukkit.broadcastMessage("item2: " + customItems[1].getId().toString());
        return customItems;
    }
    private int getDamageFromArray(CustomItem[] customItems){
        int damage = 0;
        Bukkit.broadcastMessage("getDmgFA: " + customItems[0].getId());
        //for (CustomItem customItem : customItems) {
            Bukkit.broadcastMessage("getDmgFA:2 " + customItems[0].getId());
        Bukkit.broadcastMessage("getDmgFA:2 " + customItems[0].getDamage());
        // damage = damage + customItem.getDamage();
        for(int i = 0; i < customItems.length; i++){
            Bukkit.broadcastMessage("getDmgFA:2 " +i +":" + customItems[0].getDamage());
            damage = damage + customItems[i].getDamage();
        }
        Bukkit.broadcastMessage("getDmgFA:damage " +damage);
        //}
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
