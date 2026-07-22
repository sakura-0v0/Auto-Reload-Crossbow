package com.yzhxe.autoreloadcrossbow;

import com.mojang.blaze3d.platform.InputConstants;
import com.yzhxe.autoreloadcrossbow.platform.Services;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class FabricAutoReloadCrossbowMod implements ClientModInitializer {
    public static final KeyMapping.Category KEY_CATEGORY = new KeyMapping.Category(AutoReloadCrossbowMod.id("category"));
    public static final KeyMapping KEY_TOGGLE_AUTO_RELOAD = new KeyMapping(
            "key.autoreloadcrossbow.toogle_auto_reload",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_B,
            KEY_CATEGORY
    );
    public static boolean ENABLED_AUTO_RELOAD = true;

    @Override
    public void onInitializeClient() {
        AutoReloadCrossbowMod.init();
        KeyMappingHelper.registerKeyMapping(KEY_TOGGLE_AUTO_RELOAD);

        ClientTickEvents.END_LEVEL_TICK.register((client) -> {
            while (KEY_TOGGLE_AUTO_RELOAD.consumeClick()) {
                Services.PLATFORM.toggleEnabledByKey();
            }

            AutoReloadCrossbowMod.clientPostTick();
        });
    }
}
