package com.micky2506.udaldordecor.block;

import com.micky2506.udaldordecor.UdaldorDecor;
import com.micky2506.udaldordecor.lib.Names;
import com.micky2506.udaldordecor.lib.Resources;
import com.micky2506.udaldordecor.tileentity.TileDisplayCase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class DisplayCase extends BlockContainer
{
    private static IIcon[] icons = new IIcon[1];

    public DisplayCase()
    {
        super(Material.glass);
        this.setBlockName(Names.displayCase);
        this.setHardness(2.0F);
        this.setCreativeTab(UdaldorDecor.tabUdaldorDecor);
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2)
    {
        return new TileDisplayCase();
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
    {
        ForgeDirection direction = ForgeDirection.getOrientation(side);
        Block block = world.getBlock(x, y, z);
        return block != ModBlocks.displayCase;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile != null && tile instanceof TileDisplayCase)
        {
            return ((TileDisplayCase)tile).onActivated(world, player, player.getHeldItem());
        }
        return false;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public IIcon getIcon(int par1, int par2)
    {
        return icons[0];
    }

    @Override
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
    {
        return icons[0];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        icons[0] = iconRegister.registerIcon(Resources.MOD_ID + ":" + Names.displayCase);
    }
}