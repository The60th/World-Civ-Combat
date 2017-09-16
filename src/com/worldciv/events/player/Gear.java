package com.worldciv.events.player;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class Gear {

    public void woodswordrecipe() {
        ItemStack wsword = new ItemStack(Material.WOOD_SWORD, 1);
        ItemMeta meta = wsword.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY  + "XxWooden SwordxX");
        meta.setLore(Arrays.asList("Testing", "the lore", "4 this item."));
        wsword.setItemMeta(meta);

        ShapedRecipe woodswordrecipe = new ShapedRecipe(wsword);
        woodswordrecipe.shape(
                "@ @",
                "@ @");
        woodswordrecipe.setIngredient('@', Material.WOOD);
        Bukkit.getServer().addRecipe(woodswordrecipe);
    }

}


