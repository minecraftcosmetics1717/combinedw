package com.stovetoptoad.jujutsuslop.client;

import com.stovetoptoad.jujutsuslop.JujutsuSlop;
import com.stovetoptoad.jujutsuslop.client.renderer.RockRenderer;
import com.stovetoptoad.jujutsuslop.init.JujutsuSlopEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JujutsuSlop.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {

   @SubscribeEvent
   public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
      event.registerEntityRenderer(JujutsuSlopEntities.ROCK.get(), RockRenderer::new);
   }
}