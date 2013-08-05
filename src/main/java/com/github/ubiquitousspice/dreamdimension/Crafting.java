package com.github.ubiquitousspice.dreamdimension;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class Crafting
{

    public static void addRecipes()
    {
        Block dreamFleece = DreamDimension.dreamFleece;
        Block dreamPlank = DreamDimension.dreamPlanks;

        Item unicornHorn = DreamDimension.unicornHorn;
        Item stick = Item.stick;

        // Shaped
        GameRegistry.addRecipe(new ItemStack(DreamDimension.fleeceHelmet), "xxx", "x x", "   ", 'x', dreamFleece);
        GameRegistry.addRecipe(new ItemStack(DreamDimension.fleeceChest), "x x", "xxx", "xxx", 'x', dreamFleece);
        GameRegistry.addRecipe(new ItemStack(DreamDimension.fleeceLegs), "xxx", "x x", "x x", 'x', dreamFleece);
        GameRegistry.addRecipe(new ItemStack(DreamDimension.fleeceBoots), "   ", "x x", "x x", 'x', dreamFleece);
        GameRegistry.addRecipe(new ItemStack(DreamDimension.unicornSword), " y ", " y ", " z ", 'x', unicornHorn, 'y', dreamPlank, 'z', stick);

        // Shapeless
        GameRegistry.addShapelessRecipe(new ItemStack(DreamDimension.dreamPlanks, 4), new ItemStack(DreamDimension.dreamLog));

    }

}
