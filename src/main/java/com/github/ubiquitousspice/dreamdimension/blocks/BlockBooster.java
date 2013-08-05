package com.github.ubiquitousspice.dreamdimension.blocks;

import static net.minecraftforge.common.ForgeDirection.DOWN;
import static net.minecraftforge.common.ForgeDirection.EAST;
import static net.minecraftforge.common.ForgeDirection.NORTH;
import static net.minecraftforge.common.ForgeDirection.SOUTH;
import static net.minecraftforge.common.ForgeDirection.UP;
import static net.minecraftforge.common.ForgeDirection.WEST;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import com.github.ubiquitousspice.dreamdimension.DreamDimension;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBooster extends BlockDreamBase
{
    public static final int   MAX_SPEED   = 3;
    public static final float SPEED_MULT  = 1.5f;
    public static final float BOUNCE_MULT = 1.5f;
    Icon                      speed, bounce;

    public BlockBooster(int par1)
    {
        super(par1, DreamDimension.material);
        slipperiness = 1f;
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
        return dir == DOWN && par1World.isBlockSolidOnSide(par2, par3 + 1, par4, DOWN) ||
                dir == UP && par1World.isBlockSolidOnSide(par2, par3 - 1, par4, UP) ||
                dir == NORTH && par1World.isBlockSolidOnSide(par2, par3, par4 + 1, NORTH) ||
                dir == SOUTH && par1World.isBlockSolidOnSide(par2, par3, par4 - 1, SOUTH) ||
                dir == WEST && par1World.isBlockSolidOnSide(par2 + 1, par3, par4, WEST) ||
                dir == EAST && par1World.isBlockSolidOnSide(par2 - 1, par3, par4, EAST);
    }

    @Override
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        return par1World.isBlockSolidOnSide(par2 - 1, par3, par4, EAST) ||
                par1World.isBlockSolidOnSide(par2 + 1, par3, par4, WEST) ||
                par1World.isBlockSolidOnSide(par2, par3, par4 - 1, SOUTH) ||
                par1World.isBlockSolidOnSide(par2, par3, par4 + 1, NORTH) ||
                par1World.isBlockSolidOnSide(par2, par3 - 1, par4, UP) ||
                par1World.isBlockSolidOnSide(par2, par3 + 1, par4, DOWN);
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
        setBlockBounds(0f, 0f, 0f, 1f, .2f, 1f);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
        switch (world.getBlockMetadata(x, y, z) & 7)
            {
                case 0:
                    setBlockBounds(0f, 0f, 0f, 1f, .2f, 1f);
                    break;
                case 1:
                    setBlockBounds(0f, .8f, 0f, 1f, 1f, 1f);
                    break;
                case 2:
                    setBlockBounds(0f, 0f, 0f, 1f, 1f, .2f);
                    break;
                case 3:
                    setBlockBounds(0f, 0f, .8f, 1f, 1f, 1f);
                    break;
                case 4:
                    setBlockBounds(0f, 0f, 0f, .2f, 1f, 1f);
                    break;
                case 5:
                    setBlockBounds(.8f, 0f, 0f, 1f, 1f, 1f);
                    break;
            }
    }

    @Override
    public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side)
    {
        return side == ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z)).getOpposite();
    }

    @Override
    public int damageDropped(int metadata)
    {
        return metadata & 8;
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
    public void onEntityWalking(World world, int x, int y, int z, Entity entity)
    {
        if (entity.isSneaking())
        {
            return;
        }

        // rebound entity.
        boolean isSpeed = (world.getBlockMetadata(x, y, z) & 8) == 8;
        ForgeDirection dir = ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z) & 7).getOpposite();

        if (isSpeed)
        {
            applySpeed(dir, entity);
        }
        else
        {
            applyBounce(dir, entity);
        }
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        if (entity.isSneaking())
        {
            return;
        }

        // rebound entity.
        boolean isSpeed = (world.getBlockMetadata(x, y, z) & 8) == 8;
        ForgeDirection dir = ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z) & 7).getOpposite();

        if (isSpeed)
        {
            applySpeed(dir, entity);
        }
        else
        {
            applyBounce(dir, entity);
        }
    }

    @Override
    public void onFallenUpon(World world, int x, int y, int z, Entity entity, float fallDistance)
    {
        super.onFallenUpon(world, x, y, z, entity, fallDistance);

        if (entity.isSneaking())
        {
            return;
        }

        // rebound entity.
        boolean isSpeed = (world.getBlockMetadata(x, y, z) & 8) == 8;
        ForgeDirection dir = ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z) & 7).getOpposite();

        if (isSpeed)
        {
            applySpeed(dir, entity);
        }
        else
        {
            applyBounce(dir, entity);
        }
    }

    private void applyBounce(ForgeDirection dir, Entity entity)
    {
        double nmX = entity.motionX;
        double nmY = entity.motionY;
        double nmZ = entity.motionZ;

        if (dir.offsetX != 0)
        {
            nmX = BOUNCE_MULT * Math.copySign(entity.motionX, dir.offsetX);

            if (nmX == 0)
            {
                nmX = BOUNCE_MULT * dir.offsetX;
            }
        }

        if (dir.offsetY != 0)
        {
            nmY = BOUNCE_MULT * Math.copySign(entity.motionY, dir.offsetY);

            if (nmY == 0)
            {
                nmY = BOUNCE_MULT * dir.offsetY;
            }
        }

        if (dir.offsetZ != 0)
        {
            nmZ = BOUNCE_MULT * Math.copySign(entity.motionZ, dir.offsetZ);

            if (nmZ == 0)
            {
                nmZ = BOUNCE_MULT * dir.offsetZ;
            }
        }

        nmX = clamp(nmX, MAX_SPEED);
        nmY = clamp(nmY, MAX_SPEED);
        nmZ = clamp(nmZ, MAX_SPEED);
        entity.setVelocity(nmX, nmY, nmZ);
    }

    private void applySpeed(ForgeDirection dir, Entity entity)
    {
        double nmX = entity.motionX;
        double nmY = entity.motionY;
        double nmZ = entity.motionZ;

        if (dir.offsetX == 0)
        {
            nmX = SPEED_MULT * entity.motionX;
        }

        if (dir.offsetY == 0)
        {
            nmY = SPEED_MULT * entity.motionY;
        }

        if (dir.offsetZ == 0)
        {
            nmZ = SPEED_MULT * entity.motionZ;

        }

        nmX = clamp(nmX, MAX_SPEED);
        nmY = clamp(nmY, MAX_SPEED);
        nmZ = clamp(nmZ, MAX_SPEED);
        entity.setVelocity(nmX, nmY, nmZ);
    }

    private double clamp(double input, double maxAbsolute)
    {
        if (input > maxAbsolute)
        {
            input = maxAbsolute;
        }
        else if (input < -maxAbsolute)
        {
            input = -maxAbsolute;
        }

        return input;
    }
}
