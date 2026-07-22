package com.yzhxe.autoreloadcrossbow;

import com.yzhxe.autoreloadcrossbow.platform.Services;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.util.Lazy;
import org.lwjgl.glfw.GLFW;

@Mod(value = AutoReloadCrossbowMod.MOD_ID, dist = Dist.CLIENT)
public class NeoForgeAutoReloadCrossbowMod {
    public static final KeyMapping.Category KEY_CATEGORY = new KeyMapping.Category(AutoReloadCrossbowMod.id("category"));
    public static final Lazy<KeyMapping> TOGGLE_AUTO_RELOAD = Lazy.of(() -> new KeyMapping(
            "key.autoreloadcrossbow.toggle_auto_reload",
            GLFW.GLFW_KEY_B,
            KEY_CATEGORY
    ));

    public NeoForgeAutoReloadCrossbowMod(ModContainer container, IEventBus eventBus) {
        AutoReloadCrossbowMod.init();
        container.registerConfig(ModConfig.Type.CLIENT, NeoForgeConfig.SPEC);
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        eventBus.addListener(this::registerKeyMappings);
        NeoForge.EVENT_BUS.register(this);
    }

    public void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.registerCategory(KEY_CATEGORY);
        event.register(TOGGLE_AUTO_RELOAD.get());
    }

    @SubscribeEvent
    public void clientPostTick(ClientTickEvent.Post event) {
        while (TOGGLE_AUTO_RELOAD.get().consumeClick()) {
            Services.PLATFORM.toggleEnabledByKey();
        }

        AutoReloadCrossbowMod.clientPostTick();
    }
}