package com.github.ubiquitousspice.dreamdimension.client;

import com.github.ubiquitousspice.dreamdimension.DreamDimension;
import com.github.ubiquitousspice.dreamdimension.ProxyCommon;
import com.github.ubiquitousspice.dreamdimension.entities.EntityGiantItem;
import com.github.ubiquitousspice.dreamdimension.entities.EntityLargeSheep;
import com.github.ubiquitousspice.dreamdimension.entities.ModelLargeSheep;
import com.github.ubiquitousspice.dreamdimension.entities.ModelLargeSheep2;
import com.github.ubiquitousspice.dreamdimension.entities.RenderGiantItem;
import com.github.ubiquitousspice.dreamdimension.entities.RenderLargeSheep;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;

public class ProxyClient extends ProxyCommon
{
    @Override
    public void registerRenderers()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityLargeSheep.class, new RenderLargeSheep(new ModelLargeSheep2(), new ModelLargeSheep(), 0.7F));
        
		RenderingRegistry.registerEntityRenderingHandler(EntityGiantItem.class,
				new RenderGiantItem());
    }
}
