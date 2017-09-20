package com.worldciv.filesystem;

import com.worldciv.the60th.Main;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

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

        int stat = calculateStat(rarity,tier);
        String name = "This is a dummy name";
        String id = CustomItem.unhideItemUUID(createUUID());
        Main.logger.info(("Custom ID: " + id));
        id = convertToInvisibleString(id);
        Main.logger.info(("Custom ID: " + id));
        return new CustomItem(itemStack,name,id,stat,0,rarity,tier);
    }
    public static CustomItem generateItem(ItemStack itemStack, Tier tier, ArmorType armorType){
        Rarity rarity = calculateRarity(0);

        int stat = calculateStat(rarity,tier)/4;
        if(stat <= 0){stat = 1;}
        String name = "this is a dummy name";
        String id = CustomItem.unhideItemUUID(createUUID());
        id = convertToInvisibleString(id);
        return new CustomItem(itemStack,name,id,0,stat,rarity,tier);
    }

    private static Rarity calculateRarity(double modifier){
        //Add checks for the modifier later on.
        Random random = new Random(System.currentTimeMillis());
        int x = random.nextInt(100)+1;
        if(isBetween(x,0,20)){ return Rarity.Common; }
        else if(isBetween(x,21,40)){return Rarity.Uncommon;}
        else if(isBetween(x,41,60)){return Rarity.Rare;}
        else if(isBetween(x,61,80)){return Rarity.Epic;}
        else if(isBetween(x,81,100)){return Rarity.Legendary;}
        else{
            Main.logger.info(("Rarity generation error has happened."));
            return Rarity.Common;
        }
    }
    private static int calculateStat(Rarity rarity,Tier tier){
        int calculatedValue;
        switch (tier){
            case I:
                 calculatedValue = ThreadLocalRandom.current().nextInt(tierOneMin, tierOneMax + 1);
                break;
            case II:
                 calculatedValue = ThreadLocalRandom.current().nextInt(tierTwoMin, tierTwoMax + 1);
                break;
            case III:
                 calculatedValue = ThreadLocalRandom.current().nextInt(tierFourMin, tierThreeMax + 1);
                break;
            case IV:
                 calculatedValue = ThreadLocalRandom.current().nextInt(tierThreeMin, tierFourMax + 1);
                break;
            case V:
                calculatedValue = ThreadLocalRandom.current().nextInt(tierFiveMin, tierFiveMax + 1);
                break;
            default:
                calculatedValue = -1;
                break;
        }
        switch (rarity){
            case Common:
                calculatedValue = calculatedValue + commonMod;
                break;
            case Uncommon:
                calculatedValue = calculatedValue + uncommonMod;
                break;
            case Rare:
                calculatedValue = calculatedValue + rareMod;
                break;
            case Epic:
                calculatedValue = calculatedValue + epicMod;
                break;
            case Legendary:
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
            case Common:
                return ChatColor.WHITE;
            case Uncommon:
                return ChatColor.GREEN;
            case Rare:
                return ChatColor.BLUE;
            case Epic:
                return ChatColor.DARK_PURPLE;
            case Legendary:
                return ChatColor.GOLD;
            default:
                return ChatColor.RED;
        }
    }
    private static boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }
    private static String createUUID(){
        return UUID.randomUUID().toString();
    }
    public static String convertToInvisibleString(String s) {
        String hidden = "";
        for (char c : s.toCharArray()) hidden += ChatColor.COLOR_CHAR+""+c;
        return hidden;
    }
}
