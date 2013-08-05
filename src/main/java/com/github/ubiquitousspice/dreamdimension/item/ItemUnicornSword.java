package com.github.ubiquitousspice.dreamdimension.item;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.EnumHelper;

import com.github.ubiquitousspice.dreamdimension.DreamDimension;
import com.github.ubiquitousspice.dreamdimension.dimension.WorldProviderMod;
import com.github.ubiquitousspice.dreamdimension.entities.EntityFirework;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemUnicornSword extends ItemSword
{

    public static EnumToolMaterial unicorn = EnumHelper.addToolMaterial("UNICORN", 1, 131, 6.0F, 1.0F, 22);

    private String normalName;
    private String dreamName;

    protected boolean inDreamWorld;

    Random rand = new Random();

    public ItemUnicornSword(int par1, String a, String b)
    {
        super(par1, unicorn);
        this.normalName = a;
        this.dreamName = b;

    }

    public String getItemDisplayName(ItemStack stack)
    {
        this.inDreamWorld = Minecraft.getMinecraft().thePlayer.worldObj.provider instanceof WorldProviderMod;
        return (this.inDreamWorld) ? dreamName : normalName;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        super.registerIcons(par1IconRegister);

        this.itemIcon = par1IconRegister.registerIcon(DreamDimension.MODID + ":unicornSword");
    }

    public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase)
    {
        if (!par3EntityLivingBase.isEntityInvulnerable())
        {
            System.out.println("hello");

            ArrayList arraylist = new ArrayList();

            arraylist.add(Integer.valueOf(ItemDye.dyeColors[this.rand.nextInt(ItemDye.dyeColors.length)]));

            int[] aint = new int[arraylist.size()];

            for (int j2 = 0; j2 < aint.length; ++j2)
            {
                aint[j2] = ((Integer) arraylist.get(j2)).intValue();
            }

            if (par3EntityLivingBase.worldObj.isRemote)
            {
                System.out.println("hello");
                EntityFirework fireWork = new EntityFirework(par2EntityLivingBase.worldObj, par2EntityLivingBase.posX, par2EntityLivingBase.posY + 0.5F, par2EntityLivingBase.posZ, 0, 0, 0, Minecraft.getMinecraft().effectRenderer, 20, 1, rand.nextBoolean(), 1, aint, aint, rand.nextBoolean());
                par3EntityLivingBase.worldObj.spawnEntityInWorld(fireWork);
            }
        }

        return super.hitEntity(par1ItemStack, par2EntityLivingBase, par3EntityLivingBase);
    }

}
