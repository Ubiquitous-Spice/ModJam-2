package com.github.ubiquitousspice.dreamdimension;

import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid=DreamDimension.MODID, version=DreamDimension.VERSION)
public class DreamDimension
{
    public static final String MODID = "dreamdimension";
    public static final String VERSION = "0.1";

    @Mod.Instance
    public static DreamDimension instance;

    @SidedProxy(modId=MODID, clientSide = "com.github.ubiquitousspice.dreamdimension.client.ProxyClient", serverSide = "com.github.ubiquitousspice.dreamdimension.ProxyCommon")
    public static proxyCommon proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        // do config stuff
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // do blocks and stuff here.
    }
}
