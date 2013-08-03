package com.github.ubiquitousspice.dreamdimension.sleephandle;

import com.github.ubiquitousspice.dreamdimension.DreamDimension;
import com.github.ubiquitousspice.dreamdimension.Util;
import com.github.ubiquitousspice.dreamdimension.dimension.ModTeleporter;
import cpw.mods.fml.common.IScheduledTickHandler;
import cpw.mods.fml.common.TickType;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ChunkCoordinates;

import java.util.EnumSet;
import java.util.HashMap;

public class DreamManager implements IScheduledTickHandler
{
    private static final HashMap<String, DreamerData> dreamers = new HashMap<String, DreamerData>();

    @Override
    public int nextTickSpacing()
    {
        return 20;
    }

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData)
    {
        // nothing.
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData)
    {
        // do stuff here.
        EntityPlayerMP player = (EntityPlayerMP)tickData[0];

        DreamerData data = dreamers.get(player.username);

        // null check.
        if (data == null)
            return;

        if (!data.decrementTime())
        {
            kickDreamer(player);
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
        return "PlayerDreamHandler";
    }

    // API methods

    /**
     * Loads a player, logging in while dreaming.
     */
    public static void loadDreamer(EntityPlayerMP player)
    {
        DreamerData data = DreamerData.read(player);
        dreamers.put(player.username, data);
    }

    /**
     * Saves a player logging out while dreaming.
     */
    public static void saveDreamer(EntityPlayerMP player)
    {
        DreamerData data = dreamers.remove(player.username);
        data.writeToNBT(player.getEntityData());
    }


    /**
     * Adds the player to the dreamer list, and replaces their inventory.
     */
    public static void addDreamer(EntityPlayerMP player, long time)
    {
        DreamerData data = new DreamerData(player);
        data.setTimeLeft(time);

        player.inventory.clearInventory(-1, -1);

        dreamers.put(player.username, data);
    }

    /**
     * Adds a dreamer with time until the next day.
     */
    public static void addDreamer(EntityPlayerMP player)
    {
        addDreamer(player, Util.getTillNextDay(player.worldObj));
    }

    /**
     * sends the dreamer back to their bed.
     */
    public static void kickDreamer(EntityPlayerMP player)
    {
        DreamerData data = dreamers.remove(player.username);

        // intentional no null check.

        // give em back inventory.
        player.inventory.clearInventory(-1, -1);
        player.inventory.copyInventory(data.getOldInv());

        // teleport them to the dimension.
        player.timeUntilPortal = 10;
        player.mcServer.getConfigurationManager().transferPlayerToDimension(player, data.getBedDim(), new ModTeleporter(player.mcServer.worldServerForDimension(data.getBedDim())));

        // and now to the place.
        player.setPosition(data.getBedX(), data.getBedY(), data.getBedZ());
    }
}
