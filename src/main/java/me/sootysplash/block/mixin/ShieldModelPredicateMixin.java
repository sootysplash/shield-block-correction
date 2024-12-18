package me.sootysplash.block.mixin;

import net.minecraft.client.item.ModelPredicateProvider;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ModelPredicateProviderRegistry.class)
public class ShieldModelPredicateMixin {
    @Inject(method = "get", at = @At("HEAD"), cancellable = true)
    private static void hookGet(Item item, Identifier id, CallbackInfoReturnable<ModelPredicateProvider> cir) {
        if (Items.SHIELD.equals(item) && new Identifier("blocking").equals(id)) {
            cir.setReturnValue((stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack && entity.isBlocking() ? 1.0f : 0.0f);
        }
    }
}
