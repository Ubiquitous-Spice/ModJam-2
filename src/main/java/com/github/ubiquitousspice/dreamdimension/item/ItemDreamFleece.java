package com.github.ubiquitousspice.dreamdimension.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class ItemDreamFleece extends Item
{
    public final int RADIUS = 1;

    public ItemDreamFleece(int par1)
    {
        super(par1);
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float clickX, float clickY, float clickZ)
    {
        // uh.. do stuff only on server.
        if (world.isRemote)
            return false;

        boolean canWork;

        int centerX, centerY, centerZ;
        ForgeDirection dir = ForgeDirection.getOrientation(side);
        centerX = dir.offsetX * RADIUS + dir.offsetX;
        centerY = dir.offsetY * RADIUS + dir.offsetY;
        centerZ = dir.offsetZ * RADIUS + dir.offsetZ;

        // check all the places.
        for (int testX = -RADIUS; testX <= RADIUS; testX++)
        {
            for (int testY = -RADIUS; testY <= RADIUS; testY++)
            {
                for (int testZ = -RADIUS; testZ <= RADIUS; testZ++)
                {
                    if (!world.isAirBlock(centerX + x + testX, centerY + y + testY, centerZ + z + testZ))
                        return false;
                }
            }
        }

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

        stack.stackSize--;

        return true;
    }

}
