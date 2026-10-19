package com.stovetoptoad.jujutsuslop.event;

import com.stovetoptoad.jujutsuslop.JujutsuSlop;
import com.stovetoptoad.jujutsuslop.capability.InnerselfInventoryCapability;
import com.stovetoptoad.jujutsuslop.capability.ModCapabilities;
import com.stovetoptoad.jujutsuslop.capability.ShadowInventoryCapability;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JujutsuSlop.MOD_ID)
public class ShadowInventoryEvents {

    @SubscribeEvent
    public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(
                    new ResourceLocation(JujutsuSlop.MOD_ID, "shadow_inventory"),
                    new ShadowInventoryCapability()
            );
            event.addCapability(
                    new ResourceLocation(JujutsuSlop.MOD_ID, "innerself_inventory"),
                    new InnerselfInventoryCapability()
            );
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        event.getOriginal().reviveCaps();

        // Shadow Inventory
        event.getOriginal().getCapability(ModCapabilities.SHADOW_INVENTORY).ifPresent(oldCap -> {
            event.getEntity().getCapability(ModCapabilities.SHADOW_INVENTORY).ifPresent(newCap -> {
                newCap.getInventory().deserializeNBT(oldCap.getInventory().serializeNBT());
            });
        });

        // Innerself Inventory
        event.getOriginal().getCapability(ModCapabilities.INNERSELF_INVENTORY).ifPresent(oldCap -> {
            event.getEntity().getCapability(ModCapabilities.INNERSELF_INVENTORY).ifPresent(newCap -> {
                newCap.getInventory().deserializeNBT(oldCap.getInventory().serializeNBT());
            });
        });

        event.getOriginal().invalidateCaps();
    }
}