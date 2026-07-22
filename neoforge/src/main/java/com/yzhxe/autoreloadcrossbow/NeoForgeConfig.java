package com.yzhxe.autoreloadcrossbow;

import net.neoforged.neoforge.common.ModConfigSpec;

public class NeoForgeConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec.BooleanValue ENABLED = BUILDER
            .translation("config.autoreloadcrossbow.enabled")
            .define("enabled", true);
    public static final ModConfigSpec.BooleanValue CAN_USE_ATTACK_KEY_TO_SHOOT_CROSSBOW = BUILDER
            .translation("config.autoreloadcrossbow.can_use_attack_key_to_shoot_crossbow")
            .define("can_use_attack_key_to_shoot_crossbow", false);

    static final ModConfigSpec SPEC = BUILDER.build();
}
