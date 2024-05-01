package dev.tildejustin.planifolia.mixin;

import net.caffeinemc.mods.sodium.client.gui.SodiumGameOptions;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GraphicsMode;
import org.spongepowered.asm.mixin.*;

@Mixin(value = SodiumGameOptions.GraphicsQuality.class, remap = false)
public abstract class SodiumGameOptions$GraphicsQualityMixin {
    /**
     * @author tildejustin
     * @reason always defer to overall graphics settings
     */
    @Overwrite(remap = false)
    public boolean isFancy(GraphicsMode graphicsMode) {
        return MinecraftClient.isFancyGraphicsOrBetter();
    }
}
