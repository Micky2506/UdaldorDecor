package com.micky2506.udaldordecor;

import com.micky2506.udaldordecor.block.ModBlocks;
import com.micky2506.udaldordecor.creativetab.CreativeTabUdaldorDecor;
import com.micky2506.udaldordecor.lib.Reference;
import com.micky2506.udaldordecor.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraft.creativetab.CreativeTabs;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME,version = Reference.VERSION, dependencies = Reference.DEPENDENCIES)
public class UdaldorDecor
{
    @Mod.Instance(Reference.MOD_ID)
    public static UdaldorDecor instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.COMMON_PROXY)
    public static CommonProxy proxy;

    public static CreativeTabs tabUdaldorDecor = new CreativeTabUdaldorDecor(CreativeTabs.getNextID(), Reference.MOD_ID);

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {
        //event.registerServerCommand(new CommandRefinedRelocation());
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ModBlocks.init();
        proxy.registerEventHandlers();
        proxy.initTileEntities();
//        LogHelper.init();
//
//        VersionChecker.execute();
//
//        ConfigHandler.init(event.getSuggestedConfigurationFile());
//
//        TickRegistry.registerTickHandler(new TickEvent(), Side.CLIENT);
//
//        GameRegistry.registerPlayerTracker(new PlayerTracker());
//
//
//        ModItems.init();
//
//        ModMultiBlocks.init();
//
//        RelocatorModuleRegistry.registerModules();
//
//
//        ForgeChunkManager.setForcedChunkLoadingCallback(this, new LoadingCallbackHelper());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
//        if (Mods.IS_FMP_LOADED)
//        {
//            FMPHelper.addFMPBlocks();
//            FMPHelper.addFMPRecipes();
//        }
//
//        proxy.initTileEntities();
//
//        FMLInterModComms.sendMessage("Waila", "register", "com.dynious.refinedrelocation.mods.WailaProvider.callbackRegister");
    }
}