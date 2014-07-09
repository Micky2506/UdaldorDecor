package com.micky2506.udaldordecor.tileentity;

import com.micky2506.udaldordecor.helper.IOHelper;
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
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileTrackingDisplayCase extends DisplayCaseTileBase
{
    public TileTrackingDisplayCase()
    {
        // Init code
    }

    @Override
    public boolean doRotate()
    {
        return false;
    }
}