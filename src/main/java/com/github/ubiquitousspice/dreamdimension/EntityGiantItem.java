package com.github.ubiquitousspice.dreamdimension;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityGiantItem extends EntityItem
{
    
    public EntityGiantItem(World par1World)
    {
        super(par1World);
        
    }

    public EntityGiantItem(World par1World, double posX, double posY, double posZ, ItemStack itemStack)
    {
        super(par1World, posX, posY, posZ, itemStack);
        
    }

}
