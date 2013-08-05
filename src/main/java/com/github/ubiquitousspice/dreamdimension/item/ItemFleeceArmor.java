package com.github.ubiquitousspice.dreamdimension.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.EnumHelper;

import com.github.ubiquitousspice.dreamdimension.DreamDimension;
import com.github.ubiquitousspice.dreamdimension.dimension.WorldProviderMod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFleeceArmor extends ItemArmor
{
    public static EnumArmorMaterial cloth = EnumHelper.addArmorMaterial("CLOTH", 4, new int[] { 1, 1, 1, 1 }, 120);
    private int armorType2;

    private String name;

    private boolean inDreamWorld;

    public ItemFleeceArmor(int par1, int par4, String a)
    {
        super(par1, cloth, 0, par4);
        armorType2 = par4;

        name = a;

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        super.registerIcons(par1IconRegister);

        switch (armorType2)
            {
                case 0:
                    itemIcon = par1IconRegister.registerIcon(DreamDimension.MODID + ":fleeceHelmet");
                    break;
                case 1:
                    itemIcon = par1IconRegister.registerIcon(DreamDimension.MODID + ":fleeceChest");
                    break;
                case 2:
                    itemIcon = par1IconRegister.registerIcon(DreamDimension.MODID + ":fleeceLeggings");
                    break;
                case 3:
                    itemIcon = par1IconRegister.registerIcon(DreamDimension.MODID + ":fleeceBoots");
                    break;
                default:
                    itemIcon = par1IconRegister.registerIcon(DreamDimension.MODID + ":fleeceHelmet");
                    break;
            }
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer)
    {
        return armorType2 == 2 ? DreamDimension.MODID + ":textures/models/armor/fleeceArmor02.png" : DreamDimension.MODID + ":textures/models/armor/fleeceArmor01.png";
    }

    @Override
    public String getItemDisplayName(ItemStack stack)
    {

        inDreamWorld = Minecraft.getMinecraft().thePlayer.worldObj.provider instanceof WorldProviderMod;
        String end = inDreamWorld ? "dreamName" : "name";

        // speeder.
        return StatCollector.translateToLocal("tile." + DreamDimension.MODID + ":" + name + "." + end);
    }

}
