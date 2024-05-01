package dev.tildejustin.planifolia.mixin;

import net.caffeinemc.mods.sodium.client.gui.SodiumGameOptionPages;
import net.caffeinemc.mods.sodium.client.gui.options.*;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Pseudo
@Mixin(value = SodiumGameOptionPages.class, remap = false)
public abstract class SodiumGameOptionPagesMixin {
    @ModifyArg(
            method = "lambda$general$6",
            at = @At(value = "INVOKE", target = "Lnet/caffeinemc/mods/sodium/client/gui/options/control/SliderControl;<init>(Lnet/caffeinemc/mods/sodium/client/gui/options/Option;IIILnet/caffeinemc/mods/sodium/client/gui/options/control/ControlValueFormatter;)V"),
            index = 2,
            remap = false
    )
    private static int modifyGammaSliderMaximum(int original) {
        return MinecraftClient.getInstance().world == null ? 500 : original;
    }

    @Redirect(
            method = "quality",
            at = @At(value = "INVOKE", target = "Lnet/caffeinemc/mods/sodium/client/gui/options/OptionGroup$Builder;add(Lnet/caffeinemc/mods/sodium/client/gui/options/Option;)Lnet/caffeinemc/mods/sodium/client/gui/options/OptionGroup$Builder;", ordinal = 0),
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=soundCategory.weather")),
            remap = false
    )
    private static OptionGroup.Builder removeWeatherDisablingOption(OptionGroup.Builder instance, Option<?> option) {
        return instance;
    }

    @Redirect(
            method = "quality",
            at = @At(value = "INVOKE", target = "Lnet/caffeinemc/mods/sodium/client/gui/options/OptionGroup$Builder;add(Lnet/caffeinemc/mods/sodium/client/gui/options/Option;)Lnet/caffeinemc/mods/sodium/client/gui/options/OptionGroup$Builder;", ordinal = 0),
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=sodium.options.leaves_quality.name")),
            remap = false
    )
    private static OptionGroup.Builder removeLeaveQualityOption(OptionGroup.Builder instance, Option<?> option) {
        return instance;
    }
}
