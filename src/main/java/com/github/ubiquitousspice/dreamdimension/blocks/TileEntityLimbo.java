package com.github.ubiquitousspice.dreamdimension.blocks;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;
import java.util.List;

public class TileEntityLimbo extends TileEntity
{
    ArrayList<ItemStack> stacks = new ArrayList<ItemStack>();

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public void addItem(ItemStack stack)
    {
        stacks.add(stack);
    }

    public List<ItemStack> getItems()
    {
        return stacks;
    }
}
