package com.micky2506.udaldordecor.creativetab;

import com.micky2506.udaldordecor.block.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabUdaldorDecor extends CreativeTabs
{

    public CreativeTabUdaldorDecor(int par1, String par2Str)
    {
        super(par1, par2Str);
    }

    @Override
    public ItemStack getIconItemStack()
    {
        return new ItemStack(ModBlocks.displayCase);
    }

    @Override
    public Item getTabIconItem()
    {
        return null;
    }
}