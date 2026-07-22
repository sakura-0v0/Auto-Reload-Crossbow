package com.yzhxe.autoreloadcrossbow;

import com.yzhxe.autoreloadcrossbow.platform.Services;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutoReloadCrossbowMod {
    public static final String MOD_ID = "autoreloadcrossbow";
    public static final String MOD_NAME = "Auto Reload Crossbow";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);
    private static boolean chargingCrossbow;

    public static void init() {
    }

    public static void clientPostTick() {
        if (Services.PLATFORM.isAutoReloadEnabled() && canReloadCrossbow()) {
            setChargingCrossbow(true);
        } else if (chargingCrossbow) {
            setChargingCrossbow(false);
        }
    }

    private static void setChargingCrossbow(boolean charging) {
        chargingCrossbow = charging;
        Minecraft.getInstance().options.keyUse.setDown(charging);
    }

    private static boolean canReloadCrossbow() {
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.screen != null) return false;
        LocalPlayer player = minecraft.player;

        if (player == null) return false;
        ItemStack stack = player.getMainHandItem();

        return stack.is(Items.CROSSBOW) && !CrossbowItem.isCharged(stack);
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
