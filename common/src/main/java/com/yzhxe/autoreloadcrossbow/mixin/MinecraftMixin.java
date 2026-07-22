package com.yzhxe.autoreloadcrossbow.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.yzhxe.autoreloadcrossbow.platform.Services;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {

    @Inject(method = "startAttack()Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;get(Lnet/minecraft/core/component/DataComponentType;)Ljava/lang/Object;", ordinal = 0), cancellable = true)
    public void shootCrossbowByAttack(CallbackInfoReturnable<Boolean> cir, @Local(name = "heldItem") ItemStack heldItem) {
        if (!Services.PLATFORM.canUseAttackKeyToShootCrossbow()) return;

        if (heldItem.is(Items.CROSSBOW) && CrossbowItem.isCharged(heldItem)) {
            Services.PLATFORM.incrementClickTick(Minecraft.getInstance().options.keyUse);
            cir.setReturnValue(true);
        }
    }
}
