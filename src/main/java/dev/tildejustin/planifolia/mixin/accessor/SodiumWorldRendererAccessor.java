package dev.tildejustin.planifolia.mixin.accessor;

import me.jellysquid.mods.sodium.client.render.SodiumWorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = SodiumWorldRenderer.class, remap = false)
public interface SodiumWorldRendererAccessor {
    @Accessor(value = "useEntityCulling")
    boolean getUseEntityCulling();
}
