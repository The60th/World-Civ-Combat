package com.worldciv.filesystem;

import com.worldciv.the60th.Main;
import net.minecraft.server.v1_11_R1.Item;
import net.minecraft.server.v1_11_R1.ItemStep;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import sun.security.provider.SHA;

import java.util.Arrays;

public class Gear {

    public static int x;
    public static ShapedRecipe newRecipe;
    public static ShapedRecipe customTierOneSword;
    public static void woodswordrecipe() {
        ItemStack wsword;
        wsword = CustomItem.getItemFromCustomItem(Main.fileSystem.createItem((new ItemStack(Material.WOOD_SWORD,1)),Tier.III,WeaponType.sword ));
        ShapedRecipe woodswordrecipe = new ShapedRecipe(wsword);
        woodswordrecipe.shape(
                "@ @",
                "@ @");
        woodswordrecipe.setIngredient('@', Material.WOOD);
        Bukkit.getServer().addRecipe(woodswordrecipe);

    }
    public static void newRecipe(){
        ItemStack item = CustomItem.getItemFromCustomItem(Main.fileSystem.createItem((new ItemStack(Material.WOOD_SWORD,1)),Tier.III,WeaponType.sword ));
        newRecipe = new ShapedRecipe(item);
        newRecipe.shape(
                "@ @",
                "@ @",
                "@ @");
        newRecipe.setIngredient('@',Material.GOLD_AXE);
        Bukkit.getServer().addRecipe(newRecipe);
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

    public static void customTierOneSword(){
        //ItemStack item = CustomItem.getItemFromCustomItem(Main.fileSystem.createItem((new ItemStack(Material.WOOD_SWORD,1)),Tier.five,WeaponType.sword ));
        ItemStack item = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Tier One Sword");
        item.setItemMeta(meta);
        customTierOneSword = new ShapedRecipe(item);
        customTierOneSword.shape(
                "@  ",
                "@  ",
                "#  ");
        customTierOneSword.setIngredient('@',Material.GLASS);
        customTierOneSword.setIngredient('#',Material.STICK);

        Bukkit.getServer().addRecipe(customTierOneSword);
    }
}


