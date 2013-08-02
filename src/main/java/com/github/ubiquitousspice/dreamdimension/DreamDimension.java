package com.github.ubiquitousspice.dreamdimension;

import com.github.ubiquitousspice.dreamdimension.blocks.BlockBouncy;
import java.util.logging.Logger;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.DimensionManager;

import com.github.ubiquitousspice.dreamdimension.blocks.BlockBouncy;
import com.github.ubiquitousspice.dreamdimension.blocks.BlockCheatyPortal;
//import com.github.ubiquitousspice.dreamdimension.blocks.BlockDreamDirt;
import com.github.ubiquitousspice.dreamdimension.dimension.WorldProviderMod;
import com.github.ubiquitousspice.dreamdimension.entities.EntityLargeSheep;
import com.github.ubiquitousspice.dreamdimension.world.BiomeGenDream;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.registry.LanguageRegistry;

import java.util.logging.Logger;
@Mod(modid = DreamDimension.MODID, version = DreamDimension.VERSION, name = "The Dream Dimension")
public class DreamDimension {
	public static final String MODID = "dreamdimension";
	public static final String VERSION = "0.1";

	@Mod.Instance
	public static DreamDimension instance;

	@SidedProxy(modId = MODID, clientSide = "com.github.ubiquitousspice.dreamdimension.client.ProxyClient", serverSide = "com.github.ubiquitousspice.dreamdimension.ProxyCommon")
	public static ProxyCommon proxy;

	public static Logger logger;

	// Material
	public static Material material;

	// random configurations
	public static boolean dreamMaterialBreakable = false;

	// dimension configs

	// IDS
	public static int dimensionID;
	static int startEntityId = 300;

	private int idDreamDirt;
	private int idDLauncher;

	// blocks
	public static Block dreamDirt;
	public static Block bouncyBlock;

	private int idPortalBlock;
	public static Block portalBlock;

	public static BiomeGenBase dreamy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		// get logger
		logger = event.getModLog();

		// mess with material
		material = (new MaterialDream());

		// CONFIGURATION STUFF
		{
			Configuration config = new Configuration(
					event.getSuggestedConfigurationFile());
			idDreamDirt = config.getBlock("DreamDirt", 300).getInt();
			idPortalBlock = config.getBlock("PortalBlock", 301).getInt();
			dimensionID = config.get(Configuration.CATEGORY_GENERAL,
					"Dream Dimension Idea", 2).getInt();

			// config blockIDs
			int baseid = 300;
			idDreamDirt = config.getBlock("DreamDirt", baseid++).getInt();
			idDLauncher = config.getBlock("DreamLauncher", baseid++).getInt();
			if (config.hasChanged())
				config.save();

			// config itemIDs

			// config dimension

			// config other
			dreamMaterialBreakable = config.get("Adventure",
					"dreamMaterialBreakable", false).getBoolean(false);

			// save it.
			if (config.hasChanged()) {
				config.save();
			}
		}
		// do config stuff
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		// do blocks and stuff here.
		dreamDirt = new Block(idDreamDirt, material).setUnlocalizedName(
				MODID + ".dreamDirt").func_111022_d(MODID + ":dreamDirt");
		bouncyBlock = new BlockBouncy(idDLauncher).setUnlocalizedName(
				MODID + ".dreamLauncher").func_111022_d(
				MODID + ":dreamLauncher");

		// registrations
		GameRegistry.registerBlock(dreamDirt, "dreamDirt");
		GameRegistry.registerBlock(bouncyBlock, "dreamLauncher");

		portalBlock = new BlockCheatyPortal(idPortalBlock)
				.setUnlocalizedName(MODID + ".portalBlock");
		GameRegistry.registerBlock(portalBlock, "portalBlock");

		dreamy = new BiomeGenDream(25);

		DimensionManager.registerProviderType(dimensionID,
				WorldProviderMod.class, true);
		DimensionManager.registerDimension(dimensionID, dimensionID);

		registerEntity(EntityLargeSheep.class, "LargeSheep", 0xffffff, 0x000000);
		/**EntityRegistry.registerModEntity(EntityLargeSheep.class, "LargeSheep",
				1, DreamDimension.instance, 80, 3, true);*/
		EntityRegistry.addSpawn(EntityLargeSheep.class, 10, 2, 4,
				EnumCreatureType.creature, dreamy);
		LanguageRegistry.instance().addStringLocalization(
				"entity.dreamdimension.LargeSheep.name", "Large Sheep");
		
		proxy.registerRenderers();
	}
	
	public void registerEntity(Class<? extends Entity> entityClass, String entityName, int bkEggColor, int fgEggColor)
	  {
	    int id = EntityRegistry.findGlobalUniqueEntityId();

	    EntityRegistry.registerGlobalEntityID(entityClass, entityName, id);

	    EntityList.entityEggs.put(Integer.valueOf(id), new EntityEggInfo(id, bkEggColor, fgEggColor));
	  }
}
