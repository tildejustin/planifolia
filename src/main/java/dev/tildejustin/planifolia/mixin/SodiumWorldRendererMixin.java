package dev.tildejustin.planifolia.mixin;

import me.jellysquid.mods.sodium.client.render.SodiumWorldRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SodiumWorldRenderer.class)
public class SodiumWorldRendererMixin {
    @Shadow
    @Final
    private MinecraftClient client;

    @Inject(method = "drawChunkLayer", at = @At(value = "INVOKE", target = "Lme/jellysquid/mods/sodium/client/render/chunk/RenderSectionManager;renderLayer(Lme/jellysquid/mods/sodium/client/render/chunk/ChunkRenderMatrices;Lme/jellysquid/mods/sodium/client/render/chunk/passes/BlockRenderPass;DDD)V"))
    private void startProfiler(RenderLayer renderLayer, MatrixStack matrixStack, double x, double y, double z, CallbackInfo ci) {
        this.client.getProfiler().push("filterempty");
        this.client.getProfiler().swap(() -> "render_" + renderLayer);
    }

    @Inject(method = "drawChunkLayer", at = @At(value = "INVOKE", target = "Lme/jellysquid/mods/sodium/client/render/chunk/passes/BlockRenderPass;endDrawing()V"))
    private void endProfiler(CallbackInfo ci) {
        this.client.getProfiler().pop();
    }
}
