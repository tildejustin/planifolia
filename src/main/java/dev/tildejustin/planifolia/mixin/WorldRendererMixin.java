package dev.tildejustin.planifolia.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.*;
import dev.tildejustin.planifolia.mixin.accessor.SodiumWorldRendererAccessor;
import me.jellysquid.mods.sodium.client.render.SodiumWorldRenderer;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin {
    @WrapOperation(method = "getEntitiesDebugString", at = @At(value = "FIELD", target = "Lnet/minecraft/client/render/WorldRenderer;regularEntityCount:I"))
    private int hideEntityCount(WorldRenderer instance, Operation<Integer> original) {
        return ((SodiumWorldRendererAccessor) SodiumWorldRenderer.instance()).getUseEntityCulling() ? -1 : original.call(instance);
    }
}
