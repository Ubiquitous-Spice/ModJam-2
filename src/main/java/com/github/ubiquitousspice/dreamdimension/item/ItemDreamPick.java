package com.github.ubiquitousspice.dreamdimension.item;

import com.github.ubiquitousspice.dreamdimension.dimension.WorldProviderMod;

import net.minecraft.client.Minecraft;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

public class ItemDreamPick extends ItemPickaxe
{
    private String    normalName;
    private String    dreamName;

    protected boolean inDreamWorld;
    
    public ItemDreamPick(int par1, EnumToolMaterial par2EnumToolMaterial, String a, String b)
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
