package org.polyfrost.legacytweaks.mixins.compat;

import net.minecraft.client.renderer.entity.layers.LayerCape;
import org.polyfrost.legacytweaks.LegacyTweaks;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(LayerCape.class)
public class MixinLayerCape {
    @Dynamic("Optifine")
    @ModifyConstant(
            method = "doRenderLayer(Lnet/minecraft/client/entity/AbstractClientPlayer;FFFFFFF)V",
            constant = @Constant(floatValue = 165.0f)
    )
    public float patcher$disableOptifineSwingSidesClampConstant(float original) {
        return LegacyTweaks.config.naturalCapes ? -Float.MIN_VALUE : original;
    }

    @Dynamic("Optifine")
    @ModifyConstant(
            method = "doRenderLayer(Lnet/minecraft/client/entity/AbstractClientPlayer;FFFFFFF)V",
            constant = @Constant(floatValue = -5.0f)
    )
    public float patcher$disableOptifineSwingClampConstant(float original) {
        return LegacyTweaks.config.naturalCapes ? -Float.MAX_VALUE : original;
    }
}
