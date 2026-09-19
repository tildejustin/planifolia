package dev.tildejustin.planifolia.mixin;

import dev.tildejustin.planifolia.Planifolia;
import net.caffeinemc.mods.sodium.client.gui.SodiumGameOptionPages;
import net.caffeinemc.mods.sodium.client.gui.options.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(value = SodiumGameOptionPages.class, remap = false)
public abstract class SodiumGameOptionPagesMixin {
    @ModifyArg(
            method = "lambda$general$6",
            at = @At(value = "INVOKE", target = "Lnet/caffeinemc/mods/sodium/client/gui/options/control/SliderControl;<init>(Lnet/caffeinemc/mods/sodium/client/gui/options/Option;IIILnet/caffeinemc/mods/sodium/client/gui/options/control/ControlValueFormatter;)V"),
            index = 2
    )
    private static int modifyGammaSliderMaximum(int original) {
        return Planifolia.restrictGamma() ? original : 500;
    }

    @Redirect(
            method = "quality",
            at = @At(value = "INVOKE", target = "Lnet/caffeinemc/mods/sodium/client/gui/options/OptionGroup$Builder;add(Lnet/caffeinemc/mods/sodium/client/gui/options/Option;)Lnet/caffeinemc/mods/sodium/client/gui/options/OptionGroup$Builder;", ordinal = 0),
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=soundCategory.weather"))
    )
    private static OptionGroup.Builder removeWeatherDisablingOption(OptionGroup.Builder instance, Option<?> option) {
        return instance;
    }

    @Redirect(
            method = "quality",
            at = @At(value = "INVOKE", target = "Lnet/caffeinemc/mods/sodium/client/gui/options/OptionGroup$Builder;add(Lnet/caffeinemc/mods/sodium/client/gui/options/Option;)Lnet/caffeinemc/mods/sodium/client/gui/options/OptionGroup$Builder;", ordinal = 0),
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=sodium.options.leaves_quality.name"))
    )
    private static OptionGroup.Builder removeLeaveQualityOption(OptionGroup.Builder instance, Option<?> option) {
        return instance;
    }
}
