package com.worldciv.filesystem;

import net.minecraft.server.v1_11_R1.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.rmi.CORBA.Tie;
import java.io.File;
import java.util.Arrays;

public class CustomItem extends  FileSystem{
    private static ItemStack itemStack;
    private String name;
    private String id;
    private int damage;
    private int armor;
    private int other;
    private Rarity rarity;
    private Tier tier;
    private WeaponType weaponType;
    public CustomItem(){}

    public CustomItem(ItemStack itemStack, String name, String id, int damage, int armor){
        setItemStack(itemStack);
        setName(name);
        setId(id);
        setDamage(damage);
        setArmor(armor);

    }
    public CustomItem(ItemStack itemStack, String name, String id, int damage, int armor, Rarity rarity, Tier tier){
        this.setItemStack(itemStack);
        this.setName(name);
        this.setId(id);
        this.setDamage(damage);
        this.setArmor(armor);
        this.setRarity(rarity);
        this.setTier(tier);
        this.setOther(-1);
    }



    public static ItemStack getItemFromCustomItem(CustomItem customItem){
        ItemStack item = new ItemStack(customItem.getItemStack().getType(), 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ItemGenerator.getColorFromRarity(customItem.getRarity()) + customItem.getName());
        meta.setLore(Arrays.asList(ChatColor.GRAY+"Item Tier: " + ChatColor.WHITE + customItem.tier,
                ChatColor.GRAY+"Item Rarity: " +ItemGenerator.getColorFromRarity(customItem.getRarity())+ customItem.rarity ,
                ChatColor.GRAY+"Damage: " + ChatColor.WHITE + customItem.damage, ChatColor.GRAY+"Armor: " + ChatColor.WHITE + customItem.getArmor(),
                ChatColor.GRAY+"UUID: " + unhideItemUUID(customItem.getId())));
        item.setItemMeta(meta);
        return item;
    }
    public static CustomItem getCustomItemFromUUID(String UUID){
        File dir = new File(Bukkit.getPluginManager().getPlugin("World_Civ_Combat").getDataFolder()+"/Custom_Items");
        if(dir.exists()) {
            Bukkit.broadcastMessage("da fuk1: ");
            Bukkit.broadcastMessage("da fuk1: " + UUID);
            File file = new File(dir,UUID+".yml");
            if(file.exists()){
                Bukkit.broadcastMessage("da fuk2: " );
            }
        }
        YamlConfiguration yaml;
        File file = new File(Bukkit.getPluginManager().getPlugin("World_Civ_Combat").getDataFolder()+"/Custom_Items/"+UUID+".yml");
        yaml = YamlConfiguration.loadConfiguration(file);
        Bukkit.broadcastMessage("Not clear: " + yaml);

        Bukkit.broadcastMessage(file.toString());
        Bukkit.broadcastMessage(file.getName());
        Bukkit.broadcastMessage(file.getParent());
        Bukkit.broadcastMessage(file.getPath());
        Bukkit.broadcastMessage("clear");


        Bukkit.broadcastMessage("?: " + yaml.getInt("Item-Data.Armor"));
        Bukkit.broadcastMessage("?: " + yaml.getInt("Item-Data.Damage"));
        Bukkit.broadcastMessage("?: " + yaml.getString("Item-Data.UUID"));

        return createItemFromYAML(yaml);
    }

    private static CustomItem createItemFromYAML (YamlConfiguration yaml){
        CustomItem item = new CustomItem(yaml.getItemStack("Item-Data.ItemStack"),
                                yaml.getString("Item-Data.Name"),yaml.getString("Item-Data.UUID")
                ,yaml.getInt("Item-Data.Damage"),yaml.getInt("Item-Data.Armor"),
                /*getRarityFromString(yaml.getString("Item-Data.Rarity"))*/Rarity.Common,/*getTierFromString(yaml.getString("Item-Data.Tier"))*/Tier.I);
        //ItemStack itemStack, String name, String id, int damage, int armor, Rarity rarity, Tier tier
        Bukkit.broadcastMessage("Name " + item.getName());
        return item;
    }
    private static Rarity getRarityFromString(String string){return Rarity.valueOf(string);}
    private static Tier getTierFromString(String string){return Tier.valueOf(string);}
    public static String unhideItemUUID(String string){
        return string.replaceAll("§", "");
    }

    //Save an item return true if it does.
    private boolean saveItem(){return true;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public int getOther() {
        return other;
    }

    public void setOther(int other) {
        this.other = other;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public Tier getTier() {
        return tier;
    }

    public void setTier(Tier tier) {
        this.tier = tier;
}

}
