package me.sootysplash.block.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeldItemRenderer.class)
public class ItemRendererMixin {
    @Redirect(method = "renderFirstPersonItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;isUsingItem()Z", ordinal = 1))
    private boolean doSmth(AbstractClientPlayerEntity instance) {
        if (instance.getActiveItem().isOf(Items.SHIELD) && !instance.isBlocking()) {
            return false;
        }
        return instance.isUsingItem();
    }
    @Inject(method = "resetEquipProgress", at = @At("HEAD"), cancellable = true)
    private void shieldDip(Hand hand, CallbackInfo ci) {
        ClientPlayerEntity p = MinecraftClient.getInstance().player;
        if (p == null) {
            return;
        }
        if (hand == null) {
            return;
        }
        if (!p.getStackInHand(hand).isOf(Items.SHIELD)) {
            return;
        }
        ci.cancel();
    }
}
