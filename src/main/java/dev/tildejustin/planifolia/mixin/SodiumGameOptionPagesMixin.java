package dev.tildejustin.planifolia.mixin;

import net.caffeinemc.mods.sodium.client.gui.SodiumConfigBuilder;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(value = SodiumConfigBuilder.class, remap = false)
public abstract class SodiumGameOptionPagesMixin {
    @ModifyArg(
            method = "buildGeneralPage",
            at = @At(value = "INVOKE", target = "Lnet/caffeinemc/mods/sodium/api/config/structure/IntegerOptionBuilder;setRange(III)Lnet/caffeinemc/mods/sodium/api/config/structure/IntegerOptionBuilder;", ordinal = 0),
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=options.gamma")),
            index = 1
    )
    private static int modifyGammaSliderMaximum(int original) {
        return MinecraftClient.getInstance().world == null ? 500 : original;
    }
}
