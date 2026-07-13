package dev.tildejustin.planifolia.mixin;

import net.caffeinemc.mods.sodium.client.gui.SodiumOptions;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GraphicsMode;
import org.spongepowered.asm.mixin.*;

@Mixin(value = SodiumOptions.class, remap = false)
public abstract class SodiumOptionsMixin {
    @Mixin(value = SodiumOptions.WeatherQuality.class, remap = false)
    private abstract static class WeatherQualityMixin {
        /**
         * @author tildejustin
         * @reason always defer to overall graphics settings
         */
        @Overwrite
        public boolean isFancy(GraphicsMode graphicsMode) {
            return MinecraftClient.isFancyGraphicsOrBetter();
        }
    }

    @Mixin(value = SodiumOptions.LeavesQuality.class, remap = false)
    private abstract static class LeavesQualityMixin {
        /**
         * @author tildejustin
         * @reason always defer to overall graphics settings
         */
        @Overwrite
        public boolean isFancy(GraphicsMode graphicsMode) {
            return MinecraftClient.isFancyGraphicsOrBetter();
        }
    }
}
