package com.worldciv.filesystem;

import com.worldciv.the60th.Main;
import net.minecraft.server.v1_11_R1.Enchantment;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

import javax.rmi.CORBA.Tie;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class ItemGenerator {
    private static final int tierOneMin = 1;
    private static final int tierOneMax = 6;

    private static final int tierTwoMin = 4;
    private static final int tierTwoMax = 9;

    private static final int tierThreeMin = 7;
    private static final int tierThreeMax = 11;

    private static final int tierFourMin = 9;
    private static final int tierFourMax = 13;

    private static final int tierFiveMin = 10;
    private static final int tierFiveMax = 16;

    private static final int commonMod = 0;
    private static final int uncommonMod = 1;
    private static final int rareMod = 2;
    private static final int epicMod = 3;
    private static final int legendaryMod = 5;



    public static CustomItem generateItem(ItemStack itemStack, Tier tier){


        return null;
    }
    public static CustomItem generateItem(ItemStack itemStack, Tier tier,WeaponType weaponType){
        Rarity rarity = calculateRarity(0);

        int stat = calculateStatWithRarity(rarity);
        String name = "This is a dummy name";
        String id = createUID();
        Main.logger.info(("Custom ID: " + id));
        id = convertToInvisibleString(id);
        Main.logger.info(("Custom ID: " + id));
        return new CustomItem(itemStack,name,id,stat,0,rarity,tier);
    }
    public static CustomItem generateItem(ItemStack itemStack, Tier tier, ArmorType armorType){
        Rarity rarity = calculateRarity(0);

        int stat = calculateStatWithRarity(rarity)/4;
        if(stat <= 0){stat = 1;}
        String name = "this is a dummy name";
        String id = createUID();
        id = convertToInvisibleString(id);
        return new CustomItem(itemStack,name,id,0,stat,rarity,tier);
    }

    private static Rarity calculateRarity(double modifier){
        //Add checks for the modifier later on.
        Random random = new Random(System.currentTimeMillis());
        int x = random.nextInt(100)+1;
        if(isBetween(x,0,20)){ return Rarity.common; }
        else if(isBetween(x,21,40)){return Rarity.uncommon;}
        else if(isBetween(x,41,60)){return Rarity.rare;}
        else if(isBetween(x,61,80)){return Rarity.epic;}
        else if(isBetween(x,81,100)){return Rarity.legendary;}
        else{
            Main.logger.info(("Rarity generation error has happened."));
            return Rarity.common;
        }
    }
    private static int calculateStatWithRarity(Rarity rarity){
        int calculatedValue;
        switch (rarity){
            case common:
                 calculatedValue = ThreadLocalRandom.current().nextInt(tierOneMin, tierOneMax + 1);
                calculatedValue = calculatedValue + commonMod;
                break;
            case uncommon:
                 calculatedValue = ThreadLocalRandom.current().nextInt(tierTwoMin, tierTwoMax + 1);
                calculatedValue = calculatedValue + uncommonMod;
                break;
            case rare:
                 calculatedValue = ThreadLocalRandom.current().nextInt(tierFourMin, tierThreeMax + 1);
                calculatedValue = calculatedValue + rareMod;
                break;
            case epic:
                 calculatedValue = ThreadLocalRandom.current().nextInt(tierThreeMin, tierFourMax + 1);
                calculatedValue = calculatedValue + epicMod;
                break;
            case legendary:
                 calculatedValue = ThreadLocalRandom.current().nextInt(tierFiveMin, tierFiveMax + 1);
                calculatedValue = calculatedValue + legendaryMod;
                break;
            default:
                calculatedValue = -1;
                break;
        }
        return calculatedValue;
    }
    public static ChatColor getColorFromRarity(Rarity rarity){
        switch (rarity){
            case common:
                return ChatColor.WHITE;
            case uncommon:
                return ChatColor.GREEN;
            case rare:
                return ChatColor.BLUE;
            case epic:
                return ChatColor.DARK_PURPLE;
            case legendary:
                return ChatColor.GOLD;
            default:
                return ChatColor.RED;
        }
    }
    private static boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }
    private static String createUID(){
        return UUID.randomUUID().toString();
    }
    public static String convertToInvisibleString(String s) {
        String hidden = "";
        for (char c : s.toCharArray()) hidden += ChatColor.COLOR_CHAR+""+c;
        return hidden;
    }
}
