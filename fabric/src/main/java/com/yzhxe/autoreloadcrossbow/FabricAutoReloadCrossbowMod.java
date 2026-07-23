package com.yzhxe.autoreloadcrossbow;

import com.mojang.blaze3d.platform.InputConstants;
import com.yzhxe.autoreloadcrossbow.platform.Services;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class FabricAutoReloadCrossbowMod implements ClientModInitializer {
    public static final KeyMapping KEY_TOGGLE_AUTO_RELOAD = new KeyMapping(
            "key.autoreloadcrossbow.toggle_auto_reload",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_B,
            "key.category.autoreloadcrossbow.category"
    );
    public static final KeyMapping KEY_TOGGLE_ATTACK_KEY_SHOOT = new KeyMapping(
            "key.autoreloadcrossbow.toggle_attack_key_shoot",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_N,
            "key.category.autoreloadcrossbow.category"
    );
    public static boolean ENABLED_AUTO_RELOAD = true;
    public static boolean ENABLED_ATTACK_KEY_SHOOT = true;

    @Override
    public void onInitializeClient() {
        AutoReloadCrossbowMod.init();
        KeyBindingHelper.registerKeyBinding(KEY_TOGGLE_AUTO_RELOAD);
        KeyBindingHelper.registerKeyBinding(KEY_TOGGLE_ATTACK_KEY_SHOOT);

        ClientTickEvents.END_WORLD_TICK.register((level) -> {
            while (KEY_TOGGLE_AUTO_RELOAD.consumeClick()) {
                Services.PLATFORM.toggleEnabledByKey();
            }

            while (KEY_TOGGLE_ATTACK_KEY_SHOOT.consumeClick()) {
                Services.PLATFORM.toggleAttackKeyShoot();
            }

            AutoReloadCrossbowMod.clientPostTick();
        });
    }
}
