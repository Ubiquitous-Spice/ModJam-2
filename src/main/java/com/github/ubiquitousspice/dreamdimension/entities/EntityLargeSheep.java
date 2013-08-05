package com.github.ubiquitousspice.dreamdimension.entities;

import com.github.ubiquitousspice.dreamdimension.DreamDimension;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
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

public class EntityLargeSheep extends EntityAnimal implements IBossDisplayData
{

    public static float largeSheepMod = 7;
    public static int sheepHealth = 100;

    private int sheepTimer;

    public EntityLargeSheep(World par1World)
    {
        super(par1World);
        this.setAIMoveSpeed(3F);
        this.renderDistanceWeight = 80.0D;
        this.setSize(5F, 5F);
        this.setEntityHealth(sheepHealth);

        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 1.25D));
        this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(3, new EntityAITempt(this, 1.1D, Item.wheat.itemID, false));
        this.tasks.addTask(4, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
    }

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

        if (this.worldObj.isRemote)
        {
            this.sheepTimer = Math.max(0, this.sheepTimer - 1);
        }

        if (!this.worldObj.isRemote)
        {
            this.limbYaw /= 8.0D;
            this.limbSwing /= 8.0D;
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
        this.playSound("mob.sheep.step", 0.15F, 1.0F);
    }

    @Override
    public EntityAgeable createChild(EntityAgeable entityageable)
    {

        return null;
    }

    @SideOnly(Side.CLIENT)
    public float func_70894_j(float par1)
    {
        return this.sheepTimer <= 0 ? 0.0F : (this.sheepTimer >= 4 && this.sheepTimer <= 36 ? 1.0F : (this.sheepTimer < 4 ? ((float) this.sheepTimer - par1) / 4.0F : -((float) (this.sheepTimer - 40) - par1) / 4.0F));
    }

    @SideOnly(Side.CLIENT)
    public float func_70890_k(float par1)
    {
        if (this.sheepTimer > 4 && this.sheepTimer <= 36)
        {
            float f1 = ((float) (this.sheepTimer - 4) - par1) / 32.0F;
            return ((float) Math.PI / 5F) + ((float) Math.PI * 7F / 100F) * MathHelper.sin(f1 * 28.7F);
        }
        else
        {
            return this.sheepTimer > 0 ? ((float) Math.PI / 5F) : this.rotationPitch / (180F / (float) Math.PI);
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
        if (!this.worldObj.isRemote)
        {
            //this.worldObj.spawnEntityInWorld(new EntityGiantItem(worldObj, this.posX, this.posY, this.posZ, new ItemStack(DreamDimension.giantWool)));

            this.worldObj.spawnEntityInWorld(new EntityItem(worldObj, this.posX, this.posY, this.posZ, new ItemStack(DreamDimension.dreamFleece)));
        }

        super.onDeath(par1DamageSource);
    }

    @Override
    protected void func_110147_ax()
    {
        super.func_110147_ax();
        // Max Health - default 20.0D - min 0.0D - max Double.MAX_VALUE
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(100.0D);
        // Knockback Resistance - default 0.0D - min 0.0D - max 1.0D
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0D);
        // Movement Speed - default 0.699D - min 0.0D - max Double.MAX_VALUE
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.699D);
    }

    @Override
    public boolean getCanSpawnHere()
    {

        int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_double(this.boundingBox.minY);
        int k = MathHelper.floor_double(this.posZ);

        return this.worldObj.getBlockId(i, j - 1, k) == DreamDimension.dreamDirt.blockID;
    }

    @Override
    public EntityLivingData func_110161_a(EntityLivingData par1EntityLivingData)
    {
        par1EntityLivingData = super.func_110161_a(par1EntityLivingData);

        int i = 15;

        if (this.rand.nextInt(2) == 1)
        {
            i = this.rand.nextInt(EntitySheep.fleeceColorTable.length);
        }

        if (this.rand.nextInt(5) == 1)
        {

            PotionEffect effect = new PotionEffect(Potion.invisibility.id, 999999999, 1);
            effect.setPotionDurationMax(true);
            this.addPotionEffect(effect);
        }

        this.setFleeceColor(15 - i);

        return par1EntityLivingData;
    }

    public void setFleeceColor(int par1)
    {
        byte b0 = this.dataWatcher.getWatchableObjectByte(16);
        this.dataWatcher.updateObject(16, Byte.valueOf((byte) (b0 & 240 | par1 & 15)));
    }

    public int getFleeceColor()
    {
        return this.dataWatcher.getWatchableObjectByte(16) & 15;
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, new Byte((byte) 0));
    }

}
