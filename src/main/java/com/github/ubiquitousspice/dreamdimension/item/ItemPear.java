package com.github.ubiquitousspice.dreamdimension.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.github.ubiquitousspice.dreamdimension.DreamDimension;

public class ItemPear extends ItemDreamBase
{

    public final int itemUseDuration;
    private final int healAmount;
    private final float saturationModifier;

    public ItemPear(int par1, String a)
    {
        super(par1, a);
        healAmount = 2;
        saturationModifier = 5;
        itemUseDuration = 32;
    }

    @Override
    public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        --par1ItemStack.stackSize;
        par3EntityPlayer.getFoodStats().addStats(healAmount, saturationModifier);
        par2World.playSoundAtEntity(par3EntityPlayer, "random.burp", 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);
        return par1ItemStack;
    }

    /**
     * How long it takes to use or consume an item
     */
    @Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 32;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    @Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.eat;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        par3EntityPlayer.setItemInUse(par1ItemStack, getMaxItemUseDuration(par1ItemStack));

        return par1ItemStack;
    }

    public int getHealAmount()
    {
        return healAmount;
    }

    /**
     * gets the saturationModifier of the ItemFood
     */
    public float getSaturationModifier()
    {
        return saturationModifier;
    }

    @Override
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5)
    {
        super.onUpdate(par1ItemStack, par2World, par3Entity, par4, par5);

        if (inDreamWorld)
        {
            if (((EntityPlayer) par3Entity).getItemInUseCount() > 0 && ((EntityPlayer) par3Entity).inventory.getCurrentItem().getItem() == DreamDimension.pear)
            {
                par3Entity.fallDistance = 0;

                if (par3Entity.motionY != 0.1)
                {
                    par3Entity.motionY = 0.1;
                }
            }
        }
    }

}
