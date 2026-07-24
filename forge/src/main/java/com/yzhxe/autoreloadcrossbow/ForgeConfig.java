package com.yzhxe.autoreloadcrossbow;

import net.minecraftforge.common.ForgeConfigSpec;

public class ForgeConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec.BooleanValue ENABLED = BUILDER
            .translation("config.autoreloadcrossbow.enabled")
            .define("enabled", true);
    public static final ForgeConfigSpec.BooleanValue CAN_USE_ATTACK_KEY_TO_SHOOT_CROSSBOW = BUILDER
            .translation("config.autoreloadcrossbow.can_use_attack_key_to_shoot_crossbow")
            .define("can_use_attack_key_to_shoot_crossbow", true);

    static final ForgeConfigSpec SPEC = BUILDER.build();
}
