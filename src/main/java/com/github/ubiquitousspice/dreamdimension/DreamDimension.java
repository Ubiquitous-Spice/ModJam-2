package com.github.ubiquitousspice.dreamdimension;

import com.github.ubiquitousspice.dreamdimension.blocks.*;
import com.github.ubiquitousspice.dreamdimension.client.CreativeTabDream;
import com.github.ubiquitousspice.dreamdimension.dimension.WorldProviderMod;
import com.github.ubiquitousspice.dreamdimension.dimension.world.BiomeGenDream;
import com.github.ubiquitousspice.dreamdimension.entities.EntityConfusedVillager;
import com.github.ubiquitousspice.dreamdimension.entities.EntityLargeSheep;
import com.github.ubiquitousspice.dreamdimension.entities.EntityUnicorn;
import com.github.ubiquitousspice.dreamdimension.handlers.*;
import com.github.ubiquitousspice.dreamdimension.item.*;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;

import java.util.logging.Logger;

@Mod(modid = DreamDimension.MODID, version = DreamDimension.VERSION, name = "The Dream Dimension")
public class DreamDimension
{
    public static final String MODID = "dreamdimension";
    public static final String VERSION = "0.1";

    @Mod.Instance
    public static DreamDimension instance;

    @SidedProxy(modId = MODID, clientSide = "com.github.ubiquitousspice.dreamdimension.client.ProxyClient", serverSide = "com.github.ubiquitousspice.dreamdimension.ProxyCommon")
    public static ProxyCommon proxy;

    // Random Stuff
    public static Logger logger;
    public static Material material;
    public static boolean dreamMaterialBreakable = false;
    public static BiomeGenBase dreamy;
    public static CreativeTabDream tabDream;
    public static int dreamPurple = 0x571b60;

    public static boolean boringSky = false;

    // IDS
    public static int dimensionID;
    static int startEntityId = 300;
    private int idFleeceHelm;
    private int idFleeceChest;
    private int idFleeceLegs;
    private int idFleeceBoots;
    private int idDreamDirt;
    private int idDreamBooster;
    private int idPortalBlock;
    private int idDreamLog;
    private int idDreamLeaf;
    private int idUnicornHorn;
    private int idDreamFleece;
    private int idLimbo;
    private int idDreamDiamond;
    private int idFakeDiamond;
    private int idDreamSapling;
    private int idPear;
    private int idDreamPlanks;
    private int idUnicornSword;
    private int idUnicornSwordUpgrade;
    private int idFDiamondSword;
    private int idFDiamondShovel;
    private int idFDiamondAxe;
    private int idFDiamondPickaxe;
    private int idDreamCatcher;

    // items
    public static Item fleeceHelmet;
    public static Item fleeceChest;
    public static Item fleeceLegs;
    public static Item fleeceBoots;
    public static Item unicornHorn;
    public static Item fakeDiamond;
    public static Item pear;
    public static Item unicornSword;
    public static Item unicornSwordUpgrade;
    public static Item fDiamondSword;
    public static Item fDiamondAxe;
    public static Item fDiamondPickaxe;
    public static Item fDiamondShovel;
    public static Item dreamCatcher;

    // blocks
    public static Block dreamDirt;
    public static Block boosterBlock;
    public static Block portalBlock;
    public static Block dreamFleece;
    public static Block dreamLog;
    public static Block dreamLeaf;
    public static Block dreamPlanks;
    public static Block dreamSapling;
    public static Block dreamDiamond;
    public static Block limbo;

    public static EnumToolMaterial mat = EnumToolMaterial.STONE;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        // get logger
        logger = event.getModLog();

        // mess with material
        material = new MaterialDream();

        // CONFIGURATION STUFF
        {
            Configuration config = new Configuration(event.getSuggestedConfigurationFile());

            // config itemIDs
            int baseItemId = 9000;
            idFakeDiamond = config.getItem(Configuration.CATEGORY_ITEM, "fakeDiamond", baseItemId++).getInt();
            idUnicornHorn = config.getItem(Configuration.CATEGORY_ITEM, "UnicornHorn", baseItemId++).getInt();
            idUnicornSword = config.getItem(Configuration.CATEGORY_ITEM, "DreamWoodSword", baseItemId++).getInt();
            idUnicornSwordUpgrade = config.getItem(Configuration.CATEGORY_ITEM, "UnicornBlade", baseItemId++).getInt();
            idPear = config.getItem(Configuration.CATEGORY_ITEM, "Pear", baseItemId++).getInt();
            idDreamCatcher = config.getItem(Configuration.CATEGORY_ITEM, "DreamCatcher", baseItemId++).getInt();
            idFleeceHelm = config.getItem(Configuration.CATEGORY_ITEM, "FleeceHelm", baseItemId++).getInt();
            idFleeceChest = config.getItem(Configuration.CATEGORY_ITEM, "FleeceChest", baseItemId++).getInt();
            idFleeceLegs = config.getItem(Configuration.CATEGORY_ITEM, "FleeceLegs", baseItemId++).getInt();
            idFleeceBoots = config.getItem(Configuration.CATEGORY_ITEM, "FleeceBoots", baseItemId++).getInt();
            idFDiamondSword = config.getItem(Configuration.CATEGORY_ITEM, "FakeDiamondSword", baseItemId++).getInt();
            idFDiamondShovel = config.getItem(Configuration.CATEGORY_ITEM, "FakeDiamondShovel", baseItemId++).getInt();
            idFDiamondPickaxe = config.getItem(Configuration.CATEGORY_ITEM, "FakeDiamondPickaxe", baseItemId++).getInt();
            idFDiamondAxe = config.getItem(Configuration.CATEGORY_ITEM, "FakeDiamondAxe", baseItemId++).getInt();

            // config terrain blockIDs
            int genId = 200;
            idDreamDirt = config.getTerrainBlock(Configuration.CATEGORY_BLOCK, "DreamDirt", genId++, "Base dirt for Dream Dimension").getInt();
            idDreamDiamond = config.getTerrainBlock(Configuration.CATEGORY_BLOCK, "DreamDiamond", genId++, "Diamond ore for Dream Dimension").getInt();
            idDreamLog = config.getTerrainBlock(Configuration.CATEGORY_BLOCK, "DreamLog", genId++, "Logs for dream trees").getInt();
            idDreamLeaf = config.getTerrainBlock(Configuration.CATEGORY_BLOCK, "DreamLeaf", genId++, "leaves for dream trees").getInt();
            idDreamBooster = config.getTerrainBlock(Configuration.CATEGORY_BLOCK, "DreamLauncher", genId++, "Base dirt for Dream Dimension").getInt();

            // config blockIDs
            int baseId = 300;
            idPortalBlock = config.getBlock(Configuration.CATEGORY_BLOCK, "PortalBlock", baseId++).getInt();
            idDreamSapling = config.getBlock(Configuration.CATEGORY_BLOCK, "DreamSapling", baseId++).getInt();
            idDreamPlanks = config.getBlock(Configuration.CATEGORY_BLOCK, "DreamPlanks", baseId++).getInt();
            idDreamFleece = config.getBlock(Configuration.CATEGORY_BLOCK, "DreamFleece", baseId++).getInt();
            idLimbo = config.getBlock(Configuration.CATEGORY_BLOCK, "Limbo Block", baseId++).getInt();

            // config dimension
            dimensionID = config.get(Configuration.CATEGORY_GENERAL, "Dream Dimension Idea", 2).getInt();

            // config other
            dreamMaterialBreakable = config.get("Adventure", "dreamMaterialBreakable", false).getBoolean(false);

            // config other
            boringSky = config.get("Sky Changes Color", "boringSky", true).getBoolean(true);

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
        MilkHandler milkHandler = new MilkHandler();

        // register them
        TickRegistry.registerTickHandler(bedHandler, Side.SERVER);
        TickRegistry.registerTickHandler(manager, Side.SERVER);
        TickRegistry.registerTickHandler(milkHandler, Side.SERVER);
        GameRegistry.registerPlayerTracker(tracker);
        MinecraftForge.EVENT_BUS.register(bedHandler);
        MinecraftForge.EVENT_BUS.register(kickHandler);
        MinecraftForge.EVENT_BUS.register(milkHandler);

        // creative tab
        tabDream = new CreativeTabDream();

        // blocks
        dreamDirt = new BlockDreamBase(idDreamDirt, Material.ground).setUnlocalizedName(MODID + ":dreamDirt").func_111022_d(MODID + ":dreamDirt").setHardness(0.1F);
        dreamDiamond = new BlockDreamOre(idDreamDiamond, Material.rock).setUnlocalizedName(MODID + ".dreamDiamond").func_111022_d(MODID + ":dreamDiamond").setHardness(2.0F);
        boosterBlock = new BlockBooster(idDreamBooster).setCreativeTab(tabDream).setHardness(0.2F);
        portalBlock = new BlockCheatyPortal(idPortalBlock).setUnlocalizedName(MODID + ":portalBlock").setCreativeTab(tabDream).func_111022_d("portal");
        dreamFleece = new BlockDreamFleece(idDreamFleece).setUnlocalizedName(MODID + ".dreamFleeceLarge").setCreativeTab(tabDream).setHardness(1.0F);
        dreamLog = new BlockDreamLog(idDreamLog).setUnlocalizedName(MODID + ":dreamWood").setCreativeTab(tabDream).setHardness(2.0F);
        dreamLeaf = new BlockDreamLeaf(idDreamLeaf).setUnlocalizedName(MODID + ":dreamLeaves").func_111022_d(MODID + ":dreamLeaves").setCreativeTab(tabDream).setHardness(0.2F);
        dreamPlanks = new BlockDreamBase(idDreamPlanks, Material.wood).setUnlocalizedName(MODID + ".dreamPlanks").func_111022_d(MODID + ":dreamPlanks").setCreativeTab(tabDream).setHardness(1.5F);
        dreamSapling = new BlockDreamSapling(idDreamSapling).setUnlocalizedName(MODID + ".dreamSapling").func_111022_d(MODID + ":dreamSapling").setCreativeTab(tabDream);
        limbo = new BlockLimbo(idLimbo).setUnlocalizedName(MODID + ".limbo");

        // items
        unicornHorn = new ItemDreamBase(idUnicornHorn, "unicornHorn").setUnlocalizedName(MODID + ".unicornHorn").func_111206_d(MODID + ":unicornHorn").setCreativeTab(tabDream);
        pear = new ItemPear(idPear, "pear").setUnlocalizedName(MODID + ".pear").func_111206_d(MODID + ":pear").setCreativeTab(tabDream);
        fleeceHelmet = new ItemFleeceArmor(idFleeceHelm, 0, "fleeceHelmet").setUnlocalizedName(MODID + ".fleeceHelm").setCreativeTab(tabDream);
        fleeceChest = new ItemFleeceArmor(idFleeceChest, 1, "fleeceChest").setUnlocalizedName(MODID + ".fleeceChest").setCreativeTab(tabDream);
        fleeceLegs = new ItemFleeceArmor(idFleeceLegs, 2, "fleeceLegs").setUnlocalizedName(MODID + ".fleeceLegs").setCreativeTab(tabDream);
        fleeceBoots = new ItemFleeceArmor(idFleeceBoots, 3, "fleeceBoots").setUnlocalizedName(MODID + ".fleeceBoots").setCreativeTab(tabDream);
        unicornSword = new ItemUnicornSword(idUnicornSword, "dreamWoodBlade", 0).setUnlocalizedName(MODID + ".unicornSword").setCreativeTab(tabDream);
        unicornSwordUpgrade = new ItemUnicornSword(idUnicornSwordUpgrade, "unicornBlade", 1).setUnlocalizedName(MODID + ".unicornSwordUpgrade").setCreativeTab(tabDream);
        fakeDiamond = new ItemDreamBase(idFakeDiamond, "fakeDiamond").setUnlocalizedName(MODID + ".fakeDiamond").func_111206_d("diamond").setCreativeTab(tabDream);
        dreamCatcher = new ItemDreamBase(idDreamCatcher, "dreamCatcher").setUnlocalizedName(MODID + ".dreamCatcher").func_111206_d(MODID + ":dreamCatcher").setCreativeTab(tabDream);

        // tools
        fDiamondSword = new ItemDreamSword(idFDiamondSword, mat, "fakeDiamondSword").setUnlocalizedName(MODID + ".fDiamondSword").func_111206_d("diamond_sword").setCreativeTab(tabDream);
        fDiamondShovel = new ItemDreamSpade(idFDiamondShovel, mat, "fakeDiamondShovel").setUnlocalizedName(MODID + ".fDiamondShovel").func_111206_d("diamond_shovel").setCreativeTab(tabDream);
        fDiamondAxe = new ItemDreamAxe(idFDiamondAxe, mat, "fakeDiamondAxe").setUnlocalizedName(MODID + ".fDiamondAxe").func_111206_d("diamond_axe").setCreativeTab(tabDream);
        fDiamondPickaxe = new ItemDreamPick(idFDiamondPickaxe, mat, "fakeDiamondPick").setUnlocalizedName(MODID + ".fDiamondPickaxe").func_111206_d("diamond_pickaxe").setCreativeTab(tabDream);

        // registrations
        GameRegistry.registerBlock(dreamDirt, ItemDreamDirt.class, "dreamDirt");
        GameRegistry.registerBlock(boosterBlock, ItemBlockBooster.class, "dreamBooster");
        GameRegistry.registerBlock(portalBlock, "portalBlock");
        GameRegistry.registerBlock(limbo, ItemLimbo.class, "limboTransferer");
        GameRegistry.registerBlock(dreamLog, ItemDreamLog.class, "dreamWood");
        GameRegistry.registerBlock(dreamLeaf, ItemDreamLeaves.class, "dreamLeaves");
        GameRegistry.registerBlock(dreamFleece, ItemDreamFleece.class, "dreamFleece");
        GameRegistry.registerBlock(dreamDiamond, ItemDreamDiamond.class, "dreamDiamond");
        GameRegistry.registerBlock(dreamPlanks, ItemDreamPlanks.class, "dreamPlanks");
        GameRegistry.registerBlock(dreamSapling, ItemDreamSapling.class, "dreamSapling");

        // register TEs.
        GameRegistry.registerTileEntity(TileEntityLimbo.class, "LimboTransfer");

        // dimension stuff
        dreamy = new BiomeGenDream(25).setDisableRain();
        DimensionManager.registerProviderType(dimensionID, WorldProviderMod.class, true);
        DimensionManager.registerDimension(dimensionID, dimensionID);

        // Entity stuff
        registerEntity(EntityLargeSheep.class, "LargeSheep", 0xecedc7, dreamPurple);
        registerEntity(EntityConfusedVillager.class, "ConfusedVillager", 0xbd8b72, dreamPurple);
        registerEntity(EntityUnicorn.class, "Unicorn", 0xff86d3, dreamPurple);

        // entity spawning
        EntityRegistry.addSpawn(EntityLargeSheep.class, 1, 1, 1, EnumCreatureType.creature, dreamy);
        EntityRegistry.addSpawn(EntityConfusedVillager.class, 10, 4, 6, EnumCreatureType.creature, dreamy);
        EntityRegistry.addSpawn(EntityUnicorn.class, 6, 4, 6, EnumCreatureType.creature, dreamy);

        // renders
        proxy.registerRenderers();

        // crafting
        Crafting.addRecipes();

        // ore dictionary
        OreDictionary.registerOre("logWood", new ItemStack(DreamDimension.dreamLog));
        OreDictionary.registerOre("plankWood", new ItemStack(DreamDimension.dreamPlanks));

        // misc
        proxy.registerTickHandler();
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

}
