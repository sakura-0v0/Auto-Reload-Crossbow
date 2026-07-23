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

    @Inject(method = "startAttack()Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/phys/HitResult;getType()Lnet/minecraft/world/phys/HitResult$Type;"), cancellable = true)
    public void shootCrossbowByAttack(CallbackInfoReturnable<Boolean> cir, @Local ItemStack heldItem) {
        if (!Services.PLATFORM.canUseAttackKeyToShootCrossbow()) return;

        if (heldItem.is(Items.CROSSBOW) && CrossbowItem.isCharged(heldItem)) {
            Services.PLATFORM.incrementClickTick(Minecraft.getInstance().options.keyUse);
            cir.setReturnValue(true);
        }
    }
}
