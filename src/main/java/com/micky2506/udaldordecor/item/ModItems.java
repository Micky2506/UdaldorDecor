package com.micky2506.udaldordecor.item;

import com.micky2506.udaldordecor.lib.Names;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ModItems
{
    public static ItemEntityNet entityNet;

    public static void init()
    {
        entityNet = new ItemEntityNet();

        GameRegistry.registerItem(entityNet, Names.entityNet);


        GameRegistry.addShapelessRecipe(new ItemStack(entityNet, 1, 0), new ItemStack(Items.stick, 1, 0), new ItemStack(Items.slime_ball, 1, 0), new ItemStack(Items.string, 1, 0), new ItemStack(Items.stick, 1, 0));
    }
}