package com.micky2506.udaldordecor.tileentity;

import net.minecraft.tileentity.TileEntity;

public class TileRotatingDisplayCase extends DisplayCaseTileBase
{
    public TileRotatingDisplayCase()
    {
    }

    @Override
    public boolean doRotate()
    {
        return true;
    }
}
