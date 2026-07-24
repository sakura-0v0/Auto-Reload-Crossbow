package com.yzhxe.autoreloadcrossbow.platform;

import com.yzhxe.autoreloadcrossbow.ForgeConfig;
import com.yzhxe.autoreloadcrossbow.mixin.KeyMappingAccessor;
import com.yzhxe.autoreloadcrossbow.platform.services.IPlatformHelper;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;

public class ForgePlatformHelper implements IPlatformHelper {
    @Override
    public String getPlatformName() {
        return "Forge";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return !FMLLoader.isProduction();
    }

    @Override
    public boolean isAutoReloadEnabled() {
        return ForgeConfig.ENABLED.get();
    }

    @Override
    public boolean canUseAttackKeyToShootCrossbow() {
        return ForgeConfig.CAN_USE_ATTACK_KEY_TO_SHOOT_CROSSBOW.get();
    }

    @Override
    public void toggleEnabledByKey() {
        ForgeConfig.ENABLED.set(!ForgeConfig.ENABLED.get());
        IPlatformHelper.super.toggleEnabledByKey();
    }

    @Override
    public void toggleAttackKeyShoot() {
        ForgeConfig.CAN_USE_ATTACK_KEY_TO_SHOOT_CROSSBOW.set(!ForgeConfig.CAN_USE_ATTACK_KEY_TO_SHOOT_CROSSBOW.get());
        IPlatformHelper.super.toggleAttackKeyShoot();
    }

    @Override
    public void incrementClickTick(KeyMapping key) {
        KeyMappingAccessor accessor = (KeyMappingAccessor) key;
        accessor.setClickCount(accessor.getClickCount() + 1);
    }
}