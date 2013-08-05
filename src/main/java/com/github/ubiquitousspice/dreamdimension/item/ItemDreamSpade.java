package com.github.ubiquitousspice.dreamdimension.item;

import com.github.ubiquitousspice.dreamdimension.dimension.WorldProviderMod;

import net.minecraft.client.Minecraft;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;

public class ItemDreamSpade extends ItemSpade
{

    private String    normalName;
    private String    dreamName;

    protected boolean inDreamWorld;

    public ItemDreamSpade(int par1, EnumToolMaterial par2EnumToolMaterial, String a, String b)
    {
        super(par1, par2EnumToolMaterial);
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
