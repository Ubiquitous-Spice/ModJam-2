package com.github.ubiquitousspice.dreamdimension.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import com.github.ubiquitousspice.dreamdimension.DreamDimension;

public class BlockDreamBase extends Block
{
    public BlockDreamBase(int par1, Material par2Material)
    {
        super(par1, par2Material);
        setCreativeTab(DreamDimension.tabDream);
    }

    @Override
    public void onFallenUpon(World world, int x, int y, int z, Entity entity, float fallDistance)
    {
        entity.fallDistance = 0;
    }
}
