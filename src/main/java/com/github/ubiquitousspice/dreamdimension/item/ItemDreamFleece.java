package com.github.ubiquitousspice.dreamdimension.item;

import com.github.ubiquitousspice.dreamdimension.DreamDimension;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemDreamFleece extends ItemBlockWithMetadata
{
    String name = "dreamFleece";

    public ItemDreamFleece(int par1, Block par2Block)
    {
        super(par1, par2Block);
    }

    @Override
    public String getItemDisplayName(ItemStack stack)
    {
        boolean inDreamWorld = Minecraft.getMinecraft().theWorld.provider.dimensionId == DreamDimension.dimensionID;

        String end = inDreamWorld ? "dreamName" : "name";

        if ((stack.getItemDamage() & 8) > 0)
        {
            // speeder.
            return StatCollector.translateToLocal("tile." + DreamDimension.MODID + ":" + name + "." + end);
        }
        else
        {
            return StatCollector.translateToLocal("tile." + DreamDimension.MODID + ":" + name + "." + end);
        }
    }
}
