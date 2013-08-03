package com.github.ubiquitousspice.dreamdimension.dimension;

import net.minecraft.block.Block;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;

import com.github.ubiquitousspice.dreamdimension.DreamDimension;
import com.github.ubiquitousspice.dreamdimension.world.ChunkProviderMod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WorldProviderMod extends WorldProvider
{

    public static Block dreamBlock = Block.dirt;

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
        return true;
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
     * return "/Main:TwinSuns.png";
     * }
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
        /**int j = (int) (par1 % 24000L);
         float f1 = (j + par3) / 24000.0F - 0.25F;
         if (f1 < 0.0F) {
         f1 += 1.0F;
         }
         if (f1 > 1.0F) {
         f1 -= 1.0F;
         }
         float f2 = f1;
         f1 = 1.0F - (float) ((Math.cos(f1 * 3.141592653589793D) + 1.0D) / 2.0D);
         f1 = f2 + (f1 - f2) / 3.0F;*/
        return 18000;
    }

    @SideOnly(Side.CLIENT)
    public Vec3 getFogColor(float par1, float par2)
    {

        return this.worldObj.getWorldVec3Pool().getVecFromPool(0.00F, 0.01F, 0.07F);
    }
}