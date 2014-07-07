package com.micky2506.udaldordecor.tileentity;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class TileDisplayCase extends TileEntity
{
    public ItemStack stack = null;

    public TileDisplayCase()
    {
//        stack = new ItemStack(Items.apple, 1, 0);
    }

    public boolean onActivated(World world, EntityPlayer player, ItemStack playerStack)
    {
        if (world.isRemote)
        {
            if (playerStack != null)
            {
                System.out.println("newStack");
                this.stack = playerStack.copy();
                this.stack.stackSize = 1;
            }

            if (stack != null)
                System.out.println("Stack Name: " + stack.getDisplayName());
            else
                System.out.println("No stack!");
        }
        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);

        if (compound.hasKey("Items"))
        {
            NBTTagList tagList = compound.getTagList("Items", 10);
            stack = ItemStack.loadItemStackFromNBT(tagList.getCompoundTagAt(0));
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);

        if (stack != null)
        {
            NBTTagList nbttaglist = new NBTTagList();
            NBTTagCompound tag = new NBTTagCompound();
            this.stack.writeToNBT(tag);
            nbttaglist.appendTag(tag);
            compound.setTag("Items", nbttaglist);
        }
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
        super.onDataPacket(net, pkt);
        readFromNBT(pkt.func_148857_g());
    }

    @Override
    public Packet getDescriptionPacket()
    {
        S35PacketUpdateTileEntity pkt = (S35PacketUpdateTileEntity) super.getDescriptionPacket();
        NBTTagCompound tag = new NBTTagCompound(); //ObfuscationReflectionHelper.getPrivateValue(S35PacketUpdateTileEntity.class, pkt, "field_148860_e", "e");
        writeToNBT(tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
    }
}