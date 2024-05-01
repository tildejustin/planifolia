package dev.tildejustin.planifolia.mixin.accessor;

import net.caffeinemc.mods.sodium.client.render.SodiumWorldRenderer;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Accessor;

@Pseudo
@Mixin(SodiumWorldRenderer.class)
public interface SodiumWorldRendererAccessor {
    @Accessor("useEntityCulling")
    boolean getUseEntityCulling();
}
