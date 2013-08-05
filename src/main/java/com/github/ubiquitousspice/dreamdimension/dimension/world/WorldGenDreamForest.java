package com.github.ubiquitousspice.dreamdimension.dimension.world;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class WorldGenDreamForest
{
    private int minTrees = 7;

    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        int trees = rand.nextInt(5) + minTrees;

        for (int i = 0; i <= trees; i++)
        {
            int randX = rand.nextInt(30) - 15;
            int randZ = rand.nextInt(30) - 15;

            new WorldGenDreamTree().generate(world, rand, x + randX, getTopBlock(world, x, z, rand), z + randZ);
        }

        return true;
    }

    public int getTopBlock(World world, int x, int z, Random rand)
    {
        for (int i = 0; i <= 128; i++)
        {
            if (world.getBlockMaterial(x, i, z) == Material.air)
            {
                return i;
            }
        }

        return rand.nextInt(128);
    }
}
