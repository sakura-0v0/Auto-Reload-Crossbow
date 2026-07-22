package com.yzhxe.autoreloadcrossbow.platform;

import com.yzhxe.autoreloadcrossbow.NeoForgeConfig;
import com.yzhxe.autoreloadcrossbow.platform.services.IPlatformHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;
import org.apache.logging.log4j.util.Lazy;

import java.util.*;
import java.util.function.Supplier;

public class NeoForgePlatformHelper implements IPlatformHelper {
    @Override
    public String getPlatformName() {
        return "NeoForge";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return !FMLLoader.getCurrent().isProduction();
    }

    @Override
    public boolean isAutoReloadEnabled() {
        return NeoForgeConfig.ENABLED.getAsBoolean();
    }

    @Override
    public boolean canUseAttackKeyToShootCrossbow() {
        return NeoForgeConfig.CAN_USE_ATTACK_KEY_TO_SHOOT_CROSSBOW.getAsBoolean();
    }

    @Override
    public void toggleEnabledByKey() {
        NeoForgeConfig.ENABLED.set(!NeoForgeConfig.ENABLED.get());
        IPlatformHelper.super.toggleEnabledByKey();
    }

    @Override
    public void incrementClickTick(KeyMapping key) {
        key.clickCount++;
    }
}