package com.micky2506.udaldordecor.proxy;

import com.micky2506.udaldordecor.event.TickEvent;
import com.micky2506.udaldordecor.lib.Names;
import com.micky2506.udaldordecor.tileentity.TileDisplayCase;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy
{
    public void initTileEntities()
    {
        GameRegistry.registerTileEntity(com.micky2506.udaldordecor.tileentity.TileDisplayCase.class, Names.displayCase);
//        GameRegistry.registerTileEntity(TileAdvancedBlockExtender.class, Names.advancedBlockExtender);
//        GameRegistry.registerTileEntity(TileFilteredBlockExtender.class, Names.filteredBlockExtender);
//        GameRegistry.registerTileEntity(TileAdvancedFilteredBlockExtender.class, Names.advancedFilteredBlockExtender);
//        GameRegistry.registerTileEntity(TileWirelessBlockExtender.class, Names.wirelessBlockExtender);
//        GameRegistry.registerTileEntity(TileBuffer.class, Names.buffer);
//        GameRegistry.registerTileEntity(TileAdvancedBuffer.class, Names.advancedBuffer);
//        GameRegistry.registerTileEntity(TileFilteredBuffer.class, Names.filteredBuffer);
//        GameRegistry.registerTileEntity(TileSortingChest.class, Names.sortingChest);
//        GameRegistry.registerTileEntity(TileSortingConnector.class, Names.sortingConnector);
//        GameRegistry.registerTileEntity(TileFilteringHopper.class, Names.filteringHopper);
//        GameRegistry.registerTileEntity(TileRelocationPortal.class, Names.relocationPortal);
//        GameRegistry.registerTileEntity(TileRelocationController.class, Names.relocationController);
//        GameRegistry.registerTileEntity(TilePowerLimiter.class, Names.powerLimiter);
//        GameRegistry.registerTileEntity(TileSortingInterface.class, Names.sortingInterface);
//        GameRegistry.registerTileEntity(TileSortingImporter.class, Names.sortingImporter);
//        GameRegistry.registerTileEntity(TileRelocator.class, Names.relocator);
//
//        if (Mods.IS_IRON_CHEST_LOADED)
//        {
//            GameRegistry.registerTileEntity(TileSortingIronChest.class, Names.sortingIronChest);
//        }
//        if (Mods.IS_JABBA_LOADED)
//        {
//            GameRegistry.registerTileEntity(TileSortingBarrel.class, Names.sortingBarrel);
//        }
//
//        if (Mods.IS_EE3_LOADED)
//        {
//            GameRegistry.registerTileEntity(TileSortingAlchemicalChest.class, Names.sortingAlchemicalChest);
//        }
//
//        if (Mods.IS_METAL_LOADED)
//        {
//            GameRegistry.registerTileEntity(TileSortingPreciousChest.class, Names.sortingPreciousChest);
//        }
//
//        new GuiHandler();
    }

    public void registerEventHandlers()
    {
//        FMLCommonHandler.instance().bus().register(new TickEvent());
    }
}