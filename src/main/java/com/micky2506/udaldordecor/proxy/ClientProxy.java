package com.micky2506.udaldordecor.proxy;

import com.micky2506.udaldordecor.renderer.RendererDisplayCase;
import com.micky2506.udaldordecor.tileentity.DisplayCaseTileBase;
import com.micky2506.udaldordecor.tileentity.TileRotatingDisplayCase;
import com.micky2506.udaldordecor.tileentity.TileTrackingDisplayCase;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy
{
    @Override
    public void initTileEntities()
    {
        super.initTileEntities();
        ClientRegistry.bindTileEntitySpecialRenderer(TileRotatingDisplayCase.class, new RendererDisplayCase());
        ClientRegistry.bindTileEntitySpecialRenderer(TileTrackingDisplayCase.class, new RendererDisplayCase());
//        ClientRegistry.bindTileEntitySpecialRenderer(TileWirelessBlockExtender.class, new RendererWirelessBlockExtender());
//        ClientRegistry.bindTileEntitySpecialRenderer(TileBuffer.class, new RendererBuffer());
//        ClientRegistry.bindTileEntitySpecialRenderer(TileSortingChest.class, new RendererSortingChest());
//        ClientRegistry.bindTileEntitySpecialRenderer(TileRelocationPortal.class, new RendererRelocationPortal());
//        ClientRegistry.bindTileEntitySpecialRenderer(TileMultiBlockBase.class, new RendererMultiBlock());
//        ClientRegistry.bindTileEntitySpecialRenderer(TileRelocator.class, new RendererRelocator());
//
//        MinecraftForgeClient.registerItemRenderer(ModBlocks.blockExtender.blockID, new ItemRendererBlockExtender());
//        MinecraftForgeClient.registerItemRenderer(ModBlocks.buffer.blockID, new ItemRendererBuffer());
//        MinecraftForgeClient.registerItemRenderer(ModBlocks.sortingChest.blockID, new ItemRendererSortingChest());
//        MinecraftForgeClient.registerItemRenderer(ModBlocks.relocator != null ? ModBlocks.relocator.blockID : FMPHelper.partRelocator.itemID, new ItemRendererRelocator());
//
//        if (Mods.IS_IRON_CHEST_LOADED)
//        {
//            IronChestHelper.addIronChestRenders();
//        }
//
//        if (Mods.IS_EE3_LOADED)
//        {
//            EE3Helper.addEE3Renders();
//        }
//
//        if (Mods.IS_METAL_LOADED)
//        {
//            MetallurgyHelper.addMetalRenders();
//        }
    }

    @Override
    public void registerEventHandlers()
    {
        super.registerEventHandlers();
//        MinecraftForge.EVENT_BUS.register(new EventHandlerClient());
    }
}
