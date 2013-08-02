package SampleMod.TuxCraft.World;

import java.util.List;

import SampleMod.TuxCraft.SampleModCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenSample extends BiomeGenBase {
	private WorldGenerator UnDeadworldGeneratorBigTree;
	public final Material blockMaterial;

	public BiomeGenSample(int par1) {
		super(par1);
		this.blockMaterial = Material.water;
		this.minHeight = 0.0F;
		this.maxHeight = 0.1F;
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.topBlock = ((byte) Block.blockGold.blockID);
		this.fillerBlock = ((byte) Block.blockIron.blockID);
		this.setBiomeName("Sample");

		/**
		 * this changes the water colour, its set to red now but ggole the java
		 * colours
		 **/
		this.waterColorMultiplier = 0xE42D17;
	}
}