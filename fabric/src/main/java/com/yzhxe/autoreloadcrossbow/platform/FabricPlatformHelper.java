package com.yzhxe.autoreloadcrossbow.platform;

import com.yzhxe.autoreloadcrossbow.FabricAutoReloadCrossbowMod;
import com.yzhxe.autoreloadcrossbow.platform.services.IPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.KeyMapping;

public class FabricPlatformHelper implements IPlatformHelper {
    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public boolean isAutoReloadEnabled() {
        return FabricAutoReloadCrossbowMod.ENABLED_AUTO_RELOAD;
    }

    @Override
    public boolean canUseAttackKeyToShootCrossbow() {
        return FabricAutoReloadCrossbowMod.ENABLED_ATTACK_KEY_SHOOT;
    }

    @Override
    public void toggleEnabledByKey() {
        FabricAutoReloadCrossbowMod.ENABLED_AUTO_RELOAD = !FabricAutoReloadCrossbowMod.ENABLED_AUTO_RELOAD;
        IPlatformHelper.super.toggleEnabledByKey();
    }

    @Override
    public void toggleAttackKeyShoot() {
        FabricAutoReloadCrossbowMod.ENABLED_ATTACK_KEY_SHOOT = !FabricAutoReloadCrossbowMod.ENABLED_ATTACK_KEY_SHOOT;
        IPlatformHelper.super.toggleAttackKeyShoot();
    }

    @Override
    public void incrementClickTick(KeyMapping key) {
        key.clickCount++;
    }
}
