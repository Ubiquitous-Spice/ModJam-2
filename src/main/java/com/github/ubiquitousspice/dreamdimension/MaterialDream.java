package com.github.ubiquitousspice.dreamdimension;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import cpw.mods.fml.common.ObfuscationReflectionHelper;

public class MaterialDream extends Material
{
    public static boolean adventureBreak = false;

    public MaterialDream()
    {
        super(MapColor.dirtColor);
        setTranslucent();

        if (adventureBreak)
        {
            setAdventureModeExempt();
        }
    }

    /**
     * makes the material translucent
     */
    public Material setTranslucent()
    {
        try
        {
            ObfuscationReflectionHelper.setPrivateValue(Material.class, this, true, "isTranslucent", "field_76240_I");
        }
        catch (Exception e)
        {
            System.out.println("DID NOT WORK");
        }
        return this;
    }
}
