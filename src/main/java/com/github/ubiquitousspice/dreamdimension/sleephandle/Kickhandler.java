package com.github.ubiquitousspice.dreamdimension.sleephandle;

import com.github.ubiquitousspice.dreamdimension.DreamDimension;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;

public class KickHandler
{
    @ForgeSubscribe
    public void onDeath(LivingDeathEvent event)
    {
        if (isValidEvent(event))
        {
            EntityPlayerMP player = (EntityPlayerMP) event.entityLiving;

            DreamManager.kickDreamer(player, 200);

            event.setCanceled(true);
        }
    }

    private boolean isValidEvent(LivingEvent event)
    {
        EntityLivingBase entity = event.entityLiving;

        // only server events.
        if (FMLCommonHandler.instance().getEffectiveSide().isClient())
            return false;

        return entity instanceof EntityPlayer && entity.worldObj.provider.dimensionId == DreamDimension.dimensionID;
    }
}
