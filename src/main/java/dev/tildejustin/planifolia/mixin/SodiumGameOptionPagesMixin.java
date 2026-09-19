package dev.tildejustin.planifolia.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.*;
import dev.tildejustin.planifolia.SteppedValidatorGamma;
import net.caffeinemc.mods.sodium.api.config.structure.IntegerOptionBuilder;
import net.caffeinemc.mods.sodium.client.gui.SodiumConfigBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(value = SodiumConfigBuilder.class, remap = false)
public abstract class SodiumGameOptionPagesMixin {
    @WrapOperation(
            method = "buildGeneralPage",
            at = @At(value = "INVOKE", target = "Lnet/caffeinemc/mods/sodium/api/config/structure/IntegerOptionBuilder;setRange(III)Lnet/caffeinemc/mods/sodium/api/config/structure/IntegerOptionBuilder;", ordinal = 0),
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=options.gamma"))
    )
    private static IntegerOptionBuilder modifyGammaSliderMaximum(IntegerOptionBuilder instance, int min, int max, int step, Operation<IntegerOptionBuilder> original) {
        return instance.setValidator(new SteppedValidatorGamma());
    }
}
