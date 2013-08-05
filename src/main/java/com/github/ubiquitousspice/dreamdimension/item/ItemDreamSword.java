package com.github.ubiquitousspice.dreamdimension.item;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

import com.github.ubiquitousspice.dreamdimension.dimension.WorldProviderMod;

public class ItemDreamSword extends ItemSword
{

    private String  normalName;
    private String  dreamName;

    private boolean inDreamWorld;

    public ItemDreamSword(int par1, EnumToolMaterial mat, String a, String b)
    {
        super(par1, mat);
        normalName = a;
        dreamName = b;
    }

    @Override
    public String getItemDisplayName(ItemStack stack)
    {

        inDreamWorld = Minecraft.getMinecraft().thePlayer.worldObj.provider instanceof WorldProviderMod;
        return inDreamWorld ? dreamName : normalName;
    }

}
