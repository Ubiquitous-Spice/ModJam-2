package com.github.ubiquitousspice.dreamdimension.dimension;

import java.util.Random;

import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;

import com.github.ubiquitousspice.dreamdimension.DreamDimension;
import com.github.ubiquitousspice.dreamdimension.dimension.world.ChunkProviderMod;
import com.github.ubiquitousspice.dreamdimension.dimension.world.SkyRenderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WorldProviderMod extends WorldProvider
{

    private static float skyColorMax = 1.0F;
    private static float skyColorMin = 0.05F;
    private int         fogTicks;
    private float       skyR       = 0.01F;
    private float       skyG       = 0.01F;
    private float       skyB       = 0.01F;
    private float       incrementR;
    private float       incrementG;
    private float       incrementB;

    Random               rand        = new Random();

    public void registerWorldChunkManager()
    {
        this.worldChunkMgr = new WorldChunkManagerHell(DreamDimension.dreamy, this.dimensionId, this.dimensionId);
        this.dimensionId = DreamDimension.dimensionID;
        //this.hasNoSky = false;
    }

    public IChunkProvider createChunkGenerator()
    {
        return new ChunkProviderMod(this.worldObj, this.worldObj.getSeed(), false);
    }

    public int getAverageGroundLevel()
    {
        return 0;
    }

    @SideOnly(Side.CLIENT)
    public boolean doesXZShowFog(int par1, int par2)
    {
        return false;
    }

    public String getDimensionName()
    {
        return "Dream Dimension";
    }

    public boolean renderStars()
    {
        return true;
    }

    public float getStarBrightness(World world, float f)
    {
        return 40.0F;
    }

    public boolean renderClouds()
    {
        return false;
    }

    public boolean renderVoidFog()
    {
        return true;
    }

    public boolean renderEndSky()
    {
        return true;
    }

    public float setSunSize()
    {
        return 10.0F;
    }

    public float setMoonSize()
    {
        return 2.0F;
    }

    @SideOnly(Side.CLIENT)
    public boolean isSkyColored()
    {
        return true;
    }

    public boolean canRespawnHere()
    {
        return false;
    }

    public boolean isSurfaceWorld()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public float getCloudHeight()
    {
        return 90.0F;
    }

    /**
     * @SideOnly(Side.CLIENT) public String getSunTexture() {
     *                        return "/Main:TwinSuns.png";
     *                        }
     */

    public ChunkCoordinates getEntrancePortalLocation()
    {
        return new ChunkCoordinates(50, 5, 0);
    }

    protected void generateLightBrightnessTable()
    {
        float f = 12.0F;
        for (int i = 0; i <= 15; i++)
        {
            float f1 = 12.0F - i / 15.0F;
            this.lightBrightnessTable[i] = ((1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f);
        }
    }

    @SideOnly(Side.CLIENT)
    public String getWelcomeMessage()
    {
        if ((this instanceof WorldProviderMod))
        {
            return "Come with me, and you'll be...";
        }

        return null;
    }

    public String getDepartMessage()
    {
        if (this instanceof WorldProviderMod)
        {
            return "Waking up...";
        }

        return null;
    }

    @SideOnly(Side.CLIENT)
    public float[] calcSunriseSunsetColors(float par1, float par2)
    {

        return null;
    }

    public float calculateCelestialAngle(long par1, float par3)
    {
        /**
         * int j = (int) (par1 % 24000L);
         * float f1 = (j + par3) / 24000.0F - 0.25F;
         * if (f1 < 0.0F) {
         * f1 += 1.0F;
         * }
         * if (f1 > 1.0F) {
         * f1 -= 1.0F;
         * }
         * float f2 = f1;
         * f1 = 1.0F - (float) ((Math.cos(f1 * 3.141592653589793D) + 1.0D) / 2.0D);
         * f1 = f2 + (f1 - f2) / 3.0F;
         */
        return 18000;
    }

    public static int[] skyColors = new int[] {
                                  0xb64040,
                                  0xb68b40,
                                  0xb5b640,
                                  0x50b640,
                                  0x40b682,
                                  0x40b2b6,
                                  0x4067b6,
                                  0x4042b6,
                                  0xa140b6,
                                  0xb6408b,
                                  0xb64050
                                  };

    @SideOnly(Side.CLIENT)
    public Vec3 getFogColor(float par1, float par2)
    {

        //System.out.println("getting fog color");

        if(!DreamDimension.boringSky)
        {
            fogTicks++;
    
            if (fogTicks % 50 == 0) //checks if fogTicks is divisible by x
            {
    
                this.incrementR = 0;
                this.incrementB = 0;
                this.incrementG = 0;
    
                int i = rand.nextInt(3) + 1;
                float inc = (rand.nextInt(20) + 1 - 10);
                int i2 = 200;
                
                //System.out.println("Changing an increment to: " + inc);
    
                if (i == 1)
                {
                    incrementR = inc / i2;
                }
    
                if (i == 2)
                {
                    incrementG = inc / i2;
                }
    
                if (i == 3)
                {
                    incrementB = inc / i2;
                }
            }
    
            if (fogTicks % 2 == 0)
            {
                
                this.skyR = this.skyR + (incrementR);
                //System.out.println("R: " + this.skyR + ", incrementR: " + incrementR);
    
                this.skyB = this.skyB + (incrementB);
                //System.out.println("B: " + this.skyB + ", incrementB: " + incrementB);
    
                this.skyG = this.skyG + (incrementG);
                //System.out.println("G: " + this.skyR + ", incrementG: " + incrementG);
            }
        }

        return this.worldObj.getWorldVec3Pool().getVecFromPool(this.skyR, this.skyB, this.skyG);
    }
    
    @SideOnly(Side.CLIENT)
    public IRenderHandler getSkyRenderer()
    {
        return new SkyRenderer();
    }
    
    @SideOnly(Side.CLIENT)
    public IRenderHandler getCloudRenderer()
    {
        return new CloudRenderer();
    }
}
