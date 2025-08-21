package org.polyfrost.legacytweaks.mixins;

import net.minecraft.entity.player.EntityPlayer;
import org.polyfrost.legacytweaks.LegacyTweaks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(EntityPlayer.class)
public abstract class MixinEntityPlayer extends MixinEntity {
    @Shadow
    public double prevChasingPosZ;

    @Shadow
    public double chasingPosX;

    @Shadow
    public double prevChasingPosX;

    @Shadow
    public double prevChasingPosY;

    @Shadow
    public double chasingPosY;

    @Shadow
    public double chasingPosZ;

    @ModifyVariable(method = "onUpdate", ordinal = 3, at = @At("STORE"))
    public double legacyTweaks$replaceMaxClampValue(double original) {
        return LegacyTweaks.config.naturalCapes ? Double.MAX_VALUE : original;
    }

    @Override
    protected void legacyTweaks$overrideMethod(double x, double y, double z, float yaw, float pitch, CallbackInfo ci) {
        if (LegacyTweaks.config.naturalCapes) {
            this.prevChasingPosY = this.chasingPosY = y;
            this.prevChasingPosZ = this.chasingPosZ = z;
            this.prevChasingPosX = this.chasingPosX = x;
        }

    }
}