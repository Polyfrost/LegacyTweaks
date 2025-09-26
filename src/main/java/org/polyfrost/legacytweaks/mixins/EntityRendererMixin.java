package org.polyfrost.legacytweaks.mixins;

import net.minecraft.client.renderer.EntityRenderer;
import org.polyfrost.legacytweaks.LegacyTweaks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin {

    @ModifyArg(method = "setupCameraTransform", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;scale(FFF)V"), index = 0)
    private float level(float value) {
        return 1 + (value - 1) * LegacyTweaks.config.distortionEffect / 100f;
    }

}
