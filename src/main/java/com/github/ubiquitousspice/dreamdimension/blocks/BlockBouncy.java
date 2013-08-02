package com.github.ubiquitousspice.dreamdimension.blocks;

import com.github.ubiquitousspice.dreamdimension.DreamDimension;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockBouncy extends Block
{
    public BlockBouncy(int par1)
    {
        super(par1, DreamDimension.material);
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 1;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        // TODO: change to be like snow.
//        float f = 0.3f;
//        return AxisAlignedBB.getAABBPool().getAABB((double) ((float) x + f), (double) y, (double) ((float) z + f),
//                (double) ((float) (x + 1) - f), (double) ((float) (y + 1) - f), (double) ((float) (z + 1) - f));
//        return AxisAlignedBB.getAABBPool().getAABB(0f, 0f, 0f, 1f, .2f, 1f);
        return null;
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float clickX, float clickY, float clickZ,
                             int metadata)
    {
        // return opposite of side placed on.
        return ForgeDirection.OPPOSITES[side];
    }

    @Override
    public void setBlockBoundsForItemRender()
    {
        setBlockBounds(0f, 0f, 0f, 1f, 1f, 1f);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
        switch (world.getBlockMetadata(x, y, z))
        {
            case 0:
                this.setBlockBounds(0f, 0f, 0f, 1f, .2f, 1f);
                break;
            case 1:
                this.setBlockBounds(0f, .8f, 0f, 1f, 1f, 1f);
                break;
            case 2:
                this.setBlockBounds(0f, 0f, 0f, 1f, 1f, .2f);
                break;
            case 3:
                this.setBlockBounds(0f, 0f, .8f, 1f, 1f, 1f);
                break;
            case 4:
                this.setBlockBounds(0f, 0f, 0f, .2f, 1f, 1f);
                break;
            case 5:
                this.setBlockBounds(.8f, 0f, 0f, 1f, 1f, 1f);
                break;
        }
    }


    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        // rebound entity.
        int meta = world.getBlockMetadata(x, y, z);
        ForgeDirection dir = ForgeDirection.getOrientation(meta);

        double nmX = entity.motionX;
        double nmY = entity.motionY;
        double nmZ = entity.motionZ;

        if (dir.offsetX != 0)
        {
            nmX = -2 * entity.motionX;
        }

        if (dir.offsetY != 0)
        {
            nmY = -2 * entity.motionY;
        }

        if (dir.offsetZ != 0)
        {
            nmZ = -2 * entity.motionZ;
        }

        entity.setVelocity(nmX, nmY, nmZ);
    }

}
