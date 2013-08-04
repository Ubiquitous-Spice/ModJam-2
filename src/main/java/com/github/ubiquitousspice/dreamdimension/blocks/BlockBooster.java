package com.github.ubiquitousspice.dreamdimension.blocks;

import com.github.ubiquitousspice.dreamdimension.DreamDimension;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.*;
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

import static net.minecraftforge.common.ForgeDirection.*;

public class BlockBooster extends BlockDreamBase
{
    public static final int MAX_SPEED = 3;
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
    public boolean canPlaceBlockOnSide(World par1World, int par2, int par3, int par4, int par5)
    {
        ForgeDirection dir = ForgeDirection.getOrientation(par5);
        return (dir == DOWN  && par1World.isBlockSolidOnSide(par2, par3 + 1, par4, DOWN )) ||
               (dir == UP    && par1World.isBlockSolidOnSide(par2, par3 - 1, par4, UP   )) ||
               (dir == NORTH && par1World.isBlockSolidOnSide(par2, par3, par4 + 1, NORTH)) ||
               (dir == SOUTH && par1World.isBlockSolidOnSide(par2, par3, par4 - 1, SOUTH)) ||
               (dir == WEST  && par1World.isBlockSolidOnSide(par2 + 1, par3, par4, WEST )) ||
               (dir == EAST  && par1World.isBlockSolidOnSide(par2 - 1, par3, par4, EAST ));
    }

    @Override
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        return par1World.isBlockSolidOnSide(par2 - 1, par3, par4, EAST ) ||
               par1World.isBlockSolidOnSide(par2 + 1, par3, par4, WEST ) ||
               par1World.isBlockSolidOnSide(par2, par3, par4 - 1, SOUTH) ||
               par1World.isBlockSolidOnSide(par2, par3, par4 + 1, NORTH) ||
               par1World.isBlockSolidOnSide(par2, par3 - 1, par4, UP   ) ||
               par1World.isBlockSolidOnSide(par2, par3 + 1, par4, DOWN );
    }


    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float clickX, float clickY, float clickZ, int metadata)
    {
        // return opposite of side placed on.
        // 8 if its the booster block, 0 if its the launcher
        // TODO: THIS WORKS.
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
    public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side)
    {
        return side == ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z)).getOpposite();
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

                if (nmX > MAX_SPEED)
                    nmX = MAX_SPEED;
                else if (nmX < -MAX_SPEED)
                    nmX = -MAX_SPEED;
            }

            if (dir.offsetY == 0)
            {
                nmY = 2 * entity.motionY;

                if (nmY > MAX_SPEED)
                    nmY = MAX_SPEED;
                else if (nmY < -MAX_SPEED)
                    nmY = -MAX_SPEED;
            }

            if (dir.offsetZ == 0)
            {
                nmZ = 2 * entity.motionZ;

                if (nmZ > MAX_SPEED)
                    nmZ = MAX_SPEED;
                else if (nmZ < -MAX_SPEED)
                    nmZ = -MAX_SPEED;
            }
        }
        else
        {
            if (dir.offsetX != 0)
            {
                nmX = -2 * entity.motionX;

                if (nmX > MAX_SPEED)
                    nmX = MAX_SPEED;
                else if (nmX < -MAX_SPEED)
                    nmX = -MAX_SPEED;
            }

            if (dir.offsetY != 0)
            {
                nmY = -2 * entity.motionY;

                if (nmY > MAX_SPEED)
                    nmY = MAX_SPEED;
                else if (nmY < -MAX_SPEED)
                    nmY = -MAX_SPEED;
            }
            else if (nmY == 0)
            {
                nmY = 1;
            }

            if (dir.offsetZ != 0)
            {
                nmZ = -2 * entity.motionZ;

                if (nmZ > MAX_SPEED)
                    nmZ = MAX_SPEED;
                else if (nmZ < -MAX_SPEED)
                    nmZ = -MAX_SPEED;
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

                if (nmX > MAX_SPEED)
                    nmX = MAX_SPEED;
                else if (nmX < -MAX_SPEED)
                    nmX = -MAX_SPEED;
            }

            if (dir.offsetY == 0)
            {
                nmY = 2 * entity.motionY;

                if (nmY > MAX_SPEED)
                    nmY = MAX_SPEED;
                else if (nmY < -MAX_SPEED)
                    nmY = -MAX_SPEED;
            }

            if (dir.offsetZ == 0)
            {
                nmZ = 2 * entity.motionZ;

                if (nmZ > MAX_SPEED)
                    nmZ = MAX_SPEED;
                else if (nmZ < -MAX_SPEED)
                    nmZ = -MAX_SPEED;
            }
        }
        else
        {
            if (dir.offsetX != 0)
            {
                nmX = -2 * entity.motionX;

                if (nmX > MAX_SPEED)
                    nmX = MAX_SPEED;
                else if (nmX < -MAX_SPEED)
                    nmX = -MAX_SPEED;
            }

            if (dir.offsetY != 0)
            {
                nmY = -2 * entity.motionY;

                if (nmY > MAX_SPEED)
                    nmY = MAX_SPEED;
                else if (nmY < -MAX_SPEED)
                    nmY = -MAX_SPEED;
            }

            if (dir.offsetZ != 0)
            {
                nmZ = -2 * entity.motionZ;

                if (nmZ > MAX_SPEED)
                    nmZ = MAX_SPEED;
                else if (nmZ < -MAX_SPEED)
                    nmZ = -MAX_SPEED;
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

    @Override
    public void onFallenUpon(World world, int x, int y, int z, Entity entity, float fallDistance)
    {
        super.onFallenUpon(world, x, y, z, entity, fallDistance);

        if (entity.isSneaking())
            return;

        // rebound entity.
        boolean isSpeed = (world.getBlockMetadata(x, y, z) & 8) == 8;
        ForgeDirection dir = ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z) & 7);

        double nmX = entity.motionX;
        double nmY = entity.motionY;
        double nmZ = entity.motionZ;

        if (!isSpeed)
        {
            if (dir.offsetX != 0)
            {
                nmX = -2 * entity.motionX;

                if (nmX > MAX_SPEED)
                    nmX = MAX_SPEED;
                else if (nmX < -MAX_SPEED)
                    nmX = -MAX_SPEED;
            }

            if (dir.offsetY != 0)
            {
                nmY = -2 * entity.motionY;

                if (nmY > MAX_SPEED)
                    nmY = MAX_SPEED;
                else if (nmY < -MAX_SPEED)
                    nmY = -MAX_SPEED;
            }

            if (dir.offsetZ != 0)
            {
                nmZ = -2 * entity.motionZ;

                if (nmZ > MAX_SPEED)
                    nmZ = MAX_SPEED;
                else if (nmZ < -MAX_SPEED)
                    nmZ = -MAX_SPEED;
            }
        }

        entity.setVelocity(nmX, nmY, nmZ);
    }

    @Override
    public int damageDropped(int metadata)
    {
        return metadata & 8;
    }
}
