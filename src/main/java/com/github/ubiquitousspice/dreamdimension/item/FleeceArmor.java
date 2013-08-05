package com.github.ubiquitousspice.dreamdimension.item;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.EnumHelper;

public class FleeceArmor extends ItemArmor
{
    public static EnumArmorMaterial cloth = EnumHelper.addArmorMaterial("CLOTH", 4, new int[]{1, 3, 2, 1}, 25);
    
    public FleeceArmor(int par1, int par4)
    {
        super(par1, cloth, 0, par4);
        
    }

}
