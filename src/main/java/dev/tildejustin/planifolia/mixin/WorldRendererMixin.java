package dev.tildejustin.planifolia.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.tildejustin.planifolia.mixin.accessor.SodiumWorldRendererAccessor;
import net.caffeinemc.mods.sodium.client.render.SodiumWorldRenderer;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin {
    @WrapOperation(method = "getEntitiesDebugString", at = @At(value = "INVOKE", target = "Ljava/util/List;size()I"))
    private int hideEntityCount(List<?> instance, Operation<Integer> original) {
        return ((SodiumWorldRendererAccessor) SodiumWorldRenderer.instance()).getUseEntityCulling() ? -1 : original.call(instance);
    }
}
