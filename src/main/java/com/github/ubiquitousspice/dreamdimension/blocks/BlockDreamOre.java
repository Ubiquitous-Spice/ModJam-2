package com.github.ubiquitousspice.dreamdimension.blocks;

import java.util.ArrayList;
import java.util.Random;

import com.github.ubiquitousspice.dreamdimension.DreamDimension;

import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class BlockDreamOre extends BlockDreamBase
{

    public BlockDreamOre(int par1, Material par2Material)
    {
        super(par1, par2Material);

    }

    public int idDropped(int par1, Random par2Random, int par3)
    {
        int i = par2Random.nextInt(3);
        return (i == 1) ? DreamDimension.fakeDiamond.itemID : DreamDimension.dreamDirt.blockID;
    }

    protected void dropXpOnBlockBreak(World par1World, int par2, int par3, int par4, int par5)
    {
        if (!par1World.isRemote)
        {
            int i1 = EntityXPOrb.getXPSplit(par5);
            par5 -= i1;
            par1World.spawnEntityInWorld(new EntityXPOrb(par1World, (double) par2 + 0.5D, (double) par3 + 0.5D, (double) par4 + 0.5D, i1));
        }
    }

}
