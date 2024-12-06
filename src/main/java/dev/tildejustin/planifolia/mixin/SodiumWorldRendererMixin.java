package dev.tildejustin.planifolia.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import me.jellysquid.mods.sodium.client.render.SodiumWorldRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = SodiumWorldRenderer.class, remap = false)
public class SodiumWorldRendererMixin {
    @Shadow
    @Final
    private MinecraftClient client;

    @Inject(method = "drawChunkLayer", at = @At(value = "INVOKE", target = "Lme/jellysquid/mods/sodium/client/render/chunk/RenderSectionManager;renderLayer(Lme/jellysquid/mods/sodium/client/render/chunk/ChunkRenderMatrices;Lme/jellysquid/mods/sodium/client/render/chunk/passes/BlockRenderPass;DDD)V"))
    private void startProfiler(CallbackInfo ci, @Local(argsOnly = true) RenderLayer renderLayer) {
        this.client.getProfiler().push("filterempty");
        this.client.getProfiler().swap(() -> "render_" + renderLayer);
    }

    @Inject(method = "drawChunkLayer", at = @At(value = "INVOKE", target = "Lme/jellysquid/mods/sodium/client/render/chunk/passes/BlockRenderPass;endDrawing()V"))
    private void endProfiler(CallbackInfo ci) {
        this.client.getProfiler().pop();
    }
}
