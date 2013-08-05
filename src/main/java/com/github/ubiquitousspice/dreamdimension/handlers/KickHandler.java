package com.github.ubiquitousspice.dreamdimension.handlers;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;

import com.github.ubiquitousspice.dreamdimension.DreamDimension;

import cpw.mods.fml.common.FMLCommonHandler;

public class KickHandler
{
    @ForgeSubscribe
    public void onDeath(LivingDeathEvent event)
    {
        if (isValidEvent(event))
        {
            EntityPlayerMP player = (EntityPlayerMP) event.entityLiving;

            DreamManager.kickDreamer(player, 200, null);

            event.setCanceled(true);
        }
    }

    private boolean isValidEvent(LivingEvent event)
    {
        EntityLivingBase entity = event.entityLiving;

        // only server events.
        if (FMLCommonHandler.instance().getEffectiveSide().isClient())
        {
            return false;
        }

        return entity instanceof EntityPlayer && entity.worldObj.provider.dimensionId == DreamDimension.dimensionID;
    }
}
