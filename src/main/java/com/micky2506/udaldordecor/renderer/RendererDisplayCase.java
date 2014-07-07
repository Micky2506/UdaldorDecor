package com.micky2506.udaldordecor.renderer;

import com.micky2506.udaldordecor.tileentity.TileDisplayCase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
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
            GL11.glPushMatrix();

            TileDisplayCase tile = (TileDisplayCase) tileEntity;
            float translateY = 0.5F;
            float translateX = 0.5F;
            float translateZ = 0.5F;

            if (tile.clickedSide >= 0)
            {
                ForgeDirection direction = ForgeDirection.getOrientation(tile.clickedSide);
                translateX += direction.offsetX;
                translateY += direction.offsetY - 0.5F;
                translateZ += direction.offsetZ;
            }
            else // Render inside
            {
                translateY -= 0.5F;
            }

            GL11.glTranslated(x + translateX, y + translateY, z + translateZ);
            if (tile.stack != null)
            {
                if (tile.stack.getItem() == Items.spawn_egg) // Render mobs
                {
                    Entity entity = EntityList.createEntityByID(tile.stack.getItemDamage(), tile.getWorldObj());
                    if (entity != null) {
                        if (tile.hasWorldObj()) {
                            Render renderer = RenderManager.instance.getEntityRenderObject(entity);
                            if (renderer != null)
                            {
                                GL11.glRotatef(360F - tick*tickMultiplier, 0F, 1F, 0F);
                                entity.worldObj = tile.getWorldObj();
                                renderer.doRender(entity, 0, 0, 0, 0, 0);
                                entity.worldObj = null;
                            }
                        }

                    }
                }
                else
                {
                    GL11.glTranslatef(0F, 0.5F, 0F);
                    GL11.glRotatef(360F - tick*tickMultiplier, 0F, 1F, 0F);
                    entityItem.setEntityItemStack(tile.stack);
                    renderer.doRender(entityItem, 0D, 0D, 0D, 0F, 0F);
                }
            }
        }

        GL11.glPopMatrix();
    }
}
