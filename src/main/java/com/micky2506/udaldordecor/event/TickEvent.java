package com.micky2506.udaldordecor.event;

import com.micky2506.udaldordecor.renderer.RendererDisplayCase;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class TickEvent
{
    @SubscribeEvent
    public void onClientTick(cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent event)
    {
        if (event.phase == cpw.mods.fml.common.gameevent.TickEvent.Phase.END)
        {
            RendererDisplayCase.tick++;
        }
    }
}