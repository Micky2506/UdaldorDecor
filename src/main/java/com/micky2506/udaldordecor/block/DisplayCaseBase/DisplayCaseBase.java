package com.micky2506.udaldordecor.block.DisplayCaseBase;

import com.micky2506.udaldordecor.UdaldorDecor;
import com.micky2506.udaldordecor.helper.IOHelper;
import com.micky2506.udaldordecor.lib.Names;
import com.micky2506.udaldordecor.lib.Resources;
import com.micky2506.udaldordecor.tileentity.DisplayCaseTileBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class DisplayCaseBase extends BlockContainer
{
    private static IIcon[] icons = new IIcon[1];

    public DisplayCaseBase(Material material)
    {
        super(material);
        this.setHardness(0.5F);
        this.setCreativeTab(UdaldorDecor.tabUdaldorDecor);
        this.setHarvestLevel("pickaxe", 0);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile != null && tile instanceof DisplayCaseTileBase)
        {
            return ((DisplayCaseTileBase)tile).onActivated(world, player, player.getHeldItem(), side);
        }
        return false;
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
    {
        ForgeDirection direction = ForgeDirection.getOrientation(side);
        Block block = world.getBlock(x, y, z);
        return block != this;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    @Override
    public boolean renderAsNormalBlock()
    {
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
    public void breakBlock(World world, int x, int y, int z, Block block, int meta)
    {
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile != null && tile instanceof DisplayCaseTileBase)
        {
            for (ItemStack stack : ((DisplayCaseTileBase)tile).getDrops())
            {
                IOHelper.spawnItemInWorld(world, stack, x, y, z);
            }
        }
        super.breakBlock(world, x, y, z, block, meta);
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
