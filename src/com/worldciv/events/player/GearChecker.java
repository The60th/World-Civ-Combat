package com.worldciv.events.player;

import com.worldciv.filesystem.Gear;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GearChecker extends JavaPlugin implements Listener {


    public void onEnable(){
        GChecker();
    }


    public void GChecker() {

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

            public void run() {

                for (Player all : Bukkit.getOnlinePlayers()) {
                    Player p = all.getPlayer();


                    if (p.getInventory().getHelmet().getType() == Material.WOOD) {
                        if (!p.hasPotionEffect(PotionEffectType.REGENERATION)) {
                            p.sendMessage("You have now Reginaration potion!");
                            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 2));
                        }
                    }
                }
            }
        }, 0, 10L);
    }
}