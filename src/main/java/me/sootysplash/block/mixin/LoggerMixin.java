package me.sootysplash.block.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class LoggerMixin {
    @Inject(method = "<init>", at = @At("TAIL"))
    private void log(RunArgs args, CallbackInfo ci) {
        LoggerFactory.getLogger("shield-block-correction").info("Shield Block Correction | Sootysplash was here!");
    }
}
