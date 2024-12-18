package me.sootysplash.block.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntityRenderer.class)
public abstract class IsUsingMixin {
    @Inject(at = @At("RETURN"), method = "getArmPose", cancellable = true)
    private static void gapt(AbstractClientPlayerEntity p, Hand hand, CallbackInfoReturnable<BipedEntityModel.ArmPose> cir) {
        if (cir.getReturnValue() == BipedEntityModel.ArmPose.BLOCK && !p.isBlocking()) {
            cir.setReturnValue(BipedEntityModel.ArmPose.ITEM);
        }
    }

}
