package com.github.ubiquitousspice.dreamdimension;

import java.util.EnumSet;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class TickHandler implements ITickHandler
{

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData)
    {

    }

    private void onPlayerTick(EntityPlayer player)
    {

        boolean boots = player.inventory.armorInventory[0] != null ? player.inventory.armorInventory[0].getItem() == DreamDimension.fleeceBoots : false;
        boolean legs = player.inventory.armorInventory[1] != null ? player.inventory.armorInventory[1].getItem() == DreamDimension.fleeceLegs : false;
        boolean chest = player.inventory.armorInventory[2] != null ? player.inventory.armorInventory[2].getItem() == DreamDimension.fleeceChest : false;
        boolean helm = player.inventory.armorInventory[3] != null ? player.inventory.armorInventory[3].getItem() == DreamDimension.fleeceHelmet : false;

        if (player.isCollidedVertically)
        {
            if (boots)
            {
                player.fallDistance *= 0.8F;
            }

            if (legs)
            {
                player.fallDistance *= 0.8F;
            }

            if (chest)
            {
                player.fallDistance *= 0.8F;
            }

            if (helm)
            {
                player.fallDistance *= 0.8F;
            }
        }
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData)
    {
        if (type.equals(EnumSet.of(TickType.PLAYER)))
        {
            onPlayerTick((EntityPlayer) tickData[0]);
        }

    }

    @Override
    public EnumSet<TickType> ticks()
    {

        return EnumSet.of(TickType.PLAYER);
    }

    @Override
    public String getLabel()
    {

        return "TickTock";
    }

}
