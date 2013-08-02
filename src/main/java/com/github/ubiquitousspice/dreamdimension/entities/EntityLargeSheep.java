package com.github.ubiquitousspice.dreamdimension.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityLargeSheep extends EntityAnimal {

	public static float largeSheepMod = 5;
	
	private int sheepTimer;
	
	public EntityLargeSheep(World par1World) {
		super(par1World);
		this.setAIMoveSpeed(1F);
		this.renderDistanceWeight = 80.0D;
		this.setSize(0.9F * largeSheepMod, 1.3F * largeSheepMod);
	}

	public int getMaxHealth() {
		return 200;
	}
	
	@SideOnly(Side.CLIENT)
    public float getShadowSize()
    {
        return largeSheepMod;
    }

	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEFINED;
	}
	
	public int getAttackStrength(Entity par1Entity)
    {
     return 4;
    }
	
	public int getTotalArmorValue()
    {
        return 2;
    }
	
	public void onLivingUpdate()
    {
        
        if (this.worldObj.isRemote)
        {
            this.sheepTimer = Math.max(0, this.sheepTimer - 1);
        }

        super.onLivingUpdate();
    }
	
	protected String getLivingSound()
    {
        return "mob.zombie.say";
    }
 
    protected String getHurtSound()
    {
        return "mob.zombie.hurt";
    }
    
    protected String getDeathSound()
    {
        return "mob.zombie.death";
    }
    
    protected void playStepSound(int par1, int par2, int par3, int par4)
    {
        this.worldObj.playSoundAtEntity(this, "mob.zombie.step", 0.15F, 1.0F);
    }

	@Override
	public EntityAgeable createChild(EntityAgeable entityageable) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SideOnly(Side.CLIENT)
    public float func_70894_j(float par1)
    {
        return this.sheepTimer <= 0 ? 0.0F : (this.sheepTimer >= 4 && this.sheepTimer <= 36 ? 1.0F : (this.sheepTimer < 4 ? ((float)this.sheepTimer - par1) / 4.0F : -((float)(this.sheepTimer - 40) - par1) / 4.0F));
    }
	
	@SideOnly(Side.CLIENT)
    public float func_70890_k(float par1)
    {
        if (this.sheepTimer > 4 && this.sheepTimer <= 36)
        {
            float f1 = ((float)(this.sheepTimer - 4) - par1) / 32.0F;
            return ((float)Math.PI / 5F) + ((float)Math.PI * 7F / 100F) * MathHelper.sin(f1 * 28.7F);
        }
        else
        {
            return this.sheepTimer > 0 ? ((float)Math.PI / 5F) : this.rotationPitch / (180F / (float)Math.PI);
        }
    }

}
