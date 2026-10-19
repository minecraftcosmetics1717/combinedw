package com.stovetoptoad.jujutsuslop.init;

import com.stovetoptoad.jujutsuslop.JujutsuSlop;
import com.stovetoptoad.jujutsuslop.entity.RockEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class JujutsuSlopEntities {

   public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
           DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, JujutsuSlop.MOD_ID);

   public static final RegistryObject<EntityType<RockEntity>> ROCK =
           ENTITY_TYPES.register("rock",
                   () -> EntityType.Builder.<RockEntity>of(RockEntity::new, MobCategory.MISC)
                           .sized(0.4F, 0.4F)          // small rock size
                           .clientTrackingRange(8)
                           .updateInterval(1)
                           .build("rock"));
}