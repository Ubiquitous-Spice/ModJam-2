package com.github.ubiquitousspice.dreamdimension.handlers;

import java.util.EnumSet;
import java.util.HashSet;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import com.github.ubiquitousspice.dreamdimension.DreamDimension;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class MilkHandler implements ITickHandler
{
    public static final int       MILK_TIME     = 1 * 60 * 20;
    public static final int       MAX_MILK_TIME = 12 * 60 * 20;

    private final HashSet<String> mayDrinkList  = new HashSet<String>();
    private final HashSet<String> drinkingList  = new HashSet<String>();

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData)
    {
        EntityPlayerMP player = (EntityPlayerMP) tickData[0];

        if (mayDrinkList.contains(player.username) && player.isUsingItem() && player.getItemInUse().getItem().itemID == Item.bucketMilk.itemID)
        {
            drinkingList.add(player.username);
            mayDrinkList.remove(player.username);
        }
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData)
    {
        EntityPlayerMP player = (EntityPlayerMP) tickData[0];

        if (drinkingList.contains(player.username) && !player.isUsingItem())
        {
            long milkTime = 0;
            if (player.getEntityData().hasKey(DreamDimension.MODID + ".extraDreamTime"))
            {
                milkTime = player.getEntityData().getLong(DreamDimension.MODID + ".extraDreamTime");
            }
            milkTime += MILK_TIME;

            if (milkTime > MAX_MILK_TIME)
            {
                milkTime = MAX_MILK_TIME;
            }

            player.getEntityData().setLong(DreamDimension.MODID + ".extraDreamTime", milkTime);
        }
    }

    @ForgeSubscribe()
    public void drinkingEvent(PlayerInteractEvent event)
    {
        // get correct event.
        if ((event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR || event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) && event.entityPlayer.getCurrentEquippedItem() != null && event.entityPlayer.getCurrentEquippedItem().getItem().itemID == Item.bucketMilk.itemID)
        {
            mayDrinkList.add(event.entityPlayer.username);
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
        return "MilkDrinkHandler";
    }
}
