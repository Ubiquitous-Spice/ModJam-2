package com.github.ubiquitousspice.dreamdimension.client;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import com.github.ubiquitousspice.dreamdimension.DreamDimension;

public class CreativeTabDream extends CreativeTabs
{
    public CreativeTabDream()
    {
        super("DreamStuff");
    }

    @Override
    public ItemStack getIconItemStack()
    {
        return new ItemStack(DreamDimension.unicornSword);
    }
}
