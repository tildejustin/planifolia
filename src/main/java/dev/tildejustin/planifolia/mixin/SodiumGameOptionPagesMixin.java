package dev.tildejustin.planifolia.mixin;

import me.jellysquid.mods.sodium.client.gui.SodiumGameOptionPages;
import me.jellysquid.mods.sodium.client.gui.options.*;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(value = SodiumGameOptionPages.class, remap = false)
public abstract class SodiumGameOptionPagesMixin {
    @ModifyArg(
            method = "lambda$general$3",
            at = @At(value = "INVOKE", target = "Lme/jellysquid/mods/sodium/client/gui/options/control/SliderControl;<init>(Lme/jellysquid/mods/sodium/client/gui/options/Option;IIILme/jellysquid/mods/sodium/client/gui/options/control/ControlValueFormatter;)V"),
            index = 2
    )
    private static int modifyGammaSliderMaximum(int original) {
        return MinecraftClient.getInstance().world == null ? 500 : original;
    }

    @Redirect(
            method = "quality",
            at = @At(value = "INVOKE", target = "Lme/jellysquid/mods/sodium/client/gui/options/OptionGroup$Builder;add(Lme/jellysquid/mods/sodium/client/gui/options/Option;)Lme/jellysquid/mods/sodium/client/gui/options/OptionGroup$Builder;", ordinal = 0),
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=soundCategory.weather"))
    )
    private static OptionGroup.Builder removeWeatherDisablingOption(OptionGroup.Builder instance, Option<?> option) {
        return instance;
    }

    @Redirect(
            method = "quality",
            at = @At(value = "INVOKE", target = "Lme/jellysquid/mods/sodium/client/gui/options/OptionGroup$Builder;add(Lme/jellysquid/mods/sodium/client/gui/options/Option;)Lme/jellysquid/mods/sodium/client/gui/options/OptionGroup$Builder;", ordinal = 0),
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=sodium.options.leaves_quality.name"))
    )
    private static OptionGroup.Builder removeLeaveQualityOption(OptionGroup.Builder instance, Option<?> option) {
        return instance;
    }
}
