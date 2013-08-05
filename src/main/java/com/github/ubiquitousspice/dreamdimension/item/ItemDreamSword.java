package com.github.ubiquitousspice.dreamdimension.item;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.StatCollector;

import com.github.ubiquitousspice.dreamdimension.DreamDimension;
import com.github.ubiquitousspice.dreamdimension.dimension.WorldProviderMod;

public class ItemDreamSword extends ItemSword
{

    private String name;

    private boolean inDreamWorld;

    public ItemDreamSword(int par1, EnumToolMaterial mat, String a)
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
