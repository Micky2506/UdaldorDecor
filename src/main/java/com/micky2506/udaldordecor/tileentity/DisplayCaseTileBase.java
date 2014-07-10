package com.micky2506.udaldordecor.tileentity;

import com.micky2506.udaldordecor.helper.Coordinate;
import com.micky2506.udaldordecor.helper.IOHelper;
import com.micky2506.udaldordecor.helper.RenderHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class DisplayCaseTileBase extends TileEntity implements IInventory
{
    private static final float ROTATION_PER_TICK = 10F;
    public ItemStack stack;
    public int clickedSide = -1;

    private Entity cachedEntity;

    public Entity getCachedEntity()
    {
        return cachedEntity;
    }

    public boolean onActivated(World world, EntityPlayer player, ItemStack playerStack, int side)
    {
        if (playerStack != null)
        {
            if (this.stack != null)
            {
                dropInventory(player.posX, player.posY, player.posZ, player.capabilities.isCreativeMode);
            }

            this.stack = playerStack.copy();
            if (!player.capabilities.isCreativeMode)
            {
                --playerStack.stackSize;
                if (playerStack.stackSize <= 0)
                {
                    playerStack = null;
                }
            }
            this.stack.stackSize = 1;
        }
        else
        {
            if (player.isSneaking())
            {
                if (side == this.clickedSide && this.clickedSide == ForgeDirection.UP.ordinal() && stack != null)
                {
                    dropInventory(player.posX, player.posY, player.posZ, player.capabilities.isCreativeMode);
                    return true;
                }

                this.clickedSide = -1; // Set to inside the block
            }
            else
            {
                this.clickedSide = side;
            }
        }
        searchForEntities(stack);
        markDirty();
        return true;
    }

    private void dropInventory(double x, double y, double z, boolean justEmpty)
    {

        if (stack != null && !justEmpty)
        {
            IOHelper.spawnItemInWorld(this.getWorldObj(), stack, x, y, z);
        }
        stack = null;
        markDirty();
    }

    /*
     * This method detects and caches entity in tile data to prevent GC pressure and avoid matrix manipulation in TESR
     */
    private void searchForEntities(ItemStack stack)
    {
        cachedEntity = null;//Drop cache(reevaluated)
        if (stack == null)
        {
            return;
        }
        if (stack.hasTagCompound() && stack.getTagCompound().hasKey("id"))
        {
            //This stack has entity in it!
            cachedEntity = EntityList.createEntityFromNBT(stack.getTagCompound(), getWorldObj());
            Coordinate coordinate = RenderHelper.getOffsetCoordinate(clickedSide);
            cachedEntity.setPosition(coordinate.x + xCoord, coordinate.y + yCoord, coordinate.z + zCoord);
            if (((EntityLiving) cachedEntity).hasCustomNameTag())
            {
                ((EntityLiving) cachedEntity).setAlwaysRenderNameTag(true);
                //((EntityLiving) entity).getCustomNameTag()
            }
        }
    }

    public abstract boolean doRotate();

    public ItemStack[] getDrops()
    {
        if (stack != null)
        {
            return new ItemStack[] { stack };
        }
        else
        {
            return new ItemStack[] { };
        }
    }

    /**
     * Returns the number of slots in the inventory.
     */
    @Override
    public int getSizeInventory()
    {
        return 1;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        clickedSide = compound.getInteger("clickedSide");
        if (compound.hasKey("Items"))
        {
            NBTTagList tagList = compound.getTagList("Items", 10);
            NBTTagCompound itemCompound = tagList.getCompoundTagAt(0);
            stack = ItemStack.loadItemStackFromNBT(itemCompound);
            searchForEntities(stack);
        }
    }

    /**
     * Returns the stack in slot i
     *
     * @param var1
     */
    @Override
    public ItemStack getStackInSlot(int var1)
    {
        return stack;
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     *
     * @param var1
     * @param var2
     */
    @Override
    public ItemStack decrStackSize(int var1, int var2)
    {
        ItemStack returnedStack = stack.copy();
        returnedStack.stackSize = Math.min(stack.stackSize, var2);
        stack.stackSize -= var2;
        if (stack.stackSize <= 0)
        {
            stack = null;
            markDirty();
        }
        return returnedStack;
    }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("clickedSide", clickedSide);
        if (this.stack != null)
        {
            NBTTagList itemTagList = new NBTTagList();
            NBTTagCompound itemCompound = new NBTTagCompound();
            itemCompound = this.stack.writeToNBT(itemCompound);
            itemTagList.appendTag(itemCompound);
            compound.setTag("Items", itemTagList);
        }

    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     *
     * @param var1
     */
    @Override
    public ItemStack getStackInSlotOnClosing(int var1)
    {
        return null;
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     *
     * @param var1
     * @param var2
     */
    @Override
    public void setInventorySlotContents(int var1, ItemStack var2)
    {
        stack = var2;
    }

    /**
     * Return an {@link net.minecraft.util.AxisAlignedBB} that controls the visible scope of a {@link TileEntitySpecialRenderer} associated with this {@link net.minecraft.tileentity.TileEntity}
     * Defaults to the collision bounding box {@link Block#getCollisionBoundingBoxFromPool(net.minecraft.world.World, int, int, int)} associated with the block
     * at this location.
     *
     * @return an appropriately size {@link net.minecraft.util.AxisAlignedBB} for the {@link net.minecraft.tileentity.TileEntity}
     */
    @Override
    public AxisAlignedBB getRenderBoundingBox()
    {
        //TODO: Improve getting render bounding box.
        return super.getRenderBoundingBox().expand(5D, 5D, 5D); // Chose 5D as that is (I think) max hitbox of a vanilla entity
    }

    /**
     * Returns the name of the inventory
     */
    @Override
    public String getInventoryName()
    {
        return "tile.displaycase";
    }

    /**
     * Returns if the inventory is named
     */
    @Override
    public boolean hasCustomInventoryName()
    {
        return false;
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
        readFromNBT(pkt.func_148857_g());
    }

    /**
     * Returns the maximum stack size for a inventory slot.
     */
    @Override
    public int getInventoryStackLimit()
    {
        return 1;
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     *
     * @param var1
     */
    @Override
    public boolean isUseableByPlayer(EntityPlayer var1)
    {
        return false;
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound tag = new NBTTagCompound(); //ObfuscationReflectionHelper.getPrivateValue(S35PacketUpdateTileEntity.class, pkt, "field_148860_e", "e");
        writeToNBT(tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
    }

    @Override
    public void openInventory()
    {

    }

    @Override
    public void closeInventory()
    {

    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     *
     * @param var1
     * @param var2
     */
    @Override
    public boolean isItemValidForSlot(int var1, ItemStack var2)
    {
        return stack != null;
    }

    @Override
    public void updateEntity()
    {
        if (cachedEntity != null)
        {
            if (this.doRotate())
            {
                cachedEntity.rotationYaw += ROTATION_PER_TICK;
            }
            else
            {
                ((EntityLiving) cachedEntity).faceEntity(Minecraft.getMinecraft().thePlayer, 360F, 360F);
            }

            if (cachedEntity instanceof EntityLivingBase)
            {
                EntityLivingBase living = (EntityLivingBase) cachedEntity;

                // Body yaw
                living.renderYawOffset = cachedEntity.rotationYaw;
                living.prevRenderYawOffset = cachedEntity.rotationYaw;

                // Head pitch
                living.rotationPitch = cachedEntity.rotationPitch;
                living.prevRotationPitch = cachedEntity.rotationPitch;

                // Head
                living.rotationYawHead = living.renderYawOffset;
                living.prevRotationYawHead = living.prevRenderYawOffset;
            }
        }
        super.updateEntity();
    }

    @Override
    public boolean canUpdate()
    {
        return FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT;
    }
}
