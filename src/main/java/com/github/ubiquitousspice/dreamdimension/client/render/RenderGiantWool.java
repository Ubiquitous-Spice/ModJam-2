package com.github.ubiquitousspice.dreamdimension.client.render;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.BlockHopper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ChestItemRenderHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.src.FMLRenderAccessLibrary;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class RenderGiantWool implements IItemRenderer
{

    private Minecraft                     mc;
    private static final ResourceLocation field_110930_b       = new ResourceLocation("textures/misc/enchanted_item_glint.png");

    private RenderBlocks                  renderBlocksInstance = new RenderBlocks();

    public RenderGiantWool()
    {
        mc = Minecraft.getMinecraft();
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        switch (type)
            {
                case EQUIPPED:
                    return true;
                case EQUIPPED_FIRST_PERSON:
                    return true;
                case ENTITY:
                    return true;
                case INVENTORY:
                    return true;
                default:
                    return false;
            }
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        if (type == ItemRenderType.INVENTORY && helper == ItemRendererHelper.INVENTORY_BLOCK)
        {
            return true;
        }

        return false;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack par2ItemStack, Object... data)
    {
        //        if (type == ItemRenderType.INVENTORY)
        //        {
        //            this.renderBlockAsItem(Block.blocksList[par2ItemStack.itemID], par2ItemStack.getItemDamage(), 1.0F, type);
        //            return;
        //        }

        GL11.glPushMatrix();
        TextureManager texturemanager = mc.func_110434_K();

        Block block = null;
        if (par2ItemStack.getItem() instanceof ItemBlock && par2ItemStack.itemID < Block.blocksList.length)
        {
            block = Block.blocksList[par2ItemStack.itemID];
        }

        MinecraftForgeClient.getItemRenderer(par2ItemStack, type);
        if (block != null && par2ItemStack.getItemSpriteNumber() == 0 && RenderBlocks.renderItemIn3d(Block.blocksList[par2ItemStack.itemID].getRenderType()))
        {
            texturemanager.func_110577_a(texturemanager.func_130087_a(0));
            renderBlockAsItem(Block.blocksList[par2ItemStack.itemID], par2ItemStack.getItemDamage(), 1.0F, type);
        }
        else
        {
            Icon icon = par2ItemStack.getIconIndex();

            if (icon == null)
            {
                GL11.glPopMatrix();
                return;
            }

            texturemanager.func_110577_a(texturemanager.func_130087_a(par2ItemStack.getItemSpriteNumber()));
            Tessellator tessellator = Tessellator.instance;
            float f = icon.getMinU();
            float f1 = icon.getMaxU();
            float f2 = icon.getMinV();
            float f3 = icon.getMaxV();
            float f4 = 0.0F;
            float f5 = 0.3F;
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glTranslatef(-f4, -f5, 0.0F);
            float f6 = 1.5F;
            GL11.glScalef(f6, f6, f6);
            GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(335.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(-0.9375F, -0.0625F, 0.0F);
            renderItemIn2D(tessellator, f1, f2, f, f3, icon.getOriginX(), icon.getOriginY(), 0.0625F);

            if (par2ItemStack.hasEffect(0))
            {
                GL11.glDepthFunc(GL11.GL_EQUAL);
                GL11.glDisable(GL11.GL_LIGHTING);
                texturemanager.func_110577_a(field_110930_b);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
                float f7 = 0.76F;
                GL11.glColor4f(0.5F * f7, 0.25F * f7, 0.8F * f7, 1.0F);
                GL11.glMatrixMode(GL11.GL_TEXTURE);
                GL11.glPushMatrix();
                float f8 = 0.125F;
                GL11.glScalef(f8, f8, f8);
                float f9 = (Minecraft.getSystemTime() % 3000L) / 3000.0F * 8.0F;
                GL11.glTranslatef(f9, 0.0F, 0.0F);
                GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
                renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                GL11.glScalef(f8, f8, f8);
                f9 = (Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
                GL11.glTranslatef(-f9, 0.0F, 0.0F);
                GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
                renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
                GL11.glPopMatrix();
                GL11.glMatrixMode(GL11.GL_MODELVIEW);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glDepthFunc(GL11.GL_LEQUAL);
            }

            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        }

        GL11.glPopMatrix();
    }

    public static void renderItemIn2D(Tessellator par0Tessellator, float par1, float par2, float par3, float par4, int par5, int par6, float par7)
    {
        par0Tessellator.startDrawingQuads();
        par0Tessellator.setNormal(0.0F, 0.0F, 1.0F);
        par0Tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, par1, par4);
        par0Tessellator.addVertexWithUV(1.0D, 0.0D, 0.0D, par3, par4);
        par0Tessellator.addVertexWithUV(1.0D, 1.0D, 0.0D, par3, par2);
        par0Tessellator.addVertexWithUV(0.0D, 1.0D, 0.0D, par1, par2);
        par0Tessellator.draw();
        par0Tessellator.startDrawingQuads();
        par0Tessellator.setNormal(0.0F, 0.0F, -1.0F);
        par0Tessellator.addVertexWithUV(0.0D, 1.0D, (0.0F - par7), par1, par2);
        par0Tessellator.addVertexWithUV(1.0D, 1.0D, (0.0F - par7), par3, par2);
        par0Tessellator.addVertexWithUV(1.0D, 0.0D, (0.0F - par7), par3, par4);
        par0Tessellator.addVertexWithUV(0.0D, 0.0D, (0.0F - par7), par1, par4);
        par0Tessellator.draw();
        float f5 = 0.5F * (par1 - par3) / par5;
        float f6 = 0.5F * (par4 - par2) / par6;
        par0Tessellator.startDrawingQuads();
        par0Tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        int k;
        float f7;
        float f8;

        for (k = 0; k < par5; ++k)
        {
            f7 = (float) k / (float) par5;
            f8 = par1 + (par3 - par1) * f7 - f5;
            par0Tessellator.addVertexWithUV(f7, 0.0D, (0.0F - par7), f8, par4);
            par0Tessellator.addVertexWithUV(f7, 0.0D, 0.0D, f8, par4);
            par0Tessellator.addVertexWithUV(f7, 1.0D, 0.0D, f8, par2);
            par0Tessellator.addVertexWithUV(f7, 1.0D, (0.0F - par7), f8, par2);
        }

        par0Tessellator.draw();
        par0Tessellator.startDrawingQuads();
        par0Tessellator.setNormal(1.0F, 0.0F, 0.0F);
        float f9;

        for (k = 0; k < par5; ++k)
        {
            f7 = (float) k / (float) par5;
            f8 = par1 + (par3 - par1) * f7 - f5;
            f9 = f7 + 1.0F / par5;
            par0Tessellator.addVertexWithUV(f9, 1.0D, (0.0F - par7), f8, par2);
            par0Tessellator.addVertexWithUV(f9, 1.0D, 0.0D, f8, par2);
            par0Tessellator.addVertexWithUV(f9, 0.0D, 0.0D, f8, par4);
            par0Tessellator.addVertexWithUV(f9, 0.0D, (0.0F - par7), f8, par4);
        }

        par0Tessellator.draw();
        par0Tessellator.startDrawingQuads();
        par0Tessellator.setNormal(0.0F, 1.0F, 0.0F);

        for (k = 0; k < par6; ++k)
        {
            f7 = (float) k / (float) par6;
            f8 = par4 + (par2 - par4) * f7 - f6;
            f9 = f7 + 1.0F / par6;
            par0Tessellator.addVertexWithUV(0.0D, f9, 0.0D, par1, f8);
            par0Tessellator.addVertexWithUV(1.0D, f9, 0.0D, par3, f8);
            par0Tessellator.addVertexWithUV(1.0D, f9, (0.0F - par7), par3, f8);
            par0Tessellator.addVertexWithUV(0.0D, f9, (0.0F - par7), par1, f8);
        }

        par0Tessellator.draw();
        par0Tessellator.startDrawingQuads();
        par0Tessellator.setNormal(0.0F, -1.0F, 0.0F);

        for (k = 0; k < par6; ++k)
        {
            f7 = (float) k / (float) par6;
            f8 = par4 + (par2 - par4) * f7 - f6;
            par0Tessellator.addVertexWithUV(1.0D, f7, 0.0D, par3, f8);
            par0Tessellator.addVertexWithUV(0.0D, f7, 0.0D, par1, f8);
            par0Tessellator.addVertexWithUV(0.0D, f7, (0.0F - par7), par1, f8);
            par0Tessellator.addVertexWithUV(1.0D, f7, (0.0F - par7), par3, f8);
        }

        par0Tessellator.draw();
    }

    public void renderBlockAsItem(Block par1Block, int par2, float par3, ItemRenderType type)
    {
        Tessellator tessellator = Tessellator.instance;
        boolean flag = par1Block.blockID == Block.grass.blockID;

        if (par1Block == Block.dispenser || par1Block == Block.dropper || par1Block == Block.furnaceIdle)
        {
            par2 = 3;
        }

        int j;
        float f1;
        float f2;
        float f3;

        if (renderBlocksInstance.useInventoryTint)
        {
            j = par1Block.getRenderColor(par2);

            if (flag)
            {
                j = 16777215;
            }

            f1 = (j >> 16 & 255) / 255.0F;
            f2 = (j >> 8 & 255) / 255.0F;
            f3 = (j & 255) / 255.0F;
            GL11.glColor4f(f1 * par3, f2 * par3, f3 * par3, 1.0F);
        }

        // TODO just used as a shortcut - ignore me =P

        if (type != ItemRenderType.INVENTORY)
        {
            float scale = 2;
            GL11.glScalef(scale, scale, scale);
        }

        if (type == ItemRenderType.EQUIPPED)
        {
            float scale2 = 0.7F;
            GL11.glScalef(scale2, scale2, scale2);

            GL11.glTranslatef(0.5F, 0.0F, 0.0F);
            GL11.glRotatef(20, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-40, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(20, 0.0F, 0.0F, 1.0F);
        }

        if (type == ItemRenderType.EQUIPPED_FIRST_PERSON)
        {
            float scale2 = 1F;
            GL11.glScalef(scale2, scale2, scale2);
            GL11.glTranslatef(1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-40, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(20, 0.0F, 0.0F, 1.0F);

            GL11.glTranslatef(0.5F, 0.0F, 0.0F);
            GL11.glRotatef(20, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-40, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(10, 0.0F, 0.0F, 1.0F);
        }

        if (type == ItemRenderType.ENTITY)
        {
            float scale2 = 1.5F;
            GL11.glScalef(scale2, scale2, scale2);
            GL11.glTranslatef(0F, 0.5F, 0F);
        }

        j = par1Block.getRenderType();
        renderBlocksInstance.setRenderBoundsFromBlock(par1Block);
        int k;

        if (j != 0 && j != 31 && j != 39 && j != 16 && j != 26)
        {
            if (j == 1)
            {
                tessellator.startDrawingQuads();
                tessellator.setNormal(0.0F, -1.0F, 0.0F);
                renderBlocksInstance.drawCrossedSquares(par1Block, par2, -0.5D, -0.5D, -0.5D, 1.0F);
                tessellator.draw();
            }
            else if (j == 19)
            {
                tessellator.startDrawingQuads();
                tessellator.setNormal(0.0F, -1.0F, 0.0F);
                par1Block.setBlockBoundsForItemRender();
                renderBlocksInstance.renderBlockStemSmall(par1Block, par2, renderBlocksInstance.renderMaxY, -0.5D, -0.5D, -0.5D);
                tessellator.draw();
            }
            else if (j == 23)
            {
                tessellator.startDrawingQuads();
                tessellator.setNormal(0.0F, -1.0F, 0.0F);
                par1Block.setBlockBoundsForItemRender();
                tessellator.draw();
            }
            else if (j == 13)
            {
                par1Block.setBlockBoundsForItemRender();
                GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
                f1 = 0.0625F;
                tessellator.startDrawingQuads();
                tessellator.setNormal(0.0F, -1.0F, 0.0F);
                renderBlocksInstance.renderFaceYNeg(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSide(par1Block, 0));
                tessellator.draw();
                tessellator.startDrawingQuads();
                tessellator.setNormal(0.0F, 1.0F, 0.0F);
                renderBlocksInstance.renderFaceYPos(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSide(par1Block, 1));
                tessellator.draw();
                tessellator.startDrawingQuads();
                tessellator.setNormal(0.0F, 0.0F, -1.0F);
                tessellator.addTranslation(0.0F, 0.0F, f1);
                renderBlocksInstance.renderFaceZNeg(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSide(par1Block, 2));
                tessellator.addTranslation(0.0F, 0.0F, -f1);
                tessellator.draw();
                tessellator.startDrawingQuads();
                tessellator.setNormal(0.0F, 0.0F, 1.0F);
                tessellator.addTranslation(0.0F, 0.0F, -f1);
                renderBlocksInstance.renderFaceZPos(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSide(par1Block, 3));
                tessellator.addTranslation(0.0F, 0.0F, f1);
                tessellator.draw();
                tessellator.startDrawingQuads();
                tessellator.setNormal(-1.0F, 0.0F, 0.0F);
                tessellator.addTranslation(f1, 0.0F, 0.0F);
                renderBlocksInstance.renderFaceXNeg(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSide(par1Block, 4));
                tessellator.addTranslation(-f1, 0.0F, 0.0F);
                tessellator.draw();
                tessellator.startDrawingQuads();
                tessellator.setNormal(1.0F, 0.0F, 0.0F);
                tessellator.addTranslation(-f1, 0.0F, 0.0F);
                renderBlocksInstance.renderFaceXPos(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSide(par1Block, 5));
                tessellator.addTranslation(f1, 0.0F, 0.0F);
                tessellator.draw();
                GL11.glTranslatef(0.5F, 0.5F, 0.5F);
            }
            else if (j == 22)
            {
                GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
                ChestItemRenderHelper.instance.renderChest(par1Block, par2, par3);
                GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            }
            else if (j == 6)
            {
                tessellator.startDrawingQuads();
                tessellator.setNormal(0.0F, -1.0F, 0.0F);
                renderBlocksInstance.renderBlockCropsImpl(par1Block, par2, -0.5D, -0.5D, -0.5D);
                tessellator.draw();
            }
            else if (j == 2)
            {
                tessellator.startDrawingQuads();
                tessellator.setNormal(0.0F, -1.0F, 0.0F);
                renderBlocksInstance.renderTorchAtAngle(par1Block, -0.5D, -0.5D, -0.5D, 0.0D, 0.0D, 0);
                tessellator.draw();
            }
            else if (j == 10)
            {
                for (k = 0; k < 2; ++k)
                {
                    if (k == 0)
                    {
                        renderBlocksInstance.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5D);
                    }

                    if (k == 1)
                    {
                        renderBlocksInstance.setRenderBounds(0.0D, 0.0D, 0.5D, 1.0D, 0.5D, 1.0D);
                    }

                    GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, -1.0F, 0.0F);
                    renderBlocksInstance.renderFaceYNeg(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSide(par1Block, 0));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 1.0F, 0.0F);
                    renderBlocksInstance.renderFaceYPos(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSide(par1Block, 1));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 0.0F, -1.0F);
                    renderBlocksInstance.renderFaceZNeg(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSide(par1Block, 2));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 0.0F, 1.0F);
                    renderBlocksInstance.renderFaceZPos(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSide(par1Block, 3));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(-1.0F, 0.0F, 0.0F);
                    renderBlocksInstance.renderFaceXNeg(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSide(par1Block, 4));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(1.0F, 0.0F, 0.0F);
                    renderBlocksInstance.renderFaceXPos(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSide(par1Block, 5));
                    tessellator.draw();
                    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
                }
            }
            else if (j == 27)
            {
                k = 0;
                GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
                tessellator.startDrawingQuads();

                for (int l = 0; l < 8; ++l)
                {
                    byte b0 = 0;
                    byte b1 = 1;

                    if (l == 0)
                    {
                        b0 = 2;
                    }

                    if (l == 1)
                    {
                        b0 = 3;
                    }

                    if (l == 2)
                    {
                        b0 = 4;
                    }

                    if (l == 3)
                    {
                        b0 = 5;
                        b1 = 2;
                    }

                    if (l == 4)
                    {
                        b0 = 6;
                        b1 = 3;
                    }

                    if (l == 5)
                    {
                        b0 = 7;
                        b1 = 5;
                    }

                    if (l == 6)
                    {
                        b0 = 6;
                        b1 = 2;
                    }

                    if (l == 7)
                    {
                        b0 = 3;
                    }

                    float f4 = b0 / 16.0F;
                    float f5 = 1.0F - k / 16.0F;
                    float f6 = 1.0F - (k + b1) / 16.0F;
                    k += b1;
                    renderBlocksInstance.setRenderBounds((0.5F - f4), f6, (0.5F - f4), (0.5F + f4), f5, (0.5F + f4));
                    tessellator.setNormal(0.0F, -1.0F, 0.0F);
                    renderBlocksInstance.renderFaceYNeg(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSide(par1Block, 0));
                    tessellator.setNormal(0.0F, 1.0F, 0.0F);
                    renderBlocksInstance.renderFaceYPos(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSide(par1Block, 1));
                    tessellator.setNormal(0.0F, 0.0F, -1.0F);
                    renderBlocksInstance.renderFaceZNeg(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSide(par1Block, 2));
                    tessellator.setNormal(0.0F, 0.0F, 1.0F);
                    renderBlocksInstance.renderFaceZPos(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSide(par1Block, 3));
                    tessellator.setNormal(-1.0F, 0.0F, 0.0F);
                    renderBlocksInstance.renderFaceXNeg(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSide(par1Block, 4));
                    tessellator.setNormal(1.0F, 0.0F, 0.0F);
                    renderBlocksInstance.renderFaceXPos(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSide(par1Block, 5));
                }

                tessellator.draw();
                GL11.glTranslatef(0.5F, 0.5F, 0.5F);
                renderBlocksInstance.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
            }
            else if (j == 11)
            {
                for (k = 0; k < 4; ++k)
                {
                    f2 = 0.125F;

                    if (k == 0)
                    {
                        renderBlocksInstance.setRenderBounds((0.5F - f2), 0.0D, 0.0D, (0.5F + f2), 1.0D, (f2 * 2.0F));
                    }

                    if (k == 1)
                    {
                        renderBlocksInstance.setRenderBounds((0.5F - f2), 0.0D, (1.0F - f2 * 2.0F), (0.5F + f2), 1.0D, 1.0D);
                    }

                    f2 = 0.0625F;

                    if (k == 2)
                    {
                        renderBlocksInstance.setRenderBounds((0.5F - f2), (1.0F - f2 * 3.0F), (-f2 * 2.0F), (0.5F + f2), (1.0F - f2), (1.0F + f2 * 2.0F));
                    }

                    if (k == 3)
                    {
                        renderBlocksInstance.setRenderBounds((0.5F - f2), (0.5F - f2 * 3.0F), (-f2 * 2.0F), (0.5F + f2), (0.5F - f2), (1.0F + f2 * 2.0F));
                    }

                    GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, -1.0F, 0.0F);
                    renderBlocksInstance.renderFaceYNeg(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSide(par1Block, 0));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 1.0F, 0.0F);
                    renderBlocksInstance.renderFaceYPos(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSide(par1Block, 1));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 0.0F, -1.0F);
                    renderBlocksInstance.renderFaceZNeg(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSide(par1Block, 2));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 0.0F, 1.0F);
                    renderBlocksInstance.renderFaceZPos(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSide(par1Block, 3));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(-1.0F, 0.0F, 0.0F);
                    renderBlocksInstance.renderFaceXNeg(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSide(par1Block, 4));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(1.0F, 0.0F, 0.0F);
                    renderBlocksInstance.renderFaceXPos(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSide(par1Block, 5));
                    tessellator.draw();
                    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
                }

                renderBlocksInstance.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
            }
            else if (j == 21)
            {
                for (k = 0; k < 3; ++k)
                {
                    f2 = 0.0625F;

                    if (k == 0)
                    {
                        renderBlocksInstance.setRenderBounds((0.5F - f2), 0.30000001192092896D, 0.0D, (0.5F + f2), 1.0D, (f2 * 2.0F));
                    }

                    if (k == 1)
                    {
                        renderBlocksInstance.setRenderBounds((0.5F - f2), 0.30000001192092896D, (1.0F - f2 * 2.0F), (0.5F + f2), 1.0D, 1.0D);
                    }

                    f2 = 0.0625F;

                    if (k == 2)
                    {
                        renderBlocksInstance.setRenderBounds((0.5F - f2), 0.5D, 0.0D, (0.5F + f2), (1.0F - f2), 1.0D);
                    }

                    GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, -1.0F, 0.0F);
                    renderBlocksInstance.renderFaceYNeg(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSide(par1Block, 0));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 1.0F, 0.0F);
                    renderBlocksInstance.renderFaceYPos(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSide(par1Block, 1));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 0.0F, -1.0F);
                    renderBlocksInstance.renderFaceZNeg(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSide(par1Block, 2));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 0.0F, 1.0F);
                    renderBlocksInstance.renderFaceZPos(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSide(par1Block, 3));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(-1.0F, 0.0F, 0.0F);
                    renderBlocksInstance.renderFaceXNeg(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSide(par1Block, 4));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(1.0F, 0.0F, 0.0F);
                    renderBlocksInstance.renderFaceXPos(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSide(par1Block, 5));
                    tessellator.draw();
                    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
                }
            }
            else if (j == 32)
            {
                for (k = 0; k < 2; ++k)
                {
                    if (k == 0)
                    {
                        renderBlocksInstance.setRenderBounds(0.0D, 0.0D, 0.3125D, 1.0D, 0.8125D, 0.6875D);
                    }

                    if (k == 1)
                    {
                        renderBlocksInstance.setRenderBounds(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
                    }

                    GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, -1.0F, 0.0F);
                    renderBlocksInstance.renderFaceYNeg(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSideAndMetadata(par1Block, 0, par2));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 1.0F, 0.0F);
                    renderBlocksInstance.renderFaceYPos(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSideAndMetadata(par1Block, 1, par2));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 0.0F, -1.0F);
                    renderBlocksInstance.renderFaceZNeg(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSideAndMetadata(par1Block, 2, par2));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 0.0F, 1.0F);
                    renderBlocksInstance.renderFaceZPos(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSideAndMetadata(par1Block, 3, par2));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(-1.0F, 0.0F, 0.0F);
                    renderBlocksInstance.renderFaceXNeg(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSideAndMetadata(par1Block, 4, par2));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(1.0F, 0.0F, 0.0F);
                    renderBlocksInstance.renderFaceXPos(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSideAndMetadata(par1Block, 5, par2));
                    tessellator.draw();
                    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
                }

                renderBlocksInstance.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
            }
            else if (j == 35)
            {
                GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
                renderBlocksInstance.renderBlockAnvilOrient((BlockAnvil) par1Block, 0, 0, 0, par2 << 2, true);
                GL11.glTranslatef(0.5F, 0.5F, 0.5F);
            }
            else if (j == 34)
            {
                for (k = 0; k < 3; ++k)
                {
                    if (k == 0)
                    {
                        renderBlocksInstance.setRenderBounds(0.125D, 0.0D, 0.125D, 0.875D, 0.1875D, 0.875D);
                        renderBlocksInstance.setOverrideBlockTexture(renderBlocksInstance.getBlockIcon(Block.obsidian));
                    }
                    else if (k == 1)
                    {
                        renderBlocksInstance.setRenderBounds(0.1875D, 0.1875D, 0.1875D, 0.8125D, 0.875D, 0.8125D);
                        renderBlocksInstance.setOverrideBlockTexture(renderBlocksInstance.getBlockIcon(Block.beacon));
                    }
                    else if (k == 2)
                    {
                        renderBlocksInstance.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
                        renderBlocksInstance.setOverrideBlockTexture(renderBlocksInstance.getBlockIcon(Block.glass));
                    }

                    GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, -1.0F, 0.0F);
                    renderBlocksInstance.renderFaceYNeg(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSideAndMetadata(par1Block, 0, par2));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 1.0F, 0.0F);
                    renderBlocksInstance.renderFaceYPos(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSideAndMetadata(par1Block, 1, par2));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 0.0F, -1.0F);
                    renderBlocksInstance.renderFaceZNeg(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSideAndMetadata(par1Block, 2, par2));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(0.0F, 0.0F, 1.0F);
                    renderBlocksInstance.renderFaceZPos(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSideAndMetadata(par1Block, 3, par2));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(-1.0F, 0.0F, 0.0F);
                    renderBlocksInstance.renderFaceXNeg(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSideAndMetadata(par1Block, 4, par2));
                    tessellator.draw();
                    tessellator.startDrawingQuads();
                    tessellator.setNormal(1.0F, 0.0F, 0.0F);
                    renderBlocksInstance.renderFaceXPos(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSideAndMetadata(par1Block, 5, par2));
                    tessellator.draw();
                    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
                }

                renderBlocksInstance.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
                renderBlocksInstance.clearOverrideBlockTexture();
            }
            else if (j == 38)
            {
                GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
                renderBlocksInstance.renderBlockHopperMetadata((BlockHopper) par1Block, 0, 0, 0, 0, true);
                GL11.glTranslatef(0.5F, 0.5F, 0.5F);
            }
            else
            {
                FMLRenderAccessLibrary.renderInventoryBlock(renderBlocksInstance, par1Block, par2, j);
            }
        }
        else
        {
            if (j == 16)
            {
                par2 = 1;
            }

            par1Block.setBlockBoundsForItemRender();
            renderBlocksInstance.setRenderBoundsFromBlock(par1Block);
            GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, -1.0F, 0.0F);
            renderBlocksInstance.renderFaceYNeg(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSideAndMetadata(par1Block, 0, par2));
            tessellator.draw();

            if (flag && renderBlocksInstance.useInventoryTint)
            {
                k = par1Block.getRenderColor(par2);
                f2 = (k >> 16 & 255) / 255.0F;
                f3 = (k >> 8 & 255) / 255.0F;
                float f7 = (k & 255) / 255.0F;
                GL11.glColor4f(f2 * par3, f3 * par3, f7 * par3, 1.0F);
            }

            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 1.0F, 0.0F);
            renderBlocksInstance.renderFaceYPos(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSideAndMetadata(par1Block, 1, par2));
            tessellator.draw();

            if (flag && renderBlocksInstance.useInventoryTint)
            {
                GL11.glColor4f(par3, par3, par3, 1.0F);
            }

            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 0.0F, -1.0F);
            renderBlocksInstance.renderFaceZNeg(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSideAndMetadata(par1Block, 2, par2));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 0.0F, 1.0F);
            renderBlocksInstance.renderFaceZPos(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSideAndMetadata(par1Block, 3, par2));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(-1.0F, 0.0F, 0.0F);
            renderBlocksInstance.renderFaceXNeg(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSideAndMetadata(par1Block, 4, par2));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(1.0F, 0.0F, 0.0F);
            renderBlocksInstance.renderFaceXPos(par1Block, 0.0D, 0.0D, 0.0D, renderBlocksInstance.getBlockIconFromSideAndMetadata(par1Block, 5, par2));
            tessellator.draw();
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        }
    }

}
