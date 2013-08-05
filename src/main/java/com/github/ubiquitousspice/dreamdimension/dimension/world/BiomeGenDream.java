package com.github.ubiquitousspice.dreamdimension.dimension.world;

import java.util.List;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenDream extends BiomeGenBase
{

    private WorldGenerator UnDeadworldGeneratorBigTree;

    //public final Material blockMaterial;

    public BiomeGenDream(int par1)
    {
        super(par1);
        //this.blockMaterial = Material.water;
        minHeight = 0.0F;
        maxHeight = 0.2F;
        spawnableMonsterList.clear();
        spawnableCreatureList.clear();
        topBlock = (byte) ChunkProviderMod.dreamBlock.blockID;
        fillerBlock = (byte) ChunkProviderMod.dreamBlock.blockID;
        setBiomeName("Dreamy");

        //this.spawnableCreatureList.add(new SpawnListEntry(EntityLargeSheep.class, 1, 1, 1));

        /**
         * this changes the water colour, its set to red now but ggole the java
         * colours
         **/
        waterColorMultiplier = 0xE42D17;
    }

    public List getSpawnableCreatures()
    {
        return spawnableCreatureList;
    }
}
