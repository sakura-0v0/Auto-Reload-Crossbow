package com.yzhxe.autoreloadcrossbow;

import com.yzhxe.autoreloadcrossbow.platform.Services;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.lwjgl.glfw.GLFW;

@Mod(value = AutoReloadCrossbowMod.MOD_ID)
@Mod.EventBusSubscriber(modid =  AutoReloadCrossbowMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeAutoReloadCrossbowMod {
    public static final Lazy<KeyMapping> TOGGLE_AUTO_RELOAD = Lazy.of(() -> new KeyMapping(
            "key.autoreloadcrossbow.toggle_auto_reload",
            GLFW.GLFW_KEY_B,
            "key.category.autoreloadcrossbow.category"
    ));
    public static final Lazy<KeyMapping> TOGGLE_ATTACK_KEY_SHOOT = Lazy.of(() -> new KeyMapping(
            "key.autoreloadcrossbow.toggle_attack_key_shoot",
            GLFW.GLFW_KEY_N,
            "key.category.autoreloadcrossbow.category"
    ));

    public ForgeAutoReloadCrossbowMod() {
        AutoReloadCrossbowMod.init();
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ForgeConfig.SPEC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerKeyMappings);
    }

    public void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(TOGGLE_AUTO_RELOAD.get());
        event.register(TOGGLE_ATTACK_KEY_SHOOT.get());
    }

    @SubscribeEvent
    public static void clientPostTick(TickEvent.ClientTickEvent event) {
        while (TOGGLE_AUTO_RELOAD.get().consumeClick()) {
            Services.PLATFORM.toggleEnabledByKey();
        }

        while (TOGGLE_ATTACK_KEY_SHOOT.get().consumeClick()) {
            Services.PLATFORM.toggleAttackKeyShoot();
        }

        AutoReloadCrossbowMod.clientPostTick();
    }
}