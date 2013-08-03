package com.github.ubiquitousspice.dreamdimension.blocks;

import com.github.ubiquitousspice.dreamdimension.DreamDimension;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import java.util.List;

public class BlockBooster extends Block
{
    Icon speed, bounce;

    public BlockBooster(int par1)
    {
        super(par1, DreamDimension.material);
        this.slipperiness = 1f;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
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
    public int getDamageValue(World par1World, int par2, int par3, int par4)
    {
        return this.damageDropped(par1World.getBlockMetadata(par2, par3, par4) & 8);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(int id, CreativeTabs tabs, List list)
    {
        list.add(new ItemStack(id, 1, 0));
        list.add(new ItemStack(id, 1, 8));
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        return null;
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float clickX, float clickY, float clickZ, int metadata)
    {
        // return opposite of side placed on.
        // 8 if its the booster block, 0 if its the launcher
        return ForgeDirection.OPPOSITES[side] + metadata;
    }

    @Override
    public void setBlockBoundsForItemRender()
    {
        setBlockBounds(0f, 0f, 0f, 1f, 1f, 1f);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
        switch (world.getBlockMetadata(x, y, z) & 7)
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
    public void onEntityWalking(World world, int x, int y, int z, Entity entity)
    {
        if (entity.isSneaking())
            return;

        // rebound entity.
        boolean isSpeed = (world.getBlockMetadata(x, y, z) & 8) == 8;
        ForgeDirection dir = ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z) & 7);

        double nmX = entity.motionX;
        double nmY = entity.motionY;
        double nmZ = entity.motionZ;

        if (isSpeed)
        {
            if (dir.offsetX == 0)
            {
                nmX = 2 * entity.motionX;

                if (nmX > 5)
                    nmX = 5;
                else if (nmX < -5)
                    nmX = -5;
            }

            if (dir.offsetY == 0)
            {
                nmY = 2 * entity.motionY;

                if (nmY > 5)
                    nmY = 5;
                else if (nmY < -5)
                    nmY = -5;
            }

            if (dir.offsetZ == 0)
            {
                nmZ = 2 * entity.motionZ;

                if (nmZ > 5)
                    nmZ = 5;
                else if (nmZ < -5)
                    nmZ = -5;
            }
        }
        else
        {
            if (dir.offsetX != 0)
            {
                nmX = -2 * entity.motionX;

                if (nmX > 5)
                    nmX = 5;
                else if (nmX < -5)
                    nmX = -5;
            }

            if (dir.offsetY != 0)
            {
                nmY = -2 * entity.motionY;

                if (nmY > 5)
                    nmY = 5;
                else if (nmY < -5)
                    nmY = -5;
            }
            else if (nmY == 0)
            {
                nmY = 1;
            }

            if (dir.offsetZ != 0)
            {
                nmZ = -2 * entity.motionZ;

                if (nmZ > 5)
                    nmZ = 5;
                else if (nmZ < -5)
                    nmZ = -5;
            }
        }

        entity.setVelocity(nmX, nmY, nmZ);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        if (entity.isSneaking())
            return;

        // rebound entity.
        boolean isSpeed = (world.getBlockMetadata(x, y, z) & 8) == 8;
        ForgeDirection dir = ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z) & 7);

        double nmX = entity.motionX;
        double nmY = entity.motionY;
        double nmZ = entity.motionZ;

        if (isSpeed)
        {
            if (dir.offsetX == 0)
            {
                nmX = 2 * entity.motionX;

                if (nmX > 5)
                    nmX = 5;
                else if (nmX < -5)
                    nmX = -5;
            }

            if (dir.offsetY == 0)
            {
                nmY = 2 * entity.motionY;

                if (nmY > 5)
                    nmY = 5;
                else if (nmY < -5)
                    nmY = -5;
            }

            if (dir.offsetZ == 0)
            {
                nmZ = 2 * entity.motionZ;

                if (nmZ > 5)
                    nmZ = 5;
                else if (nmZ < -5)
                    nmZ = -5;
            }
        }
        else
        {
            if (dir.offsetX != 0)
            {
                nmX = -2 * entity.motionX;

                if (nmX > 5)
                    nmX = 5;
                else if (nmX < -5)
                    nmX = -5;
            }

            if (dir.offsetY != 0)
            {
                nmY = -2 * entity.motionY;

                if (nmY > 5)
                    nmY = 5;
                else if (nmY < -5)
                    nmY = -5;
            }

            if (dir.offsetZ != 0)
            {
                nmZ = -2 * entity.motionZ;

                if (nmZ > 5)
                    nmZ = 5;
                else if (nmZ < -5)
                    nmZ = -5;
            }
        }

        entity.setVelocity(nmX, nmY, nmZ);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta)
    {
        return (meta & 8) == 8 ? speed : bounce;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister register)
    {
        bounce = register.registerIcon(DreamDimension.MODID + ":dreamBouncer");
        speed = register.registerIcon(DreamDimension.MODID + ":dreamSpeeder");
    }

}
