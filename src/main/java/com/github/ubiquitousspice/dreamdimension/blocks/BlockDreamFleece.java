package com.github.ubiquitousspice.dreamdimension.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.world.World;

import com.github.ubiquitousspice.dreamdimension.DreamDimension;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDreamFleece extends Block
{

    public BlockDreamFleece(int par1)
    {
        super(par1, Material.cloth);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister register)
    {
        this.blockIcon = register.registerIcon(DreamDimension.MODID + ":dreamFleece");
    }

    public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
    {
        par1World.setBlockToAir(par2, par3, par4);
        return par9;
    }

    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {

        return false;
    }

    @Override
    public void setBlockBoundsForItemRender()
    {
        setBlockBounds(-0.2f, -0.2f, -0.2f, 1.2f, 1.2f, 1.2f);
    }

}
