package com.micky2506.udaldordecor.renderer;

import com.micky2506.udaldordecor.helper.Coordinate;
import com.micky2506.udaldordecor.helper.RenderHelper;
import com.micky2506.udaldordecor.tileentity.DisplayCaseTileBase;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
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
                    if (((EntityLiving) entity).hasCustomNameTag())
                    {
                        ((EntityLiving) entity).setAlwaysRenderNameTag(true);
                        ((EntityLiving) entity).getCustomNameTag()
                    }

                    renderHelper.renderEntity(entity, tile.getWorldObj(), coordinate.x + tile.xCoord, coordinate.z + tile.zCoord);
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
