package com.github.ubiquitousspice.dreamdimension.client.render;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.model.ModelZombieVillager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import com.github.ubiquitousspice.dreamdimension.DreamDimension;
import com.github.ubiquitousspice.dreamdimension.client.render.model.ModelConfusedVillager;
import com.github.ubiquitousspice.dreamdimension.entities.EntityConfusedVillager;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderConfusedVillager extends RenderBiped
{
    private ResourceLocation      location      = new ResourceLocation(DreamDimension.MODID, "textures/entity/confusedVillager.png");
    private ModelBiped            field_82434_o;
    private ModelConfusedVillager field_82432_p;
    protected ModelBiped          field_82437_k;
    protected ModelBiped          field_82435_l;
    protected ModelBiped          field_82436_m;
    protected ModelBiped          field_82433_n;
    private int                   field_82431_q = 1;

    public RenderConfusedVillager()
    {
        super(new ModelZombie(), 0.5F, 1.0F);
        field_82434_o = modelBipedMain;
        field_82432_p = new ModelConfusedVillager();
    }

    @Override
    protected void func_82421_b()
    {
        field_82423_g = new ModelZombie(1.0F, true);
        field_82425_h = new ModelZombie(0.5F, true);
        field_82437_k = field_82423_g;
        field_82435_l = field_82425_h;
        field_82436_m = new ModelZombieVillager(1.0F, 0.0F, true);
        field_82433_n = new ModelZombieVillager(0.5F, 0.0F, true);
    }

    protected int func_82429_a(EntityConfusedVillager par1EntityZombie, int par2, float par3)
    {
        func_82427_a(par1EntityZombie);
        return super.func_130006_a(par1EntityZombie, par2, par3);
    }

    public void func_82426_a(EntityConfusedVillager par1EntityZombie, double par2, double par4, double par6, float par8, float par9)
    {
        func_82427_a(par1EntityZombie);
        super.doRenderLiving(par1EntityZombie, par2, par4, par6, par8, par9);
    }

    protected ResourceLocation func_110863_a(EntityConfusedVillager par1EntityZombie)
    {
        return location;
    }

    protected void func_82428_a(EntityConfusedVillager par1EntityZombie, float par2)
    {
        func_82427_a(par1EntityZombie);
        super.func_130005_c(par1EntityZombie, par2);
    }

    private void func_82427_a(EntityConfusedVillager par1EntityZombie)
    {
        if (par1EntityZombie.isVillager())
        {
            if (field_82431_q != field_82432_p.func_82897_a())
            {
                field_82432_p = new ModelConfusedVillager();
                field_82431_q = field_82432_p.func_82897_a();
                field_82436_m = new ModelZombieVillager(1.0F, 0.0F, true);
                field_82433_n = new ModelZombieVillager(0.5F, 0.0F, true);
            }

            mainModel = field_82432_p;
            field_82423_g = field_82436_m;
            field_82425_h = field_82433_n;
        }
        else
        {
            mainModel = field_82434_o;
            field_82423_g = field_82437_k;
            field_82425_h = field_82435_l;
        }

        modelBipedMain = (ModelBiped) mainModel;
    }

    protected void func_82430_a(EntityConfusedVillager par1EntityZombie, float par2, float par3, float par4)
    {

        super.rotateCorpse(par1EntityZombie, par2, par3, par4);
    }

    @Override
    protected void func_130005_c(EntityLiving par1EntityLiving, float par2)
    {
        func_82428_a((EntityConfusedVillager) par1EntityLiving, par2);
    }

    @Override
    protected ResourceLocation func_110856_a(EntityLiving par1EntityLiving)
    {
        return func_110863_a((EntityConfusedVillager) par1EntityLiving);
    }

    @Override
    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        func_82426_a((EntityConfusedVillager) par1EntityLiving, par2, par4, par6, par8, par9);
    }

    @Override
    protected int func_130006_a(EntityLiving par1EntityLiving, int par2, float par3)
    {
        return func_82429_a((EntityConfusedVillager) par1EntityLiving, par2, par3);
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    @Override
    protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
    {
        return func_82429_a((EntityConfusedVillager) par1EntityLivingBase, par2, par3);
    }

    @Override
    protected void renderEquippedItems(EntityLivingBase par1EntityLivingBase, float par2)
    {
        func_82428_a((EntityConfusedVillager) par1EntityLivingBase, par2);
    }

    @Override
    protected void rotateCorpse(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4)
    {
        func_82430_a((EntityConfusedVillager) par1EntityLivingBase, par2, par3, par4);
    }

    @Override
    public void renderPlayer(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9)
    {
        func_82426_a((EntityConfusedVillager) par1EntityLivingBase, par2, par4, par6, par8, par9);
    }

    @Override
    protected ResourceLocation func_110775_a(Entity par1Entity)
    {
        return func_110863_a((EntityConfusedVillager) par1Entity);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        func_82426_a((EntityConfusedVillager) par1Entity, par2, par4, par6, par8, par9);
    }
}
