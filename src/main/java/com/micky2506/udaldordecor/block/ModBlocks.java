package com.micky2506.udaldordecor.block;

import com.micky2506.udaldordecor.lib.Names;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ModBlocks
{
    public static BlockDisplayCase displayCase;

    public static void init()
    {
        displayCase = new BlockDisplayCase();

        GameRegistry.registerBlock(displayCase, ItemBlockDisplayCase.class, Names.displayCase);

        GameRegistry.addShapedRecipe(new ItemStack(displayCase, 1, 0), "ggg", "gig", "ggg", 'i', Blocks.glass_pane, 'g', Blocks.glass);
        GameRegistry.addShapedRecipe(new ItemStack(displayCase, 1, 1), "gsg", "gig", "gsg", 'i', Blocks.glass_pane, 'g', Blocks.glass, 's', Items.string);
    }
}