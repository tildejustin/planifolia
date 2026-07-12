package dev.tildejustin.planifolia.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.*;
import dev.tildejustin.planifolia.mixin.accessor.SodiumWorldRendererAccessor;
import net.caffeinemc.mods.sodium.client.render.SodiumWorldRenderer;
import net.minecraft.client.renderer.extract.LevelExtractor;
import net.minecraft.client.renderer.state.level.LevelRenderState;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LevelExtractor.class)
public abstract class LevelExtractorMixin {
    @WrapOperation(method = "entityStatistics", at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/state/level/LevelRenderState;lastEntityRenderStateCount:I", opcode = Opcodes.GETFIELD))
    private int hideEntityCount(LevelRenderState instance, Operation<Integer> original) {
        return ((SodiumWorldRendererAccessor) SodiumWorldRenderer.instance()).getUseEntityCulling() ? -1 : original.call(instance);
    }
}
