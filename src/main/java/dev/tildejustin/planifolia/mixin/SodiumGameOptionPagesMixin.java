package dev.tildejustin.planifolia.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.tildejustin.planifolia.SteppedValidatorGamma;
import net.caffeinemc.mods.sodium.api.config.structure.IntegerOptionBuilder;
import net.caffeinemc.mods.sodium.client.gui.SodiumConfigBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(value = SodiumConfigBuilder.class, remap = false)
public abstract class SodiumGameOptionPagesMixin {
    @ModifyExpressionValue(
            method = "buildGeneralPage",
            at = @At(value = "INVOKE", target = "Lnet/caffeinemc/mods/sodium/client/config/vanilla/VanillaOptions;createIntegerOption(Lnet/minecraft/resources/Identifier;Lnet/minecraft/client/OptionInstance;Lnet/caffeinemc/mods/sodium/client/config/vanilla/IntProjection;)Lnet/caffeinemc/mods/sodium/api/config/structure/IntegerOptionBuilder;", ordinal = 0),
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=sodium:general.gamma"))
    )
    private static IntegerOptionBuilder modifyGammaSliderMaximum(IntegerOptionBuilder original) {
        return original.setValidator(new SteppedValidatorGamma());
    }
}
