package com.github.ubiquitousspice.dreamdimension.entities;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityFireworkOverlay extends EntityFX
{
    protected EntityFireworkOverlay(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
        particleMaxAge = 4;
    }

    public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7, int[] color)
    {
        float f6 = 0.25F;
        float f7 = f6 + 0.25F;
        float f8 = 0.125F;
        float f9 = f8 + 0.25F;
        float f10 = 7.1F * MathHelper.sin((particleAge + par2 - 1.0F) * 0.25F * (float) Math.PI);
        particleAlpha = 0.6F - (particleAge + par2 - 1.0F) * 0.25F * 0.5F;
        float f11 = (float) (prevPosX + (posX - prevPosX) * par2 - interpPosX);
        float f12 = (float) (prevPosY + (posY - prevPosY) * par2 - interpPosY);
        float f13 = (float) (prevPosZ + (posZ - prevPosZ) * par2 - interpPosZ);
        par1Tessellator.setColorRGBA_F(particleRed, particleGreen, particleBlue, particleAlpha);
        par1Tessellator.addVertexWithUV((f11 - par3 * f10 - par6 * f10), (f12 - par4 * f10), (f13 - par5 * f10 - par7 * f10), f7, f9);
        par1Tessellator.addVertexWithUV((f11 - par3 * f10 + par6 * f10), (f12 + par4 * f10), (f13 - par5 * f10 + par7 * f10), f7, f8);
        par1Tessellator.addVertexWithUV((f11 + par3 * f10 + par6 * f10), (f12 + par4 * f10), (f13 + par5 * f10 + par7 * f10), f6, f8);
        par1Tessellator.addVertexWithUV((f11 + par3 * f10 - par6 * f10), (f12 - par4 * f10), (f13 + par5 * f10 - par7 * f10), f6, f9);
    }
}
