package com.micky2506.udaldordecor.block;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemBlockDisplayCase extends ItemBlock
{
    public ItemBlockDisplayCase(Block block)
    {
        super(block);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return super.getUnlocalizedName(stack) + stack.getItemDamage();
    }

    @Override
    public int getMetadata(int meta)
    {
        return meta;
    }

    @Override
    public void getSubItems(Item unkown, CreativeTabs tab, List list)
    {
        for (int i = 0; i < 2; ++i)
        {
            list.add(new ItemStack(unkown, 1, i));
        }
    }
}
