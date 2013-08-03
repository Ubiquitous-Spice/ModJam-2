package com.github.ubiquitousspice.dreamdimension.sleephandle;

import com.github.ubiquitousspice.dreamdimension.Util;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;

public class BedHandler implements ITickHandler
{
    private static final HashSet<String> sleepers = new HashSet<String>();

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData)
    {
        // efficiency
        if (sleepers.isEmpty())
            return;

        // had better by the world tickType.
        World world = (World)tickData[0];

        EntityPlayer player;
        ArrayList<String> removeList = new ArrayList<String>();

        // loop through sleepers
        for (String user : sleepers)
        {
            player = Util.getPlayerFromUsername(user);
            if (player.isPlayerFullyAsleep())
            {
                //HURRY, TELEPORT THEM NOW! to the dream!
                // TODO: TELEPORT!
                player.wakeUpPlayer(true, false, true);
                removeList.add(user);
            }
        }

        sleepers.removeAll(removeList);
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData)
    {
        // nothing here
    }

    @Override
    public EnumSet<TickType> ticks()
    {
        return EnumSet.of(TickType.WORLD);
    }

    @ForgeSubscribe
    public void catchBedEvent(PlayerSleepInBedEvent event)
    {
        sleepers.add(event.entityPlayer.username);
    }

    @Override
    public String getLabel()
    {
        return "PlayerSleepHandler";
    }
}
