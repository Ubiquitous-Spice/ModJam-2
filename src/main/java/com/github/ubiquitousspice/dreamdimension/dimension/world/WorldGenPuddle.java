package com.github.ubiquitousspice.dreamdimension.dimension.world;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenPuddle extends WorldGenerator
{
    private int blockIndex;
    private int blockMeta;

    public WorldGenPuddle(int par1)
    {
        blockIndex = par1;
        blockMeta = 0;
    }

    public WorldGenPuddle(int par1, int par2)
    {
        blockIndex = par1;
        blockMeta = par2;
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        x -= 8;

        //y -= 4;
        boolean[] aboolean = new boolean[2048];
        int l = rand.nextInt(4) + 4;
        int i1;

        for (i1 = 0; i1 < l; ++i1)
        {
            double d0 = rand.nextDouble() * 6.0D + 3.0D;
            double d1 = rand.nextDouble() * 4.0D + 2.0D;
            double d2 = rand.nextDouble() * 6.0D + 3.0D;
            double d3 = rand.nextDouble() * (16.0D - d0 - 2.0D) + 1.0D + d0 / 2.0D;
            double d4 = rand.nextDouble() * (8.0D - d1 - 4.0D) + 2.0D + d1 / 2.0D;
            double d5 = rand.nextDouble() * (16.0D - d2 - 2.0D) + 1.0D + d2 / 2.0D;

            for (int j1 = 1; j1 < 15; ++j1)
            {
                for (int k1 = 1; k1 < 15; ++k1)
                {
                    for (int l1 = 1; l1 < 7; ++l1)
                    {
                        double d6 = (j1 - d3) / (d0 / 2.0D);
                        double d7 = (l1 - d4) / (d1 / 2.0D);
                        double d8 = (k1 - d5) / (d2 / 2.0D);
                        double d9 = d6 * d6 + d7 * d7 + d8 * d8;

                        if (d9 < 1.0D)
                        {
                            aboolean[(j1 * 16 + k1) * 8 + l1] = true;
                        }
                    }
                }
            }
        }

        int i2;
        int j2;
        for (i1 = 0; i1 < 16; ++i1)
        {
            for (j2 = 0; j2 < 16; ++j2)
            {
                for (i2 = 0; i2 < 8; ++i2)
                {

                }
            }
        }

        for (i1 = 0; i1 < 16; ++i1)
        {
            for (j2 = 0; j2 < 16; ++j2)
            {
                for (i2 = 0; i2 < 8; ++i2)
                {
                    if (aboolean[(i1 * 16 + j2) * 8 + i2])
                    {

                        for (int i = 0; i <= 10; i++)
                        {
                            if (world.getBlockMaterial(x + i1, getTopBlock(world, x, z) - i - 1, z + j2) == Material.ground && world.getBlockMaterial(x + i1, getTopBlock(world, x, z) - i + 1, z + j2) == Material.air && world.getBlockMaterial(x + i1, getTopBlock(world, x, z) - i, z + j2) != Material.lava && world.getBlockMaterial(x + i1, getTopBlock(world, x, z) - i, z + j2) != Material.water)
                            {
                                world.setBlock(x + i1, getTopBlock(world, x, z) - i, z + j2, blockIndex, blockMeta, 2);
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    public static int getTopBlock(World world, int x, int z)
    {
        for (int i = 128; i > 0; i--)
        {
            if (world.getBlockId(x, i, z) != 0)
            {
                return i;
            }
        }

        return 0;
    }

}
