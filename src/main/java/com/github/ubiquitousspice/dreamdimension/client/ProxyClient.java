package com.github.ubiquitousspice.dreamdimension.client;

import com.github.ubiquitousspice.dreamdimension.client.render.*;
import net.minecraftforge.client.MinecraftForgeClient;

import com.github.ubiquitousspice.dreamdimension.DreamDimension;
import com.github.ubiquitousspice.dreamdimension.ProxyCommon;
import com.github.ubiquitousspice.dreamdimension.client.render.model.ModelLargeSheep;
import com.github.ubiquitousspice.dreamdimension.client.render.model.ModelLargeSheep2;
import com.github.ubiquitousspice.dreamdimension.client.render.model.ModelUnicorn;
import com.github.ubiquitousspice.dreamdimension.entities.EntityConfusedVillager;
import com.github.ubiquitousspice.dreamdimension.entities.EntityFirework;
import com.github.ubiquitousspice.dreamdimension.entities.EntityLargeSheep;
import com.github.ubiquitousspice.dreamdimension.entities.EntityUnicorn;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ProxyClient extends ProxyCommon
{
    public static int renderID;

    @Override
    public void registerRenderers()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityLargeSheep.class, new RenderLargeSheep(new ModelLargeSheep2(), new ModelLargeSheep(), 0.7F));
        RenderingRegistry.registerEntityRenderingHandler(EntityConfusedVillager.class, new RenderConfusedVillager());
        RenderingRegistry.registerEntityRenderingHandler(EntityUnicorn.class, new RenderUnicorn(new ModelUnicorn(), 0.7F));
        RenderingRegistry.registerEntityRenderingHandler(EntityFirework.class, new RenderFirework());

        MinecraftForgeClient.registerItemRenderer(DreamDimension.dreamFleece.blockID, new RenderGiantWool());

        // render Limbo stuff.
        //renderID = RenderingRegistry.getNextAvailableRenderId();
        //RenderingRegistry.registerBlockHandler(renderID, new RenderLimbo());
    }
}
