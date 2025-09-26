package org.polyfrost.legacytweaks.mixins;

import net.minecraft.client.renderer.entity.RenderFish;
import net.minecraft.entity.projectile.EntityFishHook;
import org.polyfrost.legacytweaks.LegacyTweaks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderFish.class)
public class RenderFishMixin {

    @Inject(method = "doRender(Lnet/minecraft/entity/projectile/EntityFishHook;DDDFF)V", at = @At("HEAD"), cancellable = true)
    public void patcher$cleanProjectiles(EntityFishHook entity, double x, double y, double z, float entityYaw, float partialTicks, CallbackInfo ci) {
        if (LegacyTweaks.config.cleanProjectiles && entity.ticksExisted < 2) ci.cancel();
    }

}