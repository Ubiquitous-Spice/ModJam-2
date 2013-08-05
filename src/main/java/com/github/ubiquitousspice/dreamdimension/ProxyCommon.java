package com.github.ubiquitousspice.dreamdimension;

import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ProxyCommon
{

    public void registerRenderers()
    {
    }

    public void registerTickHandler()
    {
        TickRegistry.registerTickHandler(new TickHandler(), Side.SERVER);

    }

}
