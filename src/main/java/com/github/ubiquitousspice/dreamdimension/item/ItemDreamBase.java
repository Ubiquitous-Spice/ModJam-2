package com.github.ubiquitousspice.dreamdimension.item;

import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.github.ubiquitousspice.dreamdimension.dimension.WorldProviderMod;

public class ItemDreamBase extends Item
{

    private String    normalName;
    private String    dreamName;

    protected boolean inDreamWorld;

    public ItemDreamBase(int par1, String a, String b)
    {
        super(par1);
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
