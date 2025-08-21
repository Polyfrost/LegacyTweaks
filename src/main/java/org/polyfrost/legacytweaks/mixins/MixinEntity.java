package org.polyfrost.legacytweaks.mixins;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class MixinEntity {
    @Inject(method = "setPositionAndRotation", at = @At("HEAD"))
    protected void legacyTweaks$overrideMethod(double x, double y, double z, float yaw, float pitch, CallbackInfo ci){
    }
}
