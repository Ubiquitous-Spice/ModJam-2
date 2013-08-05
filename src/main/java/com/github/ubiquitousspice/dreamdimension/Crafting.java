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
        Block dreamWood = DreamDimension.dreamLog;

        Item unicornHorn = DreamDimension.unicornHorn;
        Item stick = Item.stick;
        Item diamond = Item.diamond;
        Item fakeDiamond = DreamDimension.fakeDiamond;
        Item magmaCream = Item.magmaCream;

        ItemStack dreamSword = new ItemStack(DreamDimension.unicornSword);

        // Shaped
        GameRegistry.addRecipe(new ItemStack(DreamDimension.fleeceHelmet), "xxx", "x x", "   ", 'x', dreamFleece);
        GameRegistry.addRecipe(new ItemStack(DreamDimension.fleeceChest), "x x", "xxx", "xxx", 'x', dreamFleece);
        GameRegistry.addRecipe(new ItemStack(DreamDimension.fleeceLegs), "xxx", "x x", "x x", 'x', dreamFleece);
        GameRegistry.addRecipe(new ItemStack(DreamDimension.fleeceBoots), "   ", "x x", "x x", 'x', dreamFleece);
        GameRegistry.addRecipe(new ItemStack(DreamDimension.unicornSword), " y ", "yyy", " z ", 'x', unicornHorn, 'y', dreamPlank, 'z', stick);
        GameRegistry.addRecipe(new ItemStack(DreamDimension.unicornSwordUpgrade), " x ", " y ", " z ", 'x', diamond, 'y', unicornHorn, 'z', dreamSword);
        GameRegistry.addRecipe(new ItemStack(DreamDimension.fDiamondSword), " x ", " x ", " z ", 'x', fakeDiamond, 'z', stick);
        GameRegistry.addRecipe(new ItemStack(DreamDimension.fDiamondPickaxe), "xxx", " z ", " z ", 'x', fakeDiamond, 'z', stick);
        GameRegistry.addRecipe(new ItemStack(DreamDimension.fDiamondShovel), " x ", " z ", " z ", 'x', fakeDiamond, 'z', stick);
        GameRegistry.addRecipe(new ItemStack(DreamDimension.fDiamondAxe), "xx ", "xz ", " z ", 'x', fakeDiamond, 'z', stick);
        GameRegistry.addRecipe(new ItemStack(DreamDimension.fDiamondAxe), " xx", " zx", " z ", 'x', fakeDiamond, 'z', stick);
        GameRegistry.addRecipe(new ItemStack(DreamDimension.dreamCatcher), "xxx", "xyx", "xxx", 'x', stick, 'y', dreamFleece);
        GameRegistry.addRecipe(new ItemStack(DreamDimension.limbo), "xzx", "xyx", "xyx", 'x', dreamWood, 'z', DreamDimension.dreamCatcher, 'y', unicornHorn);

        // Shapeless
        GameRegistry.addShapelessRecipe(new ItemStack(DreamDimension.dreamPlanks, 4), new ItemStack(DreamDimension.dreamLog));
        GameRegistry.addShapelessRecipe(new ItemStack(Item.diamond), fakeDiamond, fakeDiamond, fakeDiamond, fakeDiamond, fakeDiamond, fakeDiamond, fakeDiamond, unicornHorn, magmaCream);

    }

}
