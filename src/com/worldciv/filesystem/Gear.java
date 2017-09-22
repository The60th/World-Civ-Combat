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
    public static ShapedRecipe customTierOneSword;
    public static ShapedRecipe customTierOneHelm;
    public static ShapedRecipe customTierOneChest;
    public static ShapedRecipe customTierOneLeg;
    public static ShapedRecipe customTierOneBoots;


    public static void customTierOneHelm(){
        //ItemStack item = CustomItem.getItemFromCustomItem(Main.fileSystem.createItem((new ItemStack(Material.WOOD_SWORD,1)),Tier.five,WeaponType.sword ));
        ItemStack item = new ItemStack(Material.IRON_HELMET, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Tier One Helm");
        item.setItemMeta(meta);
        customTierOneHelm = new ShapedRecipe(item);
        customTierOneHelm.shape(
                "@@@",
                "@ @",
                "   ");
        customTierOneHelm.setIngredient('@',Material.GLASS);

        Bukkit.getServer().addRecipe(customTierOneHelm);
    }
    public static void customTierOneChest(){
        //ItemStack item = CustomItem.getItemFromCustomItem(Main.fileSystem.createItem((new ItemStack(Material.WOOD_SWORD,1)),Tier.five,WeaponType.sword ));
        ItemStack item = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Tier One Chest plate");
        item.setItemMeta(meta);
        customTierOneChest = new ShapedRecipe(item);
        customTierOneChest.shape(
                "@ @",
                "@@@",
                "@@@");
        customTierOneChest.setIngredient('@',Material.GLASS);

        Bukkit.getServer().addRecipe(customTierOneChest);
    }
    public static void customTierOneLegs(){
        //ItemStack item = CustomItem.getItemFromCustomItem(Main.fileSystem.createItem((new ItemStack(Material.WOOD_SWORD,1)),Tier.five,WeaponType.sword ));
        ItemStack item = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Tier One Leggings");
        item.setItemMeta(meta);
        customTierOneLeg = new ShapedRecipe(item);
        customTierOneLeg.shape(
                "@@@",
                "@ @" ,
                "@ @");
        customTierOneLeg.setIngredient('@',Material.GLASS);

        Bukkit.getServer().addRecipe(customTierOneLeg);
    }
    public static void customTierOneBoots(){
        //ItemStack item = CustomItem.getItemFromCustomItem(Main.fileSystem.createItem((new ItemStack(Material.WOOD_SWORD,1)),Tier.five,WeaponType.sword ));
        ItemStack item = new ItemStack(Material.IRON_BOOTS, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "Tier One Boots");
        item.setItemMeta(meta);
        customTierOneBoots = new ShapedRecipe(item);
        customTierOneBoots.shape(
                "   ",
                "@ @",
                "@ @");
        customTierOneBoots.setIngredient('@',Material.GLASS);

        Bukkit.getServer().addRecipe(customTierOneBoots);
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


