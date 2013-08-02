package com.github.ubiquitousspice.dreamdimension.entities;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.item.ItemExpireEvent;

public class EntityGiantItem extends Entity {
	
	private ItemStack stack;
	
	public int age;
	public int lifespan = 6000;
	public float hoverStart;

	public EntityGiantItem(World par1World) {
		super(par1World);
		this.hoverStart = (float)(Math.random() * Math.PI * 2.0D);
		
	}
	
	public EntityGiantItem(World par1World, ItemStack stack) {
		super(par1World);
		this.stack = stack;
		this.hoverStart = (float)(Math.random() * Math.PI * 2.0D);
		
	}

	public void onUpdate()
    {
        super.onUpdate();
        
        this.age++;
    }
	
	@Override
	protected void entityInit() { }

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		
	}
	
	ItemStack getItemStack()
	{
		return this.stack;
	}

}
