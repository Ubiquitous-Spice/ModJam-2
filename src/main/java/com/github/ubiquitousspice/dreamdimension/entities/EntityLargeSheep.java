package com.github.ubiquitousspice.dreamdimension.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingData;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.github.ubiquitousspice.dreamdimension.DreamDimension;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityLargeSheep extends EntityAnimal implements IBossDisplayData
{

    public static float largeSheepMod = 7;
    public static int   sheepHealth   = 100;

    private int         sheepTimer;

    public EntityLargeSheep(World par1World)
    {
        super(par1World);
        setAIMoveSpeed(3F);
        renderDistanceWeight = 80.0D;
        setSize(0.9F * largeSheepMod, 1.3F * largeSheepMod);
        setEntityHealth(sheepHealth);

        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIPanic(this, 1.25D));
        tasks.addTask(2, new EntityAIMate(this, 1.0D));
        tasks.addTask(3, new EntityAITempt(this, 1.1D, Item.wheat.itemID, false));
        tasks.addTask(4, new EntityAIWander(this, 1.0D));
        tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        tasks.addTask(6, new EntityAILookIdle(this));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getShadowSize()
    {
        return largeSheepMod;
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEFINED;
    }

    public int getAttackStrength(Entity par1Entity)
    {
        return 4;
    }

    @Override
    public int getTotalArmorValue()
    {
        return 2;
    }

    @Override
    public void onLivingUpdate()
    {

        if (worldObj.isRemote)
        {
            sheepTimer = Math.max(0, sheepTimer - 1);
        }

        if (!worldObj.isRemote)
        {
            limbYaw /= 8.0D;
            limbSwing /= 8.0D;
        }

        super.onLivingUpdate();
    }

    @Override
    protected String getLivingSound()
    {
        return "mob.sheep.say";
    }

    @Override
    protected String getHurtSound()
    {
        return "mob.sheep.say";
    }

    @Override
    protected String getDeathSound()
    {
        return "mob.sheep.say";
    }

    @Override
    protected float getSoundPitch()
    {
        return -80000.0F;
    }

    @Override
    protected void playStepSound(int par1, int par2, int par3, int par4)
    {
        playSound("mob.sheep.step", 0.15F, 1.0F);
    }

    @Override
    public EntityAgeable createChild(EntityAgeable entityageable)
    {

        return null;
    }

    @SideOnly(Side.CLIENT)
    public float func_70894_j(float par1)
    {
        return sheepTimer <= 0 ? 0.0F : sheepTimer >= 4 && sheepTimer <= 36 ? 1.0F : sheepTimer < 4 ? (sheepTimer - par1) / 4.0F : -((sheepTimer - 40) - par1) / 4.0F;
    }

    @SideOnly(Side.CLIENT)
    public float func_70890_k(float par1)
    {
        if (sheepTimer > 4 && sheepTimer <= 36)
        {
            float f1 = ((sheepTimer - 4) - par1) / 32.0F;
            return (float) Math.PI / 5F + (float) Math.PI * 7F / 100F * MathHelper.sin(f1 * 28.7F);
        }
        else
        {
            return sheepTimer > 0 ? (float) Math.PI / 5F : rotationPitch / (180F / (float) Math.PI);
        }
    }

    @Override
    public String getEntityName()
    {

        return "King Lambchop";
    }

    @Override
    public void onDeath(DamageSource par1DamageSource)
    {
        if (!worldObj.isRemote)
        {
            //this.worldObj.spawnEntityInWorld(new EntityGiantItem(worldObj, this.posX, this.posY, this.posZ, new ItemStack(DreamDimension.giantWool)));

            worldObj.spawnEntityInWorld(new EntityItem(worldObj, posX, posY, posZ, new ItemStack(DreamDimension.dreamFleece)));
        }

        super.onDeath(par1DamageSource);
    }

    @Override
    public boolean getCanSpawnHere()
    {

        int i = MathHelper.floor_double(posX);
        int j = MathHelper.floor_double(boundingBox.minY);
        int k = MathHelper.floor_double(posZ);

        return worldObj.getBlockId(i, j - 1, k) == DreamDimension.dreamDirt.blockID;
    }

    @Override
    public EntityLivingData func_110161_a(EntityLivingData par1EntityLivingData)
    {
        par1EntityLivingData = super.func_110161_a(par1EntityLivingData);

        int i = 15;

        if (rand.nextInt(2) == 1)
        {
            i = rand.nextInt(EntitySheep.fleeceColorTable.length);
        }

        if (rand.nextInt(5) == 1)
        {

            PotionEffect effect = new PotionEffect(Potion.invisibility.id, 999999999, 1);
            effect.setPotionDurationMax(true);
            addPotionEffect(effect);
        }

        setFleeceColor(15 - i);

        return par1EntityLivingData;
    }

    public void setFleeceColor(int par1)
    {
        byte b0 = dataWatcher.getWatchableObjectByte(16);
        dataWatcher.updateObject(16, Byte.valueOf((byte) (b0 & 240 | par1 & 15)));
    }

    public int getFleeceColor()
    {
        return dataWatcher.getWatchableObjectByte(16) & 15;
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        dataWatcher.addObject(16, new Byte((byte) 0));
    }

}
