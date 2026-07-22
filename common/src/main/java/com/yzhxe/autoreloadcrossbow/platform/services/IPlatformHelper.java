package com.yzhxe.autoreloadcrossbow.platform.services;

import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public interface IPlatformHelper {

    /**
     * Gets the name of the current platform
     *
     * @return The name of the current platform.
     */
    String getPlatformName();

    /**
     * Checks if a mod with the given id is loaded.
     *
     * @param modId The mod to check if it is loaded.
     * @return True if the mod is loaded, false otherwise.
     */
    boolean isModLoaded(String modId);

    /**
     * Check if the game is currently in a development environment.
     *
     * @return True if in a development environment, false otherwise.
     */
    boolean isDevelopmentEnvironment();

    void incrementClickTick(KeyMapping key);

    default void toggleEnabledByKey() {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) return;

        boolean autoReloadEnabled = isAutoReloadEnabled();
        Component component = Component.translatable("autoreloadcrossbow." + (autoReloadEnabled ? "enabled" : "disabled"))
                .withStyle(autoReloadEnabled ? ChatFormatting.GREEN : ChatFormatting.RED);
        player.sendSystemMessage(component);
        player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 0.65f, 2.0f);
    }

    /**
     * Gets the name of the environment type as a string.
     *
     * @return The name of the environment type.
     */
    default String getEnvironmentName() {
        return isDevelopmentEnvironment() ? "development" : "production";
    }

    default boolean isAutoReloadEnabled() {
        return true;
    }

    default boolean canUseAttackKeyToShootCrossbow() {
        return false;
    }
}