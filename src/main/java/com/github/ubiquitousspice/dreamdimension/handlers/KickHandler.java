package com.github.ubiquitousspice.dreamdimension.handlers;

import com.github.ubiquitousspice.dreamdimension.DreamDimension;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;

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

    @SideOnly(Side.CLIENT)
    @ForgeSubscribe
    public void removeHungerBar(RenderGameOverlayEvent event)
    {
        if (Minecraft.getMinecraft().theWorld.provider.dimensionId == DreamDimension.dimensionID)
        {
            if (event.type == RenderGameOverlayEvent.ElementType.FOOD || event.type == RenderGameOverlayEvent.ElementType.EXPERIENCE)
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
