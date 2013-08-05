package com.github.ubiquitousspice.dreamdimension;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

import com.github.ubiquitousspice.dreamdimension.dimension.ModTeleporter;

import cpw.mods.fml.common.FMLCommonHandler;

public class Util
{
    public static final long DAY_TIME = 24000L;

    /**
     * Will return null if the player is not in bed.
     */
    public static float[] getWakeLocation(EntityPlayer player)
    {
        ChunkCoordinates loc = player.playerLocation;

        Block block = loc == null ? null : Block.blocksList[player.worldObj.getBlockId(loc.posX, loc.posY, loc.posZ)];

        if (loc != null && block != null && block.isBed(player.worldObj, loc.posX, loc.posY, loc.posZ, player))
        {
            block.setBedOccupied(player.worldObj, loc.posX, loc.posY, loc.posZ, player, false);
            loc = block.getBedSpawnPosition(player.worldObj, loc.posX, loc.posY, loc.posZ, player);

            if (loc == null)
            {
                loc = new ChunkCoordinates(loc.posX, loc.posY + 1, loc.posZ);
            }

            return new float[] { loc.posX + 0.5F, loc.posY + player.yOffset + 0.1F, loc.posZ + 0.5F };
        }

        return null;
    }

    public static EntityPlayerMP getPlayerFromUsername(String username)
    {
        return FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().getPlayerForUsername(username);
    }

    public static long getTillNextDay(World world)
    {
        long current = world.getWorldInfo().getWorldTime();
        return DAY_TIME - current % DAY_TIME;
    }

    public static void moveToDream(EntityPlayerMP player)
    {
        player.timeUntilPortal = 10;
        MinecraftServer.getServer().getConfigurationManager().transferPlayerToDimension(player, DreamDimension.dimensionID, new ModTeleporter(player.mcServer.worldServerForDimension(DreamDimension.dimensionID)));
    }
}
