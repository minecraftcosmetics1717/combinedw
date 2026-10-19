package com.stovetoptoad.jujutsuslop;

import com.stovetoptoad.jujutsuslop.network.ModNetwork;
import com.stovetoptoad.jujutsuslop.rika.RikaMenuTypes;

// Abyss imports
import com.ghosts.jujutsureborn.effect.ModEffects;
import com.ghosts.jujutsureborn.entity.RockEntity;
import com.ghosts.jujutsureborn.event.ShadowDiveTickHandler;
import com.ghosts.jujutsureborn.init.JujutsuRebornEntities;
import com.ghosts.jujutsureborn.item.RockItem;
import com.ghosts.jujutsureborn.menu.ModMenus;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(JujutsuSlop.MOD_ID)
public class JujutsuSlop {
   public static final String MOD_ID = "jujutsuslop";
   public static final Logger LOGGER = LogManager.getLogger();

   // ===== From Abyss =====
   public static final DeferredRegister<Item> ABYSS_ITEMS =
           DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

   public static final RegistryObject<Item> ROCK = ABYSS_ITEMS.register("rock",
           () -> new RockItem(new Item.Properties().stacksTo(16)));

   public static final GameRules.Key<GameRules.BooleanValue> STEVENSON_NOT_DESPAWN =
           GameRules.register("stevensonNotDespawn", GameRules.Category.MISC,
                   GameRules.BooleanValue.create(false));

   public JujutsuSlop() {
      IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

      // ===== jslops =====
      JslopGamerules.init();
      ModItems.ITEMS.register(modBus);
      RikaMenuTypes.MENU_TYPES.register(modBus);

      // ===== Abyss =====
      ABYSS_ITEMS.register(modBus);
      JujutsuRebornEntities.ENTITY_TYPES.register(modBus);
      ModMenus.MENUS.register(modBus);
      ModEffects.register(modBus);

      // Network (already merged – only call once)
      ModNetwork.register();

      // Event listeners
      modBus.addListener(this::addCreativeTabs);
      modBus.addListener(this::onEntityAttributeCreation);

      MinecraftForge.EVENT_BUS.register(ShadowDiveTickHandler.class);

      LOGGER.info("JujutsuSlop + Abyss initialising...");
   }

   private void addCreativeTabs(BuildCreativeModeTabContentsEvent event) {
      if (event.getTabKey() == CreativeModeTabs.COMBAT) {
         event.accept(ROCK);
      }
   }

   private void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
      event.put(JujutsuRebornEntities.ROCK.get(), RockEntity.createAttributes().build());
   }
}