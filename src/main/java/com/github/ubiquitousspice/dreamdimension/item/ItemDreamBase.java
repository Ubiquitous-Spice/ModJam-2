package com.github.ubiquitousspice.dreamdimension.item;

import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import com.github.ubiquitousspice.dreamdimension.DreamDimension;
import com.github.ubiquitousspice.dreamdimension.dimension.WorldProviderMod;

public class ItemDreamBase extends Item
{

    private String name;

    protected boolean inDreamWorld;

    public ItemDreamBase(int par1, String a)
    {
        super(par1);
        this.name = a;
    }

    @Override
    public String getItemDisplayName(ItemStack stack)
    {

        inDreamWorld = Minecraft.getMinecraft().thePlayer.worldObj.provider instanceof WorldProviderMod;
        String end = inDreamWorld ? "dreamName" : "name";

        // speeder.
        return StatCollector.translateToLocal("tile." + DreamDimension.MODID + ":" + name + "." + end);
    }

}
