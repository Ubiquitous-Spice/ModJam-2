package com.github.ubiquitousspice.dreamdimension.entities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityFireworkSparkFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityFirework extends EntityFX
{
    private int                  field_92042_ax;
    private final EffectRenderer field_92040_ay;
    boolean                      field_92041_a;
    private int                  effects;
    private int                  type;
    private boolean              flicker;
    private int[]                colors2;
    private int[]                colors;
    private boolean              trail;

    public EntityFirework(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, EffectRenderer par14EffectRenderer, int maxAge, int effects, boolean flicker, int type, int[] colors, int[] colors2, boolean trail)
    {
        super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
        motionX = par8;
        motionY = par10;
        motionZ = par12;
        field_92040_ay = par14EffectRenderer;
        particleMaxAge = 8;
        particleMaxAge = maxAge;
        this.type = type;
        this.effects = effects;
        this.flicker = flicker;
        this.colors = colors;
        this.colors2 = colors2;
        this.trail = trail;
        setInvisible(true);

        for (int i = 0; i < effects; ++i)
        {

            if (flicker)
            {
                field_92041_a = true;
                particleMaxAge += 15;
                break;
            }
        }
    }

    @Override
    public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
    {
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate()
    {
        boolean flag;

        if (field_92042_ax == 0)
        {
            flag = func_92037_i();
            boolean flag1 = false;

            if (effects >= 3)
            {
                flag1 = true;
            }
            else
            {
                for (int i = 0; i < effects; ++i)
                {

                    if (type == 1)
                    {
                        flag1 = true;
                        break;
                    }
                }
            }

            String s = "fireworks." + (flag1 ? "largeBlast" : "blast") + (flag ? "_far" : "");
            worldObj.playSound(posX, posY, posZ, s, 20.0F, 0.95F + rand.nextFloat() * 0.1F, true);
        }

        if (field_92042_ax % 2 == 0 && field_92042_ax / 2 < effects)
        {
            byte b0 = (byte) type;
            boolean flag2 = trail;
            boolean flag3 = flicker;
            int[] aint = colors;
            int[] aint1 = colors2;

            if (b0 == 1)
            {
                func_92035_a(0.5D, 4, aint, aint1, flag2, flag3);
            }
            else if (b0 == 2)
            {
                func_92038_a(0.5D, new double[][] { { 0.0D, 1.0D }, { 0.3455D, 0.309D }, { 0.9511D, 0.309D }, { 0.3795918367346939D, -0.12653061224489795D }, { 0.6122448979591837D, -0.8040816326530612D }, { 0.0D, -0.35918367346938773D } }, aint, aint1, flag2, flag3, false);
            }
            else if (b0 == 3)
            {
                func_92038_a(0.5D, new double[][] { { 0.0D, 0.2D }, { 0.2D, 0.2D }, { 0.2D, 0.6D }, { 0.6D, 0.6D }, { 0.6D, 0.2D }, { 0.2D, 0.2D }, { 0.2D, 0.0D }, { 0.4D, 0.0D }, { 0.4D, -0.6D }, { 0.2D, -0.6D }, { 0.2D, -0.4D }, { 0.0D, -0.4D } }, aint, aint1, flag2, flag3, true);
            }
            else if (b0 == 4)
            {
                func_92036_a(aint, aint1, flag2, flag3);
            }
            else
            {
                func_92035_a(0.25D, 2, aint, aint1, flag2, flag3);
            }

            int k = aint[0];
            float f = ((k & 16711680) >> 16) / 255.0F;
            float f1 = ((k & 65280) >> 8) / 255.0F;
            float f2 = (k & 255) / 255.0F;
            EntityFireworkOverlay entityfireworkoverlayfx = new EntityFireworkOverlay(worldObj, posX, posY, posZ);
            entityfireworkoverlayfx.setRBGColorF(f, f1, f2);
            field_92040_ay.addEffect(entityfireworkoverlayfx);
        }

        ++field_92042_ax;

        if (field_92042_ax > particleMaxAge)
        {
            if (field_92041_a)
            {
                flag = func_92037_i();
                String s1 = "fireworks." + (flag ? "twinkle_far" : "twinkle");
                worldObj.playSound(posX, posY, posZ, s1, 20.0F, 0.9F + rand.nextFloat() * 0.15F, true);
            }

            setDead();
        }
    }

    private boolean func_92037_i()
    {
        Minecraft minecraft = Minecraft.getMinecraft();
        return minecraft == null || minecraft.renderViewEntity == null || minecraft.renderViewEntity.getDistanceSq(posX, posY, posZ) >= 256.0D;
    }

    private void func_92034_a(double par1, double par3, double par5, double par7, double par9, double par11, int[] par13ArrayOfInteger, int[] par14ArrayOfInteger, boolean par15, boolean par16)
    {
        EntityFireworkSparkFX entityfireworksparkfx = new EntityFireworkSparkFX(worldObj, par1, par3, par5, par7, par9, par11, field_92040_ay);
        entityfireworksparkfx.func_92045_e(par15);
        entityfireworksparkfx.func_92043_f(par16);
        int i = rand.nextInt(par13ArrayOfInteger.length);
        entityfireworksparkfx.func_92044_a(par13ArrayOfInteger[i]);

        if (par14ArrayOfInteger != null && par14ArrayOfInteger.length > 0)
        {
            entityfireworksparkfx.func_92046_g(par14ArrayOfInteger[rand.nextInt(par14ArrayOfInteger.length)]);
        }

        field_92040_ay.addEffect(entityfireworksparkfx);
    }

    private void func_92035_a(double par1, int par3, int[] par4ArrayOfInteger, int[] par5ArrayOfInteger, boolean par6, boolean par7)
    {
        double d1 = posX;
        double d2 = posY;
        double d3 = posZ;

        for (int j = -par3; j <= par3; ++j)
        {
            for (int k = -par3; k <= par3; ++k)
            {
                for (int l = -par3; l <= par3; ++l)
                {
                    double d4 = k + (rand.nextDouble() - rand.nextDouble()) * 0.5D;
                    double d5 = j + (rand.nextDouble() - rand.nextDouble()) * 0.5D;
                    double d6 = l + (rand.nextDouble() - rand.nextDouble()) * 0.5D;
                    double d7 = MathHelper.sqrt_double(d4 * d4 + d5 * d5 + d6 * d6) / par1 + rand.nextGaussian() * 0.05D;
                    func_92034_a(d1, d2, d3, d4 / d7, d5 / d7, d6 / d7, par4ArrayOfInteger, par5ArrayOfInteger, par6, par7);

                    if (j != -par3 && j != par3 && k != -par3 && k != par3)
                    {
                        l += par3 * 2 - 1;
                    }
                }
            }
        }
    }

    private void func_92038_a(double par1, double[][] par3ArrayOfDouble, int[] par4ArrayOfInteger, int[] par5ArrayOfInteger, boolean par6, boolean par7, boolean par8)
    {
        double d1 = par3ArrayOfDouble[0][0];
        double d2 = par3ArrayOfDouble[0][1];
        func_92034_a(posX, posY, posZ, d1 * par1, d2 * par1, 0.0D, par4ArrayOfInteger, par5ArrayOfInteger, par6, par7);
        float f = rand.nextFloat() * (float) Math.PI;
        double d3 = par8 ? 0.034D : 0.34D;

        for (int i = 0; i < 3; ++i)
        {
            double d4 = f + (i * (float) Math.PI) * d3;
            double d5 = d1;
            double d6 = d2;

            for (int j = 1; j < par3ArrayOfDouble.length; ++j)
            {
                double d7 = par3ArrayOfDouble[j][0];
                double d8 = par3ArrayOfDouble[j][1];

                for (double d9 = 0.25D; d9 <= 1.0D; d9 += 0.25D)
                {
                    double d10 = (d5 + (d7 - d5) * d9) * par1;
                    double d11 = (d6 + (d8 - d6) * d9) * par1;
                    double d12 = d10 * Math.sin(d4);
                    d10 *= Math.cos(d4);

                    for (double d13 = -1.0D; d13 <= 1.0D; d13 += 2.0D)
                    {
                        func_92034_a(posX, posY, posZ, d10 * d13, d11, d12 * d13, par4ArrayOfInteger, par5ArrayOfInteger, par6, par7);
                    }
                }

                d5 = d7;
                d6 = d8;
            }
        }
    }

    private void func_92036_a(int[] par1ArrayOfInteger, int[] par2ArrayOfInteger, boolean par3, boolean par4)
    {
        double d0 = rand.nextGaussian() * 0.05D;
        double d1 = rand.nextGaussian() * 0.05D;

        for (int i = 0; i < 70; ++i)
        {
            double d2 = motionX * 0.5D + rand.nextGaussian() * 0.15D + d0;
            double d3 = motionZ * 0.5D + rand.nextGaussian() * 0.15D + d1;
            double d4 = motionY * 0.5D + rand.nextDouble() * 0.5D;
            func_92034_a(posX, posY, posZ, d2, d4, d3, par1ArrayOfInteger, par2ArrayOfInteger, par3, par4);
        }
    }

    @Override
    public int getFXLayer()
    {
        return 0;
    }
}
