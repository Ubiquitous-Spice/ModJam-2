package com.github.ubiquitousspice.dreamdimension.client;

import com.github.ubiquitousspice.dreamdimension.ProxyCommon;
import com.github.ubiquitousspice.dreamdimension.client.render.RenderConfusedVillager;
import com.github.ubiquitousspice.dreamdimension.client.render.RenderLargeSheep;
import com.github.ubiquitousspice.dreamdimension.client.render.model.ModelLargeSheep;
import com.github.ubiquitousspice.dreamdimension.client.render.model.ModelLargeSheep2;
import com.github.ubiquitousspice.dreamdimension.entities.EntityConfusedVillager;
import com.github.ubiquitousspice.dreamdimension.entities.EntityLargeSheep;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ProxyClient extends ProxyCommon
{
    @Override
    public void registerRenderers()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityLargeSheep.class, new RenderLargeSheep(new ModelLargeSheep2(), new ModelLargeSheep(), 0.7F));
        RenderingRegistry.registerEntityRenderingHandler(EntityConfusedVillager.class, new RenderConfusedVillager());
    }
}
