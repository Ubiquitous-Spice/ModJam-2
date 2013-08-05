package com.github.ubiquitousspice.dreamdimension.item;

import com.github.ubiquitousspice.dreamdimension.dimension.WorldProviderMod;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumHelper;

public class ItemDreamSword extends ItemSword
{

    private String normalName;
    private String dreamName;
    
    private boolean inDreamWorld;

    public static EnumToolMaterial mat = EnumToolMaterial.WOOD;
    
    public ItemDreamSword(int par1, String a, String b)
    {
        super(par1, mat);
        this.normalName = a;
        this.dreamName = b;
    }
    
    public String getItemDisplayName(ItemStack stack)
    {

        this.inDreamWorld = Minecraft.getMinecraft().thePlayer.worldObj.provider instanceof WorldProviderMod;
        return (this.inDreamWorld) ? dreamName : normalName;
    }
    
}
