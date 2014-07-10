package com.micky2506.udaldordecor.helper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class RenderHelper
{
    private static RenderItem renderer;
    private EntityItem entityItem = new EntityItem(null);

    public RenderHelper()
    {
        renderer = new RenderItem()
        {
            public boolean shouldSpreadItems()
            {
                return false;
            }

            public boolean shouldBob()
            {
                return false;
            }
        };
        renderer.setRenderManager(RenderManager.instance);
        renderer.renderInFrame = true;
        entityItem.hoverStart = 0F;
    }

    public static Coordinate getOffsetCoordinate(int side)
    {
        Coordinate coordinate = new Coordinate(0.5D, 0D, 0.5D);
        if (side >= 0)
        {
            ForgeDirection direction = ForgeDirection.getOrientation(side);
            coordinate.x += direction.offsetX;
            coordinate.y += direction.offsetY + 0.5D;
            coordinate.z += direction.offsetZ;
        }
        else // Render inside
        {
            coordinate.y += 0.5D;
        }
        return coordinate;
    }


    // Used only for item rendering
    @SideOnly(Side.CLIENT)
    public static float getHorizontalRotation(double x, double z)
    {
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;

        if (player != null)
        {
            double aLength = Math.sqrt(Math.pow(player.posX - x, 2) + Math.pow(player.posZ - z, 2));
            double cLength = Math.sqrt(Math.pow(player.posX - player.posX, 2) + Math.pow(player.posZ - z, 2));

            float degrees = (float) Math.toDegrees(Math.asin(cLength / aLength));

            if (player.posZ > z && player.posX > x)
            {
                return 360F - degrees;
            }
            else if (player.posZ < z && player.posX > x)
            {
                return degrees;
            }
            else if (player.posZ < z && player.posX < x)
            {
                return 180F - degrees;
            }
            else if (player.posZ > z && player.posX < x) // Unneeded because we went through other 3 quadrants
            {
                return 180F + degrees;
            }
        }

        return 0F;
    }

    public void renderItem(ItemStack stack)
    {
        entityItem.setEntityItemStack(stack);
        renderer.doRender(entityItem, 0D, 0D, 0D, entityItem.rotationYaw, 0F);
    }

    public void renderEntity(Entity entity, World world, double x, double z)
    {
        if (entity != null && world != null)
        {
            Render renderer = RenderManager.instance.getEntityRenderObject(entity);
            if (renderer != null)
            {
                entity.worldObj = world;
                renderer.doRender(entity, 0, 0, 0, entity.rotationYaw, 0F);
                entity.worldObj = null;
            }
        }
    }
}