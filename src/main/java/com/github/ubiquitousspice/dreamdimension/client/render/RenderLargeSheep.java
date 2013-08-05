package com.github.ubiquitousspice.dreamdimension.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.github.ubiquitousspice.dreamdimension.client.render.model.ModelLargeSheep2;
import com.github.ubiquitousspice.dreamdimension.entities.EntityLargeSheep;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderLargeSheep extends RenderLiving
{
    private static final ResourceLocation field_110885_a = new ResourceLocation("textures/entity/sheep/sheep_fur.png");
    private static final ResourceLocation field_110884_f = new ResourceLocation("textures/entity/sheep/sheep.png");

    public RenderLargeSheep(ModelBase par1ModelBase, ModelBase par2ModelBase, float par3)
    {
        super(par1ModelBase, par3);
        setRenderPassModel(par2ModelBase);
    }

    private double func_110828_a(double par1, double par3, double par5)
    {
        return par1 + (par3 - par1) * par5;
    }

    @Override
    protected void func_110827_b(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        Entity entity = par1EntityLiving.func_110166_bE();

        if (entity != null)
        {
            par4 -= (1.6D - par1EntityLiving.height) * 0.5D;
            Tessellator tessellator = Tessellator.instance;
            double d3 = func_110828_a(entity.prevRotationYaw, entity.rotationYaw, (par9 * 0.5F)) * 0.01745329238474369D;
            double d4 = func_110828_a(entity.prevRotationPitch, entity.rotationPitch, (par9 * 0.5F)) * 0.01745329238474369D;
            double d5 = Math.cos(d3);
            double d6 = Math.sin(d3);
            double d7 = Math.sin(d4);

            if (entity instanceof EntityHanging)
            {
                d5 = 0.0D;
                d6 = 0.0D;
                d7 = -1.0D;
            }

            double d8 = Math.cos(d4);
            double d9 = func_110828_a(entity.prevPosX, entity.posX, par9) - d5 * 0.7D - d6 * 0.5D * d8;
            double d10 = func_110828_a(entity.prevPosY + entity.getEyeHeight() * 0.7D, entity.posY + entity.getEyeHeight() * 0.7D, par9) - d7 * 0.5D - 0.25D;
            double d11 = func_110828_a(entity.prevPosZ, entity.posZ, par9) - d6 * 0.7D + d5 * 0.5D * d8;
            double d12 = func_110828_a(par1EntityLiving.prevRenderYawOffset, par1EntityLiving.renderYawOffset, par9) * 0.01745329238474369D + Math.PI / 2D;
            d5 = Math.cos(d12) * par1EntityLiving.width * 0.4D;
            d6 = Math.sin(d12) * par1EntityLiving.width * 0.4D;
            double d13 = func_110828_a(par1EntityLiving.prevPosX, par1EntityLiving.posX, par9) + d5;
            double d14 = func_110828_a(par1EntityLiving.prevPosY, par1EntityLiving.posY, par9);
            double d15 = func_110828_a(par1EntityLiving.prevPosZ, par1EntityLiving.posZ, par9) + d6;
            par2 += d5;
            par6 += d6;
            double d16 = (float) (d9 - d13);
            double d17 = (float) (d10 - d14);
            double d18 = (float) (d11 - d15);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_CULL_FACE);
            tessellator.startDrawing(5);
            int i;
            float f2;

            for (i = 0; i <= 24; ++i)
            {
                if (i % 2 == 0)
                {
                    tessellator.setColorRGBA_F(0.5F, 0.4F, 0.3F, 1.0F);
                }
                else
                {
                    tessellator.setColorRGBA_F(0.35F, 0.28F, 0.21000001F, 1.0F);
                }

                f2 = i / 24.0F;
                tessellator.addVertex(par2 + d16 * f2 + 0.0D, par4 + d17 * (f2 * f2 + f2) * 0.5D + ((24.0F - i) / 18.0F + 0.125F), par6 + d18 * f2);
                tessellator.addVertex(par2 + d16 * f2 + 0.025D, par4 + d17 * (f2 * f2 + f2) * 0.5D + ((24.0F - i) / 18.0F + 0.125F) + 0.025D, par6 + d18 * f2);
            }

            tessellator.draw();
            tessellator.startDrawing(5);

            for (i = 0; i <= 24; ++i)
            {
                if (i % 2 == 0)
                {
                    tessellator.setColorRGBA_F(0.5F, 0.4F, 0.3F, 1.0F);
                }
                else
                {
                    tessellator.setColorRGBA_F(0.35F, 0.28F, 0.21000001F, 1.0F);
                }

                f2 = i / 24.0F;
                tessellator.addVertex(par2 + d16 * f2 + 0.0D, par4 + d17 * (f2 * f2 + f2) * 0.5D + ((24.0F - i) / 18.0F + 0.125F) + 0.025D, par6 + d18 * f2);
                tessellator.addVertex(par2 + d16 * f2 + 0.025D, par4 + d17 * (f2 * f2 + f2) * 0.5D + ((24.0F - i) / 18.0F + 0.125F), par6 + d18 * f2 + 0.025D);
            }

            tessellator.draw();
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glEnable(GL11.GL_CULL_FACE);
        }
    }

    protected int setWoolColorAndRender(EntityLargeSheep par1EntitySheep, int par2, float par3)
    {
        boolean flag = false;

        if (par2 == 0 && !flag)
        {
            func_110776_a(field_110885_a);
            float f1 = 1.0F;
            int j = par1EntitySheep.getFleeceColor();
            GL11.glColor3f(f1 * EntitySheep.fleeceColorTable[j][0], f1 * EntitySheep.fleeceColorTable[j][1], f1 * EntitySheep.fleeceColorTable[j][2]);
            return 1;
        }
        else
        {
            return -1;
        }
    }

    protected ResourceLocation func_110883_a(EntityLargeSheep par1EntitySheep)
    {
        return field_110884_f;
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    @Override
    protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
    {
        return setWoolColorAndRender((EntityLargeSheep) par1EntityLivingBase, par2, par3);
    }

    @Override
    protected ResourceLocation func_110775_a(Entity par1Entity)
    {
        return func_110883_a((EntityLargeSheep) par1Entity);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
    {
        preRenderSheep((EntityLargeSheep) par1EntityLivingBase, par2);
    }

    private void preRenderSheep(EntityLargeSheep par1EntityLivingBase, float par2)
    {
        GL11.glScalef(EntityLargeSheep.largeSheepMod, EntityLargeSheep.largeSheepMod, EntityLargeSheep.largeSheepMod);

    }

    @Override
    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        renderHealthBar((EntityLargeSheep) par1EntityLiving);

        super.doRenderLiving(par1EntityLiving, par2, par4, par6, par8, par9);
    }

    private void renderHealthBar(EntityLargeSheep par1EntityLiving)
    {
        BossStatus.func_82824_a(par1EntityLiving, true);
        ((ModelLargeSheep2) mainModel).func_82903_a();

    }
}
