package dev.tildejustin.planifolia.mixin;

import me.jellysquid.mods.sodium.client.gui.SodiumGameOptionPages;
import me.jellysquid.mods.sodium.client.gui.options.*;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Pseudo
@Mixin(value = SodiumGameOptionPages.class, remap = false)
public abstract class SodiumGameOptionPagesMixin {
    @ModifyArg(
            method = "lambda$general$6",
            at = @At(value = "INVOKE", target = "Lme/jellysquid/mods/sodium/client/gui/options/control/SliderControl;<init>(Lme/jellysquid/mods/sodium/client/gui/options/Option;IIILme/jellysquid/mods/sodium/client/gui/options/control/ControlValueFormatter;)V"),
            index = 2,
            remap = false
    )
    private static int modifyGammaSliderMaximum(int original) {
        return MinecraftClient.getInstance().world == null ? 500 : original;
    }

    @Redirect(
            method = "quality",
            at = @At(value = "INVOKE", target = "Lme/jellysquid/mods/sodium/client/gui/options/OptionGroup$Builder;add(Lme/jellysquid/mods/sodium/client/gui/options/Option;)Lme/jellysquid/mods/sodium/client/gui/options/OptionGroup$Builder;", ordinal = 0),
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=soundCategory.weather")),
            remap = false
    )
    private static OptionGroup.Builder removeWeatherDisablingOption(OptionGroup.Builder instance, Option<?> option) {
        return instance;
    }

    @Redirect(
            method = "quality",
            at = @At(value = "INVOKE", target = "Lme/jellysquid/mods/sodium/client/gui/options/OptionGroup$Builder;add(Lme/jellysquid/mods/sodium/client/gui/options/Option;)Lme/jellysquid/mods/sodium/client/gui/options/OptionGroup$Builder;", ordinal = 0),
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=sodium.options.leaves_quality.name")),
            remap = false
    )
    private static OptionGroup.Builder removeLeaveQualityOption(OptionGroup.Builder instance, Option<?> option) {
        return instance;
    }

    @Redirect(
            method = "quality",
            at = @At(value = "INVOKE", target = "Lme/jellysquid/mods/sodium/client/gui/options/OptionGroup$Builder;add(Lme/jellysquid/mods/sodium/client/gui/options/Option;)Lme/jellysquid/mods/sodium/client/gui/options/OptionGroup$Builder;", ordinal = 0),
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=sodium.options.vignette.name")), remap = false
    )
    private static OptionGroup.Builder removeVignetteOption(OptionGroup.Builder instance, Option<?> option) {
        return instance;
    }
}
