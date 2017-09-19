package com.worldciv.filesystem;

import com.sun.org.apache.bcel.internal.generic.Select;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.WeatherType;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;

public class FileSystem {
    public static boolean exists = false;

    public FileSystem(){
        File dir = new File(Bukkit.getPluginManager().getPlugin("CombatTestStatsMain").getDataFolder()+"/Custom_Items");
        if(!dir.exists()) {
            dir.mkdir();
            Bukkit.broadcastMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Warning!");
            System.out.print("Creating new UID System, please check all plugins if this is not wanted!");
            File file = new File(dir, "Custom_Items_UID_System.yml");
            if (!(file.exists())) {
                Bukkit.broadcastMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Warning!");
                Bukkit.broadcastMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Creating new UID System, please check all plugins if this is not wanted!");
                YamlConfiguration yamlFile = YamlConfiguration.loadConfiguration(file);
                yamlFile.createSection("UID");

                yamlFile.createSection("UID.customArmor");
                yamlFile.createSection("UID.customWeapon");

                yamlFile.createSection("UID.customArmor.Helm");
                yamlFile.createSection("UID.customArmor.ChestPlate");
                yamlFile.createSection("UID.customArmor.Leggings");
                yamlFile.createSection("UID.customArmor.Boots");
                yamlFile.createSection("UID.customArmor.Other");

                yamlFile.createSection("UID.customWeapon.Sword");
                yamlFile.createSection("UID.customWeapon.Shield");
                yamlFile.createSection("UID.customWeapon.Axe");
                yamlFile.createSection("UID.customWeapon.Bow");
                yamlFile.createSection("UID.customWeapon.Other");

                yamlFile.set("UID.customArmor.Helm", 1);
                yamlFile.set("UID.customArmor.ChestPlate", 1);
                yamlFile.set("UID.customArmor.Leggings", 1);
                yamlFile.set("UID.customArmor.Boots", 1);
                yamlFile.set("UID.customArmor.Other", 1);

                yamlFile.set("UID.customWeapon.Sword", 1);
                yamlFile.set("UID.customWeapon.Shield", 1);
                yamlFile.set("UID.customWeapon.Axe", 1);
                yamlFile.set("UID.customWeapon.Bow", 1);
                yamlFile.set("UID.customWeapon.Other", 1);
                try {
                    yamlFile.save(file);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        else{
            exists = true;
        }
        System.out.print("Loading UID system.");

    }

    public CustomItem createItem(ItemStack itemType,Tier tier, WeaponType weaponType){
        CustomItem customItem = ItemGenerator.generateItem(itemType,tier,weaponType);
        return null;
    }
    public CustomItem createItem(ItemStack itemType, Tier tier, ArmorType armorType){


        return null;
    }

}
