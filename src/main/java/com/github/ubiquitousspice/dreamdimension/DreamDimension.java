package com.github.ubiquitousspice.dreamdimension;

import com.github.ubiquitousspice.dreamdimension.blocks.BlockDreamDirt;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.block.Block;
import net.minecraftforge.common.Configuration;

@Mod(modid=DreamDimension.MODID, version=DreamDimension.VERSION)
public class DreamDimension
{
    public static final String MODID = "dreamdimension";
    public static final String VERSION = "0.1";

    @Mod.Instance
    public static DreamDimension instance;

    @SidedProxy(modId=MODID, clientSide = "com.github.ubiquitousspice.dreamdimension.client.ProxyClient", serverSide = "com.github.ubiquitousspice.dreamdimension.ProxyCommon")
    public static ProxyCommon proxy;

    private int idDreamDirt;
    private Block dreamDirt;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        idDreamDirt = config.getBlock("DreamDirt", 300).getInt();

        if (config.hasChanged())
            config.save();

        // do config stuff
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // do blocks and stuff here.
        dreamDirt = new BlockDreamDirt(idDreamDirt).setUnlocalizedName(MODID+".dreamDirt");
    }
}
