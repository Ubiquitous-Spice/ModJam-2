package com.github.ubiquitousspice.dreamdimension.dimension.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.github.ubiquitousspice.dreamdimension.DreamDimension;

public class WorldGenDreamTree extends WorldGenerator
{
    public static Block treeWood      = DreamDimension.dreamLog;
    public static Block treeLeaf      = DreamDimension.dreamLeaf;
    private int         minTreeHeight = 6;

    public WorldGenDreamTree()
    {
        
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        int l = rand.nextInt(5) + minTreeHeight;
        int level;

        level = 0; // ========= Level 0 ==========
        addLeaves(x - 1, y + level, z - 1, 3, 1, 3, treeLeaf.blockID, world);

        level = 2; // ========= Level 2 ==========
        addLeaves(x - 1, y + level, z - 1, 3, 1, 3, treeLeaf.blockID, world);

        level = 3; // ========= Level 3 ==========
        addLeaves(x - 2, y + level, z - 2, 5, 1, 5, treeLeaf.blockID, world);

        level = 4; // ========= Level 4 ==========
        addLeaves(x - 1, y + level, z - 1, 3, 1, 3, treeLeaf.blockID, world);

        level = 5; // ========= Level 5 ==========
        addLeaves(x - 2, y + level, z - 2, 5, 1, 5, treeLeaf.blockID, world);

        level = 6; // ========= Level 6 ==========
        addLeaves(x - 1, y + level, z - 1, 3, 1, 3, treeLeaf.blockID, world);

        if (l >= 8)
        {
            level = 6; // ========= Level 6 ==========
            addLeaves(x - 3, y + level, z - 3, 7, 1, 7, treeLeaf.blockID, world);
        }

        for (int i = 0; i <= l; i++)
        {
            world.setBlock(x, y + i, z, treeWood.blockID);
        }

        return true;
    }

    public static void addLeaves(int x, int y, int z, int w, int h, int d, int bid, World world)
    {
        for (int i = x; i < x + w; i++)
        {
            for (int j = y; j < y + h; j++)
            {
                for (int k = z; k < z + d; k++)
                {
                    world.setBlock(i, j, k, bid);
                }
            }
        }

        world.setBlockToAir(x + d, y, z + d);
        world.setBlockToAir(x - d, y, z + d);
        world.setBlockToAir(x + d, y, z - d);
        world.setBlockToAir(x - d, y, z - d);
    }

}
