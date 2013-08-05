package com.github.ubiquitousspice.dreamdimension.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import com.github.ubiquitousspice.dreamdimension.DreamDimension;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDreamFleece extends Block
{
    public final int RADIUS = 1;

    public BlockDreamFleece(int par1)
    {
        super(par1, Material.cloth);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister register)
    {
        blockIcon = register.registerIcon(DreamDimension.MODID + ":dreamFleece");
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float clickX, float clickY, float clickZ, int meta)
    {
        int centerX, centerY, centerZ;
        ForgeDirection dir = ForgeDirection.getOrientation(side);
        centerX = dir.offsetX * RADIUS;
        centerY = dir.offsetY * RADIUS;
        centerZ = dir.offsetZ * RADIUS;

        // place the blocks.
        for (int testX = -RADIUS; testX <= RADIUS; testX++)
        {
            for (int testY = -RADIUS; testY <= RADIUS; testY++)
            {
                for (int testZ = -RADIUS; testZ <= RADIUS; testZ++)
                {
                    // change blockID... or if they are broken, give something else back.
                    //world.setBlock(x, y, z, blockID);
                    world.setBlock(centerX + x + testX, centerY + y + testY, centerZ + z + testZ, Block.cloth.blockID);
                }
            }
        }
        
        world.setBlock(x, y + 1, z, Block.cloth.blockID);

        return 0;
    }

    @Override
    public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side)
    {
        int centerX, centerY, centerZ;
        ForgeDirection dir = ForgeDirection.getOrientation(side);
        centerX = dir.offsetX * RADIUS;
        centerY = dir.offsetY * RADIUS;
        centerZ = dir.offsetZ * RADIUS;

        // check all the places.
        for (int testX = -RADIUS; testX <= RADIUS; testX++)
        {
            for (int testY = -RADIUS; testY <= RADIUS; testY++)
            {
                for (int testZ = -RADIUS; testZ <= RADIUS; testZ++)
                {
                    if (!world.isAirBlock(centerX + x + testX, centerY + y + testY, centerZ + z + testZ))
                    {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    @Override
    public void setBlockBoundsForItemRender()
    {
        setBlockBounds(-0.2f, -0.2f, -0.2f, 1.2f, 1.2f, 1.2f);
    }
    
    public void onBlockAdded(World par1World, int par2, int par3, int par4) {par1World.setBlock(par2, par3, par4, Block.cloth.blockID);}

}
