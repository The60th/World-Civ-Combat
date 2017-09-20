package com.worldciv.filesystem;

import com.worldciv.the60th.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class Gear {

    public static int x;

    public static void woodswordrecipe() {
        ItemStack wsword;
        wsword = CustomItem.getItemFromCustomItem(Main.fileSystem.createItem((new ItemStack(Material.WOOD_SWORD,1)),Tier.three,WeaponType.sword ));
        ShapedRecipe woodswordrecipe = new ShapedRecipe(wsword);
        woodswordrecipe.shape(
                "@ @",
                "@ @");
        woodswordrecipe.setIngredient('@', Material.WOOD);
        Bukkit.getServer().addRecipe(woodswordrecipe);


    }


    public static void basiccplaterecipe() {
        ItemStack itembasiccplate = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemMeta meta = itembasiccplate.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "XxBasicChestplatexX");
        meta.setLore(Arrays.asList("Testing", "the lore", "4 this chestplate item."));
        itembasiccplate.setItemMeta(meta);

        ShapedRecipe basiccplaterecipe = new ShapedRecipe(itembasiccplate);
        basiccplaterecipe.shape(
                "@ @",
                "@@@",
                "@@@");
        basiccplaterecipe.setIngredient('@', Material.IRON_BLOCK);
        Bukkit.getServer().addRecipe(basiccplaterecipe);


    }

}


