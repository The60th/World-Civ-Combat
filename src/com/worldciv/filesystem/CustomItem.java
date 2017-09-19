package com.worldciv.filesystem;

import org.bukkit.inventory.ItemStack;

import javax.rmi.CORBA.Tie;

public class CustomItem extends  FileSystem{
    private ItemStack itemStack;
    private String name;
    private String id;
    private int damage;
    private int armor;
    private int other;
    private Rarity rarity;
    private Tier tier;

    public CustomItem(){}

    public CustomItem(ItemStack itemStack, String name, String id, int damage, int armor){
        setItemStack(itemStack);
        setName(name);
        setId(id);
        setDamage(damage);
        setArmor(armor);

    }
    public CustomItem(ItemStack itemStack, String name, String id, int damage, int armor, Rarity rarity, Tier tier){

    }



    public ItemStack getItemFromCustomItem(CustomItem customItem){
        return null;
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
}
