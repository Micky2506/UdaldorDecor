package com.micky2506.udaldordecor.renderer;

import com.micky2506.udaldordecor.helper.Coordinate;
import com.micky2506.udaldordecor.helper.RenderHelper;
import com.micky2506.udaldordecor.tileentity.DisplayCaseTileBase;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class RendererDisplayCase extends TileEntitySpecialRenderer
{
    public static int tick = 0;
    private static RenderHelper renderHelper;

    public RendererDisplayCase ()
    {
        renderHelper = new RenderHelper();
    }

    @Override
    public void renderTileEntityAt (TileEntity tileEntity, double x, double y, double z, float f)
    {
        if (tick >= 360)
        {
            tick = 0;
        }

        int tickMultiplier = 10;

        if (tileEntity instanceof DisplayCaseTileBase)
        {
            DisplayCaseTileBase tile = (DisplayCaseTileBase) tileEntity;

            if (tile.stack != null)
            {
                Coordinate coordinate = RenderHelper.getCoordinate(tile.clickedSide);
                coordinate.x += x;
                coordinate.y += y;
                coordinate.z += z;

                GL11.glPushMatrix();
                GL11.glTranslated(coordinate.x, coordinate.y, coordinate.z);

                if (tile.doRotate())
                {
                    GL11.glRotatef(tick * tickMultiplier, 0F, 1F, 0F);
                }
                else
                {
                    float horizontalEntityRotation = RenderHelper.getHorizontalRotation(coordinate.x + tile.xCoord, coordinate.z + tile.zCoord) + 90F;
                    GL11.glRotatef(horizontalEntityRotation, 0F, 1F, 0F);
                }

                if (tile.stack.hasTagCompound() && tile.stack.getTagCompound().hasKey("id")) // Entity rendering
                {
                    Entity entity = EntityList.createEntityFromNBT(tile.stack.getTagCompound(), tile.getWorldObj());
                    renderHelper.renderEntity(entity, tile.getWorldObj());
                }
                else
                {
                    renderHelper.renderItem(tile.stack);
                }


                GL11.glPopMatrix();
            }
        }
    }
}
