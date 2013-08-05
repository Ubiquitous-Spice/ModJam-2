package com.github.ubiquitousspice.dreamdimension.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import com.github.ubiquitousspice.dreamdimension.DreamDimension;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDreamLog extends BlockRotatedPillar
{
    Icon top, side;

    public BlockDreamLog(int par1)
    {
        super(par1, Material.wood);
    }

    @Override
    public void onFallenUpon(World world, int x, int y, int z, Entity entity, float fallDistance)
    {
        entity.fallDistance = 0;
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 1;
    }

    @Override
    public int idDropped(int par1, Random random, int par3)
    {
        return blockID;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int id, int meta)
    {
        byte b0 = 4;
        int j1 = b0 + 1;

        if (world.checkChunksExist(x - j1, y - j1, z - j1, x + j1, y + j1, z + j1))
        {
            for (int k1 = -b0; k1 <= b0; ++k1)
            {
                for (int l1 = -b0; l1 <= b0; ++l1)
                {
                    for (int i2 = -b0; i2 <= b0; ++i2)
                    {
                        int j2 = world.getBlockId(x + k1, y + l1, z + i2);

                        if (Block.blocksList[j2] != null)
                        {
                            Block.blocksList[j2].beginLeavesDecay(world, x + k1, y + l1, z + i2);
                        }
                    }
                }
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected Icon func_111048_c(int par1)
    {
        return side;
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected Icon func_111049_d(int par1)
    {
        return top;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister register)
    {
        top = register.registerIcon(DreamDimension.MODID + ":dreamWood_top");
        side = register.registerIcon(DreamDimension.MODID + ":dreamWood_side");
    }

    @Override
    public boolean canSustainLeaves(World world, int x, int y, int z)
    {
        return true;
    }

    @Override
    public boolean isWood(World world, int x, int y, int z)
    {
        return true;
    }
}
