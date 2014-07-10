package com.micky2506.udaldordecor.item;

import com.micky2506.udaldordecor.UdaldorDecor;
import com.micky2506.udaldordecor.lib.Names;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

public class ItemEntityNet extends Item
{
    public int entityID = -1;

    public ItemEntityNet()
    {
        super();
        this.setUnlocalizedName(Names.entityNet);
        this.setCreativeTab(UdaldorDecor.tabUdaldorDecor);
    }

    /**
     * Returns true if the item can be used on the given entity, e.g. shears on sheep.
     */
    public boolean itemInteractionForEntity(ItemStack itemStack, EntityPlayer player, EntityLivingBase entity)
    {
        if (player.worldObj.isRemote)
        {
            return false;
        }

        if (!(entity instanceof EntityPlayer))
        {
            NBTTagCompound compound = new NBTTagCompound();
            entity.writeToNBT(compound);
            compound.setString("id", (String) EntityList.classToStringMapping.get(entity.getClass()));
            itemStack.setTagCompound(compound);
            player.inventory.mainInventory[player.inventory.currentItem] = itemStack; // Update current item stack.
        }
        return true;
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     *
     * @param itemStack
     * @param player
     * @param list
     * @param par4
     */
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4)
    {
        if (itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("id"))
        {
            list.add("Stored Entity: " + itemStack.getTagCompound().getString("id"));
        }
        else
        {
            list.add("No entity stored");
        }
    }
}
