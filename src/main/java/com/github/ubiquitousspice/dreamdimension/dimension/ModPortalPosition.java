package com.github.ubiquitousspice.dreamdimension.dimension;

import net.minecraft.util.ChunkCoordinates;

public class ModPortalPosition extends ChunkCoordinates
{
    public long         field_85087_d;
    final ModTeleporter teleporter;

    public ModPortalPosition(ModTeleporter teleporter, int x, int y, int z, long l)
    {
        super(x, y, z);
        this.teleporter = teleporter;
        field_85087_d = l;
    }
}
