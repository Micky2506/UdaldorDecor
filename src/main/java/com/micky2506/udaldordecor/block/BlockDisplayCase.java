package com.micky2506.udaldordecor.block;

import com.micky2506.udaldordecor.UdaldorDecor;
import com.micky2506.udaldordecor.block.DisplayCaseBase.DisplayCaseBase;
import com.micky2506.udaldordecor.lib.Names;
import com.micky2506.udaldordecor.tileentity.TileDisplayCase;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockDisplayCase extends DisplayCaseBase
{
    public BlockDisplayCase()
    {
        super(Material.glass);
        this.setBlockName(Names.displayCase);
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     *
     * @param var1
     * @param var2
     */
    @Override
    public TileEntity createNewTileEntity(World var1, int var2)
    {
        return new TileDisplayCase();
    }
}