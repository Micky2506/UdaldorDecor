package com.micky2506.udaldordecor.renderer;

import com.micky2506.udaldordecor.tileentity.TileDisplayCase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class RendererDisplayCase extends TileEntitySpecialRenderer
{
    private static RenderItem renderer;
    public static int tick = 0;
    private static EntityItem entityItem = new EntityItem(null);

    public RendererDisplayCase()
    {
        renderer = new RenderItem()
        {
            public boolean shouldBob()
            {
                return false;
            }

            public boolean shouldSpreadItems()
            {
                return false;
            }
        };
        renderer.setRenderManager(RenderManager.instance);
        renderer.renderInFrame = true;
        entityItem.hoverStart = 0F;
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f)
    {
        if (tick >= 360)
            tick = 0;

        float translate = 0.5F;
        int tickMultiplier = 10;

        if (tileEntity instanceof TileDisplayCase)
        {
            TileDisplayCase tile = (TileDisplayCase) tileEntity;
            if (tile.stack != null)
            {
                if (tile.stack.getItem() == Items.spawn_egg) // Render mobs
                {
                    Entity entity = EntityList.createEntityByID(tile.stack.getItemDamage(), tile.getWorldObj());
                    if (entity != null) {
//                        float entityHeight = entity.height;
//                        float entityWidth = entity.width;
                        float heightRatio = 0.5F;
                        float widthRatio = 0.5F;
                        float scaleFactor = 1 / Math.min(entity.height, entity.width);
                        GL11.glPushMatrix();
                        GL11.glTranslated(x + translate, y, z + translate);
                        GL11.glScalef(scaleFactor, scaleFactor, scaleFactor);
                        GL11.glRotatef(360F - tick*tickMultiplier, 0F, 1F, 0F);

                        if (tile.hasWorldObj()) {
                            Render renderer = RenderManager.instance.getEntityRenderObject(entity);
                            if (renderer != null)
                            {
                                entity.worldObj = tile.getWorldObj();
                                renderer.doRender(entity, 0, 0, 0, 0, 0);
                                entity.worldObj = null;
                            }
                        }
                        GL11.glPopMatrix();

                    }
                }
                else
                {
                    float scale = 1.0F;

                    GL11.glPushMatrix();
                    GL11.glTranslated(x + translate, y + translate, z + translate);
                    GL11.glScalef(scale, scale, scale);
                    GL11.glRotatef(360.0F - tick*tickMultiplier, 0.0F, 1.0F, 0.0F);
                    entityItem.setEntityItemStack(tile.stack);
                    renderer.doRender(entityItem, 0D, 0D, 0D, 0F, 0F);
                    GL11.glPopMatrix();
                }
            }
        }
    }
}
