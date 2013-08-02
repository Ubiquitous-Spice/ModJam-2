package com.github.ubiquitousspice.dreamdimension.world;

import java.util.List;

import com.github.ubiquitousspice.dreamdimension.DreamDimension;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenDream extends BiomeGenBase {
	
	private WorldGenerator UnDeadworldGeneratorBigTree;
	//public final Material blockMaterial;

	public BiomeGenDream(int par1) {
		super(par1);
		//this.blockMaterial = Material.water;
		this.minHeight = 0.0F;
		this.maxHeight = -0.1F;
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.topBlock = ((byte) ChunkProviderMod.dreamBlock.blockID);
		this.fillerBlock = ((byte) ChunkProviderMod.dreamBlock.blockID);
		this.setBiomeName("Dreamy");

		/**
		 * this changes the water colour, its set to red now but ggole the java
		 * colours
		 **/
		this.waterColorMultiplier = 0xE42D17;
	}
}