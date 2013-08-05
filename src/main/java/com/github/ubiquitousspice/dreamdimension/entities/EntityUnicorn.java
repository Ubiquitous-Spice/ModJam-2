package com.github.ubiquitousspice.dreamdimension.entities;

import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.github.ubiquitousspice.dreamdimension.DreamDimension;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityUnicorn extends EntityFlying implements IMob
{

    public int    courseChangeCooldown;
    public double waypointX;
    public double waypointY;
    public double waypointZ;
    private float eating  = 0.00F;
    private float jumping = 0.00F;
    private float actionTicks;

    public EntityUnicorn(World par1World)
    {
        super(par1World);
        setSize(1.4F, 1.6F);
        isImmuneToFire = true;
        experienceValue = 5;
    }

    public float getEating()
    {
        return eating;
    }

    public float getJumping()
    {
        return jumping;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_110182_bF()
    {
        return dataWatcher.getWatchableObjectByte(16) != 0;
    }

    @Override
    protected void func_110147_ax()
    {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(4.0D);
    }

    @Override
    protected void updateEntityActionState()
    {
        if (!worldObj.isRemote && worldObj.difficultySetting == 0)
        {
            setDead();
        }

        despawnEntity();
        double d0 = waypointX - posX;
        double d1 = waypointY - posY;
        double d2 = waypointZ - posZ;
        double d3 = d0 * d0 + d1 * d1 + d2 * d2;

        if (d3 < 1.0D || d3 > 3600.0D)
        {
            waypointX = posX + ((rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
            waypointY = posY + ((rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
            waypointZ = posZ + ((rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
        }

        if (courseChangeCooldown-- <= 0)
        {
            courseChangeCooldown += rand.nextInt(5) + 2;
            d3 = MathHelper.sqrt_double(d3);

            if (isCourseTraversable(waypointX, waypointY, waypointZ, d3))
            {
                motionX += d0 / d3 * 0.1D;
                motionY += d1 / d3 * 0.1D;
                motionZ += d2 / d3 * 0.1D;
            }
            else
            {
                waypointX = posX;
                waypointY = posY;
                waypointZ = posZ;
            }
        }
    }

    /**
     * True if the ghast has an unobstructed line of travel to the waypoint.
     */
    private boolean isCourseTraversable(double par1, double par3, double par5, double par7)
    {
        double d4 = (waypointX - posX) / par7;
        double d5 = (waypointY - posY) / par7;
        double d6 = (waypointZ - posZ) / par7;
        AxisAlignedBB axisalignedbb = boundingBox.copy();

        for (int i = 1; i < par7; ++i)
        {
            axisalignedbb.offset(d4, d5, d6);

            if (!worldObj.getCollidingBoundingBoxes(this, axisalignedbb).isEmpty())
            {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    @Override
    protected String getLivingSound()
    {
        return "mob.horse.idle";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected String getHurtSound()
    {
        return "mob.horse.hit";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    @Override
    protected String getDeathSound()
    {
        return "mob.horse.death";
    }

    @Override
    protected float getSoundPitch()
    {
        return 10000.0F;
    }

    /**
     * Will return how many at most can spawn in a chunk at once.
     */
    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
    }

    @Override
    public void onDeath(DamageSource par1DamageSource)
    {
        if (!worldObj.isRemote)
        {
            //this.worldObj.spawnEntityInWorld(new EntityGiantItem(worldObj, this.posX, this.posY, this.posZ, new ItemStack(DreamDimension.giantWool)));

            worldObj.spawnEntityInWorld(new EntityItem(worldObj, posX, posY, posZ, new ItemStack(DreamDimension.unicornHorn)));
        }

        super.onDeath(par1DamageSource);
    }

    @Override
    protected void updateFallState(double par1, boolean par3)
    {
        motionY -= 0.01;

        /**
         * if(this.posY >= 75)
         * {
         * this.motionY -= 0.05;
         * }
         */
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        actionTicks += 1;

        if (jumping == 0 && eating == 0)
        {
            int i = rand.nextInt(200);

            if (i == 1)
            {
                jumping = 1;
                actionTicks = 0;
            }

            if (i >= 160)
            {
                eating = 1;
                actionTicks = 0;
            }

        }

        if (actionTicks >= 20)
        {
            jumping = 0.0F;
            eating = 0.0F;
        }
    }

    @Override
    public boolean getCanSpawnHere()
    {

        int i = MathHelper.floor_double(posX);
        int j = MathHelper.floor_double(boundingBox.minY);
        int k = MathHelper.floor_double(posZ);

        return worldObj.getBlockId(i, j - 1, k) == DreamDimension.dreamDirt.blockID;
    }

}
