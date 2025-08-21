package org.polyfrost.legacytweaks.mixins;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerCape;
import net.minecraft.util.MathHelper;
import org.polyfrost.legacytweaks.LegacyTweaks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//#if MC >= 1.12.2
//$$ import net.minecraft.inventory.EntityEquipmentSlot;
//#endif

@Mixin(LayerCape.class)
public class MixinLayerCape {
    @Unique
    private AbstractClientPlayer legacyTweaks$entityLivingBaseIn;
    @Unique
    private float legacyTweaks$height;
    @Unique
    private float legacyTweaks$swing;
    @Unique
    private float legacyTweaks$v;

    @ModifyConstant(
            method = "doRenderLayer(Lnet/minecraft/client/entity/AbstractClientPlayer;FFFFFFF)V",
            constant = @Constant(floatValue = 0.0F, ordinal = 2)
    )
    public float legacyTweaks$disableSwingClampValue(float original) {
        return LegacyTweaks.config.naturalCapes ? -Float.MAX_VALUE : original;
    }

    @Inject(
            method = "doRenderLayer(Lnet/minecraft/client/entity/AbstractClientPlayer;FFFFFFF)V",
            at = @At(value = "HEAD")
    )
    public void legacyTweaks$setEntityLivingBaseIn(AbstractClientPlayer entityLivingBaseIn, float f, float g, float partialTicks, float h, float i, float j, float scale, CallbackInfo ci) {
        this.legacyTweaks$entityLivingBaseIn = entityLivingBaseIn;
    }

    @Redirect(
            method = "doRenderLayer(Lnet/minecraft/client/entity/AbstractClientPlayer;FFFFFFF)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;translate(FFF)V")
    )
    public void legacyTweaks$replaceGLStateManagerTranslate(float x, float y, float z) {
        if (LegacyTweaks.config.naturalCapes) {
            float y1 = 0.00F;
            float z1 = 0.125F;
            if (this.legacyTweaks$entityLivingBaseIn.isSneaking()) {
                z1 = 0.027F;
                y1 = 0.05F;
            }

            //#if MC <= 1.8.9
            if (legacyTweaks$entityLivingBaseIn.getCurrentArmor(2) != null || legacyTweaks$entityLivingBaseIn.getCurrentArmor(3) != null) {
                z1 += 0.032f;

            }
            //#else
            //$$ if (this.legacyTweaks$entityLivingBaseIn.getItemStackFromSlot(EntityEquipmentSlot.CHEST) != null ||
            //$$        this.legacyTweaks$entityLivingBaseIn.getItemStackFromSlot(EntityEquipmentSlot.LEGS) != null
            //$$ ) {
            //$$    z1 += 0.032f;
            //$$ }
            //#endif

            GlStateManager.translate(0.0F, y1, z1);
            return;
        }
        GlStateManager.translate(x, y, z);
    }

    @ModifyVariable(
            method = "doRenderLayer(Lnet/minecraft/client/entity/AbstractClientPlayer;FFFFFFF)V",
            ordinal = 8,
            at = @At(value = "STORE")
    )
    public float legacyTweaks$captureHeight(float ori) {
        legacyTweaks$height = ori;
        return ori;
    }

    @ModifyVariable(
            method = "doRenderLayer(Lnet/minecraft/client/entity/AbstractClientPlayer;FFFFFFF)V",
            ordinal = 9,
            at = @At(value = "STORE")
    )
    public float legacyTweaks$captureSwing(float ori) {
        legacyTweaks$swing = ori;
        return ori;
    }

    @ModifyVariable(
            method = "doRenderLayer(Lnet/minecraft/client/entity/AbstractClientPlayer;FFFFFFF)V",
            ordinal = 10,
            at = @At(value = "STORE")
    )
    public float legacyTweaks$captureSwingSides(float ori) {
        legacyTweaks$v = (float) ((ori / 2) / Math.sqrt(2 + (Math.pow((ori - 10) / 60, 2))));
        return ori;
    }

    @ModifyArg(
            method = "doRenderLayer(Lnet/minecraft/client/entity/AbstractClientPlayer;FFFFFFF)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;rotate(FFFF)V", ordinal = 0),
            index = 0
    )
    private float legacyTweaks$modifyCapeRotation0(float angle) {
        if (!LegacyTweaks.config.naturalCapes) return angle;

        float min;
        if (legacyTweaks$entityLivingBaseIn.isSneaking()) {
            min = 8.0f;
            //#if MC >= 1.8.9
            if ((legacyTweaks$entityLivingBaseIn.getCurrentArmor(2) != null || legacyTweaks$entityLivingBaseIn.getCurrentArmor(3) != null)) {
                min += 3.0F;
            }
            //#else
            //$$ if (this.legacyTweaks$entityLivingBaseIn.getItemStackFromSlot(EntityEquipmentSlot.CHEST) != null ||
            //$$    this.legacyTweaks$entityLivingBaseIn.getItemStackFromSlot(EntityEquipmentSlot.LEGS) != null
            //$$ ) {
            //$$    min+=10.0F;
            //$$ }
            //#endif

        } else {
            min = 5;
        }

        return MathHelper.clamp_float((legacyTweaks$swing / ((float) Math.sqrt(6 + (Math.pow(legacyTweaks$swing / 150, 2))))), min, 130.0F) + legacyTweaks$height;
    }

    @ModifyArg(
            method = "doRenderLayer(Lnet/minecraft/client/entity/AbstractClientPlayer;FFFFFFF)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;rotate(FFFF)V", ordinal = 1),
            index = 0
    )
    private float legacyTweaks$modifyCapeRotation1(float angle) {
        return LegacyTweaks.config.naturalCapes ? MathHelper.clamp_float(legacyTweaks$v, -50.0F, 65.0F) : angle;
    }

    @ModifyArg(
            method = "doRenderLayer(Lnet/minecraft/client/entity/AbstractClientPlayer;FFFFFFF)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;rotate(FFFF)V", ordinal = 2),
            index = 0
    )
    private float legacyTweaks$modifyCapeRotation2(float angle) {
        return LegacyTweaks.config.naturalCapes ? MathHelper.clamp_float(-legacyTweaks$v, -50.0F, 65.0F) : angle;
    }
}
