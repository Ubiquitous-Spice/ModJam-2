package com.github.ubiquitousspice.dreamdimension.item;

import com.github.ubiquitousspice.dreamdimension.dimension.WorldProviderMod;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemDreamBase extends Item
{

    private String normalName;
    private String dreamName;

    protected boolean inDreamWorld;

    public ItemDreamBase(int par1, String a, String b)
    {
        super(par1);
        this.normalName = a;
        this.dreamName = b;
    }

    public String getItemDisplayName(ItemStack stack)
    {

        this.inDreamWorld = Minecraft.getMinecraft().thePlayer.worldObj.provider instanceof WorldProviderMod;
        return (this.inDreamWorld) ? dreamName : normalName;
    }

}
