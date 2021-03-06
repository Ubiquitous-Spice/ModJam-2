package com.github.ubiquitousspice.dreamdimension.item;

import com.github.ubiquitousspice.dreamdimension.DreamDimension;
import com.github.ubiquitousspice.dreamdimension.dimension.WorldProviderMod;

import net.minecraft.client.Minecraft;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemDreamSpade extends ItemSpade
{

    private String name;

    private boolean inDreamWorld;

    public ItemDreamSpade(int par1, EnumToolMaterial mat, String a)
    {
        super(par1, mat);
        name = a;
    }

    @Override
    public String getItemDisplayName(ItemStack stack)
    {
        inDreamWorld = Minecraft.getMinecraft().thePlayer.worldObj.provider instanceof WorldProviderMod;
        String end = inDreamWorld ? "dreamName" : "name";

        return StatCollector.translateToLocal("tile." + DreamDimension.MODID + ":" + name + "." + end);
    }

}
