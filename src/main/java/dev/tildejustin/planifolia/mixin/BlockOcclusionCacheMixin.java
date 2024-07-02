package dev.tildejustin.planifolia.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import me.jellysquid.mods.sodium.client.render.occlusion.BlockOcclusionCache;
import net.minecraft.block.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockOcclusionCache.class)
public class BlockOcclusionCacheMixin {
    @Inject(method = "shouldDrawSide", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isSideInvisible(Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/Direction;)Z"), cancellable = true)
    private void adjStateNullCheck(CallbackInfoReturnable<Boolean> cir, @Local(ordinal = 1) BlockState adjState) {
        if (adjState == null) {
            cir.setReturnValue(false);
        }
    }
}
