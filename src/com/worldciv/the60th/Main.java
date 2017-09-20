package com.worldciv.the60th;

import com.worldciv.events.inventory.CraftEvent;
import com.worldciv.events.inventory.CraftEvent;
import com.worldciv.events.player.JoinEvent;
import com.worldciv.events.player.AttackEvent;
import com.worldciv.filesystem.CustomItem;
import com.worldciv.filesystem.FileSystem;
import com.worldciv.filesystem.Gear;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import sun.rmi.runtime.Log;


import java.io.File;
import java.util.logging.Logger;

public class Main extends JavaPlugin implements Listener{

    FileConfiguration config = getConfig();
    public static Plugin plugin;
    public static FileSystem fileSystem;
    public static Logger logger;

    public void onEnable() {
        plugin = this;
        logger = Logger.getLogger("Minecraft");
        fileSystem = new FileSystem();
        PluginDescriptionFile pdfFile = this.getDescription();

        logger.info(pdfFile.getName()
                + "has successfully enabled. The current version is: "
                + pdfFile.getVersion());
        registerEvents();

        Gear.woodswordrecipe();
        Gear.basiccplaterecipe();
        Gear.newRecipe();
        Gear.customTierOneSword();
    }

    public void onDisable() {
        PluginDescriptionFile pdfFile = getDescription();
        logger.info(pdfFile.getName() + "has successfully disabled.");
    }

    public void registerEvents(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new JoinEvent(), this);
        pm.registerEvents(new AttackEvent(), this);
        pm.registerEvents(new CraftEvent(), this);
    }

}
