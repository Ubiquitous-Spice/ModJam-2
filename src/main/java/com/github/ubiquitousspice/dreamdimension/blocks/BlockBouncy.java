package com.github.ubiquitousspice.dreamdimension.blocks;

import com.github.ubiquitousspice.dreamdimension.DreamDimension;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockBouncy extends Block
{
    public BlockBouncy(int par1)
    {
        super(par1, DreamDimension.material);
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float clickX, float clickY, float clickZ, int metadata)
    {
        // return opposite of side placed on.
        return ForgeDirection.OPPOSITES[side];
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        // TODO: change to be like snow.
        float f = 0.0625F;
        return AxisAlignedBB.getAABBPool().getAABB((double)((float)x + f), (double)y, (double)((float)z + f), (double)((float)(x + 1) - f), (double)((float)(y + 1) - f), (double)((float)(z + 1) - f));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
    {
        // TODO: change to be like snow.
        float f = 0.0625F;
        return AxisAlignedBB.getAABBPool().getAABB((double)((float)x + f), (double)y, (double)((float)z + f), (double)((float)(x + 1) - f), (double)(y + 1), (double)((float)(z + 1) - f));
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        // rebound entity.
        int meta = world.getBlockMetadata(x , y, z);
        ForgeDirection dir = ForgeDirection.getOrientation(meta);



        //entity.attackEntityFrom(DamageSource.cactus, 1.0F);
    }

}
