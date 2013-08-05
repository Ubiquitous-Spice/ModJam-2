package com.github.ubiquitousspice.dreamdimension.blocks;

import java.util.Random;

import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import com.github.ubiquitousspice.dreamdimension.DreamDimension;
import com.github.ubiquitousspice.dreamdimension.dimension.world.WorldGenDreamTree;

public class BlockDreamSapling extends BlockFlower
{

    public BlockDreamSapling(int par1)
    {
        super(par1);
        float f = 0.4F;
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
    }

    @Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!par1World.isRemote)
        {
            super.updateTick(par1World, par2, par3, par4, par5Random);

            if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9 && par5Random.nextInt(7) == 0)
            {
                markOrGrowMarked(par1World, par2, par3, par4, par5Random);
            }
        }
    }

    public void markOrGrowMarked(World par1World, int par2, int par3, int par4, Random par5Random)
    {

        growTree(par1World, par2, par3, par4, par5Random);
    }

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        if (par5EntityPlayer.inventory.getCurrentItem().getItem() == Item.dyePowder && par5EntityPlayer.inventory.getCurrentItem().getItemDamage() == 15)
        {

            if (!par1World.isRemote)
            {
                if (par1World.rand.nextFloat() < 0.45D)
                {
                    ((BlockDreamSapling) DreamDimension.dreamSapling).markOrGrowMarked(par1World, par2, par3, par4, par1World.rand);
                }

                --par5EntityPlayer.inventory.getCurrentItem().stackSize;

                for (int j1 = 0; j1 < 15; ++j1)
                {
                    double d0 = par1World.rand.nextGaussian() * 0.02D;
                    double d1 = par1World.rand.nextGaussian() * 0.02D;
                    double d2 = par1World.rand.nextGaussian() * 0.02D;
                    par1World.spawnParticle("happyVillager", (par2 + par1World.rand.nextFloat()), par3 + par1World.rand.nextFloat() * getBlockBoundsMaxY(), (par4 + par1World.rand.nextFloat()), d0, d1, d2);
                }
            }
        }

        return true;
    }

    /**
     * Attempts to grow a sapling into a tree
     */
    public void growTree(World par1World, int x, int y, int z, Random par5Random)
    {
        par1World.setBlockToAir(x, y, z);

        WorldGenDreamTree tree = new WorldGenDreamTree();
        tree.generate(par1World, par5Random, x, y, z);
    }

    /**
     * Determines if the same sapling is present at the given location.
     */
    public boolean isSameSapling(World par1World, int par2, int par3, int par4, int par5)
    {
        return par1World.getBlockId(par2, par3, par4) == blockID && (par1World.getBlockMetadata(par2, par3, par4) & 3) == par5;
    }

    @Override
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        Material mat = par1World.getBlockMaterial(par2, par3 - 1, par4);

        return mat == Material.grass || mat == Material.ground ? true : false;
    }

    @Override
    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
        Material mat = par1World.getBlockMaterial(par2, par3 - 1, par4);

        return mat == Material.grass || mat == Material.ground ? true : false;
    }

    @Override
    protected boolean canThisPlantGrowOnThisBlockID(int par1)
    {
        return true;
    }

}
