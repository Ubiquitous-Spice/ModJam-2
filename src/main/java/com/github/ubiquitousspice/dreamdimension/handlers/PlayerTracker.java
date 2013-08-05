package com.github.ubiquitousspice.dreamdimension.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

import com.github.ubiquitousspice.dreamdimension.DreamDimension;

import cpw.mods.fml.common.IPlayerTracker;

public class PlayerTracker implements IPlayerTracker
{
    @Override
    public void onPlayerLogin(EntityPlayer player)
    {
        if (player.worldObj.provider.dimensionId == DreamDimension.dimensionID)
        {
            DreamManager.loadDreamer((EntityPlayerMP) player);
        }
    }

    @Override
    public void onPlayerLogout(EntityPlayer player)
    {
        if (player.worldObj.provider.dimensionId == DreamDimension.dimensionID)
        {
            DreamManager.saveDreamer((EntityPlayerMP) player);
        }
    }

    @Override
    public void onPlayerChangedDimension(EntityPlayer player)
    {
        // don't care.
    }

    @Override
    public void onPlayerRespawn(EntityPlayer player)
    {
        // don't care
    }
}
