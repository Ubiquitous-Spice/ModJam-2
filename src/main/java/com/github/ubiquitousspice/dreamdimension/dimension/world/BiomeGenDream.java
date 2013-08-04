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
        this.minHeight = 0.0F;
        this.maxHeight = 0.2F;
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.topBlock = ((byte) ChunkProviderMod.dreamBlock.blockID);
        this.fillerBlock = ((byte) ChunkProviderMod.dreamBlock.blockID);
        this.setBiomeName("Dreamy");

        //this.spawnableCreatureList.add(new SpawnListEntry(EntityLargeSheep.class, 1, 1, 1));

        /**
         * this changes the water colour, its set to red now but ggole the java
         * colours
         **/
        this.waterColorMultiplier = 0xE42D17;
    }

    public List getSpawnableCreatures()
    {
        return this.spawnableCreatureList;
    }
}