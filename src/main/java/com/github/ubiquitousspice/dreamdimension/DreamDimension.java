package com.github.ubiquitousspice.dreamdimension;

import java.util.logging.Logger;

import com.github.ubiquitousspice.dreamdimension.blocks.BlockDreamBase;
import com.github.ubiquitousspice.dreamdimension.sleephandle.BedHandler;
import com.github.ubiquitousspice.dreamdimension.sleephandle.DreamManager;
import com.github.ubiquitousspice.dreamdimension.sleephandle.KickHandler;
import com.github.ubiquitousspice.dreamdimension.sleephandle.PlayerTracker;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.DimensionManager;

import com.github.ubiquitousspice.dreamdimension.blocks.BlockBooster;
import com.github.ubiquitousspice.dreamdimension.blocks.BlockCheatyPortal;
import com.github.ubiquitousspice.dreamdimension.blocks.GiantWoolBlock;
import com.github.ubiquitousspice.dreamdimension.client.CreativeTabDream;
import com.github.ubiquitousspice.dreamdimension.dimension.WorldProviderMod;
import com.github.ubiquitousspice.dreamdimension.dimension.world.BiomeGenDream;
import com.github.ubiquitousspice.dreamdimension.entities.EntityConfusedVillager;
import com.github.ubiquitousspice.dreamdimension.entities.EntityLargeSheep;
import com.github.ubiquitousspice.dreamdimension.entities.EntityUnicorn;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.MinecraftForge;

//import com.github.ubiquitousspice.dreamdimension.blocks.BlockDreamDirt;

@Mod(modid = DreamDimension.MODID, version = DreamDimension.VERSION, name = "The Dream Dimension")
public class DreamDimension
{
    // TODO: need a wood & leaf block when you can get around to it
    
    public static final String MODID = "dreamdimension";
    public static final String VERSION = "0.1";

    @Mod.Instance
    public static DreamDimension instance;

    @SidedProxy(modId = MODID, clientSide = "com.github.ubiquitousspice.dreamdimension.client.ProxyClient",
            serverSide = "com.github.ubiquitousspice.dreamdimension.ProxyCommon")
    public static ProxyCommon proxy;

    // Random Stuff
    public static Logger logger;
    public static Material material;
    public static boolean dreamMaterialBreakable = false;
    public static BiomeGenBase dreamy;
    public static CreativeTabDream tabDream;
    public static int dreamPurple = 0x571b60;
    
    // Abrar: Make this a config option plz =P
    public static boolean boringSky = false;

    // IDS
    public static int dimensionID;
    static int startEntityId = 300;
    private int idDreamDirt;
    private int idDreamBooster;
    private int idPortalBlock;
    private int idGiantWool;

    // blocks
    public static Block dreamDirt;
    public static Block boosterBlock;
    public static Block portalBlock;
    public static Block giantWool;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        // get logger
        logger = event.getModLog();

        // mess with material
        material = (new MaterialDream());

        // CONFIGURATION STUFF
        {
            Configuration config = new Configuration(event.getSuggestedConfigurationFile());

            // config blockIDs
            int genId = 200;
            int baseId = 300;
            idDreamDirt = config.getTerrainBlock(Configuration.CATEGORY_BLOCK, "DreamDirt", genId++, "Base dirt for Dream Dimension").getInt();
            idDreamBooster = config.getTerrainBlock(Configuration.CATEGORY_BLOCK, "DreamLauncher", genId++, "Base dirt for Dream Dimension").getInt();
            idPortalBlock = config.getBlock(Configuration.CATEGORY_BLOCK, "PortalBlock", baseId++).getInt();
            idGiantWool = config.getBlock(Configuration.CATEGORY_BLOCK, "GiantWool", baseId++).getInt();

            // config itemIDs

            // config dimension
            dimensionID = config.get(Configuration.CATEGORY_GENERAL, "Dream Dimension Idea", 2).getInt();

            // config other
            dreamMaterialBreakable = config.get("Adventure", "dreamMaterialBreakable", false).getBoolean(false);

            // save it.
            if (config.hasChanged())
            {
                config.save();
            }
        }
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // instantiate handlers and stuff
        BedHandler bedHandler = new BedHandler();
        PlayerTracker tracker = new PlayerTracker();
        DreamManager manager = new DreamManager();
        KickHandler kickHandler = new KickHandler();

        // register them
        TickRegistry.registerTickHandler(bedHandler, Side.SERVER);
        TickRegistry.registerScheduledTickHandler(manager, Side.SERVER);
        GameRegistry.registerPlayerTracker(tracker);
        MinecraftForge.EVENT_BUS.register(bedHandler);
        MinecraftForge.EVENT_BUS.register(kickHandler);

        // creative tab
        tabDream = new CreativeTabDream();

        // do blocks and stuff here.
        dreamDirt = new BlockDreamBase(idDreamDirt, Material.ground).setUnlocalizedName(MODID + ":dreamDirt").func_111022_d(MODID + ":dreamDirt");
        boosterBlock = new BlockBooster(idDreamBooster).setCreativeTab(tabDream);
        portalBlock = new BlockCheatyPortal(idPortalBlock).setUnlocalizedName(MODID + ".portalBlock").setCreativeTab(tabDream);
        giantWool = new GiantWoolBlock(idGiantWool).setUnlocalizedName(MODID + ".giantWool").setCreativeTab(tabDream);

        // registrations
        GameRegistry.registerBlock(dreamDirt, "dreamDirt");
        GameRegistry.registerBlock(boosterBlock, ItemBlockWithMetadata.class, "dreamBooster");
        GameRegistry.registerBlock(portalBlock, "portalBlock");

        // dimension stuff
        dreamy = new BiomeGenDream(25);
        DimensionManager.registerProviderType(dimensionID, WorldProviderMod.class, true);
        DimensionManager.registerDimension(dimensionID, dimensionID);

        // Entity stuff
        registerEntity(EntityLargeSheep.class, "LargeSheep", 0xecedc7, dreamPurple);
        registerEntity(EntityConfusedVillager.class, "ConfusedVillager", 0xbd8b72, dreamPurple);

        // entity spawning
        registerEntity(EntityUnicorn.class, "Unicorn", 0xff86d3, dreamPurple);
        registerEntity(EntityGiantItem.class, "GiantItem");
        
        EntityRegistry.addSpawn(EntityLargeSheep.class, 1, 1, 1, EnumCreatureType.creature, dreamy);
        EntityRegistry.addSpawn(EntityConfusedVillager.class, 10, 4, 6, EnumCreatureType.creature, dreamy);

        // renders
        proxy.registerRenderers();
    }

    /**
     * registers an entity
     *
     * @param entityClass Entity Class
     * @param entityName  Entity Name
     * @param fgColor     Primary foreground egg color
     * @param bgColor     Secondary background egg color
     */
    public void registerEntity(Class<? extends Entity> entityClass, String entityName, int fgColor, int bgColor)
    {
        int id = EntityRegistry.findGlobalUniqueEntityId();

        EntityRegistry.registerGlobalEntityID(entityClass, entityName, id);

        EntityList.entityEggs.put(Integer.valueOf(id), new EntityEggInfo(id, bgColor, fgColor));
    }

    public void registerEntity(Class<? extends Entity> entityClass, String entityName)
    {
        int id = EntityRegistry.findGlobalUniqueEntityId();

        EntityRegistry.registerGlobalEntityID(entityClass, entityName, id);
    }
}
