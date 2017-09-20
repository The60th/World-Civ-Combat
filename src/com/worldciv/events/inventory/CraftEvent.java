package com.worldciv.events.inventory;

import com.worldciv.filesystem.CustomItem;
import com.worldciv.filesystem.Gear;
import com.worldciv.filesystem.Tier;
import com.worldciv.filesystem.WeaponType;
import com.worldciv.the60th.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

public class CraftEvent implements Listener {
    //TODO
    //Current bugs
    //Shift clicking items creates non custom items.
    @EventHandler
    public void onItemCrafted(CraftItemEvent event){
        Player clicked = (Player)event.getWhoClicked();
        if(clicked == null) return;
        String result = event.getRecipe().getResult().toString();
        if(result.equals(Gear.newRecipe.getResult().toString())){
            event.setCurrentItem(CustomItem.getItemFromCustomItem(Main.fileSystem.createItem((new ItemStack(Material.WOOD_SWORD,1)), Tier.I, WeaponType.sword )));
        }
        else if(result.equals(Gear.customTierOneSword.getResult().toString())){
            event.setCurrentItem(CustomItem.getItemFromCustomItem
                    (Main.fileSystem.createItem((new ItemStack(Material.IRON_SWORD,1)), Tier.I, WeaponType.sword )));
        }


    }




}
