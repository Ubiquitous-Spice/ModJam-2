package com.github.ubiquitousspice.dreamdimension.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderLargeSheep extends RenderLiving
{
    private static final ResourceLocation field_110885_a = new ResourceLocation("textures/entity/sheep/sheep_fur.png");
    private static final ResourceLocation field_110884_f = new ResourceLocation("textures/entity/sheep/sheep.png");
    
    private static final ResourceLocation field_110814_a = new ResourceLocation("textures/misc/enchanted_item_glint.png");

    public RenderLargeSheep(ModelBase par1ModelBase, ModelBase par2ModelBase, float par3)
    {
        super(par1ModelBase, par3);
        this.setRenderPassModel(par2ModelBase);
    }
    
    protected int setWoolColorAndRender(EntityLargeSheep par1EntitySheep, int par2, float par3)
    {
    	boolean flag = false;
    	
        if (par2 == 0 && !flag)
        {
            this.func_110776_a(field_110885_a);
            float f1 = 1.0F;
            int j = 0;
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
    protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
    {
        return this.setWoolColorAndRender((EntityLargeSheep)par1EntityLivingBase, par2, par3);
    }

    protected ResourceLocation func_110775_a(Entity par1Entity)
    {
        return this.func_110883_a((EntityLargeSheep)par1Entity);
    }
    
    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
    {
        this.preRenderSheep((EntityLargeSheep)par1EntityLivingBase, par2);
    }

	private void preRenderSheep(EntityLargeSheep par1EntityLivingBase,
			float par2) {
		GL11.glScalef(EntityLargeSheep.largeSheepMod, EntityLargeSheep.largeSheepMod, EntityLargeSheep.largeSheepMod);
		
	}
}