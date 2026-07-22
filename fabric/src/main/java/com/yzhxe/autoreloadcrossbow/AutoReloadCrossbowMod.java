package com.yzhxe.autoreloadcrossbow;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class AutoReloadCrossbowMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        CommonAutoReloadCrossbowMod.init();
    }
}
