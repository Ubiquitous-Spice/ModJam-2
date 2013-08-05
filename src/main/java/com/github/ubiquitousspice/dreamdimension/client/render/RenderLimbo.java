package com.github.ubiquitousspice.dreamdimension.client.render;

import com.github.ubiquitousspice.dreamdimension.DreamDimension;
import com.github.ubiquitousspice.dreamdimension.blocks.BlockLimbo;
import com.github.ubiquitousspice.dreamdimension.client.ProxyClient;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHopper;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;

public class RenderLimbo implements ISimpleBlockRenderingHandler
{
    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
    {
        // do nothing.
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
        float f = 1.0F;
        int l = block.colorMultiplier(world, x, y, z);
        float f1 = (float) (l >> 16 & 255) / 255.0F;
        float f2 = (float) (l >> 8 & 255) / 255.0F;
        float f3 = (float) (l & 255) / 255.0F;

        if (EntityRenderer.anaglyphEnable)
        {
            float f4 = (f1 * 30.0F + f2 * 59.0F + f3 * 11.0F) / 100.0F;
            float f5 = (f1 * 30.0F + f2 * 70.0F) / 100.0F;
            float f6 = (f1 * 30.0F + f3 * 70.0F) / 100.0F;
            f1 = f4;
            f2 = f5;
            f3 = f6;
        }

        tessellator.setColorOpaque_F(f * f1, f * f2, f * f3);

        int meta = world.getBlockMetadata(x, y, z);

        // ----------------------------------------------------------------
        int i1 = BlockHopper.getDirectionFromMetadata(meta);
        double d0 = 0.625D;
        renderer.setRenderBounds(0.0D, d0, 0.0D, 1.0D, 1.0D, 1.0D);

//        if (par6)
//        {
//            tessellator.startDrawingQuads();
//            tessellator.setNormal(0.0F, -1.0F, 0.0F);
//            renderer.renderFaceYNeg(par1BlockHopper, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(par1BlockHopper, 0, meta));
//            tessellator.draw();
//            tessellator.startDrawingQuads();
//            tessellator.setNormal(0.0F, 1.0F, 0.0F);
//            renderer.renderFaceYPos(par1BlockHopper, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(par1BlockHopper, 1, meta));
//            tessellator.draw();
//            tessellator.startDrawingQuads();
//            tessellator.setNormal(0.0F, 0.0F, -1.0F);
//            renderer.renderFaceZNeg(par1BlockHopper, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(par1BlockHopper, 2, meta));
//            tessellator.draw();
//            tessellator.startDrawingQuads();
//            tessellator.setNormal(0.0F, 0.0F, 1.0F);
//            renderer.renderFaceZPos(par1BlockHopper, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(par1BlockHopper, 3, meta));
//            tessellator.draw();
//            tessellator.startDrawingQuads();
//            tessellator.setNormal(-1.0F, 0.0F, 0.0F);
//            renderer.renderFaceXNeg(par1BlockHopper, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(par1BlockHopper, 4, meta));
//            tessellator.draw();
//            tessellator.startDrawingQuads();
//            tessellator.setNormal(1.0F, 0.0F, 0.0F);
//            renderer.renderFaceXPos(par1BlockHopper, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(par1BlockHopper, 5, meta));
//            tessellator.draw();
//        }
//        else
//        {
//            renderer.renderStandardBlock(par1BlockHopper, par2, par3, par4);
//        }

        float f10;

        tessellator.setBrightness(block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z));
        float f11 = 1.0F;
        int j1 = block.colorMultiplier(renderer.blockAccess, x, y, z);
        f10 = (float) (j1 >> 16 & 255) / 255.0F;
        float f12 = (float) (j1 >> 8 & 255) / 255.0F;
        float f13 = (float) (j1 & 255) / 255.0F;

        if (EntityRenderer.anaglyphEnable)
        {
            float f4 = (f10 * 30.0F + f12 * 59.0F + f13 * 11.0F) / 100.0F;
            float f5 = (f10 * 30.0F + f12 * 70.0F) / 100.0F;
            float f6 = (f10 * 30.0F + f13 * 70.0F) / 100.0F;
            f10 = f4;
            f12 = f5;
            f13 = f6;
        }

        tessellator.setColorOpaque_F(f11 * f10, f11 * f12, f11 * f13);

        Icon outsideIcon = BlockLimbo.outsideIcon;
        Icon insideIcon = BlockLimbo.insideIcon;
        f10 = 0.125F;

//        if (par6)
//        {
//            tessellator.startDrawingQuads();
//            tessellator.setNormal(1.0F, 0.0F, 0.0F);
//            renderer.renderFaceXPos(block, (double)(-1.0F + f), 0.0D, 0.0D, icon);
//            tessellator.draw();
//            tessellator.startDrawingQuads();
//            tessellator.setNormal(-1.0F, 0.0F, 0.0F);
//            renderer.renderFaceXNeg(block, (double)(1.0F - f), 0.0D, 0.0D, icon);
//            tessellator.draw();
//            tessellator.startDrawingQuads();
//            tessellator.setNormal(0.0F, 0.0F, 1.0F);
//            renderer.renderFaceZPos(block, 0.0D, 0.0D, (double)(-1.0F + f), icon);
//            tessellator.draw();
//            tessellator.startDrawingQuads();
//            tessellator.setNormal(0.0F, 0.0F, -1.0F);
//            renderer.renderFaceZNeg(block, 0.0D, 0.0D, (double)(1.0F - f), icon);
//            tessellator.draw();
//            tessellator.startDrawingQuads();
//            tessellator.setNormal(0.0F, 1.0F, 0.0F);
//            renderer.renderFaceYPos(block, 0.0D, -1.0D + d0, 0.0D, icon1);
//            tessellator.draw();
//        }
//        else
//        {
        renderer.renderFaceXPos(block, (double) ((float) x - 1.0F + f10), (double) y, (double) z, outsideIcon);
        renderer.renderFaceXNeg(block, (double) ((float) x + 1.0F - f10), (double) y, (double) z, outsideIcon);
        renderer.renderFaceZPos(block, (double) x, (double) y, (double) ((float) z - 1.0F + f10), outsideIcon);
        renderer.renderFaceZNeg(block, (double) x, (double) y, (double) ((float) z + 1.0F - f10), outsideIcon);
        renderer.renderFaceYPos(block, (double) x, (double) ((float) y - 1.0F) + d0, (double) z, insideIcon);
//        }

        renderer.setOverrideBlockTexture(outsideIcon);
        double d1 = 0.25D;
        double d2 = 0.25D;
        renderer.setRenderBounds(d1, d2, d1, 1.0D - d1, d0 - 0.002D, 1.0D - d1);

//        if (par6)
//        {
//            tessellator.startDrawingQuads();
//            tessellator.setNormal(1.0F, 0.0F, 0.0F);
//            renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, icon);
//            tessellator.draw();
//            tessellator.startDrawingQuads();
//            tessellator.setNormal(-1.0F, 0.0F, 0.0F);
//            renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, icon);
//            tessellator.draw();
//            tessellator.startDrawingQuads();
//            tessellator.setNormal(0.0F, 0.0F, 1.0F);
//            renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, icon);
//            tessellator.draw();
//            tessellator.startDrawingQuads();
//            tessellator.setNormal(0.0F, 0.0F, -1.0F);
//            renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, icon);
//            tessellator.draw();
//            tessellator.startDrawingQuads();
//            tessellator.setNormal(0.0F, 1.0F, 0.0F);
//            renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, icon);
//            tessellator.draw();
//            tessellator.startDrawingQuads();
//            tessellator.setNormal(0.0F, -1.0F, 0.0F);
//            renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, icon);
//            tessellator.draw();
//        }
//        else
//        {
        renderer.renderStandardBlock(block, x, y, z);
//        }

        double d3 = 0.375D;
        double d4 = 0.25D;
        renderer.setOverrideBlockTexture(outsideIcon);

        if (i1 == 0)
        {
            renderer.setRenderBounds(d3, 0.0D, d3, 1.0D - d3, 0.25D, 1.0D - d3);
            renderer.renderStandardBlock(block, x, y, z);
        }

        if (i1 == 2)
        {
            renderer.setRenderBounds(d3, d2, 0.0D, 1.0D - d3, d2 + d4, d1);
            renderer.renderStandardBlock(block, x, y, z);
        }

        if (i1 == 3)
        {
            renderer.setRenderBounds(d3, d2, 1.0D - d1, 1.0D - d3, d2 + d4, 1.0D);
            renderer.renderStandardBlock(block, x, y, z);
        }

        if (i1 == 4)
        {
            renderer.setRenderBounds(0.0D, d2, d3, d1, d2 + d4, 1.0D - d3);
            renderer.renderStandardBlock(block, x, y, z);
        }

        if (i1 == 5)
        {
            renderer.setRenderBounds(1.0D - d1, d2, d3, 1.0D, d2 + d4, 1.0D - d3);
            renderer.renderStandardBlock(block, x, y, z);
        }

        renderer.clearOverrideBlockTexture();
        return true;
    }

    @Override
    public boolean shouldRender3DInInventory()
    {
        return false;
    }

    @Override
    public int getRenderId()
    {
        return ProxyClient.renderID;
    }
}
