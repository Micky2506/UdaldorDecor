package com.micky2506.udaldordecor.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;

public class EventHandler
{
    @SubscribeEvent
    public void RenderLivingEvent(EntityLivingBase entity, RendererLivingEntity renderer, double x, double y, double z)
    {

    }
}
