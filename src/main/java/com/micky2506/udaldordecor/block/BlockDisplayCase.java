package com.micky2506.udaldordecor.block;

import com.micky2506.udaldordecor.block.DisplayCaseBase.DisplayCaseBase;
import com.micky2506.udaldordecor.lib.Names;
import com.micky2506.udaldordecor.tileentity.TileRotatingDisplayCase;
import com.micky2506.udaldordecor.tileentity.TileTrackingDisplayCase;
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

    @Override
    public TileEntity createNewTileEntity(World var1, int meta)
    {
        switch (meta)
        {
            case 0:
                return new TileRotatingDisplayCase();
            case 1:
                return new TileTrackingDisplayCase();
            default:
                return null;
        }
    }
}