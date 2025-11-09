package org.polyfrost.legacytweaks.mixins;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import org.polyfrost.legacytweaks.LegacyTweaks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("deprecation")
@Mixin(RenderItem.class)
public class RenderItemMixin {
    //#if MC==10809
    @Inject(method = "renderItemModelTransform", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/RenderItem;renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/resources/model/IBakedModel;)V"))
    private void legacytweaks$flipItemAgain(ItemStack stack, IBakedModel model, ItemCameraTransforms.TransformType cameraTransformType, CallbackInfo ci) {
        if (LegacyTweaks.isRenderingItemInFirstPerson) {
            LegacyTweaks.isRenderingItemInFirstPerson = false;
        } else {
            return;
        }
        if (LegacyTweaks.config.leftHandInFirstPerson && !(stack.getItem().isFull3D() || stack.getItem() instanceof ItemBow)) {
            LegacyTweaks.isRenderingItemInFirstPerson = false;
            GlStateManager.scale(-1, 1, 1);
            GL11.glFrontFace(GL11.GL_CCW);
        }
    }
    //#endif
}
