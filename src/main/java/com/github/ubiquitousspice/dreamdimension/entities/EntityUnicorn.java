package com.github.ubiquitousspice.dreamdimension.entities;

import com.github.ubiquitousspice.dreamdimension.DreamDimension;

import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAIRunAroundLikeCrazy;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityUnicorn extends EntityFlying implements IMob
{

    public int courseChangeCooldown;
    public double waypointX;
    public double waypointY;
    public double waypointZ;
    private float eating = 0.00F;
    private float jumping = 0.00F;
    private float actionTicks;

    public EntityUnicorn(World par1World)
    {
        super(par1World);
        this.setSize(1.4F, 1.6F);
        this.isImmuneToFire = true;
        this.experienceValue = 5;
    }

    public float getEating()
    {
        return this.eating;
    }

    public float getJumping()
    {
        return this.jumping;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_110182_bF()
    {
        return this.dataWatcher.getWatchableObjectByte(16) != 0;
    }

    @Override
    protected void func_110147_ax()
    {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10.0D);
    }

    @Override
    protected void updateEntityActionState()
    {
        if (!this.worldObj.isRemote && this.worldObj.difficultySetting == 0)
        {
            this.setDead();
        }

        this.despawnEntity();
        double d0 = this.waypointX - this.posX;
        double d1 = this.waypointY - this.posY;
        double d2 = this.waypointZ - this.posZ;
        double d3 = d0 * d0 + d1 * d1 + d2 * d2;

        if (d3 < 1.0D || d3 > 3600.0D)
        {
            this.waypointX = this.posX + (double) ((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.waypointY = this.posY + (double) ((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.waypointZ = this.posZ + (double) ((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
        }

        if (this.courseChangeCooldown-- <= 0)
        {
            this.courseChangeCooldown += this.rand.nextInt(5) + 2;
            d3 = (double) MathHelper.sqrt_double(d3);

            if (this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, d3))
            {
                this.motionX += d0 / d3 * 0.1D;
                this.motionY += d1 / d3 * 0.1D;
                this.motionZ += d2 / d3 * 0.1D;
            }
            else
            {
                this.waypointX = this.posX;
                this.waypointY = this.posY;
                this.waypointZ = this.posZ;
            }
        }

        double d4 = 64.0D;
    }

    /**
     * True if the ghast has an unobstructed line of travel to the waypoint.
     */
    private boolean isCourseTraversable(double par1, double par3, double par5, double par7)
    {
        double d4 = (this.waypointX - this.posX) / par7;
        double d5 = (this.waypointY - this.posY) / par7;
        double d6 = (this.waypointZ - this.posZ) / par7;
        AxisAlignedBB axisalignedbb = this.boundingBox.copy();

        for (int i = 1; (double) i < par7; ++i)
        {
            axisalignedbb.offset(d4, d5, d6);

            if (!this.worldObj.getCollidingBoundingBoxes(this, axisalignedbb).isEmpty())
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

    public void onDeath(DamageSource par1DamageSource)
    {
        if (!this.worldObj.isRemote)
        {
            //this.worldObj.spawnEntityInWorld(new EntityGiantItem(worldObj, this.posX, this.posY, this.posZ, new ItemStack(DreamDimension.giantWool)));

            this.worldObj.spawnEntityInWorld(new EntityItem(worldObj, this.posX, this.posY, this.posZ, new ItemStack(DreamDimension.unicornHorn)));
        }

        super.onDeath(par1DamageSource);
    }

    @Override
    protected void updateFallState(double par1, boolean par3)
    {
        this.motionY -= 0.01;

        /**if(this.posY >= 75)
         {
         this.motionY -= 0.05;
         }*/
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        this.actionTicks += 1;

        if (this.jumping == 0 && this.eating == 0)
        {
            int i = this.rand.nextInt(200);

            if (i == 1)
            {
                this.jumping = 1;
                this.actionTicks = 0;
            }

            if (i >= 160)
            {
                this.eating = 1;
                this.actionTicks = 0;
            }

        }

        if (actionTicks >= 20)
        {
            this.jumping = 0.0F;
            this.eating = 0.0F;
        }
    }

    @Override
    public boolean getCanSpawnHere()
    {

        int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_double(this.boundingBox.minY);
        int k = MathHelper.floor_double(this.posZ);

        return this.worldObj.getBlockId(i, j - 1, k) == DreamDimension.dreamDirt.blockID;
    }
}
