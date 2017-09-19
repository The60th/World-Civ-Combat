package com.worldciv.the60th;

import com.worldciv.events.player.JoinEvent;
import com.worldciv.events.player.AttackEvent;
import com.worldciv.filesystem.CustomItems;
import com.worldciv.filesystem.FileSystem;
import com.worldciv.filesystem.Gear;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


import java.io.File;
import java.util.logging.Logger;

public class Main extends JavaPlugin implements Listener{

    FileConfiguration config = getConfig();
    public static Plugin plugin;
    public static FileSystem fileSystem;

    public void onEnable() {
        plugin = this;
        fileSystem = new FileSystem();
        PluginDescriptionFile pdfFile = this.getDescription();
        Logger logger = Logger.getLogger("Minecraft");

        logger.info(pdfFile.getName()
                + "has successfully enabled. The current version is: "
                + pdfFile.getVersion());
        registerEvents();

        Gear.woodswordrecipe();
        Gear.basiccplaterecipe();
    }

    public void onDisable() {
        PluginDescriptionFile pdfFile = getDescription();
        Logger logger = Logger.getLogger("Minecraft");
        logger.info(pdfFile.getName() + "has successfully disabled.");
    }

    public void registerEvents(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new JoinEvent(), this);
        pm.registerEvents(new AttackEvent(), this);
    }

}
