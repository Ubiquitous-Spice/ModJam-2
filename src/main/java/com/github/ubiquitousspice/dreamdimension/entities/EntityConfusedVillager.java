package com.github.ubiquitousspice.dreamdimension.entities;

import java.util.Arrays;
import java.util.Calendar;
import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityLivingData;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.github.ubiquitousspice.dreamdimension.DreamDimension;
import com.github.ubiquitousspice.dreamdimension.ItemBlacklist;

public class EntityConfusedVillager extends EntityMob
{
    private static final UUID field_110187_bq = UUID.fromString("B9766B59-9566-4402-BC1F-2EE2A276D836");

    public EntityConfusedVillager(World par1World)
    {
        super(par1World);
        getNavigator().setBreakDoors(true);
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIOpenDoor(this, true));
        tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 1.0D));
        tasks.addTask(4, new EntityAIWander(this, 1.0D));
        tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        tasks.addTask(6, new EntityAIWatchClosest(this, EntityLargeSheep.class, 50.0F));
        tasks.addTask(7, new EntityAIWatchClosest(this, Entity.class, 20.0F));
        tasks.addTask(8, new EntityAILookIdle(this));
        tasks.addTask(9, new EntityAIAvoidEntity(this, EntityVillager.class, 8.0F, 0.6D, 0.6D));
        tasks.addTask(10, new EntityAIAvoidEntity(this, EntityConfusedVillager.class, 8.0F, 0.6D, 0.6D));
        targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    @Override
    protected void func_110147_ax()
    {
        super.func_110147_ax();
        // Max Health - default 20.0D - min 0.0D - max Double.MAX_VALUE
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0D);
        // Follow Range - default 32.0D - min 0.0D - max 2048.0D
        func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(5.0D);
        // Knockback Resistance - default 0.0D - min 0.0D - max 1.0D
        func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(0.0D);
        // Movement Speed - default 0.699D - min 0.0D - max Double.MAX_VALUE
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.23000000417232513D);
        // Attack Damage - default 2.0D - min 0.0D - max Doubt.MAX_VALUE
        func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(2.0D);
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        getDataWatcher().addObject(12, Byte.valueOf((byte) 0));
        getDataWatcher().addObject(13, Byte.valueOf((byte) 0));
        getDataWatcher().addObject(14, Byte.valueOf((byte) 0));
    }

    /**
     * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
     */
    @Override
    public int getTotalArmorValue()
    {
        int i = super.getTotalArmorValue() + 2;

        if (i > 20)
        {
            i = 20;
        }

        return i;
    }

    /**
     * Returns true if the newer Entity AI code should be run
     */
    @Override
    protected boolean isAIEnabled()
    {
        return true;
    }

    /**
     * Return whether this zombie is a villager.
     */
    public boolean isVillager()
    {
        return true;
    }

    /**
     * Set whether this zombie is a villager.
     */
    public void setVillager(boolean par1)
    {
        getDataWatcher().updateObject(13, Byte.valueOf((byte) (par1 ? 1 : 0)));
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    @Override
    public void onLivingUpdate()
    {
        if (worldObj.isDaytime() && !worldObj.isRemote && !isChild())
        {
            float f = getBrightness(1.0F);

            if (f > 0.5F && rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && worldObj.canBlockSeeTheSky(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ)))
            {
                ItemStack itemstack = getCurrentItemOrArmor(4);

                if (itemstack != null)
                {
                    if (itemstack.isItemStackDamageable())
                    {
                        itemstack.setItemDamage(itemstack.getItemDamageForDisplay() + rand.nextInt(2));

                        if (itemstack.getItemDamageForDisplay() >= itemstack.getMaxDamage())
                        {
                            renderBrokenItemStack(itemstack);
                            setCurrentItemOrArmor(4, (ItemStack) null);
                        }
                    }
                }
            }
        }

        super.onLivingUpdate();
    }

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
    {
        if (!super.attackEntityFrom(par1DamageSource, par2))
        {
            return false;
        }
        else
        {
            EntityLivingBase entitylivingbase = getAttackTarget();

            if (entitylivingbase == null && getEntityToAttack() instanceof EntityLivingBase)
            {
                entitylivingbase = (EntityLivingBase) getEntityToAttack();
            }

            if (entitylivingbase == null && par1DamageSource.getEntity() instanceof EntityLivingBase)
            {
                entitylivingbase = (EntityLivingBase) par1DamageSource.getEntity();
            }

            if (entitylivingbase != null && worldObj.difficultySetting >= 3)
            {
                int i = MathHelper.floor_double(posX);
                int j = MathHelper.floor_double(posY);
                int k = MathHelper.floor_double(posZ);
                EntityConfusedVillager entityzombie = new EntityConfusedVillager(worldObj);

                for (int l = 0; l < 50; ++l)
                {
                    int i1 = i + MathHelper.getRandomIntegerInRange(rand, 7, 40) * MathHelper.getRandomIntegerInRange(rand, -1, 1);
                    int j1 = j + MathHelper.getRandomIntegerInRange(rand, 7, 40) * MathHelper.getRandomIntegerInRange(rand, -1, 1);
                    int k1 = k + MathHelper.getRandomIntegerInRange(rand, 7, 40) * MathHelper.getRandomIntegerInRange(rand, -1, 1);

                    if (worldObj.doesBlockHaveSolidTopSurface(i1, j1 - 1, k1) && worldObj.getBlockLightValue(i1, j1, k1) < 10)
                    {
                        entityzombie.setPosition(i1, j1, k1);

                        if (worldObj.checkNoEntityCollision(entityzombie.boundingBox) && worldObj.getCollidingBoundingBoxes(entityzombie, entityzombie.boundingBox).isEmpty() && !worldObj.isAnyLiquid(entityzombie.boundingBox))
                        {
                            worldObj.spawnEntityInWorld(entityzombie);
                            entityzombie.setAttackTarget(entitylivingbase);
                            entityzombie.func_110161_a((EntityLivingData) null);
                            break;
                        }
                    }
                }
            }

            return true;
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate()
    {

        super.onUpdate();
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity)
    {
        boolean flag = super.attackEntityAsMob(par1Entity);

        if (flag && getHeldItem() == null && isBurning() && rand.nextFloat() < worldObj.difficultySetting * 0.3F)
        {
            par1Entity.setFire(2 * worldObj.difficultySetting);
        }

        return flag;
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    @Override
    protected String getLivingSound()
    {
        return "mob.cow.say";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected String getHurtSound()
    {
        return "mob.cow.hurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    @Override
    protected String getDeathSound()
    {
        return "mob.cow.hurt";
    }

    /**
     * Plays step sound at given x, y, z for the entity
     */
    @Override
    protected void playStepSound(int par1, int par2, int par3, int par4)
    {
        playSound("mob.cow.step", 0.15F, 1.0F);
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    @Override
    protected void dropFewItems(boolean par1, int par2)
    {
        EntityItem entityItem = new EntityItem(worldObj, posX, posY, posZ, getCurrentItemOrArmor(0));
        worldObj.spawnEntityInWorld(entityItem);
    }

    /**
     * Get this Entity's EnumCreatureAttribute
     */
    @Override
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }

    @Override
    protected void dropRareDrop(int par1)
    {

    }

    /**
     * Makes entity wear random armor based on difficulty
     */
    @Override
    protected void addRandomArmor()
    {

        boolean flag = true;

        while (flag)
        {
            int id = rand.nextInt(Item.itemsList.length);
            Item item = Item.itemsList[id];
            ItemStack stack;

            if (!Arrays.asList(ItemBlacklist.blackList).contains(item) && item != null)
            {
                stack = new ItemStack(item);

                if (item.isDamageable())
                {
                    stack.setItemDamage(rand.nextInt(item.getMaxDamage()));
                }

                setCurrentItemOrArmor(0, stack);
                flag = false;
            }
        }

        if (rand.nextFloat() < 0.15F * worldObj.func_110746_b(posX, posY, posZ))
        {
            int i = rand.nextInt(2);
            float f = worldObj.difficultySetting == 3 ? 0.5F : 0.4F;

            if (rand.nextFloat() < 0.095F)
            {
                ++i;
            }

            if (rand.nextFloat() < 0.095F)
            {
                ++i;
            }

            if (rand.nextFloat() < 0.095F)
            {
                ++i;
            }

            for (int j = 3; j >= 0; --j)
            {
                ItemStack itemstack = func_130225_q(j);

                if (j < 3 && rand.nextFloat() < f)
                {
                    break;
                }

                if (itemstack == null)
                {
                    Item item = getArmorItemForSlot(j + 1, i);

                    if (item != null)
                    {
                        setCurrentItemOrArmor(j, new ItemStack(item));
                    }
                }
            }
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);

        if (isVillager())
        {
            par1NBTTagCompound.setBoolean("IsVillager", true);
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);

        if (par1NBTTagCompound.getBoolean("IsVillager"))
        {
            setVillager(true);
        }
    }

    @Override
    public EntityLivingData func_110161_a(EntityLivingData par1EntityLivingData)
    {
        Object par1EntityLivingData1 = super.func_110161_a(par1EntityLivingData);
        float f = worldObj.func_110746_b(posX, posY, posZ);
        setCanPickUpLoot(rand.nextFloat() < 0.55F * f);

        addRandomArmor();
        enchantEquipment();

        if (getCurrentItemOrArmor(4) == null)
        {
            Calendar calendar = worldObj.getCurrentDate();

            if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && rand.nextFloat() < 0.25F || rand.nextInt(50) == 42)
            {
                setCurrentItemOrArmor(4, new ItemStack(rand.nextFloat() < 0.1F ? Block.pumpkinLantern : Block.pumpkin));
                equipmentDropChances[4] = 0.0F;
            }
        }

        return (EntityLivingData) par1EntityLivingData1;
    }

    /**
     * Determines if an entity can be despawned, used on idle far away entities
     */
    @Override
    protected boolean canDespawn()
    {
        return true;
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
