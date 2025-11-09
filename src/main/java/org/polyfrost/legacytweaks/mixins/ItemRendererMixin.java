package org.polyfrost.legacytweaks.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.ItemMap;
import org.polyfrost.legacytweaks.LegacyTweaks;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin {
    @Shadow @Final private Minecraft mc;

    //#if MC == 1.8.9
    // I have no clue (untested) but this might not work with optifine?
    // that's a bridge for a later version of myself to cross probably
    @Inject(method = "renderItemInFirstPerson", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;pushMatrix()V", shift = At.Shift.AFTER))
    private void flipHand(float partialTicks, CallbackInfo ci) {
        if (LegacyTweaks.config.leftHandInFirstPerson) {
            // maps are held with 2 hands
            if (this.mc.thePlayer.getHeldItem() != null && this.mc.thePlayer.getHeldItem().getItem() instanceof ItemMap) return;

            GlStateManager.scale(-1, 1, 1);
            GlStateManager.disableCull();
        }
    }
    //#endif
}
