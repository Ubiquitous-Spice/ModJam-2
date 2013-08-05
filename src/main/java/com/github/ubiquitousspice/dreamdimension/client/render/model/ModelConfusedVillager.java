package com.github.ubiquitousspice.dreamdimension.client.render.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelConfusedVillager extends ModelBiped
{

    public ModelRenderer villagerApron;

    public ModelConfusedVillager()
    {
        this(0.0F, 0.0F, false);
    }

    public ModelConfusedVillager(float par1, float par2, boolean par3)
    {
        super(par1, 0.0F, 64, par3 ? 32 : 64);

        if (par3)
        {
            bipedHead = new ModelRenderer(this, 0, 0);
            bipedHead.addBox(-4.0F, -10.0F, -4.0F, 8, 6, 8, par1);
            bipedHead.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
        }
        else
        {
            bipedHead = new ModelRenderer(this);
            bipedHead.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
            bipedHead.setTextureOffset(0, 32).addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, par1);
            bipedHead.setTextureOffset(24, 32).addBox(-1.0F, -3.0F, -6.0F, 2, 4, 2, par1);
        }

        villagerApron = new ModelRenderer(this, 32, 32);
        villagerApron.addBox(-4.0F, 0.0F, -2.0F, 8, 20, 4, par1 + 0.5F);
        villagerApron.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
    }

    @Override
    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);

        if (isChild)
        {
            float f6 = 2.0F;
            GL11.glPushMatrix();
            GL11.glScalef(1.5F / f6, 1.5F / f6, 1.5F / f6);
            GL11.glTranslatef(0.0F, 16.0F * par7, 0.0F);
            bipedHead.render(par7);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
            GL11.glTranslatef(0.0F, 24.0F * par7, 0.0F);
            bipedBody.render(par7);
            bipedRightArm.render(par7);
            bipedLeftArm.render(par7);
            bipedRightLeg.render(par7);
            bipedLeftLeg.render(par7);
            bipedHeadwear.render(par7);
            villagerApron.render(par7);
            GL11.glPopMatrix();
        }
        else
        {
            bipedHead.render(par7);
            bipedBody.render(par7);
            bipedRightArm.render(par7);
            bipedLeftArm.render(par7);
            bipedRightLeg.render(par7);
            bipedLeftLeg.render(par7);
            bipedHeadwear.render(par7);
            villagerApron.render(par7);
        }
    }

    public int func_82897_a()
    {
        return 10;
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
    {
        super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);

        float f61;
        if (onGround > -9990.0F)
        {
            f61 = onGround;
            villagerApron.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(f61) * (float) Math.PI * 2.0F) * 0.2F;
        }

        if (isSneak)
        {
            villagerApron.rotateAngleX = 0.5F;
        }
        else
        {
            villagerApron.rotateAngleX = 0.0F;
        }

        float f6 = MathHelper.sin(onGround * (float) Math.PI);
        float f7 = MathHelper.sin((1.0F - (1.0F - onGround) * (1.0F - onGround)) * (float) Math.PI);
        bipedRightArm.rotateAngleZ = 0.0F;
        bipedLeftArm.rotateAngleZ = 0.0F;
        bipedRightArm.rotateAngleY = -(0.1F - f6 * 0.6F);
        bipedLeftArm.rotateAngleY = 0.1F - f6 * 0.6F;
        bipedRightArm.rotateAngleX = -((float) Math.PI / 2F);
        bipedLeftArm.rotateAngleX = -((float) Math.PI / 2F);
        bipedRightArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
        bipedLeftArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
        bipedRightArm.rotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
        bipedLeftArm.rotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
        bipedRightArm.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
        bipedLeftArm.rotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;
    }
}
