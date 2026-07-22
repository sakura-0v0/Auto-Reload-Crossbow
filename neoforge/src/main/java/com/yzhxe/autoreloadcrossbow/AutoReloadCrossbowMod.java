package com.yzhxe.autoreloadcrossbow;


import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(value = AutoReloadCrossbowConstants.MOD_ID, dist = Dist.CLIENT)
public class AutoReloadCrossbowMod {

    public AutoReloadCrossbowMod(IEventBus eventBus) {
        CommonAutoReloadCrossbowMod.init();
    }
}