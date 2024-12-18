package me.sootysplash.block.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BipedEntityModel.class)
public class BipedEntityModelMixin {
    @Redirect(method = "setAngles(Lnet/minecraft/entity/LivingEntity;FFFFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;isUsingItem()Z", ordinal = 0))
    private boolean useHook(LivingEntity instance) {
        if (instance.getActiveItem().isOf(Items.SHIELD) && !instance.isBlocking()) {
            return false;
        }
        return instance.isUsingItem();
    }
}
