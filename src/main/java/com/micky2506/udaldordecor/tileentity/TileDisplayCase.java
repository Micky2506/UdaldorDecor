package com.micky2506.udaldordecor.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileDisplayCase extends TileEntity implements IInventory
{
    public ItemStack stack;

    public TileDisplayCase()
    {
        stack = null;
    }

    public boolean onActivated(World world, EntityPlayer player, ItemStack playerStack)
    {
//        if (world.isRemote)
//        {
            if (playerStack != null)
            {
//                System.out.println("newStack");
                this.stack = playerStack.copy();
                this.stack.stackSize = 1;
            }
//        }
        world.markBlockForUpdate(xCoord, yCoord, zCoord);
        markDirty();
        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        System.out.println("Reading from NBT");
        NBTTagList tagList = compound.getTagList("Items", 10);
        this.stack = null;

        NBTTagCompound nbttagcompound1 = tagList.getCompoundTagAt(0);
        stack = ItemStack.loadItemStackFromNBT(nbttagcompound1);
    }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);

        if (this.stack != null)
        {
            System.out.println("Writing to NBT");
            this.stack.writeToNBT(compound);
        }

    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
        this.stack = ItemStack.loadItemStackFromNBT(pkt.func_148857_g());
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound tag = new NBTTagCompound(); //ObfuscationReflectionHelper.getPrivateValue(S35PacketUpdateTileEntity.class, pkt, "field_148860_e", "e");
        writeToNBT(tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
    }

    /**
     * Returns the number of slots in the inventory.
     */
    @Override
    public int getSizeInventory()
    {
        return 1;
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
        }
        return returnedStack;
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
}