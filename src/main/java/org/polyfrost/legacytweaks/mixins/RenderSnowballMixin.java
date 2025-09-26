package org.polyfrost.legacytweaks.mixins;

import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import org.polyfrost.legacytweaks.LegacyTweaks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderSnowball.class)
public class RenderSnowballMixin<T extends Entity> {

    @Inject(method = "doRender", at = @At("HEAD"), cancellable = true)
    public void patcher$cleanProjectiles(T entity, double x, double y, double z, float entityYaw, float partialTicks, CallbackInfo ci) {
        if (LegacyTweaks.config.cleanProjectiles && entity.ticksExisted < 2) ci.cancel();
    }

}
