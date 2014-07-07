package com.micky2506.udaldordecor.renderer;

import com.micky2506.udaldordecor.tileentity.TileDisplayCase;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class RendererDisplayCase extends TileEntitySpecialRenderer
{
    private static RenderItem renderer;
    private static int tick = 0;
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
        ++tick;
        if (tick >= 360)
            tick = 0;

        if (tileEntity instanceof TileDisplayCase)
        {
            TileDisplayCase tile = (TileDisplayCase) tileEntity;
            if (tile.stack != null)
            {
                float scale = 1.0F;
                float translate = 0.5F;
                GL11.glPushMatrix();
                GL11.glTranslated(x + translate, y + translate, z + translate);
                GL11.glScalef(scale, scale, scale);
                GL11.glRotatef(360.0F - tick, 0.0F, 1.0F, 0.0F);
                entityItem.setEntityItemStack(tile.stack);
                renderer.doRender(entityItem, 0D, 0D, 0D, 0F, 0F);
                GL11.glPopMatrix();
            }
        }
    }
}
